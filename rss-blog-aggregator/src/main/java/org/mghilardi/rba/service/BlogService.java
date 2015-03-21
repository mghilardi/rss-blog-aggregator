package org.mghilardi.rba.service;

import java.util.List;

import org.mghilardi.rba.entity.Blog;
import org.mghilardi.rba.entity.Item;
import org.mghilardi.rba.entity.User;
import org.mghilardi.rba.excpetion.RssException;
import org.mghilardi.rba.repository.BlogRepository;
import org.mghilardi.rba.repository.ItemRepository;
import org.mghilardi.rba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RssService rssService;

	@Autowired
	private ItemRepository itemRepository;

	@PreAuthorize("#blog.user.name == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("blog") Blog blog) {
		blogRepository.delete(blog);
	}

	public Blog findOne(int id) {
		return blogRepository.findOne(id);
	}

	@Scheduled(fixedDelay = 3600000)
	public void reloadBlogs() {
		final List<Blog> blogs = blogRepository.findAll();
		for (final Blog blog : blogs) {
			saveItems(blog);
		}
	}

	public void save(Blog blog, String name) {
		final User user = userRepository.findByName(name);
		blog.setUser(user);
		blogRepository.save(blog);
		saveItems(blog);
	}

	public void saveItems(Blog blog) {
		try {
			final List<Item> items = rssService.getItems(blog.getUrl());
			for (final Item item : items) {
				final Item savedItem = itemRepository.findByBlogAndLink(blog,
						item.getLink());
				if (savedItem == null) {
					item.setBlog(blog);
					itemRepository.save(item);
				}
			}
		} catch (final RssException e) {
			e.printStackTrace();
		}
	}
}

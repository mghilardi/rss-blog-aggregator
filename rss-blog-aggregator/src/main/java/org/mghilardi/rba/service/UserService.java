package org.mghilardi.rba.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.mghilardi.rba.entity.Blog;
import org.mghilardi.rba.entity.Item;
import org.mghilardi.rba.entity.Role;
import org.mghilardi.rba.entity.User;
import org.mghilardi.rba.repository.BlogRepository;
import org.mghilardi.rba.repository.ItemRepository;
import org.mghilardi.rba.repository.RoleRepository;
import org.mghilardi.rba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private RoleRepository roleRepository;

	public void delete(int id) {
		userRepository.delete(id);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findOne(int id) {
		return userRepository.findOne(id);
	}

	public User findOne(String username) {
		return userRepository.findByName(username);
	}

	@Transactional
	public User findOneWithBlogs(int id) {
		final User user = findOne(id);
		final List<Blog> blogs = blogRepository.findByUser(user);
		for (final Blog blog : blogs) {
			final List<Item> items = itemRepository.findByBlog(blog, new PageRequest(0, 10, Direction.DESC, "publishedDate"));
			blog.setItems(items);
		}
		user.setBlogs(blogs);
		return user;
	}

	public User findOneWithBlogs(String name) {
		final User user = userRepository.findByName(name);
		return findOneWithBlogs(user.getId());
	}

	public void save(User user) {
		user.setEnabled(true);
		final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		final List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		user.setRoles(roles);

		userRepository.save(user);
	}
}

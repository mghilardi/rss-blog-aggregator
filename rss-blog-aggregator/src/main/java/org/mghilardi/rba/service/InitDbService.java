package org.mghilardi.rba.service;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.mghilardi.rba.entity.Blog;
//import org.mghilardi.rba.entity.Item;
import org.mghilardi.rba.entity.Role;
import org.mghilardi.rba.entity.User;
import org.mghilardi.rba.repository.BlogRepository;
import org.mghilardi.rba.repository.ItemRepository;
import org.mghilardi.rba.repository.RoleRepository;
import org.mghilardi.rba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class InitDbService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private ItemRepository itemRepository;

	@PostConstruct
	public void init() {
		if (roleRepository.findByName("ROLE_ADMIN") == null) {

			final Role roleUser = new Role();
			roleUser.setName("ROLE_USER");
			roleRepository.save(roleUser);

			final Role roleAdmin = new Role();
			roleAdmin.setName("ROLE_ADMIN");
			roleRepository.save(roleAdmin);

			final User userAdmin = new User();
			userAdmin.setEnabled(true);
			userAdmin.setName("admin");
			final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			userAdmin.setPassword(encoder.encode("admin"));
			final List<Role> roles = new ArrayList<Role>();
			roles.add(roleAdmin);
			roles.add(roleUser);
			userAdmin.setRoles(roles);
			userRepository.save(userAdmin);

			final Map<String, String> blogs = new TreeMap<String, String>();
			// blogs.put("F1 Fanatic", "http://www.f1fanatic.co.uk/activity/feed/");
			blogs.put("FlatOut", "http://www.flatout.com.br/feed/");
			blogs.put("Autosport F1", "http://www.autosport.com/rss/f1news.xml");
			blogs.put("Autosport All", "http://www.autosport.com/rss/allnews.xml");
			blogs.put("Autosprint", "http://autosprint.corrieredellosport.it/formula1/feed/");
			blogs.put("Gazzetta", "http://www.gazzetta.it/rss/formula-1.xml");
			blogs.put("Grande Premio", "http://grandepremio.uol.com.br/rss.aspx");
			

			for (final Map.Entry<String, String> entry : blogs.entrySet()) {
				final Blog blog = new Blog();
				blog.setName(entry.getKey());
				blog.setUrl(entry.getValue());
				blog.setUser(userAdmin);
				blogRepository.save(blog);
			}
			/**
			 * FlatOut http://www.flatout.com.br/feed/ JavaVids
			 * http://feeds.feedburner.com/javavids?format=xml
			 */

			// After insert the @Scheduled in the reloadBlogs method in
			// BlogService
			// you do not need to add items here anymore because the items will
			// be retrieved at the start of the application
			/*
			 * final Item item1 = new Item(); item1.setBlog(blogFlatOut);
			 * item1.setTitle("first");
			 * item1.setLink("http://www.flatout.com.br");
			 * item1.setPublishedDate(new Date()); itemRepository.save(item1);
			 *
			 * final Item item2 = new Item(); item2.setBlog(blogFlatOut);
			 * item2.setTitle("second");
			 * item2.setLink("http://www.flatout.com.br");
			 * item2.setPublishedDate(new Date()); itemRepository.save(item2);
			 */
		}
	}

}

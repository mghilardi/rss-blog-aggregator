package org.mghilardi.rba.controller;

import java.security.Principal;

import org.mghilardi.rba.entity.User;
import org.mghilardi.rba.service.ItemService;
import org.mghilardi.rba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private UserService userService;

	@RequestMapping("/index")
	public String index(Model model, Principal principal) {
		if (principal != null) {
			final String name = principal.getName();
			final User user = userService.findOneWithBlogs(name);
			model.addAttribute("items", itemService.getItems(user));
		}
		return "index";
	}
}

package org.mghilardi.rba.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.mghilardi.rba.entity.Blog;
import org.mghilardi.rba.service.BlogService;
import org.mghilardi.rba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private UserService userService;

	@RequestMapping("/account")
	public String account(Model model, Principal principal) {
		final String name = principal.getName();
		model.addAttribute("user", userService.findOneWithBlogs(name));
		return "account";
	}

	@ModelAttribute(value = "blog")
	public Blog constructBlog() {
		return new Blog();
	}

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public String doAddBlog(Model model,
			@Valid @ModelAttribute("blog") Blog blog, BindingResult result,
			Principal principal) {
		if (result.hasErrors()) {
			return account(model, principal);
		}
		final String name = principal.getName();
		blogService.save(blog, name);
		return "redirect:/account.html";
	}

	@RequestMapping("/blog/remove/{id}")
	public String removeBlog(@PathVariable int id) {
		final Blog blog = blogService.findOne(id);
		blogService.delete(blog);
		return "redirect:/account.html";
	}

}

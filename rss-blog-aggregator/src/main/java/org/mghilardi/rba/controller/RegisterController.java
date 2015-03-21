package org.mghilardi.rba.controller;

import javax.validation.Valid;

import org.mghilardi.rba.entity.User;
import org.mghilardi.rba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserService userService;

	/**
	 * http://localhost:8080/register/available.html?username=admin false
	 * http://localhost:8080/register/available.html?username=test true
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping("/available")
	@ResponseBody
	public String available(@RequestParam String username) {
		final Boolean available = userService.findOne(username) == null;
		return available.toString();
	}

	/**
	 * Creates the form
	 *
	 * @return
	 */
	@ModelAttribute(value = "user")
	public User construct() {
		return new User();
	}

	/**
	 * Receives the form from the user and save to the database
	 * See comment on user-register.jsp (shows paramater on the url)
	 * @param user
	 * @param result
	 * @return
	 */
	/**
	@RequestMapping(method = RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") User user,
			BindingResult result) {
		if (result.hasErrors()) {
			return "user-register";
		}
		userService.save(user);
		return "redirect:/register.html?success=true";
	}
	*/

	/**
	 * Receives the form from the user and save to the database
	 * See comment on user-register.jsp (do not show paramater on the url)
	 * @param user
	 * @param result
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") User user,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "user-register";
		}
		userService.save(user);
		redirectAttributes.addFlashAttribute("success", true);
		return "redirect:/register.html";
	}
	
	/**
	 * Shows the register page
	 *
	 * @return
	 */
	@RequestMapping
	public String showRegister() {
		return "user-register";
	}
}

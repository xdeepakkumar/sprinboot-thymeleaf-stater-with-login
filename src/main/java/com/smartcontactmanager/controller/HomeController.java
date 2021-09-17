package com.smartcontactmanager.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smartcontactmanager.entities.User;
import com.smartcontactmanager.helper.Message;
import com.smartcontactmanager.repository.UserRepository;

@Controller
public class HomeController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home - Smart Contact Manager");
		return "home";
	}
	
	@RequestMapping("/login")
	public String signup(Model model) {
		model.addAttribute("title", "login - Smart Contact Manager");
		return "login";
	}
	
	@RequestMapping("/signup")
	public String login(Model model) {
		model.addAttribute("title", "signup - Smart Contact Manager");
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@RequestMapping("/services")
	public String services(Model model) {
		model.addAttribute("title", "Our Services - Smart Contact Manager");
		return "services";
	}
	
	@RequestMapping("/about-us")
	public String about(Model model) {
		model.addAttribute("title", "About Us - Smart Contact Manager");
		return "about";
	}
	
	@RequestMapping("/contact-us")
	public String contact(Model model) {
		model.addAttribute("title", "Contact Us - Smart Contact Manager");
		return "contact";
	}
	
	
	//registration handler
	@PostMapping("/do_register")
	public String signupUser(@Valid @ModelAttribute("user") User user,BindingResult bindingResult, @RequestParam(value = "termsAndConditions", defaultValue = "false") Boolean termsAndConditions, Model model, HttpSession httpSession) {
		
		try {
			if(!termsAndConditions) {
				httpSession.setAttribute("message", new Message("Agree with terms & condition", "alert-danger"));
				model.addAttribute("user", user);
				return "/signup";
			}
			// server side validation 
			if(bindingResult.hasErrors()) {
				System.out.println("Error : "+ bindingResult.toString());
				model.addAttribute("user", user);
				return "/signup";
			}
			user.setEnabled(true);
			user.setRole("USER");
			user.setImageUrl("https://bootdey.com/img/Content/avatar/avatar1.png");
			this.userRepository.save(user);
			model.addAttribute("user", new User());
			httpSession.setAttribute("message", new Message("Successfully registered...", "alert-success"));
			return "signup";
		} catch (Exception e) {
			e.printStackTrace();
			httpSession.setAttribute("message", new Message("something went wrong", "alert-danger"));
		}
		model.addAttribute("user", user);
		return "signup";
	}
	
}

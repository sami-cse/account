package com.hellokoding.account.web;

import com.hellokoding.account.model.Permission;
import com.hellokoding.account.model.User;
import com.hellokoding.account.repository.PermissionRepository;
import com.hellokoding.account.repository.UserRepository;
import com.hellokoding.account.service.SecurityService;
import com.hellokoding.account.service.UserService;
import com.hellokoding.account.validator.UserValidator;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	PermissionRepository permissionRepository;
	
	@Autowired
	UserRepository userRepository;
	
	Integer size = new Integer(3);
	Integer page = new Integer(0);

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model,
			HttpSession session) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		}

		//session.setAttribute("username", userForm.getUsername());
		userService.save(userForm);

		//securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

		return "redirect:/adminhome";
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String doLoginGet() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(@ModelAttribute("loginForm") User loginForm, Model model) {
		securityService.autologin(loginForm.getUsername(), loginForm.getPassword());
		return "redirect:/";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		model.addAttribute("permissions", permissionRepository.getUserByuserName(username));
		return "welcome";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String users(Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		model.addAttribute("permissions", permissionRepository.getUserByuserName(username));
		return "users";
	}
	
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String errorPage() {
		return "404";
	}
	
	@RequestMapping(value = "/efgh", method = RequestMethod.GET)
	public String efgh() {
		return "efgh";
	}
	
	
	/* User Permission---------------------------------------------------- */
	@RequestMapping(value = "/userPermissions", method = RequestMethod.GET)
	public String userPermissionsGet(Model model) {

		Permission permission = new Permission();
		model.addAttribute("permission", permission);
		model.addAttribute("action", "/userPermissions");
		model.addAttribute("userLists", userRepository.findAllByOrderByIdDesc());
		return "userPermissions";
	}

	@RequestMapping(value = "/userPermissions", method = RequestMethod.POST)
	public String userPermissionsPOST(Model model, @ModelAttribute("permission") Permission permission) {

		model.addAttribute("permission", permission);
		model.addAttribute("action", "/userPermissions");
		model.addAttribute("userLists", userRepository.findAll());
		permissionRepository.save(permission);
		return "redirect:/userPermissionLists";
	}

	@RequestMapping(value = "/userPermissionLists", method = RequestMethod.GET)
	public String userPermissionLists(Model model, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size) {

		if (page == null) {
			page = 0;
		}
		if (size == null) {
			size = this.size;
		}

		Pageable pageable = new PageRequest(page, size);
		Page<Permission> permissions = permissionRepository.findAllByOrderByIdAsc(pageable);
		model.addAttribute("permissions", permissions);
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		return "userPermissionLists";
	}


}

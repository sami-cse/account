package com.hellokoding.account.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hellokoding.account.model.User;
import com.hellokoding.account.repository.PermissionRepository;
import com.hellokoding.account.repository.UserRepository;

@Controller
public class HomeController {

	@Autowired
	UserRepository userRepository;

	
	@Autowired
	PermissionRepository permissionRepository;

	Integer size = new Integer(3);
	Integer page = new Integer(0);

	@RequestMapping("/adminhome")
	public String home(Model model, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size) {

		if (page == null) {
			page = 0;
		}
		if (size == null) {
			size = this.size;
		}

		Pageable pageable = new PageRequest(page, size);

		Page<User> users = userRepository.findAllByOrderByIdDesc(pageable);
		model.addAttribute("users", users);
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		return "home";
	}

	

}

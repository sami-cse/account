package com.hellokoding.account.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.hellokoding.account.repository.PermissionRepository;

@ControllerAdvice
public class GlobalController {

	
	@Autowired
	PermissionRepository permissionRepository;
	
	@ModelAttribute("username")
	public void getUserRole(Model model, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		model.addAttribute("username", username);

	}
	
	
	@ModelAttribute("permissions")
	public void permissions(Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		model.addAttribute("permissions", permissionRepository.getUserByuserName(username));
	}

	
}

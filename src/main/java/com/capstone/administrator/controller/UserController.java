package com.capstone.administrator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capstone.administrator.entity.UserDto;
import com.capstone.administrator.service.NotificationService;

@Controller
public class UserController {

	@Autowired
	private NotificationService notificationService;

	@RequestMapping(value = "/users")
	public String index(Model model) {

		List<UserDto> listUser = notificationService.getUserByRole("");
		model.addAttribute("listUser", listUser);
		return "user/index";
	}
}

package com.capstone.administrator.controller;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capstone.administrator.entity.UserDto;
import com.capstone.administrator.service.NotificationService;

@Controller
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@RequestMapping(value = { "/notification" })
	public String index(@RequestParam(name = "title", required = false) String title,
			@RequestParam(name = "body", required = false) String body,
			@RequestParam(name = "imageUrl", required = false) String imageUrl, Model model) {
		model.addAttribute("title", title);
		model.addAttribute("body", body);
		model.addAttribute("imageUrl", imageUrl == null ? "" : imageUrl);
		return "notification/index";
	}

	@RequestMapping(value = { "/notification/next" })
	public String nextStep(@RequestParam(name = "title", required = true) String title,
			@RequestParam(name = "body", required = true) String body,
			@RequestParam(name = "imageUrl", required = false) String imageUrl, Model model) {
		model.addAttribute("title", title);
		model.addAttribute("body", body);
		model.addAttribute("imageUrl", imageUrl);
		List<UserDto> listUser = notificationService.getUserByRole("");
		model.addAttribute("listUser", listUser);
		return "notification/create";
	}

	@RequestMapping(value = { "/notification/create" })
	public String create(@RequestParam(name = "title", required = true) String title,
			@RequestParam(name = "body", required = true) String body,
			@RequestParam(name = "imageUrl", required = false) String imageUrl,
			@RequestParam(name = "targetUser", required = false) String targetUser) {

		notificationService.sendNotification(targetUser, title, body, imageUrl);
		return "redirect:/dashboard";
	}

	@RequestMapping(value = { "/notification/resend" })
	public String resend(@RequestParam(name = "messageId", required = true) String messageId) {
		JSONArray arr = new JSONArray(messageId);
		for (Object x : arr) {
			notificationService.resendNotification(x.toString());
		}
		return "redirect:/dashboard";
	}
}

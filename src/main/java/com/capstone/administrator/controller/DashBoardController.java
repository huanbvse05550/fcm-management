package com.capstone.administrator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capstone.administrator.entity.MessageDto;
import com.capstone.administrator.service.MessageSerivce;

@Controller
public class DashBoardController {

	@Autowired
	private MessageSerivce messageSerivce;

	@RequestMapping(value = { "", "/dashboard" })
	public String index(Model model) {
		List<MessageDto> listMessage = messageSerivce.getMessages();
		model.addAttribute("listMessage", listMessage);
		return "dashboard/index";
	}
}

package com.myframework.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myframework.core.MyController;

public class AddController implements MyController {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		req.setAttribute("num", req.getParameter("num"));
		return "add";
	}

}

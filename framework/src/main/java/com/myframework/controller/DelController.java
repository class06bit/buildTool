package com.myframework.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myframework.core.MyController;

public class DelController implements MyController {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {

		req.setAttribute("num", req.getParameter("num"));
		return "add";
	}

}

package com.vincent.spring;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vincent.spring.dao.UserDAO;
import com.vincent.spring.model.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
    @Autowired
	UserDAO userDao;
	/**
	 * Handles requests for the application home page
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(){
		logger.info("Calling home method in HomeController.java");
		List<User> listUsers = userDao.list();
		ModelAndView model = new ModelAndView("home");
		model.addObject("userList",listUsers);
		return model;
	}

	
}

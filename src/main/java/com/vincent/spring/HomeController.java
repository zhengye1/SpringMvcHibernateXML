package com.vincent.spring;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newUser() {
		ModelAndView model = new ModelAndView("UserForm");
		model.addObject("user", new User());
		return model;      
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editUser(HttpServletRequest request) {
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = userDao.get(userId);
		ModelAndView model = new ModelAndView("UserForm");
		model.addObject("user", user);
		return model;      
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest request) {
		int userId = Integer.parseInt(request.getParameter("id"));
		userDao.delete(userId);
		return new ModelAndView("redirect:/");     
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute User user) {
		userDao.saveOrUpdate(user);
		return new ModelAndView("redirect:/");
	}
}

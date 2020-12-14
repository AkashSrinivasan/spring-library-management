package com.home.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.PreQuery;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.gson.Gson;
import com.home.model.Service;
//import com.home.model.User;
import com.home.model.Student;
import com.home.model.Staff;
import com.home.util.Util;


@Controller
public class Auth {
	
	@RequestMapping("/")
	public String getLoginPage() {
		return "index";
	}
	
	@RequestMapping("/signup")
	public String getSignupPage() {
		return "signup";
	}
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping("/logout")
	public RedirectView logout(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		httpSession.invalidate();
		return new RedirectView("/");
	}
	
	
	@RequestMapping(value = "/signup-user", method=RequestMethod.POST)
	public RedirectView setUser(@RequestParam int id,@RequestParam String name, @RequestParam String password,
			@RequestParam String userType, @RequestParam CommonsMultipartFile myFile,RedirectAttributes object) throws ServletException, IOException {
		
		InputStream imgStream ;
		if(myFile.getSize()==0) {
			File file = new File(servletContext.getRealPath("/default.jpg"));
			imgStream = new FileInputStream(file);
		}else {
			imgStream = myFile.getInputStream();
		}
        Blob imageBlob = new Blob(IOUtils.toByteArray(imgStream));
		if(userType.equals("Student")) {	
			if(Service.addStudent(id,name,password,imageBlob)) {
				object.addFlashAttribute("message", "SignedUp succesfully, Please Login.!");
			}else {
				object.addFlashAttribute("message", "Already have account please login");
			}
		}else {
			if(Service.addStaff(id,name,password,imageBlob)) {
				object.addFlashAttribute("message", "Staff added sucessfully.");
			}else {
				object.addFlashAttribute("message", "Already have account please login");
			}
		}
		return new RedirectView("/");
	}
	
	@PostMapping("/login")
	public RedirectView userLogin(HttpServletRequest request, HttpServletResponse response,RedirectAttributes object) {
		int id = Integer.parseInt(request.getParameter("id"));
		String password = request.getParameter("password");
		String userType = request.getParameter("user");
		Cookie cookie = new Cookie("userType", request.getParameter("user"));
		if(userType.equals("Student")) {
			Student student = Service.getStudent( id);
			
			if(student.getId() != 0 && student.getPassword().equals(password)) {
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("id", id);
				httpSession.setAttribute("name", student.getName());
				httpSession.setAttribute("profilePic", student.getProfilePic());
				httpSession.setAttribute("userType", "Student");
				response.addCookie(cookie);
				return new RedirectView("/home");
			}else {
				object.addFlashAttribute("message", "Invalid id or password.");
				return new RedirectView("/");
			}
		}else if(userType.equals("Staff")) {
			Staff staff =Service.getStaff(id);
			
			if(staff.getId()!=0 && staff.getPassword().equals(password)) {
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("id", id);
				httpSession.setAttribute("name", staff.getName());
				httpSession.setAttribute("profilePic", staff.getProfilePic());
				httpSession.setAttribute("userType", "Student");
				response.addCookie(cookie);
				return new RedirectView("/home");
			}else {
				object.addFlashAttribute("message", "Invalid id or password.");
				return new RedirectView("/");
			}
		}else {
			if(Util.AdminId == id && Util.AdminPassword.equals("Anikki@12345")) {
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("id", request.getParameter("id"));
				response.addCookie(cookie);
				return new RedirectView("/adminHome");
			}else {
				object.addFlashAttribute("message", "Invalid id or password.");
				return new RedirectView("/");
			}	
		}
	}
	
	
}

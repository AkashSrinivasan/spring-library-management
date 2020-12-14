package com.home.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.google.appengine.api.datastore.Blob;
import com.google.gson.Gson;
import com.home.model.Book;
import com.home.model.Service;


@Controller
public class UserController {
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/displayBook")
	public String getBook() {
		return "display_book";
	}
	
	@GetMapping(value = "/getBook", produces = "application/json")
	@ResponseBody
	public String displayBook(@SessionAttribute int id,Model model) {
		List<Book> b = new ArrayList<>();
		ArrayList<Book> books = Service.getBooks(id);
		b = books;
		Gson gson = new Gson();
		String bookAsJson = gson.toJson(b);
		return bookAsJson;
	}
	
	@GetMapping("/barrowBook")
	public String barrowBook() {
		return "barrow_book";
	}
	
	
	@GetMapping(value = "/availableBook", produces = "application/json")
	@ResponseBody
	public String barrowBook(@SessionAttribute int id,Model model) {
		List<Book> books = Service.getBooks(0);
		Gson gson = new Gson();
		String bookAsJson = gson.toJson(books);
		return bookAsJson;
	}
	
	
	@PostMapping("/getBook")
	public RedirectView getBook(@RequestParam long bookid,@SessionAttribute int id,Model model,RedirectAttributes object) {
		if(Service.addUserIdInBook(bookid, id)) {
//			model.addAttribute("message", "Invalid book id, please enter it again.!");
			object.addFlashAttribute("message", "Barrowed Sucessfully.!");
			return new RedirectView("/barrowBook");
		}else {
			object.addFlashAttribute("message", "Invalid book id, please enter it again.!");
			return new RedirectView("/barrowBook");
		}
	}
	
	@GetMapping("/returnBook")
	public String returnBook() {
		return "return_book";
	}
	
	@PostMapping("/giveBook")
	public RedirectView giveBook(@RequestParam long bookId,@SessionAttribute int id,Model model,RedirectAttributes object) {		
		if(Service.updateUserIdInBook(bookId)) {
			object.addFlashAttribute("message", "Book returnrd sucessfully.!");
			return new RedirectView("/returnBook");
		}else {
			object.addFlashAttribute("message", "Invalid book id, please enter it again.!");
			return new RedirectView("/returnBook");
		}
	}
	
	@GetMapping("/uploadImage")
	public String uploadImage() {
		return "upload_image";
	}
	
	
	@PostMapping("/updateImage")
	public RedirectView addImage(@RequestParam CommonsMultipartFile myFile,@SessionAttribute int id,
			@SessionAttribute String userType,HttpServletRequest request,Model model,RedirectAttributes object) throws ServletException, IOException{
		
		InputStream imgStream = myFile.getInputStream();
		Blob imageBlob = new Blob(IOUtils.toByteArray(imgStream));
		if(Service.uploadImage(id,imageBlob,userType)) {
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("profilePic", imageBlob);
			return new RedirectView("/home");
		}else {
			object.addFlashAttribute("message", "Can't upload the photo now please try again.!");
			return new RedirectView("/uploadImage");
		}
	}
}

package com.home.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.google.gson.Gson;
import com.home.model.Book;
import com.home.model.Service;
import com.home.model.Staff;
import com.home.model.Student;


@Controller
public class AdminController {

	
	@RequestMapping("/adminHome")
	public String adminHome() {
		return "admin_home";
	}
	
	
	@RequestMapping("/displayAllUser")
	public String display_user() {
		return "display_user";
	}
	
	@RequestMapping("/displayAllBook")
	public String displayBooks() {
		return "display_all_book";
	}
	
	@GetMapping("/addBook")
	public String getAddBookPage() {
		return "add_a_book";
	}
	
	@RequestMapping("/addNewBook")
	public RedirectView addNewBook(@RequestParam int bookId,@RequestParam String bookName,Model model,RedirectAttributes object) {
		Book book = new Book(bookId, bookName, 0, null, null);
		Service.addNewBook(book);
		object.addFlashAttribute("message","Book added sucessfully.!");
		return new RedirectView("/addBook");
	}
	
	@GetMapping(value = "/getAllStudent", produces = "application/json")
	@ResponseBody
	public String getAllStudent(Model model) {
		List<Student> allStudent = Service.getAllStudent();
		Gson gson = new Gson();
		String studentJson = gson.toJson(allStudent);
		return studentJson;
		
	}
	
	@GetMapping(value = "/getAllStaff", produces = "application/json")
	@ResponseBody
	public String getAllStaff(Model model) {
		Gson gson = new Gson();
		List<Staff> allStaff = Service.getAllStaff();
		String staffJson = gson.toJson(allStaff);
		return staffJson;
	}
	
	@GetMapping(value = "/getAllBook", produces = "application/json")
	@ResponseBody
	public String getAllBook(Model model) {
		List<Book> allBooks = Service.getAllBooks();
		Gson gson = new Gson();
		String booksJson = gson.toJson(allBooks);
		return booksJson;
	}
}

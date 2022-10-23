package com.brummer.heroku.herokupostgresql;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.brummer.heroku.herokupostgresql.test.Test;
import com.brummer.heroku.herokupostgresql.test.TestRepository;

@Controller
public class HomeController {

	private final static String BASE_PATH = "/homeController";
	private final TestRepository testRepository;
	
	private String extra = 
			"This application uses SpringBoot, Spring Data Spring Web(MVC and REST), Thymeleave MVC, Lombok, and PostgresSQL database (http://127.0.0.1:53784/browser/)";
	
	public HomeController(TestRepository testRepository) {
		this.testRepository = testRepository;
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("extra", extra);
		return "index";
	}
	
	@RequestMapping( BASE_PATH + "/listTests")
	public String listTests(Model model) {
		model.addAttribute("extra", extra);
		model.addAttribute("tests", testRepository.findAll());
		return "testsList";
	}
	
	@PostMapping( BASE_PATH + "/addTest")
	public String addTest(Test test) {
		testRepository.save(test);
		return "redirect:"+BASE_PATH+"/listTests";
	}
	
	@GetMapping( BASE_PATH + "/deleteTest")
	public String deleteTest(@RequestParam String id) {
		if(id != null) {
			try{
				long idLong = Long.parseLong(id);
				testRepository.deleteById(idLong);
			} catch (NumberFormatException e) {
				System.out.print(e);
				e.printStackTrace();
				// do nothing
			}
		}
		return "redirect:"+BASE_PATH+"/listTests";
	}
	
	@PostMapping( BASE_PATH + "/updateTest")
	public String updateTest(Test test) {
		testRepository.save(test);
		return "redirect:"+BASE_PATH+"/listTests";
	}
	
}

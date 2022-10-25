package com.brummer.heroku.herokupostgresql;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brummer.heroku.herokupostgresql.test.Test;
import com.brummer.heroku.herokupostgresql.test.TestRepository;

@RestController
@RequestMapping("/homeRestController")
public class HomeRestController {

	private final TestRepository testRepository;
	
	public HomeRestController(TestRepository testRepository) {
		this.testRepository = testRepository;
	}
	
	@GetMapping("/listAllTests")
	public Iterable<Test> listTests(){
		return testRepository.findAll();
	}
	
	@GetMapping("/deleteAllTests")
	public void deleteAll() {
		testRepository.deleteAll();
	}
	
}

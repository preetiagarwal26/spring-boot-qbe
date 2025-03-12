package com.qbe.controller;

import java.time.LocalDate;
import java.util.List;

import com.qbe.persistence.User;
import com.qbe.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/user")
@Log4j2
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/list")
	public ResponseEntity<List<User>> retrieveAll() {
		log.info("Request received to get all users");
		return ResponseEntity.ok(userService.getAll());
	}

	@GetMapping("/firstname")
	public ResponseEntity<List<User>> getAllUsersFirstNameEndsWith(@RequestParam(name = "endsWith") String endsWith) {
		log.info("Get all users whose first name ends with {}", endsWith);
		return ResponseEntity.ok(userService.findByFirstNameEndsWith(endsWith));
	}
	
	
	@GetMapping("/lastname")
	public ResponseEntity<List<User>> getAllUsersLastNameContains(@RequestParam(name = "contains") String contains) {
		log.info("Get all users whose last name contains {}", contains);
		return ResponseEntity.ok(userService.findByLastNameContains(contains));
	}
	
	
	
	@GetMapping("/disabled")
	public ResponseEntity<List<User>> getAllCustomersLastNameEndsWith(@RequestParam(name = "emailContains") String emailContains,
																	  @RequestParam(name="fromDate") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)LocalDate fromDate,
																	  @RequestParam(name="toDate") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)LocalDate toDate) {
		log.info("Get all disabled users whose email contains {}, within time period {} - {}", emailContains, fromDate, toDate);
		return ResponseEntity.ok(userService.findDisabledInTimePeriodAndEmailContains(emailContains, fromDate, toDate));
	}
	
	@GetMapping("/{firstName}")
	public ResponseEntity<List<User>> getUserByName(@PathVariable String firstName) {
		log.info("Get all users by firstname {}", firstName);
		return ResponseEntity.ok(userService.findByFirstName(firstName));
	}
}

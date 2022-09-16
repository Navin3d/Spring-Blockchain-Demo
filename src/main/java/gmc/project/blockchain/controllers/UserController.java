package gmc.project.blockchain.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gmc.project.blockchain.models.UserModel;
import gmc.project.blockchain.services.UserService;

@RestController
@RequestMapping(path = "/user")
public class UserController {
	
	private UserService userService;
	
	@PostMapping
	private ResponseEntity<String> signUp(@RequestBody UserModel userData) {
		userService.save(userData);
		return ResponseEntity.status(HttpStatus.CREATED).body("User Registered Successfully...");
	}
	
	@GetMapping(path = "/{userId}")
	private ResponseEntity<UserModel> getAUser(@PathVariable String userId) {
		UserModel returnValue = userService.findAUser(userId);
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}
	
}

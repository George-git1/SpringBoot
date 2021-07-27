package com.example.demo.controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;


@RestController
@RequestMapping("/user")
public class UserController {

    private List<User> users;

    public UserController() {
        this.users = new ArrayList<>();
        this.users.add(new User(1011, "George", 22 ,"GeorgesEmail@email.com"));

    }

    @GetMapping
    public List<User> getUsers() {
        return this.users;
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") int id) {
    	for (User user : users) {
    		if (user.getId() == id) {
    			return user;
    		}
    	}
		return null;
    }
    @GetMapping("/email")
    public List<User> getUsersByEmail(@RequestParam("email") String email) {
    return users.stream()
    		.filter(u -> u.getEmail().equals(email))
    		.collect(Collectors.toList());
}
    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") int id, @RequestBody User user) {
    	for (User u : users) {
    		if (u.getId() == id) {
    			u.setAge(user.getAge());
    			u.setEmail(user.getEmail());
    			u.setName(user.getName());
    			
    			return u;
    		}
    	}
    	return null;
    }

    @PostMapping
    public User createUJser(@Valid @RequestBody User user) {
    	this.users.add(user);
    	return user;
    }
    
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int id) {
    	for (int i = 0; i < users.size(); i ++) {
    		User u = users.get(i);
    		
    		if (u.getId() == id) {
    			this.users.remove(u);
    			break;
    		}
    	}
    }
 }
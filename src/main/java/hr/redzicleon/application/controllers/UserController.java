package hr.redzicleon.application.controllers;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hr.redzicleon.application.controllers.reponses.ResponseObject;
import hr.redzicleon.application.model.User;
import hr.redzicleon.application.services.UserDTO;
import hr.redzicleon.application.services.UserService;

@RestController()
@RequestMapping("user")
public class UserController {
	
	UserService userService;
	
	UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.GET, value= {"", "/"})
	@ResponseBody
	public List<UserDTO> getAllUsers() {   
	    return this.userService.getAllUsersWithCompanyName();
	}

	@ResponseBody
	@RequestMapping(value = "/{id}")
	public User getUser(@PathVariable("id") int companyId) {
		return this.userService.getUser(companyId);
	}
	
	@ResponseBody
	@RequestMapping(value = {"/", ""}, method = RequestMethod.POST )
	public ResponseObject registerUser(@RequestBody User user) {
		this.userService.addNewUser(user);
		return ResponseObject.success("");
	 }
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Void> handleContentNotAllowedException() {
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
	
	
	@ExceptionHandler(org.springframework.dao.DuplicateKeyException.class)
    public ResponseObject handleUserExists() {
        return ResponseObject.error("Username already exists");
    }
}

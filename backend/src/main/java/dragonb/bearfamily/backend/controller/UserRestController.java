package dragonb.bearfamily.backend.controller;
 
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dragonb.bearfamily.backend.model.Result;
import dragonb.bearfamily.backend.model.User;
import dragonb.bearfamily.backend.repository.UserRepository;
import dragonb.bearfamily.backend.service.UserService;

// @CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value="restapi/user")
public class UserRestController {
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(UserRestController.class);
 
	@Autowired
	UserRepository repository;
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public Result retrieveUserList() {
		Result result = userService.retrieveUserList();
		return result;
	}

	@GetMapping("/{identity}")
	public Result retrieveUser(@PathVariable String identity) {
		Result result = userService.retrieveUser(identity);
		return result;
	}
	
	@PostMapping
	public Result createUser(@ModelAttribute User user) {
		Result result = userService.createUser(user);
		return result;
	}
	
	@PutMapping
	public Result updateUser(@ModelAttribute User user) {
		Result result = userService.updateUser(user);
		return result;
	}
	
	@DeleteMapping
	public Result deleteUser(@RequestParam String identity) {
		Result result = userService.deleteUser(identity);
		return result;
	}
	
}
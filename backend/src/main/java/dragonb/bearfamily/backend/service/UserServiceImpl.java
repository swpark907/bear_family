package dragonb.bearfamily.backend.service;
 
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dragonb.bearfamily.backend.model.Result;
import dragonb.bearfamily.backend.model.User;
import dragonb.bearfamily.backend.repository.UserRepository;
 
@Service
public class UserServiceImpl implements UserService{

	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(UserServiceImpl.class);
 
	@Autowired
	UserRepository repository;
	
	public Result updateUser(User user) {

		Optional<User> search = repository.findById(user.getIdentity());
		Result result = new Result();
		if(search.isPresent()) {
			user = repository.save(user);
			result.setPayload(user);
		}else {
		}
		return result;
	}
	
	public Result deleteUser(String identity) {
		Result result = new Result();
		boolean isPresent = repository.findById(identity).isPresent();
		if(!isPresent) {
		}else {
			repository.deleteById(identity);
		}
		return result;
	}
 
	@Override
	public Result createUser(User user) {
		user = repository.save(user);
		Result result = new Result();
		result.setPayload(user);
		return result;
	}
 
	@Override
	public Result retrieveUserList() {
		List<User> list = repository.findAllByOrderByIdentityDesc();
		Result result = new Result();
		result.setPayload(list);
		return result;
	}
 
	@Override
	public Result retrieveUser(String identity) {
		Optional<User> optionalUser = repository.findById(identity);
		Result result = new Result();
		if(optionalUser.isPresent()) {
			result.setPayload(optionalUser.get());
		}else {
		}
		return result;
	}
}
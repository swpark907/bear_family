package dragonb.bearfamily.backend.service;

import dragonb.bearfamily.backend.model.Result;
import dragonb.bearfamily.backend.model.User;

public interface UserService {
	public Result createUser(User user);
	public Result retrieveUserList();
	public Result retrieveUser(String id);
	public Result updateUser(User user);
	public Result deleteUser(String id);
}

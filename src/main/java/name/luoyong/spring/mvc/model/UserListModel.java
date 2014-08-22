package name.luoyong.spring.mvc.model;

import java.util.List;

import name.luoyong.spring.mvc.entity.User;

public class UserListModel {
	
	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}


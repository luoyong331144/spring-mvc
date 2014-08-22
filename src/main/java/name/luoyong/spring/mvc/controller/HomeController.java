package name.luoyong.spring.mvc.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import name.luoyong.spring.mvc.entity.User;
import name.luoyong.spring.mvc.model.NameListModel;
import name.luoyong.spring.mvc.model.UserListModel;
import name.luoyong.spring.mvc.model.UserModel;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String showHomePage() {
		return "home";
	}
	
	@RequestMapping("/basicValue")
	public String basicValue(HttpServletRequest request, String username, User user) {
		printParameterNames(request);
		System.out.println("username : "+username);
		
		System.out.println("user.getUsername() : "+user.getUsername());
		System.out.println("user.getPassword() : "+user.getPassword());
		System.out.println("user.getCnName() : "+user.getCnName());
		
		System.out.println("***************************************");
		return "home";
	}
	
	@RequestMapping("/objectValue")
	public String objectValue(HttpServletRequest request, UserModel userModel) {
		printParameterNames(request);
		
		System.out.println("userModel.getUser().getUsername() : "+userModel.getUser().getUsername());
		System.out.println("userModel.getUser().getPassword() : "+userModel.getUser().getPassword());
		System.out.println("userModel.getUser().getCnName() : "+  userModel.getUser().getCnName());
		
		System.out.println("***************************************");
		return "home";
	}
	
	@RequestMapping("/listString")
	public String listString(HttpServletRequest request, NameListModel nameListModel) {
		printParameterNames(request);
		
		List<String> names = nameListModel.getNames();
		for(String name : names) {
			System.out.println(name);
		}
		
		System.out.println("***************************************");
		return "home";
	}
	
	@RequestMapping("/listObject")
	public String listObject(HttpServletRequest request, UserListModel userListModel) {
		printParameterNames(request);
		
		List<User> users = userListModel.getUsers();
		System.out.println("users.size() : "+users.size());
		
		for(User user : users) {
			System.out.println("user.getUsername() : "+user.getUsername());
			System.out.println("user.getPassword() : "+user.getPassword());
			System.out.println("user.getCnName()   : "+user.getCnName());
		}
		
		System.out.println("***************************************");
		return "home";
	}
	
	
	@RequestMapping("/basicValue.json")
	public String basicValueJson(HttpServletRequest request, String username, User user) {
		return basicValue(request, username, user);
	}
	
	@RequestMapping("/objectValue.json")
	public String objectValueJson(HttpServletRequest request, UserModel userModel) {
		return objectValue(request, userModel);
	}
	
	@RequestMapping("/listStringJson")
	public String listStringJson(HttpServletRequest request, NameListModel nameListModel) {
		return listString(request, nameListModel);
	}
	
	@RequestMapping("/listObjectJson")
	public String listObjectJson(HttpServletRequest request, UserListModel userListModel) {
		return listObject(request, userListModel);
	}
	
	

	@RequestMapping("/userInfo.json")
	@ResponseBody
	public List<User> getUserInfo(HttpServletRequest request) {
		printParameterNames(request);
		
		String usersStr = request.getParameter("users");
		System.out.println("users : "+usersStr);
		List<User> userList = jsonToList(usersStr, User.class);
		
	
		for(User user : userList) {
			System.out.println(user.getUsername());
			System.out.println(user.getPassword());
			System.out.println(user.getCnName());
		}
		return null;
	}
	
	private List<Map> jsonToList(String jsonStr) {
		ObjectMapper mapper = new ObjectMapper(); 
		List<Map> list = null;
		try {
			list = mapper.readValue(jsonStr, List.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private <T> List<T> jsonToList(String jsonStr, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper(); 
		JavaType javaType = mapper.getTypeFactory().constructParametricType(LinkedList.class, clazz);
		List<T> list = null;
		try {
			list = mapper.readValue(jsonStr, javaType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private Map jsonToMap(String jsonStr) {
		ObjectMapper mapper = new ObjectMapper(); 
		Map map = null;
		try {
			map = mapper.readValue(jsonStr, Map.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	private void printAttributeNames(HttpServletRequest request) {
		System.out.println("AttributNames : ");
		Enumeration<String> es = request.getAttributeNames();
		while(es.hasMoreElements()) {
			System.out.println(es.nextElement());
		}
		System.out.println("------------------------------------");
	}
	
	private void printParameterNames(HttpServletRequest request) {
		System.out.println("Parameter names :");
		Enumeration<String> es = request.getParameterNames();
		while(es.hasMoreElements()) {
			String name = es.nextElement();
			String value = request.getParameter(name);
			System.out.println(name+" : "+value);
		}
		System.out.println("------------------------------------");
	}


}

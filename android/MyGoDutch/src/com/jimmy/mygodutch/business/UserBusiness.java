package com.jimmy.mygodutch.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jimmy.mygodutch.model.User;
import com.jimmy.mygodutch.util.HttpUtil;



public class UserBusiness {
public UserBusiness()
{}
	// 定义发送请求的方法
		public JSONObject findUserByNameAndPwd(String username, String password) throws Exception
		{
			// 使用Map封装请求参数
			Map<String, String> map = new HashMap<String, String>();
			map.put("user", username);
			map.put("pass", password);
			// 定义发送请求的URL http://localhost:8080/GoDutchServer/android/user/getAllUser
			String url = HttpUtil.BASE_URL + "/user/login.do";
			// 发送请求
			String ss=HttpUtil.getRequest(url+"?name="+username+"&pwd="+password);
			return new JSONObject(ss);
		}
		
		public String findUserByName(String username) throws Exception
		{
			
			// 定义发送请求的URL http://localhost:8080/GoDutchServer/android/user/getAllUser
			String url = HttpUtil.BASE_URL + "/user/findUserByName.do";
			// 发送请求
			String ss=HttpUtil.getRequest(url+"?name="+username);
			JSONObject jb= new JSONObject(ss);
			
			return jb.getString("userId");
		}
		public boolean findUserById(User u) throws Exception
		{
			
			//map.put("pass", password);
			// 定义发送请求的URL http://localhost:8080/GoDutchServer/android/user/getAllUser
			String url = HttpUtil.BASE_URL + "/user/findUserById.do";
			// 发送请求
			String ss=HttpUtil.getRequest(url+"?id="+u.getId());
			JSONObject jb= new JSONObject(ss);
			
			return jb.getString("userId")=="false"?false:true;
		}
		public boolean insertUser(User u) throws Exception
		{
			// 使用Map封装请求参数
			Map<String, String> map = new HashMap<String, String>();
			//map.put("n", u.getName());
			//map.put("pass", password);
			// 定义发送请求的URL http://localhost:8080/GoDutchServer/android/user/getAllUser
			String url = HttpUtil.BASE_URL + "/user/insertUser.do";
			// 发送请求
			String ss=HttpUtil.getRequest(url+"?name="+u.getName()+"&pwd="+u.getPwd());
			JSONObject jb= new JSONObject(ss);
			
			return jb.getString("userId")=="true"?true:false;
		}
		
		public boolean updateUser(User u) throws Exception
		{
			// 使用Map封装请求参数
			Map<String, String> map = new HashMap<String, String>();
			//map.put("n", u.getName());
			//map.put("pass", password);
			// 定义发送请求的URL http://localhost:8080/GoDutchServer/android/user/getAllUser
			String url = HttpUtil.BASE_URL + "/user/updateUser.do";
			// 发送请求
			String ss=HttpUtil.getRequest(url+"?id="+u.getId()+"&name="+u.getName()+"&pwd="+u.getPwd());
			JSONObject jb= new JSONObject(ss);
			
			return jb.getString("userId")=="true"?true:false;
		}
		
		public boolean deleteUser(User u) throws Exception
		{
			// 使用Map封装请求参数
			Map<String, String> map = new HashMap<String, String>();
			//map.put("n", u.getName());
			//map.put("pass", password);
			// 定义发送请求的URL http://localhost:8080/GoDutchServer/android/user/getAllUser
			String url = HttpUtil.BASE_URL + "/user/deleteUser.do";
			// 发送请求
			String ss=HttpUtil.getRequest(url+"?name="+u.getName());
			JSONObject jb= new JSONObject(ss);
			
			return jb.getString("userId")=="true"?true:false;
		}
		public List<User> findAllUser() throws Exception
		{
			// 使用Map封装请求参数
			Map<String, String> map = new HashMap<String, String>();
List<User> list=new ArrayList<User>();
			// 定义发送请求的URL http://localhost:8080/GoDutchServer/android/user/getAllUser
			String url = HttpUtil.BASE_URL + "/user/getAllUser.do";
			// 发送请求
			String ss=HttpUtil.getRequest(url);
			JSONArray jr= new JSONArray(ss);
			
			for(int i=0;i<jr.length();i++)
			{
				JSONObject jb=jr.getJSONObject(i);
				String name=jb.getString("name");
				String pwd=jb.getString("pwd");
				User u =new User(name,pwd);
				list.add(u);
			}
			return list;
		}
}

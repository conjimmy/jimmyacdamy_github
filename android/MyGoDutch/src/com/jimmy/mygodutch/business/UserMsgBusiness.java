package com.jimmy.mygodutch.business;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;

import com.jimmy.mygodutch.model.UserMsg;
import com.jimmy.mygodutch.util.HttpUtil;



public class UserMsgBusiness {
public UserMsgBusiness()
{}

public  List<UserMsg> getMsgsFromUserId(long userId){
	
	//map.put("pass", password);
	// 定义发送请求的URL http://localhost:8080/GoDutchServer/android/user/getAllUser
	String url = HttpUtil.BASE_URL + "/usermsg/getMsgsFromUserId.do";
	// 发送请求
	String ss;
	 List<UserMsg> list=new ArrayList<UserMsg>();
	try {
		ss = HttpUtil.getRequest(url+"?id="+userId);
		JSONArray jr= new JSONArray(ss);
		
		for(int i=0;i<jr.length();i++)
		{
			JSONObject jb=jr.getJSONObject(i);
			long fromuid=jb.getLong("fromuid");
			String fromuname=jb.getString("fromuname");
			String pwd=jb.getString("pwd");
			UserMsg u =new UserMsg();
			u.setFromuid(fromuid);
			u.setFromuname(fromuname);
			u.setContent(jb.getString("content"));
			list.add(u);
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return list;
	
	
}
public  List<UserMsg> getMsgsFromUsername(String username){
	//map.put("pass", password);
	// 定义发送请求的URL http://localhost:8080/GoDutchServer/android/user/getAllUser
	String url = HttpUtil.BASE_URL + "/usermsg/getMsgsFromUsername.do";
	// 发送请求
	String ss;
	 List<UserMsg> list=new ArrayList<UserMsg>();
	try {
		ss = HttpUtil.getRequest(url+"?username="+username);
		JSONArray jr= new JSONArray(ss);
		
		for(int i=0;i<jr.length();i++)
		{
			
			JSONObject jb=jr.getJSONObject(i);
			long fromuid=jb.getLong("fromuid");
			String fromuname=jb.getString("fromuname");
			
			UserMsg u =new UserMsg();
			u.setFromuid(fromuid);
			u.setFromuname(fromuname);
			u.setContent(jb.getString("content"));
			list.add(u);
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return list;
}
public UserMsg getMsgsFromMsgId(long  msgId){
	
	String url = HttpUtil.BASE_URL + "/usermsg/getMsgsFromUsername.do";
	// 发送请求
	String ss;

		
		JSONObject jb;
		UserMsg u=null;
		try {
			ss = HttpUtil.getRequest(url+"?msgId="+msgId);
			jb = new JSONObject(ss);
			long fromuid=jb.getLong("fromuid");
			String fromuname=jb.getString("fromuname");
			u =new UserMsg();
			
			u.setFromuid(fromuid);
			u.setFromuname(fromuname);
			u.setContent(jb.getString("content"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return u;

}
public Bitmap getImage(String s){
	Bitmap b=null;
	String url = HttpUtil.BASE_URL+"/img/getImg?pic=" + s;
	try {
		b=HttpUtil.getImg(url);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return b;
}
public List<UserMsg> getUserMsgs(){
	//map.put("pass", password);
		// 定义发送请求的URL http://localhost:8080/GoDutchServer/android/user/getAllUser
		String url = HttpUtil.BASE_URL + "/usermsg/getUserMsgs.do";
		// 发送请求
		String ss;
		 List<UserMsg> list=new ArrayList<UserMsg>();
		try {
			ss = HttpUtil.getRequest(url);
			JSONArray jr= new JSONArray(ss);
			
			for(int i=0;i<jr.length();i++)
			{
				
				JSONObject jb=jr.getJSONObject(i);
				UserMsg u =new UserMsg();
				long fromuid;
				if(jb.getString("fromuid")!=null)
				{ fromuid=jb.getLong("fromuid");
				u.setFromuid(fromuid);
				}
				String fromuname;
				if(jb.getString("fromuname")!=null)
				{ fromuname=jb.getString("fromuname");
				u.setFromuname(fromuname);}
				
				
				u.setId(jb.getLong("id"));
				
				if(jb.getString("pic")!=null)
				u.setPic(jb.getString("pic"));
				
				if(jb.getString("content")!=null)
				u.setContent(jb.getString("content"));
				
				list.add(u);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
}
// public abstract List<UserMsg> getMsgsFromUserTo(int userId);

public  boolean saveUserMsg(UserMsg usermsg)
{
	String url = HttpUtil.BASE_URL + "/usermsg/saveUserMsg.do";
	// 发送请求
	String ss;

		
		JSONObject jb;
		UserMsg u=null;
		boolean b=false;
		try {
			ss = HttpUtil.getRequest(url+"?fromuname="+usermsg.getFromuname()+"&fromuid="+usermsg.getFromuid()+"&content="+usermsg.getContent());
			jb = new JSONObject(ss);
	b=jb.getString("userId")=="false"?false:true;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return b;
	}
public  boolean saveUserMsg(UserMsg usermsg,InputStream in,String file)
{
	String url = HttpUtil.BASE_URL + "/img/saveImg.do";
	// 发送请求
	String ss;

		
		JSONObject jb;
		UserMsg u=null;
		boolean b=false;
		try {
			ss = HttpUtil.saveUserMsg(url,in,usermsg,file);
			jb = new JSONObject(ss);
	b=jb.getString("userId")=="false"?false:true;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return b;
	}
//public  boolean updateUserMsg(UserMsg usermsg)
//{
//	}
//
//public  boolean removeUserMsg(UserMsg usermsg){
//	
//}
//
//public  boolean removeUserMsg(long msgId){
//	
//}
//
//	
//		public boolean insertUser(User u) throws Exception
//		{
//			// 使用Map封装请求参数
//			Map<String, String> map = new HashMap<String, String>();
//			//map.put("n", u.getName());
//			//map.put("pass", password);
//			// 定义发送请求的URL http://localhost:8080/GoDutchServer/android/user/getAllUser
//			String url = HttpUtil.BASE_URL + "/user/insertUser.do";
//			// 发送请求
//			String ss=HttpUtil.getRequest(url+"?name="+u.getName()+"&pwd="+u.getPwd());
//			JSONObject jb= new JSONObject(ss);
//			
//			return jb.getString("userId")=="true"?true:false;
//		}
//		
//		public boolean updateUser(User u) throws Exception
//		{
//			// 使用Map封装请求参数
//			Map<String, String> map = new HashMap<String, String>();
//			//map.put("n", u.getName());
//			//map.put("pass", password);
//			// 定义发送请求的URL http://localhost:8080/GoDutchServer/android/user/getAllUser
//			String url = HttpUtil.BASE_URL + "/user/updateUser.do";
//			// 发送请求
//			String ss=HttpUtil.getRequest(url+"?id="+u.getId()+"&name="+u.getName()+"&pwd="+u.getPwd());
//			JSONObject jb= new JSONObject(ss);
//			
//			return jb.getString("userId")=="true"?true:false;
//		}
//		
//		public boolean deleteUser(User u) throws Exception
//		{
//			// 使用Map封装请求参数
//			Map<String, String> map = new HashMap<String, String>();
//			//map.put("n", u.getName());
//			//map.put("pass", password);
//			// 定义发送请求的URL http://localhost:8080/GoDutchServer/android/user/getAllUser
//			String url = HttpUtil.BASE_URL + "/user/deleteUser.do";
//			// 发送请求
//			String ss=HttpUtil.getRequest(url+"?name="+u.getName());
//			JSONObject jb= new JSONObject(ss);
//			
//			return jb.getString("userId")=="true"?true:false;
//		}
//		public List<User> findAllUser() throws Exception
//		{
//			// 使用Map封装请求参数
//			Map<String, String> map = new HashMap<String, String>();
//List<User> list=new ArrayList<User>();
//			// 定义发送请求的URL http://localhost:8080/GoDutchServer/android/user/getAllUser
//			String url = HttpUtil.BASE_URL + "/user/getAllUser.do";
//			// 发送请求
//			String ss=HttpUtil.getRequest(url);
//			JSONArray jr= new JSONArray(ss);
//			
//			for(int i=0;i<jr.length();i++)
//			{
//				JSONObject jb=jr.getJSONObject(i);
//				String name=jb.getString("name");
//				String pwd=jb.getString("pwd");
//				User u =new User(name,pwd);
//				list.add(u);
//			}
//			return list;
//		}


}

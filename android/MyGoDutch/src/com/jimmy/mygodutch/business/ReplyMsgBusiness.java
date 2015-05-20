package com.jimmy.mygodutch.business;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jimmy.mygodutch.model.ReplyMsg;
import com.jimmy.mygodutch.util.HttpUtil;

public class ReplyMsgBusiness {
	public ReplyMsgBusiness() {
	}

	public List<ReplyMsg> getMsgsFromUserId(long userId) {

		String url = HttpUtil.BASE_URL + "/replymsg/getMsgsFromUserId.do";
		// 发送请求
		String ss;
		List<ReplyMsg> list = new ArrayList<ReplyMsg>();
		try {
			ss = HttpUtil.getRequest(url + "?id=" + userId);
			JSONArray jr = new JSONArray(ss);

			for (int i = 0; i < jr.length(); i++) {
				JSONObject jb = jr.getJSONObject(i);
				long fromuid = jb.getLong("fromuid");
				String fromuname = jb.getString("fromuname");
				String pwd = jb.getString("pwd");
				ReplyMsg u = new ReplyMsg();
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

	public List<ReplyMsg> getMsgsFromUsername(String username) {
		// map.put("pass", password);
		// 定义发送请求的URL
		// http://localhost:8080/GoDutchServer/android/user/getAllUser
		String url = HttpUtil.BASE_URL + "/replymsg/getMsgsFromUsername.do";
		// 发送请求
		String ss;
		List<ReplyMsg> list = new ArrayList<ReplyMsg>();
		try {
			ss = HttpUtil.getRequest(url + "?username=" + username);
			JSONArray jr = new JSONArray(ss);

			for (int i = 0; i < jr.length(); i++) {

				JSONObject jb = jr.getJSONObject(i);
				long fromuid = jb.getLong("fromuid");
				String fromuname = jb.getString("fromuname");

				ReplyMsg u = new ReplyMsg();
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

	public ReplyMsg getMsgsFromMsgId(long msgId) {

		String url = HttpUtil.BASE_URL + "/replymsg/getMsgsFromUsername.do";
		// 发送请求
		String ss;

		JSONObject jb;
		ReplyMsg u = null;
		try {
			ss = HttpUtil.getRequest(url + "?msgId=" + msgId);
			jb = new JSONObject(ss);
			long fromuid = jb.getLong("fromuid");
			String fromuname = jb.getString("fromuname");
			u = new ReplyMsg();

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

	public List<ReplyMsg> getReplyMsgs() {
		// map.put("pass", password);
		// 定义发送请求的URL
		// http://localhost:8080/GoDutchServer/android/user/getAllUser
		String url = HttpUtil.BASE_URL + "/replymsg/getReplyMsgs.do";
		// 发送请求
		String ss;
		List<ReplyMsg> list = new ArrayList<ReplyMsg>();
		try {
			ss = HttpUtil.getRequest(url);
			JSONArray jr = new JSONArray(ss);

			for (int i = 0; i < jr.length(); i++) {

				JSONObject jb = jr.getJSONObject(i);
				long fromuid = jb.getLong("fromuid");
				String fromuname = jb.getString("fromuname");

				ReplyMsg u = new ReplyMsg();
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

	// public abstract List<ReplyMsg> getMsgsFromUserTo(int userId);

	public boolean saveReplyMsg(ReplyMsg ReplyMsg) {
		String url = HttpUtil.BASE_URL + "/replymsg/saveReplyMsg.do";
		// 发送请求
		String ss;

		JSONObject jb;
		ReplyMsg u = null;
		boolean b = false;
		try {
			ss = HttpUtil.getRequest(url + "?fromuname="
					+ ReplyMsg.getFromuname() + "&fromuid="
					+ ReplyMsg.getFromuid() + "&content="
					+ ReplyMsg.getContent());
			jb = new JSONObject(ss);
			b = jb.getString("userId") == "false" ? false : true;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return b;
	}

	// public boolean updateReplyMsg(ReplyMsg ReplyMsg)
	// {
	// }
	//
	// public boolean removeReplyMsg(ReplyMsg ReplyMsg){
	//
	// }
	//
	// public boolean removeReplyMsg(long msgId){
	//
	// }
	//
	//
	// public boolean insertUser(User u) throws Exception
	// {
	// // 使用Map封装请求参数
	// Map<String, String> map = new HashMap<String, String>();
	// //map.put("n", u.getName());
	// //map.put("pass", password);
	// // 定义发送请求的URL http://localhost:8080/GoDutchServer/android/user/getAllUser
	// String url = HttpUtil.BASE_URL + "/user/insertUser.do";
	// // 发送请求
	// String
	// ss=HttpUtil.getRequest(url+"?name="+u.getName()+"&pwd="+u.getPwd());
	// JSONObject jb= new JSONObject(ss);
	//
	// return jb.getString("userId")=="true"?true:false;
	// }
	//
	// public boolean updateUser(User u) throws Exception
	// {
	// // 使用Map封装请求参数
	// Map<String, String> map = new HashMap<String, String>();
	// //map.put("n", u.getName());
	// //map.put("pass", password);
	// // 定义发送请求的URL http://localhost:8080/GoDutchServer/android/user/getAllUser
	// String url = HttpUtil.BASE_URL + "/user/updateUser.do";
	// // 发送请求
	// String
	// ss=HttpUtil.getRequest(url+"?id="+u.getId()+"&name="+u.getName()+"&pwd="+u.getPwd());
	// JSONObject jb= new JSONObject(ss);
	//
	// return jb.getString("userId")=="true"?true:false;
	// }
	//
	// public boolean deleteUser(User u) throws Exception
	// {
	// // 使用Map封装请求参数
	// Map<String, String> map = new HashMap<String, String>();
	// //map.put("n", u.getName());
	// //map.put("pass", password);
	// // 定义发送请求的URL http://localhost:8080/GoDutchServer/android/user/getAllUser
	// String url = HttpUtil.BASE_URL + "/user/deleteUser.do";
	// // 发送请求
	// String ss=HttpUtil.getRequest(url+"?name="+u.getName());
	// JSONObject jb= new JSONObject(ss);
	//
	// return jb.getString("userId")=="true"?true:false;
	// }
	// public List<User> findAllUser() throws Exception
	// {
	// // 使用Map封装请求参数
	// Map<String, String> map = new HashMap<String, String>();
	// List<User> list=new ArrayList<User>();
	// // 定义发送请求的URL http://localhost:8080/GoDutchServer/android/user/getAllUser
	// String url = HttpUtil.BASE_URL + "/user/getAllUser.do";
	// // 发送请求
	// String ss=HttpUtil.getRequest(url);
	// JSONArray jr= new JSONArray(ss);
	//
	// for(int i=0;i<jr.length();i++)
	// {
	// JSONObject jb=jr.getJSONObject(i);
	// String name=jb.getString("name");
	// String pwd=jb.getString("pwd");
	// User u =new User(name,pwd);
	// list.add(u);
	// }
	// return list;
	// }

	public List<ReplyMsg> getReplyMsgsFromMsgId(Long id) {

		String url = HttpUtil.BASE_URL
				+ "/replymsg/getReplyMsgsFromMsgId.do";
		// 发送请求
		String ss;
		List<ReplyMsg> list = new ArrayList<ReplyMsg>();
		try {
			ss = HttpUtil.getRequest(url+"?msgid="+id);
			JSONArray jr = new JSONArray(ss);

			for (int i = 0; i < jr.length(); i++) {

				
				JSONObject jb = jr.getJSONObject(i);
				long fromuid = jb.getLong("fromuid");

				String fromuname = jb.getString("fromuname");

				ReplyMsg u = new ReplyMsg();
				u.setFromuid(fromuid);
				if (fromuname != null)
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

	public int getReplyCountByUserMgsId(Long id) {
		String url = HttpUtil.BASE_URL
				+ "/replymsg/getReplyCountByUserMgsId.do=";
		// 发送请求
		String ss;
		int ret = 0;
		try {
			ss = HttpUtil.getRequest(url + "?msgid=" + id);
			JSONObject jb = new JSONObject(ss);
			ret = jb.getInt("count");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
}

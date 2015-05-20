package com.jimmy.mygodutch.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.jimmy.mygodutch.R;
import com.jimmy.mygodutch.model.User;
import com.jimmy.mygodutch.util.DialogUtil;
import com.jimmy.mygodutch.util.HttpUtil;
/**
 * Description:
 * <br/>site: <a href="http://www.crazyit.org">crazyit.org</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class Login extends Activity
{
	// 定义界面中两个文本框
	EditText etName, etPass;
	// 定义界面中两个按钮
	Button bnLogin, bnCancel;
	User user=new User();
	//boolean result=false;
	String id;
	Runnable networkTask = new Runnable() {  
		  
	    @Override  
	    public void run() {  
	        // TODO  
	        // 在这里进行 http request.网络请求相关操作  
	    	boolean result=loginPro();
	        Message msg = new Message();  
	        Bundle data = new Bundle();  
	        if(result)
	        data.putString("result", "1");  
	        else
	        	  data.putString("result", "0");  
	        msg.setData(data);  
	        handler.sendMessage(msg);  
	    }  
	};  
	Handler handler = new Handler() {  
	    @Override  
	    public void handleMessage(Message msg) {  
	        super.handleMessage(msg);  
	        Bundle data = msg.getData();  
	        String result = data.getString("result");  
	        
	        if (Integer.parseInt(result)==1)
			{
				// 启动Main Activity
				Intent intent = new Intent(Login.this, MainActivity.class);
				intent.putExtra("id",id);   
				startActivity(intent);
				// 结束该Activity
				finish();
			}
			else
			{
				DialogUtil.showDialog(Login.this
					, "用户名称或者密码错误，请重新输入！", false);
			}
	        Log.i("mylog", "请求结果为-->" + result);  
	        // TODO  
	        // UI界面的更新等相关操作  
	    }  
	};  
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		// 获取界面中两个编辑框
		etName = (EditText) findViewById(R.id.userEditText);
		etPass = (EditText) findViewById(R.id.pwdEditText);
		// 获取界面中的两个按钮
		bnLogin = (Button) findViewById(R.id.bnLogin);
		bnCancel = (Button) findViewById(R.id.bnCancel);
		// 为bnCancal按钮的单击事件绑定事件监听器
		bnCancel.setOnClickListener(new FinishListener(this));
		bnLogin.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// 执行输入校验
				if (validate())
				{
					
					new Thread(networkTask).start();
					
					
				}
			}
		});
	}

	private boolean loginPro()
	{
		// 获取用户输入的用户名、密码
		String username = etName.getText().toString();
		String pwd = etPass.getText().toString();
		JSONObject jsonObj;
		try
		{
			jsonObj = query(username, pwd);
			 id=jsonObj.getString("userId");
			// 如果userId 大于0
			if (Integer.parseInt(id) > 0)
			{
			
				return true;
			}
		}
		catch (Exception e)
		{
			//System.out(e.printStackTrace());
		//	DialogUtil.showDialog(this, "服务器响应异常，请稍后再试！", false);
			e.printStackTrace();
		}

		return false;
	}

	// 对用户输入的用户名、密码进行校验
	private boolean validate()
	{
		String username = etName.getText().toString().trim();
		if (username.equals(""))
		{
			DialogUtil.showDialog(this, "用户账户是必填项！", false);
			return false;
		}
		String pwd = etPass.getText().toString().trim();
		if (pwd.equals(""))
		{
			DialogUtil.showDialog(this, "用户口令是必填项！", false);
			return false;
		}
		return true;
	}

	// 定义发送请求的方法
	private JSONObject query(String username, String password) throws Exception
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
}
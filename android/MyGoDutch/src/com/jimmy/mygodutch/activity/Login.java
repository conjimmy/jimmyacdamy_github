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
	// ��������������ı���
	EditText etName, etPass;
	// ���������������ť
	Button bnLogin, bnCancel;
	User user=new User();
	//boolean result=false;
	String id;
	Runnable networkTask = new Runnable() {  
		  
	    @Override  
	    public void run() {  
	        // TODO  
	        // ��������� http request.����������ز���  
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
				// ����Main Activity
				Intent intent = new Intent(Login.this, MainActivity.class);
				intent.putExtra("id",id);   
				startActivity(intent);
				// ������Activity
				finish();
			}
			else
			{
				DialogUtil.showDialog(Login.this
					, "�û����ƻ�������������������룡", false);
			}
	        Log.i("mylog", "������Ϊ-->" + result);  
	        // TODO  
	        // UI����ĸ��µ���ز���  
	    }  
	};  
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		// ��ȡ�����������༭��
		etName = (EditText) findViewById(R.id.userEditText);
		etPass = (EditText) findViewById(R.id.pwdEditText);
		// ��ȡ�����е�������ť
		bnLogin = (Button) findViewById(R.id.bnLogin);
		bnCancel = (Button) findViewById(R.id.bnCancel);
		// ΪbnCancal��ť�ĵ����¼����¼�������
		bnCancel.setOnClickListener(new FinishListener(this));
		bnLogin.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// ִ������У��
				if (validate())
				{
					
					new Thread(networkTask).start();
					
					
				}
			}
		});
	}

	private boolean loginPro()
	{
		// ��ȡ�û�������û���������
		String username = etName.getText().toString();
		String pwd = etPass.getText().toString();
		JSONObject jsonObj;
		try
		{
			jsonObj = query(username, pwd);
			 id=jsonObj.getString("userId");
			// ���userId ����0
			if (Integer.parseInt(id) > 0)
			{
			
				return true;
			}
		}
		catch (Exception e)
		{
			//System.out(e.printStackTrace());
		//	DialogUtil.showDialog(this, "��������Ӧ�쳣�����Ժ����ԣ�", false);
			e.printStackTrace();
		}

		return false;
	}

	// ���û�������û������������У��
	private boolean validate()
	{
		String username = etName.getText().toString().trim();
		if (username.equals(""))
		{
			DialogUtil.showDialog(this, "�û��˻��Ǳ����", false);
			return false;
		}
		String pwd = etPass.getText().toString().trim();
		if (pwd.equals(""))
		{
			DialogUtil.showDialog(this, "�û������Ǳ����", false);
			return false;
		}
		return true;
	}

	// ���巢������ķ���
	private JSONObject query(String username, String password) throws Exception
	{
		// ʹ��Map��װ�������
		Map<String, String> map = new HashMap<String, String>();
		map.put("user", username);
		map.put("pass", password);
		// ���巢�������URL http://localhost:8080/GoDutchServer/android/user/getAllUser
		String url = HttpUtil.BASE_URL + "/user/login.do";
		// ��������
		String ss=HttpUtil.getRequest(url+"?name="+username+"&pwd="+password);
		return new JSONObject(ss);
	}
}
package com.jimmy.mygodutch.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.jimmy.mygodutch.R;
import com.jimmy.mygodutch.adapter.AdapterTopics;
import com.jimmy.mygodutch.business.UserMsgBusiness;
import com.jimmy.mygodutch.model.UserMsg;
import com.jimmy.mygodutch.util.DialogUtil;

public class RecentTopics extends ActivityFrame {
	private ExpandableListView elvTopicsList;
	private UserMsg mSelectModelTopics;
	private UserMsgBusiness mBusinessTopics;
	private AdapterTopics mAdapterTopics;
	private List<UserMsg> list = new ArrayList<UserMsg>();
	private Button bnPost;
	String id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();   
		 id = intent.getStringExtra("id"); 
		appendCenterBody(R.layout.recent_topics);
		InitVariable();
		InitView();
		InitListeners();
		BindData();
	//	CreateSlideMenu(R.array.SlideMenuTopics);
		
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
        .detectDiskReads().detectDiskWrites().detectNetwork()  
        .penaltyLog().build()); 
		
	}
	
//	private void SetTitle() {
//		int _Count = mBusinessTopics.GetNotHideCount();
//		String _Titel = getString(R.string.ActivityTitleTopics, new Object[]{_Count});
//		SetTopBarTitle(_Titel);
//	}

	protected void InitView() {
		bnPost=(Button)findViewById(R.id.bnPost);
		//imv_camera=(ImageView)findViewById(R.id.imv_camera);

		elvTopicsList = (ExpandableListView) findViewById(R.id.ExpandableListViewTopics);
	}

	protected void InitListeners() {
		registerForContextMenu(elvTopicsList);
		bnPost.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent=new Intent(RecentTopics.this,CameraActivity.class);
				Bundle data = new Bundle();  
			
				data.putString("id",id);  //uid
				intent.putExtras(data);  
				startActivityForResult(intent,100);
				
			}
		});
	}


	protected void InitVariable() {
		mBusinessTopics = new UserMsgBusiness();
	}
	ProgressDialog d;
	public void BindData()
	{
		mAdapterTopics = new AdapterTopics(this,list);
		elvTopicsList.setAdapter(mAdapterTopics);
		d = new ProgressDialog(RecentTopics.this);
		d.show();
		new Thread(networkTask).start();

	}

	Runnable networkTask = new Runnable() {

		@Override
		public void run() {
			// TODO
			// 在这里进行 http request.网络请求相关操作
			boolean result = false;
			try {
				list = mBusinessTopics.getUserMsgs();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Message msg = new Message();
			Bundle data = new Bundle();
			if (list != null && list.size() > 0)
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

			if (Integer.parseInt(result) == 1) {
				updateListViewData();
			} else {
				DialogUtil.showDialog(RecentTopics.this, "no user list", false);
			}
			Log.i("mylog", "请求结果为-->" + result);
			// TODO
			// UI界面的更新等相关操作
		}
	};
	public void updateListViewData() {
		mAdapterTopics.setM_List(list);
		mAdapterTopics.notifyDataSetChanged();
		d.dismiss();

	}
}

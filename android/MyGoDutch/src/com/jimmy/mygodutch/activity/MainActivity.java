package com.jimmy.mygodutch.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.jimmy.mygodutch.R;
import com.jimmy.mygodutch.adapter.MyGridViewAdapter;

public class MainActivity extends ActivityFrame {

	private MyGridViewAdapter myGridAdap;
	private GridView gv;
	String id = "";   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();   
		//获取数据   
		 id = intent.getStringExtra("id");   
		appendCenterBody(R.layout.centerbody);
		InitVar();
		InitView();
		InitListener();
		BindData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}
	public void InitVar()
	{
		myGridAdap=new MyGridViewAdapter(this);
	}
	public void InitView()
	{
		gv=(GridView)this.findViewById(R.id.gvGrid);
	}
	public void InitListener()
	{
		gv.setOnItemClickListener(new onAppGridItemClickListener());
	}
	public void BindData()
	{
		gv.setAdapter(myGridAdap);
	}
	
	private class onAppGridItemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView p_Adapter, View p_View, int p_Position,long arg3) {
			String _MenuName = (String)p_Adapter.getAdapter().getItem(p_Position);
			if(_MenuName.equals(getString(R.string.appGridTextUserManage)))
			{
				openActivity(ActivityUser.class,id);
				return;
			}
			
			if(_MenuName.equals(getString(R.string.appGridTextTopicsManage)))
			{
				openActivity(RecentTopics.class,id);
				return;
			}
			
		}
		
	}

}

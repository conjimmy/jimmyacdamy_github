package com.jimmy.mygodutch.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import com.jimmy.mygodutch.R;
import com.jimmy.mygodutch.activity.base.ActivityBase;

public class ActivityFrame extends ActivityBase {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	protected void appendCenterBody(int resID) {
		 LinearLayout layout=(LinearLayout)this.findViewById(R.id.lyCenterBody);
		
		 LayoutInflater inflater=LayoutInflater.from(this);
		 View view=inflater.inflate(resID,null);
		 LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT);
		
		 layout.addView(view,lp);
	}
	protected void CreateMenu(Menu p_Menu)
	{
		int _GroupID = 0;
		int _Order = 0;
		int[] p_ItemID = {1,2};
		
		for(int i=0;i<p_ItemID.length;i++)
		{
			switch(p_ItemID[i])
			{
			case 1:
				p_Menu.add(_GroupID, p_ItemID[i], _Order, R.string.MenuTextEdit);
				break;
			case 2:
				p_Menu.add(_GroupID, p_ItemID[i], _Order, R.string.MenuTextDelete);
				break;
			default:
				break;
			}
		}
	}
}

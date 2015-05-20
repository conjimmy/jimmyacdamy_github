package com.jimmy.mygodutch.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jimmy.mygodutch.R;
import com.jimmy.mygodutch.adapter.base.AdapterBase;
import com.jimmy.mygodutch.business.UserBusiness;
import com.jimmy.mygodutch.model.User;


public class UserAdapter extends AdapterBase {


	private class Holder
	{
		ImageView ivUserIcon;
		TextView tvUserName;
	}
	public UserAdapter(Context pContext,List<User> list) {
		super(pContext, null);
		SetList(list);
//		UserBusiness userBusiness = new UserBusiness();
//		try {
//			List _List =userBusiness.findAllUser();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//SetList(_List);
	}
	
	public UserAdapter(Context pContext) {
		super(pContext, null);
//		UserBusiness userBusiness = new UserBusiness();
//		try {
//			List _List =userBusiness.findAllUser();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	SetList(_List);
	}


	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder _Holder;
		
		if (convertView == null) {
			convertView = GetLayoutInflater().inflate(R.layout.user_list_item, null);
			_Holder = new Holder();
			_Holder.ivUserIcon = (ImageView) convertView.findViewById(R.id.ivUserIcon);
			_Holder.tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
			convertView.setTag(_Holder);
		}
		else {
			_Holder = (Holder) convertView.getTag();
		}
		
		User _Info = (User) GetList().get(position);
		
		_Holder.ivUserIcon.setImageResource(R.drawable.user_big_icon);
		_Holder.tvUserName.setText(_Info.getName());
		
		return convertView;
	}

}

package com.jimmy.mygodutch.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jimmy.mygodutch.R;

public class MyGridViewAdapter extends BaseAdapter {
private Context ct;

private Integer[] imv={
		R.drawable.grid_payout,
		R.drawable.grid_bill,
		R.drawable.grid_report,
		R.drawable.grid_account_book,
		R.drawable.grid_category,
		R.drawable.grid_user,		
};
private class Hold{
	public ImageView imvIcon;
	public TextView  tvName;
	
};
private String[] imvTxt=new String[6];
	public MyGridViewAdapter(Context ct){
		this.ct=ct;
	
		imvTxt[0] = ct.getString(R.string.appGridTextPayoutAdd);
		imvTxt[1] = ct.getString(R.string.appGridTextPayoutManage);
		imvTxt[2] = ct.getString(R.string.appGridTextStatisticsManage);
		imvTxt[3] = ct.getString(R.string.appGridTextAccountBookManage);
		imvTxt[4] = ct.getString(R.string.appGridTextTopicsManage);
		imvTxt[5] = ct.getString(R.string.appGridTextUserManage);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imvTxt.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return imvTxt[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		Hold hold;
		if(convertView==null){
			hold=new Hold();
			LayoutInflater inflater=LayoutInflater.from(ct);
			 convertView=inflater.inflate(R.layout.centerbody_item,null);
			 hold.imvIcon=(ImageView)convertView.findViewById(R.id.imvIcon);
			 hold.tvName=(TextView)convertView.findViewById(R.id.tvName);
			 convertView.setTag(hold);
		}else{
			hold=(Hold)convertView.getTag();
		}
		hold.imvIcon.setImageResource(imv[position]);
		hold.tvName.setText(imvTxt[position]);
		return convertView;
	}

}

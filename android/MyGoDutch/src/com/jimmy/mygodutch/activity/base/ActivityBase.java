package com.jimmy.mygodutch.activity.base;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;

import com.jimmy.mygodutch.R;

public class ActivityBase extends Activity {
	
	protected static final int SHOW_TIME = 1;
protected void ShowMsg(String ss){
	
}
protected void openActivity(Class<?> pclass,String uid){
	Intent intent=new Intent(this,pclass);
	intent.putExtra("id",uid);   
	startActivity(intent);
	
}
protected LayoutInflater GetLayouInflater() {
	LayoutInflater _LayoutInflater = LayoutInflater.from(this);
	return _LayoutInflater;
}
protected AlertDialog ShowAlertDialog(int p_TitelResID,String p_Message,DialogInterface.OnClickListener p_ClickListener)
{
	String _Title = getResources().getString(p_TitelResID);
	return ShowAlertDialog(_Title, p_Message, p_ClickListener);
}
protected AlertDialog ShowAlertDialog(String p_Title,String p_Message,DialogInterface.OnClickListener p_ClickListener)
{		
	return new AlertDialog.Builder(this)
	.setTitle(p_Title)
	.setMessage(p_Message).setPositiveButton(R.string.ButtonTextYes, p_ClickListener).setNegativeButton(R.string.ButtonTextNo, null)
	.show();
}

}

/**
 * 
 */
package com.jimmy.mygodutch.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class DialogUtil
{
	// ����һ����ʾ��Ϣ�ĶԻ���
	public static void showDialog(final Context ctx
		, String msg , boolean closeSelf)
	{
		// ����һ��AlertDialog.Builder����
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx)
			.setMessage(msg).setCancelable(false);
		if(closeSelf)
		{
			builder.setPositiveButton("ȷ��", new OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					// ������ǰActivity
					((Activity)ctx).finish();
				}
			});		
		}
		else
		{
			builder.setPositiveButton("ȷ��", null);
		}
		builder.create().show();
	}	
	// ����һ����ʾָ������ĶԻ���
	public static void showDialog(Context ctx , View view)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx)
			.setView(view).setCancelable(false)
			.setPositiveButton("ȷ��", null);
		builder.create()
			.show();
	}
}

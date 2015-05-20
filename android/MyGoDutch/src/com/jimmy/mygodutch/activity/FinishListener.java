/**
 * 
 */
package com.jimmy.mygodutch.activity;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

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
// ����һ��������ǰActivity��
public class FinishListener implements OnClickListener
{
	private Activity activity;
	public FinishListener(Activity activity)
	{
		this.activity = activity;
	}
	@Override
	public void onClick(View source)
	{
		// ������ǰActivity
		activity.finish();
	}
}

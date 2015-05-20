package com.xia.ui.component;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.jimmy.mygodutch.R;
import com.xia.datetime.wheelview.NumericWheelAdapter;
import com.xia.datetime.wheelview.OnWheelChangedListener;
import com.xia.datetime.wheelview.WheelView;

/**
 * @auther:summer 鏃堕棿锛�2012-7-19 涓嬪崍2:59:56
 */
public class MyDateTimePickerDialog extends AlertDialog implements
		OnClickListener{
	private static int START_YEAR = 2000,END_YEAR=2100;
	private final OnDateTimeSetListener mCallBack;
	private final Calendar mCalendar;
	private int curr_year, curr_month, curr_day, curr_hour, curr_minute;
	// 娣诲姞澶у皬鏈堟湀浠藉苟灏嗗叾杞崲涓簂ist,鏂逛究涔嬪悗鐨勫垽鏂�
	String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
	String[] months_little = { "4", "6", "9", "11" };
	final WheelView wv_year, wv_month, wv_day, wv_hours, wv_mins;
	final List<String> list_big, list_little;

	public MyDateTimePickerDialog(Context context,
			OnDateTimeSetListener callBack) {
		this(context, START_YEAR,END_YEAR,callBack);
	}

	public MyDateTimePickerDialog(Context context, final int START_YEAR,
			 final int END_YEAR,OnDateTimeSetListener callBack) {
		super(context);
		this.START_YEAR = START_YEAR;
		
		mCalendar = Calendar.getInstance();
		int year = mCalendar.get(Calendar.YEAR);
		int month = mCalendar.get(Calendar.MONTH);
		int day = mCalendar.get(Calendar.DATE);
		int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
		int minute = mCalendar.get(Calendar.MINUTE);
		this.END_YEAR = END_YEAR;
		mCallBack = callBack;

		list_big = Arrays.asList(months_big);
		list_little = Arrays.asList(months_little);
	     setButton(context.getText(R.string.ok),this);
	     setButton2(context.getText(R.string.cancle), (OnClickListener) null);
		setIcon(R.drawable.ic_launcher);
		setTitle("set time");
		// 鎵惧埌dialog鐨勫竷灞�枃浠�
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.time_layout, null);
      
		int textSize = 0;
		textSize = adjustFontSize(getWindow().getWindowManager()); 
		// 骞�
		wv_year = (WheelView) view.findViewById(R.id.year);
		wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 璁剧疆"骞�鐨勬樉绀烘暟鎹�
		wv_year.setCyclic(true);//
		wv_year.setLabel("YYYY");// 添加文字
		wv_year.setCurrentItem(year - START_YEAR);// 鍒濆鍖栨椂鏄剧ず鐨勬暟鎹�

		// 鏈�
		wv_month = (WheelView) view.findViewById(R.id.month);
		wv_month.setAdapter(new NumericWheelAdapter(1, 12));
		wv_month.setCyclic(true);
		wv_month.setLabel("MM");
		wv_month.setCurrentItem(month);

		// 鏃�
		wv_day = (WheelView) view.findViewById(R.id.day);
		wv_day.setCyclic(true);
		// 鍒ゆ柇澶у皬鏈堝強鏄惁闂板勾,鐢ㄦ潵纭畾"鏃�鐨勬暟鎹�
		if (list_big.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 31));
		} else if (list_little.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 30));
		} else {
			// 闂板勾
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
				wv_day.setAdapter(new NumericWheelAdapter(1, 29));
			else
				wv_day.setAdapter(new NumericWheelAdapter(1, 28));
		}
		wv_day.setLabel("DD");
		wv_day.setCurrentItem(day - 1);

		// 鏃�
		wv_hours = (WheelView) view.findViewById(R.id.hour);
		wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
		wv_hours.setCyclic(true);
		wv_hours.setCurrentItem(hour);

		// 鍒�
		wv_mins = (WheelView) view.findViewById(R.id.mins);
		wv_mins.setAdapter(new NumericWheelAdapter(0, 59, "%02d"));
		wv_mins.setCyclic(true);
		wv_mins.setCurrentItem(minute);
		// 娣诲姞"骞�鐩戝惉
		OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int year_num = newValue + START_YEAR;
				// 鍒ゆ柇澶у皬鏈堝強鏄惁闂板勾,鐢ㄦ潵纭畾"鏃�鐨勬暟鎹�
				if (list_big
						.contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
				} else if (list_little.contains(String.valueOf(wv_month
						.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
				} else {
					if ((year_num % 4 == 0 && year_num % 100 != 0)
							|| year_num % 400 == 0)
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
					else
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
				}
			}
		};
		// 娣诲姞"鏈�鐩戝惉
		OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int month_num = newValue + 1;
				// 鍒ゆ柇澶у皬鏈堝強鏄惁闂板勾,鐢ㄦ潵纭畾"鏃�鐨勬暟鎹�
				if (list_big.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
				} else if (list_little.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
				} else {
					if (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year
							.getCurrentItem() + START_YEAR) % 100 != 0)
							|| (wv_year.getCurrentItem() + START_YEAR) % 400 == 0)
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
					else
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
				}
			}
		};
		wv_year.addChangingListener(wheelListener_year);
		wv_month.addChangingListener(wheelListener_month);
		wv_day.TEXT_SIZE = textSize;
		wv_hours.TEXT_SIZE = textSize;
		wv_mins.TEXT_SIZE = textSize;
		wv_month.TEXT_SIZE = textSize;
		wv_year.TEXT_SIZE = textSize;
		setView(view);
	}
	public void onClick(DialogInterface dialog, int which) {

		curr_year = wv_year.getCurrentItem() + START_YEAR;
		curr_month = wv_month.getCurrentItem() + 1;
		curr_day = wv_day.getCurrentItem() + 1;
		curr_hour = wv_hours.getCurrentItem();
		curr_minute = wv_mins.getCurrentItem();
		if (mCallBack != null) {
			mCallBack.onDateTimeSet(curr_year, curr_month, curr_day, curr_hour,
					curr_minute);
		}
	}
	 public void show() {
	        super.show();
	 }
	public  interface OnDateTimeSetListener {
			void onDateTimeSet(int year, int monthOfYear, int dayOfMonth, int hour,
					int minute);
		}
	public static int adjustFontSize(WindowManager windowmanager) {

		 int screenWidth = windowmanager.getDefaultDisplay().getWidth();
	     int screenHeight = windowmanager.getDefaultDisplay().getHeight();
	     /*  DisplayMetrics dm = new DisplayMetrics();
	      dm = windowmanager.getApplicationContext().getResources().getDisplayMetrics();
	     int widthPixels = dm.widthPixels;
	     int heightPixels = dm.heightPixels;
	     float density = dm.density;
	     fullScreenWidth = (int)(widthPixels * density);
	     fullScreenHeight = (int)(heightPixels * density);*/
		if (screenWidth <= 240) { // 240X320 灞忓箷
			return 10;
		} else if (screenWidth <= 320) { // 320X480 灞忓箷
			return 14;
		} else if (screenWidth <= 480) { // 480X800 鎴�480X854 灞忓箷
			return 24;
		} else if (screenWidth <= 540) { // 540X960 灞忓箷
			return 26;
		} else if (screenWidth <= 800) { // 800X1280 灞忓箷
			return 30;
		} else { // 澶т簬 800X1280
			return 30;
		}
	}
}


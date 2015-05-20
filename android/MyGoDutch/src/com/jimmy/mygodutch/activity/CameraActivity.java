package com.jimmy.mygodutch.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.jimmy.mygodutch.R;
import com.jimmy.mygodutch.adapter.UserAdapter;
import com.jimmy.mygodutch.business.UserBusiness;
import com.jimmy.mygodutch.business.UserMsgBusiness;
import com.jimmy.mygodutch.model.User;
import com.jimmy.mygodutch.model.UserMsg;
import com.jimmy.mygodutch.util.DialogUtil;
import com.jimmy.mygodutch.util.RegexTools;
import com.xia.ui.component.MyDateTimePickerDialog;
import com.xia.ui.component.MyDateTimePickerDialog.OnDateTimeSetListener;

@SuppressLint("NewApi")
public class CameraActivity extends ActivityFrame {
	private ListView lvUserList;
	ProgressDialog d = null;
	private UserAdapter mAdapterUser;
	private List<User> list = new ArrayList<User>();
	private UserBusiness userBusiness = new UserBusiness();
	UserMsgBusiness userMsgBusiness=new UserMsgBusiness();
	private User mSelectModlUser;
	private Button bnCamera;
	
	private Button bnSend;
	private Button btDate;
	private EditText edDate;
	private EditText edPlace;
	private EditText edContent;
	private ImageView imv_camera;
	File file;
	String id = ""; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();   
		//获取数据   
		  
		 id = intent.getStringExtra("id");   
		appendCenterBody(R.layout.camera);
		InitView();
		InitListeners();
		//BindData();
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
        .detectDiskReads().detectDiskWrites().detectNetwork()  
        .penaltyLog().build());  
	}

	public void InitView() {
		bnCamera=(Button)findViewById(R.id.bnCamera);
		bnSend=(Button)findViewById(R.id.bnSend);
		btDate=(Button)findViewById(R.id.btDate);
		
		edPlace=(EditText)findViewById(R.id.edPlace);
		edDate=(EditText)findViewById(R.id.edDate);
		edContent=(EditText)findViewById(R.id.edContent);
	
		imv_camera=(ImageView)findViewById(R.id.imv_camera);

	//	lvUserList = (ListView) findViewById(R.id.lvUserList);
	}

	public void InitListeners() {
		bnCamera.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				System.out.print(Environment.getExternalStorageDirectory());
				System.out.print(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED));
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
				 file =new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis()+".jpg");
				else
					 file =new File(System.currentTimeMillis()+".jpg");
					intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(file));
					System.out.println(file.getAbsolutePath());
					System.out.println(file.getPath());
					
				startActivityForResult(intent,100);
				
			}
		});
	      btDate.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					new MyDateTimePickerDialog(CameraActivity.this, new OnDateTimeSetListener() {
						
						@Override
						public void onDateTimeSet(int year, int monthOfYear, int dayOfMonth,
								int hour, int minute) {
							edDate.setText(year+"-"+monthOfYear+"-"+dayOfMonth+" "+hour+":"+minute);	
						}
					}).show();
				}
				
			});
	      bnSend.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					UserMsg userMsg=new UserMsg();
					userMsg.setPlace(edPlace.getText().toString());
					userMsg.setPic(file.getPath());
					
					userMsg.setFromuid(Long.parseLong(id));
					try {
						InputStream in=new FileInputStream(file);
						userMsgBusiness.saveUserMsg(userMsg,in,file.getName());
						CameraActivity.this.finish();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==100){
			imv_camera.setImageURI(Uri.fromFile(file));
		}
	}
	public void BindData() {

		mAdapterUser = new UserAdapter(this, list); // list必须被new出来传到UserAdapter里，否则会报错

		lvUserList.setAdapter(mAdapterUser);
		d = new ProgressDialog(CameraActivity.this);
		d.show();
		new Thread(networkTask).start();

		// SetTitle();
	}

	Runnable networkTask = new Runnable() {

		@Override
		public void run() {
			// TODO
			// 在这里进行 http request.网络请求相关操作
			boolean result = false;
			try {
				list = userBusiness.findAllUser();
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
				DialogUtil.showDialog(CameraActivity.this, "no user list", false);
			}
			Log.i("mylog", "请求结果为-->" + result);
			// TODO
			// UI界面的更新等相关操作
		}
	};

	public void updateListViewData() {
		mAdapterUser.SetList(list);
		mAdapterUser.notifyDataSetChanged();
		d.dismiss();

	}
	 @Override
	    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
	    	AdapterContextMenuInfo _AdapterContextMenuInfo = (AdapterContextMenuInfo) menuInfo;
	    	ListAdapter _ListAdapter = lvUserList.getAdapter();
	    	
	    	mSelectModlUser = (User)_ListAdapter.getItem(_AdapterContextMenuInfo.position);
	    	
	    	menu.setHeaderIcon(R.drawable.user_small_icon);
	    	menu.setHeaderTitle(mSelectModlUser.getName());
	    	
	    	CreateMenu(menu);
	    }
	 @Override
	    public boolean onContextItemSelected(MenuItem item) {

	    	switch (item.getItemId()) {
			case 1:
				ShowUserAddOrEditDialog(mSelectModlUser);
				break;
			case 2:
				Delete();
				break;
			default:
				break;
			}

	    	return super.onContextItemSelected(item);
	    }
	 private void ShowUserAddOrEditDialog(User pModelUser)
		{
			View _View = GetLayouInflater().inflate(R.layout.user_add_or_edit, null);
			
			EditText _etUserName = (EditText) _View.findViewById(R.id.etUserName);
			
			if (pModelUser != null) {
				_etUserName.setText(pModelUser.getName());
			}
			
			String _Title;
			
			if(pModelUser == null)
			{
				_Title = getString(R.string.DialogTitleUser,new Object[]{getString(R.string.TitleAdd)});
			}
			else {
				_Title = getString(R.string.DialogTitleUser,new Object[]{getString(R.string.TitleEdit)});
			}
			
			AlertDialog.Builder _Builder = new AlertDialog.Builder(this);
			_Builder.setTitle(_Title)
					.setView(_View)
					.setIcon(R.drawable.user_big_icon)
					.setNeutralButton(getString(R.string.ButtonTextSave), new OnAddOrEditUserListener(pModelUser,_etUserName,true))
					.setNegativeButton(getString(R.string.ButtonTextCancel), new OnAddOrEditUserListener(null,null,false))
					.show();
		}
	 
	 private class OnAddOrEditUserListener implements DialogInterface.OnClickListener
		{
			private User mModelUser;
			private EditText etUserName;
			private boolean mIsSaveButton;
			
			public OnAddOrEditUserListener(User pModelUser,EditText petUserName,Boolean pIsSaveButton)
			{
				mModelUser = pModelUser;
				etUserName = petUserName;
				mIsSaveButton = pIsSaveButton;
			}
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if(mIsSaveButton == false)
				{
					SetAlertDialogIsClose(dialog, true);
					return;
				}
				
				if (mModelUser == null) {
					mModelUser = new User();
				}
				String userid="0";
				try {
					userid = userBusiness.findUserByName(mModelUser.getName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
				
				String _UserName = etUserName.getText().toString().trim();
				
				boolean _CheckResult = RegexTools.IsChineseEnglishNum(_UserName);
				
				if (!_CheckResult) {
					Toast.makeText(getApplicationContext(), getString(R.string.CheckDataTextChineseEnglishNum,new Object[]{etUserName.getHint()}), SHOW_TIME).show();
					SetAlertDialogIsClose(dialog, false);
					return;
				}
				else {
					SetAlertDialogIsClose(dialog, true);
				}
				
				
				
//				if (_CheckResult) {
//					Toast.makeText(getApplicationContext(), getString(R.string.CheckDataTextUserExist), SHOW_TIME).show();
//					
//					SetAlertDialogIsClose(dialog, false);
//					return;
//				}
//				else {
//					SetAlertDialogIsClose(dialog, true);
//				}
				
				mModelUser.setName(etUserName.getText().toString());
				
				boolean _Result = false;
				
				if (mModelUser.getId()== 0) {
					try {
						mModelUser.setId(Long.parseLong(userid));
						_Result = userBusiness.updateUser(mModelUser);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						_Result=false;
					}
				}
				else {
					try {
						_Result = userBusiness.updateUser(mModelUser);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						_Result=false;
					}
				}
				
				if (_Result) {
					BindData();
				}
				else {
					Toast.makeText(CameraActivity.this, getString(R.string.TipsAddFail), 1).show();
				}
			}
			
		}
	 public void SetAlertDialogIsClose(DialogInterface pDialog,Boolean pIsClose)
		{
			try {
				Field _Field = pDialog.getClass().getSuperclass().getDeclaredField("mShowing");
				_Field.setAccessible(true);
			    _Field.set(pDialog, pIsClose);
			} catch (Exception e) {
			}
		}
	 private void Delete() {
			String _Message = getString(R.string.DialogMessageUserDelete,new Object[]{mSelectModlUser.getName()});
			ShowAlertDialog(R.string.DialogTitleDelete,_Message,new OnDeleteClickListener());
		}
	 private class OnDeleteClickListener implements DialogInterface.OnClickListener
		{
			@Override
			public void onClick(DialogInterface dialog, int which) {
				boolean _Result=false;
				try {
					_Result = CameraActivity.this.userBusiness.deleteUser(mSelectModlUser);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if (_Result == true) {
					BindData();
				}
			}
		}

}

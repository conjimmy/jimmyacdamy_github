package com.jimmy.mygodutch.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jimmy.mygodutch.R;
import com.jimmy.mygodutch.activity.RecentTopics;
import com.jimmy.mygodutch.business.ReplyMsgBusiness;
import com.jimmy.mygodutch.business.UserMsgBusiness;
import com.jimmy.mygodutch.model.ReplyMsg;
import com.jimmy.mygodutch.model.UserMsg;

public class AdapterTopics extends BaseExpandableListAdapter {
	
	private class GroupHolder
	{
		TextView Name;
		TextView Count;
		ImageView imv;
	}
	
	private class ChildHolder
	{
		TextView Name;
	}

	private Context m_Context;
	private List m_List;
	public List getM_List() {
		return m_List;
	}

	public void setM_List(List m_List) {
		this.m_List = m_List;
	}

	private UserMsgBusiness m_BusinessTopics;
	private ReplyMsgBusiness m_replyBusiness;
	public List _ChildCountOfGroup;
	
	public AdapterTopics(Context p_Context)
	{
		m_BusinessTopics = new UserMsgBusiness();
		m_replyBusiness = new ReplyMsgBusiness();
		m_Context = p_Context;
		m_List = m_BusinessTopics.getUserMsgs();
		_ChildCountOfGroup = new ArrayList();
	}
	
	public AdapterTopics(Context recentTopics, List<UserMsg> list) {
		m_Context = recentTopics;
		m_BusinessTopics = new UserMsgBusiness();
		m_replyBusiness = new ReplyMsgBusiness();
		m_List = m_BusinessTopics.getUserMsgs();
		_ChildCountOfGroup = new ArrayList();
	}

	public Integer GetChildCountOfGroup(int groupPosition)
	{
		return (Integer) _ChildCountOfGroup.get(groupPosition);
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		UserMsg _UserMsg = (UserMsg) getGroup(groupPosition);
		List<ReplyMsg> _List = m_replyBusiness.getReplyMsgsFromMsgId(_UserMsg.getId());
		return _List.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		ChildHolder _ChildHolder;
		if(convertView == null)
		{
			convertView = LayoutInflater.from(m_Context).inflate(R.layout.recent_topics_children_list_item, null);
			_ChildHolder = new ChildHolder();
			_ChildHolder.Name = (TextView)convertView.findViewById(R.id.tvReplyName);
			convertView.setTag(_ChildHolder);
		}
		else {
			_ChildHolder = (ChildHolder)convertView.getTag();
		}
		ReplyMsg   reply= (ReplyMsg)getChild(groupPosition, childPosition);
		_ChildHolder.Name.setText(reply.getContent());
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		UserMsg _UserMsg = (UserMsg) getGroup(groupPosition);
		int count = m_replyBusiness.getReplyCountByUserMgsId(_UserMsg.getId());
		return count;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return (UserMsg)m_List.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return m_List.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return (long)groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		final GroupHolder _GroupHolder;
		if (convertView == null) {
			convertView = LayoutInflater.from(m_Context).inflate(R.layout.recent_topics_group_list_item, null);
			_GroupHolder = new GroupHolder();
			_GroupHolder.Name = (TextView)convertView.findViewById(R.id.tvTopicsName);
			_GroupHolder.Count = (TextView)convertView.findViewById(R.id.tvCount);
		//	_GroupHolder.imv = (ImageView)convertView.findViewById(R.id.imageView1);
			convertView.setTag(_GroupHolder);
		}
		else {
			_GroupHolder = (GroupHolder)convertView.getTag();
		}
		UserMsg _UserMsg = (UserMsg)getGroup(groupPosition);
		_GroupHolder.Name.setText(_UserMsg.getContent());
		int   count = m_replyBusiness.getReplyCountByUserMgsId(_UserMsg.getId());
		_GroupHolder.Count.setText(m_Context.getString(R.string.TextViewTextChildrenTopics, count));
		
//		try {
//			final Bitmap  bmp = m_BusinessTopics.getImage(_UserMsg.getPic());
//			if(bmp!=null)
//				((RecentTopics)m_Context).runOnUiThread(new Runnable()    
//		        {    
//		            public void run()    
//		            {    
//		            	_GroupHolder.imv.setImageBitmap(bmp);
//		            }    
//		      
//		        });   
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
			
		
		_ChildCountOfGroup.add(count);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		//行是否具有唯一id
		//是否指定分组视图及其子视图的ID对应的后台数据改变也会保持该ID
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		////行是否可选
		return true;
	}

}

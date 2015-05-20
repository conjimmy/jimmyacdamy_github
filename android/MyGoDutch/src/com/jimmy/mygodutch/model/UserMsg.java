package com.jimmy.mygodutch.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


public class UserMsg extends BaseObject
  implements Serializable
{
  private static final long serialVersionUID = 4902517526979768717L;
  private Long id;
  private String fromuname;
  private Long fromuid;
  private String title;
  private String content;
  private int haveread;
  private int isHis;
  private Timestamp postTime;  //发帖时间

  public Timestamp getPostTime() {
	return postTime;
}

public void setPostTime(Timestamp postTime) {
	this.postTime = postTime;
}



private String date;  //活动时间
  private String place;    //地点
  private String pic;       //图片
  

  public String getPlace() {
	return place;
}

public void setPlace(String place) {
	this.place = place;
}


public String getPic() {
	return pic;
}

public void setPic(String pic) {
	this.pic = pic;
}


  
  private Set<ReplyMsg> replyMsgs = new HashSet();

  public UserMsg(String id, String content) {
	this.id=Long.parseLong(id);
	this.content=content;
}

public UserMsg() {
	// TODO Auto-generated constructor stub
}


  public Set<ReplyMsg> getReplyMsgs()
  {
    return this.replyMsgs; }

  public void setReplyMsgs(Set<ReplyMsg> replyMsgs) {
    this.replyMsgs = replyMsgs; }


  public String getDate() {
    return this.date; }

  public void setDate(String date) {
    this.date = date;
  }


  public Long getId()
  {
    return this.id; }


  public int getIsHis() {
    return this.isHis;
  }

  public void setIsHis(int isHis) {
    this.isHis = isHis;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public Long getFromuid()
  {
    return this.fromuid;
  }

  public void setFromuid(Long fromuid) {
    this.fromuid = fromuid;
  }


  public String getFromuname() {
    return this.fromuname;
  }

  public void setFromuname(String fromuname) {
    this.fromuname = fromuname;
  }


  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title; }


  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content; }


  public int getHaveread() {
    return this.haveread;
  }

  public void setHaveread(int haveread) {
    this.haveread = haveread;
  }

  public String toString()
  {
    return null;
  }

  public boolean equals(Object o)
  {
    return false;
  }

  public int hashCode()
  {
    return 0;
  }


}
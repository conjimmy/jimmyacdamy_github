package com.jimmy.mygodutch.model;

import java.io.Serializable;
import java.sql.Timestamp;


public class ReplyMsg extends BaseObject
  implements Serializable
{
  private static final long serialVersionUID = 4902517526979768717L;
  private Long id;
  private String touname;
  private String fromuname;
  private Long touid;
  private Long fromuid;
  private String title;
  private String content;
  private int haveread;
  private int isHis;
  private Timestamp date;

  public Timestamp getDate()
  {
    return this.date; }

  public void setDate(Timestamp date) {
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


  public Long getTouid()
  {
    return this.touid;
  }

  public void setTouid(Long touid) {
    this.touid = touid; }

  
  public Long getFromuid() {
    return this.fromuid;
  }

  public void setFromuid(Long fromuid) {
    this.fromuid = fromuid; }

 
  public String getTouname() {
    return this.touname;
  }

  public void setTouname(String touname) {
    this.touname = touname; }

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

  public static void main(String[] args)
  {
  }
}
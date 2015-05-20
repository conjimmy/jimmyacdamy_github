package com.jimmy.mygodutch.model;

import java.io.Serializable;

public abstract class BaseObject
  implements Serializable
{
  public abstract String toString();

  public abstract boolean equals(Object paramObject);

  public abstract int hashCode();
}
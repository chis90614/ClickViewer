package com.clickjava.lib.bean;

public interface ThumbEventSource {
  public void addThumbListener(ThumbListener listener);
  public void removeThumbListener(ThumbListener listener);
  public void fireThumbEvent();
}
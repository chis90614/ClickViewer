package com.clickjava.lib.bean;

import java.util.EventObject;

public class ThumbEvent extends EventObject {
  public ThumbEvent(ThumbBean t) {
    super(t);
  }

  public String getImageName() {
    ThumbBean t = (ThumbBean)this.getSource();
    return t.getImageName();
  }
}
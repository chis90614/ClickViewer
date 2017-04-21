package com.clickjava.lib.bean;

import java.awt.*;
import java.util.Vector;
import java.awt.event.*;

public class SuperThumb extends ThumbBean implements ThumbEventSource{
  protected Vector _List;

  public SuperThumb() {
    _List = new Vector();
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  public void addThumbListener(ThumbListener listener) {
    _List.add(listener);
  }

  public void removeThumbListener(ThumbListener listener) {
    _List.remove(listener);
  }

  public void fireThumbEvent() {
    ThumbEvent event = new ThumbEvent(this);
    synchronized (_List) {
      for(int idx=0; idx < _List.size(); idx++) {
        ThumbListener listener = (ThumbListener)_List.get(idx);
        listener.selected(event);
      }
    }
  }
  private void jbInit() throws Exception {
    this.addMouseListener(new SuperThumb_this_mouseAdapter(this));
  }

  public void this_mouseClicked(MouseEvent e) {
    fireThumbEvent();
  }
}

class SuperThumb_this_mouseAdapter extends java.awt.event.MouseAdapter {
  SuperThumb adaptee;

  SuperThumb_this_mouseAdapter(SuperThumb adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.this_mouseClicked(e);
  }
}
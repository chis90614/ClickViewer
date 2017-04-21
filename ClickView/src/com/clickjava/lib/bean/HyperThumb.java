package com.clickjava.lib.bean;

import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class HyperThumb extends SuperThumb implements ThumbEventSource{
  private boolean bReverse = false;
  private Color _Orig;

  public HyperThumb() {
    _Orig = this.getBackground();
  }

  public void reverse() {
    bReverse = !bReverse;
    if(bReverse)
      this.setBackground(Color.BLUE);
    else
      this.setBackground(_Orig);
    repaint();
  }

  public void this_mouseClicked(MouseEvent e) {
    reverse();
    fireThumbEvent();
  }
}
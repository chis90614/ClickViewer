package com.clickjava.app.clickview;

import java.io.*;
import javax.imageio.*;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

import com.clickjava.lib.bean.*;

public class ImageView extends JComponent implements ThumbListener {
  BufferedImage image;
  HyperThumb thumb;

  public void selected(ThumbEvent e) {
    try {
      image = ImageIO.read(new File(e.getImageName()));
    }
    catch (IOException ex) {
      return;
    }
    if (thumb != null) {
      thumb.reverse();
    }
    thumb = (HyperThumb) e.getSource();
    repaint();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (image == null) {
      return;
    }
    double ratio = (double) image.getWidth() / (double) image.getHeight();
    Dimension d = this.getSize();
    double panelRatio = d.getWidth() / d.getHeight();
    int width = (ratio > panelRatio) ? (int) d.getWidth() :
        (int) (d.getHeight() * ratio);
    int height = (ratio > panelRatio) ? (int) (d.getWidth() / ratio) :
        (int) d.getHeight();
    g.drawImage(image, (int) ( (d.getWidth() - width) / 2),
                (int) ( (d.getHeight() - height) / 2), width, height, null);
  }
}
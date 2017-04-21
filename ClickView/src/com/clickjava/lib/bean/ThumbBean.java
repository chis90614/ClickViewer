package com.clickjava.lib.bean;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import javax.swing.*;
import java.net.*;

public class ThumbBean
    extends JComponent
    implements Serializable {

  // 프로퍼티
  protected String imageName; // path용
  protected int hgap;
  protected int vgap;
  protected String caption;

  // 내부 변수
  protected BufferedImage image;

  public ThumbBean() {
    setPreferredSize(new Dimension(150, 150));
  }

  public String getImageName() {
    return imageName;
  }

  // 이미지 이름이 주어졌을때, 해당 이미지도 빈에 추가한다.
  public void setImageName(String imageName) {
    try {
      image = ImageIO.read(new File(imageName));
      // 절대 패스로 주어진 경우를 처리한다.
      int idx = imageName.lastIndexOf(File.separatorChar);
      if (idx != -1) {
        this.caption = imageName.substring(idx + 1);
      } else {
        this.caption = imageName;
      }
      this.imageName = imageName;
    }
    catch (IOException e) {
      this.imageName = null;
      this.image = null;
      this.caption = "해당 이미지 없음";
    }
    this.repaint();
  }

  public int getHgap() {
    return hgap;
  }

  public void setHgap(int hgap) {
    this.hgap = hgap;
  }

  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public int getVgap() {
    return vgap;
  }

  public void setVgap(int vgap) {
    this.vgap = vgap;
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    int width, height;
    width = this.getWidth();
    height = this.getHeight();
    g.setColor(this.getBackground());
    g.fill3DRect(0, 0, width - 1, height - 1, true);

    if (image != null) {
      // 이미지 객체를 뿌림
      FontMetrics metrics = g.getFontMetrics();
      width = this.getWidth() - (hgap * 2);
      height = this.getHeight() - (vgap * 2) - metrics.getHeight() - 2;
      g.drawImage(image, hgap, vgap, width, height, this);
    }
    /**
     * @todo
     * 문자열의 크기를 재서, 중앙에 위치하고,
     * 8자 이상이면 "..." 으로 처리하면 더 좋겠죠 ?
     * 독자 여러분들이 해보세요.
     */
    g.setColor(this.getForeground());
    g.drawString(caption, hgap, this.getHeight() - vgap);
  }
}

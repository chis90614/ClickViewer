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

  // ������Ƽ
  protected String imageName; // path��
  protected int hgap;
  protected int vgap;
  protected String caption;

  // ���� ����
  protected BufferedImage image;

  public ThumbBean() {
    setPreferredSize(new Dimension(150, 150));
  }

  public String getImageName() {
    return imageName;
  }

  // �̹��� �̸��� �־�������, �ش� �̹����� �� �߰��Ѵ�.
  public void setImageName(String imageName) {
    try {
      image = ImageIO.read(new File(imageName));
      // ���� �н��� �־��� ��츦 ó���Ѵ�.
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
      this.caption = "�ش� �̹��� ����";
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
      // �̹��� ��ü�� �Ѹ�
      FontMetrics metrics = g.getFontMetrics();
      width = this.getWidth() - (hgap * 2);
      height = this.getHeight() - (vgap * 2) - metrics.getHeight() - 2;
      g.drawImage(image, hgap, vgap, width, height, this);
    }
    /**
     * @todo
     * ���ڿ��� ũ�⸦ �缭, �߾ӿ� ��ġ�ϰ�,
     * 8�� �̻��̸� "..." ���� ó���ϸ� �� ������ ?
     * ���� �����е��� �غ�����.
     */
    g.setColor(this.getForeground());
    g.drawString(caption, hgap, this.getHeight() - vgap);
  }
}

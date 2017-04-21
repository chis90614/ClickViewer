package com.clickjava.app.clickview;

import java.io.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.clickjava.lib.bean.*;

public class ThumbPanel extends JPanel implements Observer,
    DirectoryChangeListener {
  GridLayout gridLayout1 = new GridLayout();
  private HashSet extension = new HashSet();
  private File[] fileList;
  private JScrollPane parentScrollPane;
  private DirectoryChangeObservable observable;
  private ImageView imageView;

  public ThumbPanel(DirectoryChangeObservable observable, ImageView imageView,
                    JScrollPane pane) {
    try {
      this.observable = observable;
      this.parentScrollPane = pane;
      this.imageView = imageView;
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    gridLayout1.setColumns(2);
    gridLayout1.setHgap(2);
    gridLayout1.setRows(0);
    gridLayout1.setVgap(2);
    parentScrollPane.addComponentListener(new ResizeListener());
    this.setLayout(gridLayout1);
  }

  public void addExtension(String extension) {
    this.extension.add(extension.toLowerCase());
  }

  private void setFileList(File path) {
    fileList = path.listFiles(new ThumbFileFilter());
  }

  private void setGrid() {
    if (fileList == null) {
      return;
    }
    double width = parentScrollPane.getViewportBorderBounds().getWidth();
    if (width <= 0) {
      return;
    }
    int column = (int) Math.floor(width / 150);
    if (gridLayout1.getColumns() == column) {
      return;
    }
    gridLayout1.setColumns(column);
  }

  private void addThumb() {
    removeAll();
    gridLayout1.setColumns(1);
    if (fileList == null) {
      return;
    }
    for (int i = 0; i < fileList.length; i++) {
      if (fileList[i].isDirectory()) {
        DirectoryThumb thumb = new DirectoryThumb(fileList[i]);
        thumb.addDirectoryChangeListener(this);
        thumb.setHgap(5);
        thumb.setVgap(5);
        add(thumb);
      }
      else {
        HyperThumb thumb = new HyperThumb();
        thumb.setImageName(fileList[i].getAbsolutePath());
        thumb.setHgap(5);
        thumb.setVgap(5);
        thumb.addThumbListener(imageView);
        add(thumb);
      }
    }
  }

  public void directoryChange(DirectoryChangeEvent e) {
    DirectoryThumb thumb = (DirectoryThumb) e.getSource();
    observable.changeDirectory(thumb.getDirectory());
  }

  public void update(Observable o, Object arg) {
    setFileList( (File) arg);
    addThumb();
    setGrid();
    revalidate();
  }

  class ThumbFileFilter implements FileFilter {
    public boolean accept(File pathname) {
      if (pathname.isDirectory()) {
        return true;
      }

      String fileExt = getExtension(pathname);
      if (fileExt == null) {
        return false;
      }

      String[] ext = (String[]) extension.toArray(new String[0]);
      for (int i = 0; i < ext.length; i++) {
        if (ext[i].equals(fileExt)) {
          return true;
        }
      }
      return false;
    }

    private String getExtension(File file) {
      String path = file.getAbsolutePath();
      int slash = path.lastIndexOf('\\');
      String filename = path.substring(slash + 1);
      if (filename.charAt(0) == '.') {
        return null;
      }

      int point = filename.lastIndexOf('.');
      if (point == 0) {
        return null;
      }
      else {
        return filename.substring(point + 1).toLowerCase();
      }
    }
  }

  class ResizeListener extends ComponentAdapter {
    public void componentResized(ComponentEvent e) {
      setGrid();
      revalidate();
    }
  }
}
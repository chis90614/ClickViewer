package com.clickjava.app.clickview;

import java.io.*;
import java.util.*;

import java.awt.event.*;

import com.clickjava.lib.bean.*;

public class DirectoryThumb extends HyperThumb {
  private File directory;
  private Vector listeners = new Vector();
  public DirectoryThumb(File directory) {
    this.directory = directory;
    setImageName(getClass().getResource("directory.gif").getPath());
    setCaption(directory.getName());
    addMouseListener(new DirectoryMouseListener());
  }

  public void this_mouseClicked(MouseEvent e) {
  }

  public File getDirectory() {
    return this.directory;
  }

  public void addDirectoryChangeListener(DirectoryChangeListener listener) {
    listeners.add(listener);
  }

  public void removeDirectoryChangeListener(DirectoryChangeListener listener) {
    listeners.remove(listener);
  }

  public void fireDirectoryChange() {
    DirectoryChangeEvent event = new DirectoryChangeEvent(this);
    synchronized (listeners) {
      for (int idx = 0; idx < listeners.size(); idx++) {
        DirectoryChangeListener listener = (DirectoryChangeListener) listeners.
            get(idx);
        listener.directoryChange(event);
      }
    }
  }

  class DirectoryMouseListener extends MouseAdapter {
    public void mouseClicked(MouseEvent e) {
      if (e.getClickCount() > 1) {
        fireDirectoryChange();
      }
    }
  }
}
package com.clickjava.app.clickview;

import java.io.*;
import java.util.*;

import javax.swing.*;

public class DirectoryChangeObservable extends Observable {
  public void changeDirectory(String directoryPath) {
    File f = new File(directoryPath);
    if (f == null || !f.isDirectory()) {
      SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          JOptionPane.showMessageDialog(null, "���丮�� �������� �ʽ��ϴ�.", "���丮 ����",
                                        JOptionPane.ERROR_MESSAGE);
        }
      });
      return;
    }

    try {
      changeDirectory(f.getCanonicalFile());
    }
    catch (IOException e) {}
  }

  public void changeDirectory(File directory) {
    setChanged();
    notifyObservers(directory);
  }
}

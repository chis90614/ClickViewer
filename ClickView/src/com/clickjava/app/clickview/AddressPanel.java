package com.clickjava.app.clickview;

import java.io.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddressPanel extends JPanel implements Observer {
  BorderLayout borderLayout1 = new BorderLayout();
  JButton btnMove = new JButton();
  JLabel lblDirectory = new JLabel();
  JTextField txtDirectory = new JTextField();
  DirectoryChangeObservable observable;
  public AddressPanel(DirectoryChangeObservable observable) {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    this.observable = observable;
  }

  void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    lblDirectory.setText("디렉토리 : ");
    btnMove.setText("이동");
    btnMove.addActionListener(new AddressPanel_btnMove_actionAdapter(this));
    txtDirectory.setText("");
    txtDirectory.addActionListener(new AddressPanel_txtDirectory_actionAdapter(this));

    add(lblDirectory, BorderLayout.WEST);
    add(txtDirectory, BorderLayout.CENTER);
    add(btnMove, BorderLayout.EAST);

  }

  void btnMove_actionPerformed(ActionEvent e) {
    observable.changeDirectory(txtDirectory.getText());
  }

  public void update(Observable o, Object arg) {
    File f = (File) arg;
    txtDirectory.setText(f.getAbsolutePath());
  }

  void txtDirectory_actionPerformed(ActionEvent e) {
    btnMove_actionPerformed(e);
  }
}

class AddressPanel_btnMove_actionAdapter implements java.awt.event.
    ActionListener {
  AddressPanel adaptee;

  AddressPanel_btnMove_actionAdapter(AddressPanel adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnMove_actionPerformed(e);
  }
}

class AddressPanel_txtDirectory_actionAdapter implements java.awt.event.
    ActionListener {
  AddressPanel adaptee;

  AddressPanel_txtDirectory_actionAdapter(AddressPanel adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.txtDirectory_actionPerformed(e);
  }
}
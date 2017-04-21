package com.clickjava.app.clickview;

import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

public class DirectoryTree extends JTree implements Observer,
    TreeSelectionListener {
  private DirectoryChangeObservable observable;
  public DirectoryTree(DirectoryChangeObservable observable) {
    super(new DirectoryTreeModel(null));
    this.observable = observable;
    this.setRootVisible(false);
    addTreeSelectionListener(this);
    getSelectionModel().setSelectionMode(TreeSelectionModel.
                                         SINGLE_TREE_SELECTION);
  }

  public void update(Observable o, Object arg) {
    TreePath path = ( (DirectoryTreeModel) getModel()).setDirectory( (File) arg);
    this.setSelectionPath(path);
    makeVisible(path);
  }

  public void valueChanged(TreeSelectionEvent e) {
    DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode)this.
        getLastSelectedPathComponent();
    if (selectNode == null) {
      return;
    }
    FileItem item = (FileItem) selectNode.getUserObject();
    observable.changeDirectory(item.getFile());
  }
}
package com.clickjava.app.clickview;

import java.io.*;

import javax.swing.tree.*;

public class DirectoryTreeModel extends DefaultTreeModel {
  private DefaultMutableTreeNode rootNode;

  public DirectoryTreeModel(TreeNode root) {
    super(root, true);
    rootNode = new DefaultMutableTreeNode();
    File[] file = File.listRoots();
    for (int i = 0; i < file.length; i++) {
      DefaultMutableTreeNode node = new DefaultMutableTreeNode(new FileItem(
          file[i], FileItem.DRIVE));
      rootNode.add(node);
    }
    setRoot(rootNode);
  }

  public TreePath setDirectory(File directory) {
    DefaultMutableTreeNode selectNode = searchDirectory(directory);
    return new TreePath(selectNode.getPath());
  }

  private DefaultMutableTreeNode searchDirectory(File f) {
    File parent = f.getParentFile();
    if (parent != null) {
      DefaultMutableTreeNode parentNode = searchDirectory(parent);
      FileItem fi = new FileItem(f, FileItem.DIRECTORY);
      DefaultMutableTreeNode nowNode = searchChildNode(parentNode, fi);
      if (nowNode != null) {
        return nowNode;
      }
      else {
        nowNode = new DefaultMutableTreeNode(fi);
        parentNode.add(nowNode);
        this.nodeStructureChanged(parentNode);
        return nowNode;
      }
    }
    FileItem item = new FileItem(f, FileItem.DRIVE);
    return searchChildNode(rootNode, item);
  }

  private DefaultMutableTreeNode searchChildNode(DefaultMutableTreeNode node,
                                                 FileItem f) {
    int count = node.getChildCount();
    for (int i = 0; i < count; i++) {
      DefaultMutableTreeNode nowNode = (DefaultMutableTreeNode) node.getChildAt(
          i);
      if (f.equals(nowNode.getUserObject())) {
        return nowNode;
      }
    }
    return null;
  }
}
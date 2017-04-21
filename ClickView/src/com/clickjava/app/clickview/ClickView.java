package com.clickjava.app.clickview;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Fixing Version
public class ClickView extends JFrame {
  JPanel contentPane;
  BorderLayout borderLayout1 = new BorderLayout();
  JSplitPane jSplitPane1 = new JSplitPane();
  JSplitPane jSplitPane2 = new JSplitPane();
  JPanel jPanel1 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JScrollPane jScrollPane2 = new JScrollPane();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel2 = new JPanel();

  // 모듈 생성및 배치
  DirectoryChangeObservable observable = new DirectoryChangeObservable();
  DirectoryTree directoryTree = new DirectoryTree(observable);
  ImageView imageView = new ImageView();
  AddressPanel addressPanel = new AddressPanel(observable);
  ThumbPanel thumbPanel = new ThumbPanel(observable, imageView, jScrollPane2);
  FlowLayout flowLayout1 = new FlowLayout();

  //Construct the frame
  public ClickView() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  //Component initialization
  private void jbInit() throws Exception {
    contentPane = (JPanel)this.getContentPane();
    contentPane.setLayout(borderLayout1);
    this.setSize(new Dimension(800, 600));
    this.setTitle("클릭 뷰어");
    jSplitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);
    jSplitPane2.setDividerSize(2);
    jPanel1.setLayout(borderLayout2);
    jSplitPane1.setDividerSize(2);
    jPanel2.setLayout(flowLayout1);
    flowLayout1.setAlignment(FlowLayout.LEFT);
    flowLayout1.setHgap(1);
    flowLayout1.setVgap(1);
    contentPane.add(jSplitPane1, BorderLayout.CENTER);
    jSplitPane1.add(jSplitPane2, JSplitPane.LEFT);
    jSplitPane2.add(jScrollPane1, JSplitPane.TOP);
    jSplitPane1.add(jPanel1, JSplitPane.RIGHT);
    jPanel1.add(jScrollPane2, BorderLayout.CENTER);
    jScrollPane2.getViewport().add(jPanel2, null);
    jSplitPane1.setDividerLocation(300);
    jSplitPane2.setDividerLocation(300);

    // 모듈배치
    observable.addObserver(directoryTree);
    jScrollPane1.getViewport().add(directoryTree);

    jSplitPane2.add(imageView, JSplitPane.BOTTOM);

    observable.addObserver(addressPanel);
    jPanel1.add(addressPanel, BorderLayout.NORTH);

    observable.addObserver(thumbPanel);
    jPanel2.add(thumbPanel);
    jScrollPane2.getViewport().add(jPanel2);

    thumbPanel.addExtension("jpg");
    thumbPanel.addExtension("gif");
  }

  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }
}

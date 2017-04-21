package com.clickjava.app.clickview;

import java.util.EventObject;
import java.io.*;

public class DirectoryChangeEvent extends EventObject {
  public DirectoryChangeEvent(DirectoryThumb changeDirectory){
    super(changeDirectory);
  }
}
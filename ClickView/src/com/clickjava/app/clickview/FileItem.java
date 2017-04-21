package com.clickjava.app.clickview;

import java.io.*;

public class FileItem {
  private File file;
  private int type;
  public static final int DRIVE = 1;
  public static final int DIRECTORY = 2;
  public static final int FILE = 3;
  public FileItem(){
    this(null, FILE);
  }

  public FileItem(File file, int type){
    this.file = file;
    this.type = type;
  }

  public String toString(){
    if(file == null) return "";
    else{
      if(type == DRIVE)
        return file.getPath();
      else
        return file.getName();
    }
  }

  public boolean equals(Object obj){
    FileItem item = (FileItem)obj;
    return (this.file.equals(item.getFile()))? true : false;
  }

  public File getFile(){
    return file;
  }
}
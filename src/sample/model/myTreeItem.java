/*
javafxfilebrowsedemo - Demo application for browsing files in a JavaFX TreeView
Copyright (C) 2012 Hugues Johnson

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; version 2.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
the GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */

package sample.model;

/**
 * Created by t on 4/30/17.
 */

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class myTreeItem extends TreeItem<String>{

    //image loaded for setting
    public static Image folderCollapseImage=new Image(ClassLoader.getSystemResourceAsStream("sample/res/folder.png"));
    public static Image folderExpandImage=new Image(ClassLoader.getSystemResourceAsStream("sample/res/folder-open.png"));
    public static Image fileImage=new Image(ClassLoader.getSystemResourceAsStream("sample/res/text-x-generic.png"));

    //this stores the full path to the file or directory
    private String fullPath;
    public String getFullPath(){return(this.fullPath);}

    private boolean isDirectory;
    public boolean isDirectory(){return(this.isDirectory);}

    public myTreeItem(Path file){
        super(file.toString());
        this.fullPath=file.toString();

        //test if this is a directory and set the icon
        if(Files.isDirectory(file)){
            this.isDirectory=true;
            this.setGraphic(new ImageView(folderCollapseImage));
        }else{
            this.isDirectory=false;
            this.setGraphic(new ImageView(fileImage));
            //if you want different icons for different file types
        }

        //set the value
        if(!fullPath.endsWith(File.separator)){
            //set the value (which is what is displayed in the tree)
            String value=file.toString();
            int indexOf=value.lastIndexOf(File.separator);
            if(indexOf>0){
                this.setValue(value.substring(indexOf+1));
            }else{
                this.setValue(value);
            }
        }


        this.addEventHandler(TreeItem.branchCollapsedEvent(),new EventHandler(){
            @Override
            public void handle(Event e){
                myTreeItem source=(myTreeItem)e.getSource();
                if(source.isDirectory()&&!source.isExpanded()){
                    ImageView iv=(ImageView)source.getGraphic();
                    iv.setImage(folderCollapseImage);
                }
            }
        });

    }
}

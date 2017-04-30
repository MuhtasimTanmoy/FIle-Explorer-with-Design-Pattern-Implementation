package sample.model;

import javafx.scene.image.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;

import static sample.model.myTreeItem.folderExpandImage;

import static sample.model.myTreeItem.fileImage;

/**
 * Created by t on 5/1/17.
 */
public class TableItem extends baseItem{


    private long size;
    private String lastModified;
    private ImageView imageView;


    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public TableItem(File file){
        super(file);
        size=file.length();
        lastModified= new SimpleDateFormat("MM/dd/yyyy").format(file.lastModified());
        if(file.isDirectory()){
            imageView=new ImageView(folderExpandImage);
        }
        else {
            imageView=new ImageView(fileImage);
        }

    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }




}

package sample.model;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * Created by t on 5/1/17.
 */
public class TableItem extends baseItem{


    private long size;
    private String lastModified;


    public TableItem(File file){
        super(file);
        size=file.length();
        lastModified= new SimpleDateFormat("MM/dd/yyyy").format(file.lastModified());

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

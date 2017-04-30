package sample.model;


import javafx.scene.image.Image;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * Created by t on 5/1/17.
 */
public class baseItem {

    private String name;

    private String path;

    private Image image;



    public baseItem(File file) {
        name=file.getName();
        path=file.getPath();

    }

    public Image getImage(){return image;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

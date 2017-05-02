package sample.model;

import javafx.scene.image.Image;

import java.io.File;

/**
 * Created by t on 4/30/17.
 */

//tileItem-the adapter that takes the adaptee file and set it on TilePane through Tile class

public class TileItem extends baseItem {

    public static Image folderExpandImage=new Image(ClassLoader.getSystemResourceAsStream("sample/res/folderIcon.png"));
    public static Image fileImage=new Image(ClassLoader.getSystemResourceAsStream("sample/res/fileIcon.png"));
    private Image image;
    public TileItem(File file) {
        super(file);
        if(file.isDirectory()){
            image=folderExpandImage;
        }else{
            image=fileImage;
        }
  }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}

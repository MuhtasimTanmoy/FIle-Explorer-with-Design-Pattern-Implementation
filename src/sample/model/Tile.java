package sample.model;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * Created by t on 4/30/17.
 */

//The structure defined for TileElement

public class Tile extends VBox {
    Label name;
    Image image;
    ImageView imageView;
    String path;

    public Label getName() {
        return name;
    }

    public void setName(Label name) {
        this.name = name;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Tile(String n, Image image,String p){
        name=new Label(n);
        path=p;
        image=image;
        imageView=new ImageView(image);
        imageView.setFitHeight(48);
        imageView.setFitWidth(48);
        getChildren().addAll(imageView,name);

    }



    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}

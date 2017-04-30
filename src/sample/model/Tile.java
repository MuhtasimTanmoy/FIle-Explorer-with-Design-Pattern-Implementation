package sample.model;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * Created by t on 5/1/17.
 */
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

        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
    }



    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}

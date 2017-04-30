package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import sample.model.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ResourceBundle;

import static sample.model.myTreeItem.folderExpandImage;

public class Controller implements Initializable {

    private String currentPath;

    private boolean tableEnabled;

    @FXML
    private TextField currDir;

    @FXML
    private TilePane tilePane;

    @FXML
    private TreeView<String> treeView;

    @FXML
    private Button goToDIr;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ToggleButton toggle2;

    @FXML
    private TableColumn<baseItem,Long> Size;

    @FXML
    private TableColumn<baseItem,ImageView> Icon;

    @FXML
    private Button backButton;

    @FXML
    private TableView<baseItem> tableView;

    @FXML
    private ToggleButton toggle;

    @FXML
    private TableColumn<baseItem, String> Date;

    @FXML
    private TableColumn<baseItem, String> Name;


    private ObservableList<baseItem> observableList= FXCollections.observableArrayList();

    ItemFactory itemFactory=new ItemFactory();

    @FXML
    void currDirShow(ActionEvent event) {

    }

    @FXML
    void goToDirAction(ActionEvent event) {
        currentPath=currDir.getText();

        showView();
    }

    @FXML
    void toggleList(ActionEvent event) {
        tableEnabled=true;
        tableView.setVisible(true);
        showView();
    }

    @FXML
    void toggleTile(ActionEvent event) {
        tableEnabled=false;
        tableView.setVisible(false);
        showView();
    }

    @FXML
    void backButtonAction(ActionEvent event) {
        File up=new File(currentPath);
        currentPath=up.getParentFile().getPath();
        currDir.setText(currentPath);
        showView();
    }


    void showView(){

        if(tableEnabled)observableList.clear();
        else{
            //clearing the view before setting the new directory file
            if(!tilePane.getChildren().isEmpty()){
                tilePane.getChildren().clear();
            }
        }

        File file=new File(currentPath);
        for(File x :file.listFiles())
        {
            //TableItem tableItem=new TableItem(x);
            baseItem baseItem=itemFactory.getItem(tableEnabled,x);
            if(tableEnabled)observableList.add(baseItem);
            else{
                Tile tile=new Tile(baseItem.getName(),baseItem.getImage(),baseItem.getPath());
                tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(mouseEvent.getClickCount()==2){
                            currentPath=tile.getPath();
                            currDir.setText(currentPath);
                            showView();
                        }
                    }
                });
                tilePane.getChildren().add(tile);
            }
        }

        if(tableEnabled)tableView.setItems(observableList);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //taking home directiry for tree root node
        File init=new File(System.getProperty("user.home"));
        currentPath=System.getProperty("user.dir");
        File curr=new File(currentPath);


        //setting root path
        treeView.setRoot(new myTreeItem(init.toPath()));

        treeView.getRoot().setExpanded(true);

        //listener for tree implemented

        treeView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                myTreeItem source = (myTreeItem) treeView.getSelectionModel().getSelectedItem();


                if (source!= null){
                    currentPath=source.getFullPath();
                    showView();
                    //setting current directory
                    currDir.setText(currentPath);
                    //setting image for expanded treeNode
                    if(source.isDirectory()&&source.isExpanded()){
                        ImageView iv=(ImageView)source.getGraphic();
                        iv.setImage(folderExpandImage);
                    }
                    try{

                        if(source.getChildren().isEmpty()){
                            Path path= Paths.get(source.getFullPath());
                            BasicFileAttributes attribs= Files.readAttributes(path,BasicFileAttributes.class);
                            if(attribs.isDirectory()){
                                DirectoryStream<Path> dir=Files.newDirectoryStream(path);
                                for(Path file:dir){
                                    myTreeItem treeNode=new myTreeItem(file);
                                    source.getChildren().add(treeNode);
                                }
                            }
                        }else{

                    }
                    }catch(IOException x){
                        x.printStackTrace();
                    }
                }
            }
        });


        //table view column added here
        Icon.setCellValueFactory(new PropertyValueFactory<>("imageView"));
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Date.setCellValueFactory(new PropertyValueFactory<>("lastModified"));
        Size.setCellValueFactory(new PropertyValueFactory<>("size"));

        tableEnabled=false;
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if(mouseEvent.getClickCount()==2){
                baseItem baseItem = tableView.getSelectionModel().getSelectedItem();

                currDir.setText(baseItem.getPath());

                currentPath=baseItem.getPath();

                showView();}

            }

        });





        //avoiding horizontal scrolling
        scrollPane.setFitToWidth(true);


        //tile pane designing and padding
        tilePane.setHgap(30);
        tilePane.setVgap(30);


        //column preference
        tilePane.setPrefColumns(5);

        //tile height width
        tilePane.setPrefTileHeight(50);
        tilePane.setPrefTileWidth(80);

        currDir.setText(currentPath);
        showView();

    }


}

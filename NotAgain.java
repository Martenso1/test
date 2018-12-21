/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notagain;

import java.awt.*;
import java.awt.AWTEventMulticaster;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.util.*;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import java.util.List;
import java.util.ArrayList;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener.Change;
import javafx.event.Event;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.util.Callback;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import sun.plugin.com.Dispatch;
import javafx.event.EventHandler;

/**
 *
 * @author Marcin
 */
public class NotAgain extends Application {
    
    private  TableView<JOB> jobList = new TableView<JOB>(); 
    private  TableView<CURRENTjobs> currJobList = new TableView<CURRENTjobs>();    
  
    public final ObservableList<JOB> data =
        FXCollections.observableArrayList(
        new JOB("Jacob", "dupa"),
        new JOB("sdfgh", "dufgbpa"),
        new JOB("sdf", "duregpa"),
        new JOB("dfg", "wet"));   

    public final ObservableList<CURRENTjobs> data2 = 
        FXCollections.observableArrayList(
        new CURRENTjobs("",""));


    final HBox addHbox = new HBox();
    
    //declaring VBox for POP UP
    VBox assignVBox = new VBox();
    //assigning creating new scene and stage for POP UP
    Scene scene2 = new Scene(assignVBox);
    Stage stage2 = new Stage();
    
    
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane bPane = new BorderPane();
         
        //TABLE
        //Right
        final Label testLabel2 = new Label("Current Jobs");
        testLabel2.setFont(new Font("Arial",20));
        currJobList.setEditable(true);
        
        
        TableColumn currJobNameColumn = new TableColumn("Job Name");
        currJobNameColumn.setMinWidth(100);
        currJobNameColumn.setPrefWidth(100);
        currJobNameColumn.setCellValueFactory(new PropertyValueFactory<CURRENTjobs, 
                String>("currJobName"));
        
        
        TableColumn currJobLocColumn=new TableColumn("Location");
        currJobLocColumn.setMinWidth(100);
        currJobLocColumn.setPrefWidth(100);
        currJobLocColumn.setCellValueFactory(new PropertyValueFactory<CURRENTjobs,
                String>("currJobLoc"));
        
        //Table
        //Left
        final Label testLabel = new Label("Available Jobs");
        testLabel.setFont(new Font("Arial",20));
        jobList.setEditable(true);
        
        
        TableColumn jobNameCol = new TableColumn("Job Name");
        jobNameCol.setMinWidth(100);
        jobNameCol.setPrefWidth(100);
        jobNameCol.setCellValueFactory(new PropertyValueFactory<JOB,
                String>("jobName"));
        

        TableColumn jobLocationCol=new TableColumn("Job Location");
        jobLocationCol.setMinWidth(100);
        jobLocationCol.setPrefWidth(100);
        jobLocationCol.setCellValueFactory(new PropertyValueFactory<JOB,
                String>("jobLocation")); 
         
         
         
         
         
        //top area HBox
        HBox top = new HBox(5);
        top.setAlignment (Pos.CENTER);
        top.setMinHeight(75);
        final ComboBox employeeComboBox = new ComboBox();
        employeeComboBox.getItems().addAll("nie","wiem","co","kurwa","Robie!");
        employeeComboBox.setMinHeight(50);
        employeeComboBox.setMinWidth(500);
        
        top.getChildren().addAll(employeeComboBox);
        
        
        
        
        //bottom area Hbox
        HBox bottom = new HBox(280);
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(25));
        
        Button exitBtn= new Button("EXIT");
        exitBtn.setPrefHeight(50);
        exitBtn.setPrefWidth(150);
        exitBtn.setOnAction(e->Platform.exit());

        Button refreshBtn = new Button ("Refresh");
        refreshBtn.setPrefHeight(50);
        refreshBtn.setPrefWidth(150);
        

        bottom.getChildren().addAll(exitBtn,refreshBtn);
        
        //right area
        VBox right = new VBox();
        right.setSpacing(10);
        right.setPadding(new Insets(15));
        
  
        currJobList.setItems(data2);
        currJobList.getColumns().addAll(currJobNameColumn, currJobLocColumn);
        right.getChildren().addAll(testLabel2,currJobList);
  
        //center
        VBox center = new VBox(25);
        center.setPrefWidth(150);
        
        Button jobListButton= new Button("Job List");
        jobListButton.setPrefHeight(75);
        jobListButton.setPrefWidth(100);
        
        
        //REMOVE JOB
        Button removeJobsButton= new Button("Remove Jobs");
        removeJobsButton.setPrefHeight(75);
        removeJobsButton.setPrefWidth(100);
        removeJobsButton.setOnAction(e -> deleteButtonClicked());
     
                
        center.setAlignment(Pos.CENTER);
        center.getChildren().addAll(jobListButton,removeJobsButton);
        
        
        
        //left area
        VBox left = new VBox();
        left.setSpacing(10);
        left.setPadding(new Insets(15));
        
        

        
        
        
        jobList.setItems(data);
        jobList.getColumns().addAll(jobNameCol, jobLocationCol);
        
        
        TextField addJobNameTextField = new TextField();
        addJobNameTextField.setPromptText("Job Name");
        addJobNameTextField.setPrefWidth(jobNameCol.getPrefWidth());
        TextField addJobLocation = new TextField();
        addJobLocation.setPromptText("Location");
        addJobLocation.setPrefWidth(jobLocationCol.getPrefWidth());
        
        
        //add job button action
        Button addJobBtn = new Button("Add");
        addJobBtn.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle (ActionEvent e){
            data.add(new JOB(addJobNameTextField.getText(),
                    addJobLocation.getText()));
            addJobNameTextField.clear();
            addJobLocation.clear();
            }
         });
        
        
        
        addHbox.getChildren().addAll(addJobNameTextField, addJobLocation,
                addJobBtn);
        addHbox.setSpacing(5);  
        
        left.getChildren().addAll(testLabel,jobList,addHbox);
        
        
        
        //Creating POP UP Window//
        stage2.setTitle("Confirm Assigment");
        
        final Label confirnNameLabel = new Label();
        final Label confirmLocationLabel = new Label();
        Button assignButton = new Button("Assign");
        
        assignButton.setOnAction(e ->{
            data2.add(new CURRENTjobs(confirnNameLabel.getText(),
                    confirmLocationLabel.getText()));
            });
        
        
        
        
        
        
        
        //set panes into border pane
        bPane.setTop(top);
        bPane.setBottom(bottom);
        bPane.setLeft(left);
        bPane.setRight(right);
        bPane.setCenter(center);
        
        
        Scene scene = new Scene(bPane, 700, 600);
        primaryStage.setTitle("u1610329");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);     
        
        
        
        
        

    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
   
     public void deleteButtonClicked(){
    
        currJobList.getItems().removeAll(currJobList.getSelectionModel()
                .getSelectedItems());
        currJobList.getSelectionModel().clearSelection();
        System.out.print(currJobList.getSelectionModel().getSelectedItems());
    }
    
    
}

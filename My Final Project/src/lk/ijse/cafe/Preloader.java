/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.cafe;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.cafe.controller.SplashScreenFormController;

public class Preloader extends javafx.application.Preloader {

    private Stage preloaderStage;
    private Scene scene;
    
    public Preloader() {
        
    }

    @Override
    public void init() throws Exception {               
                                         
    Parent root1 = FXMLLoader.load(getClass().getResource("view/SplashScreen.fxml"));
    scene = new Scene(root1); 
    //scene = new Scene(root1, 700, 400, Color.TRANSPARENT); 
    //root1.setStyle("-fx-background-color: transparent;"); 
                
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
       this.preloaderStage = primaryStage;

               
       
       // preloaderStage.initStyle(StageStyle.TRANSPARENT); 
       
        // Set preloader scene and show stage.
        preloaderStage.setScene(scene);  
        preloaderStage.initStyle(StageStyle.UNDECORATED);
        preloaderStage.show();
        
        
      
    }

    @Override
    public void handleApplicationNotification(javafx.application.Preloader.PreloaderNotification info) {
      
          if (info instanceof ProgressNotification) {
            SplashScreenFormController.label.setText("Loading "+((ProgressNotification) info).getProgress()*100 + "%");
            System.out.println("Value@ :" + ((ProgressNotification) info).getProgress());
            SplashScreenFormController.statProgressBar.setProgress(((ProgressNotification) info).getProgress());
        }

               
        
    }

    @Override
    public void handleStateChangeNotification(javafx.application.Preloader.StateChangeNotification info) {
      
        StateChangeNotification.Type type = info.getType();
        switch (type) {
            
            case BEFORE_START:
                // Called after MyApplication#init and before MyApplication#start is called.
                System.out.println("BEFORE_START");
                preloaderStage.hide();
                break;
        }
        
        
    }
}


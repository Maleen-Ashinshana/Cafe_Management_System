/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.cafe;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {
    
    private static final int COUNT_LIMIT = 10;
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("view/NewForm.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.getIcons().add(new Image("lk/ijse/cafe/access/logo/—Pngtree—vector verified cart items icon_3782890.png"));
        stage.show();

//        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/NewForm.fxml"))));
//        stage.show();
    }

    
      @Override
    public void init() throws Exception {       
        
        // Perform some heavy lifting (i.e. database start, check for application updates, etc. )
        for (int i = 1; i <= COUNT_LIMIT; i++) {
            double progress =(double) i/10;
            System.out.println("progress: " +  progress);            
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
            Thread.sleep(100);
        }
        
    }
    

    public static void main(String[] args) {        
        LauncherImpl.launchApplication(lk.ijse.cafe.AppInitializer.class, lk.ijse.cafe.Preloader.class, args);
    }
    
}

package eAsistentMini;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class Main extends Application {
    int ActiveSceene=4;
    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{

        window=primaryStage;
//        Parent login = FXMLLoader.load(getClass().getResource("login.fxml"));
        selectedStage(ActiveSceene,window);
        window.setTitle("Login");
        window.show();


    }
    public void selectedStage(int selectedSceene,Stage stage) throws IOException {
        Parent login = FXMLLoader.load(getClass().getResource("login.fxml"));
        Parent admin= FXMLLoader.load(getClass().getResource("admin.fxml"));
        Parent teacherMain= FXMLLoader.load(getClass().getResource("teacherMain.fxml"));
        Parent userMain= FXMLLoader.load(getClass().getResource("userMain.fxml"));
        if(selectedSceene==0){
            stage.setScene(new Scene(admin));
        }else
            if(selectedSceene==1){
            stage.setScene(new Scene(teacherMain));
        }else
            if(selectedSceene==2){
            stage.setScene(new Scene(userMain));
        }else{
                stage.setScene(new Scene(login));
            }

    }
    public static void main(String[] args) {
        launch(args);
    }
}



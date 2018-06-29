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
    Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage=primaryStage;
        Parent logss = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setScene(new Scene(logss));
        selectedStage(0,0);


    }
    public void selectedStage(int selectedScene,int userid) throws IOException {
        if(selectedScene==0){
            stage.show();
        }else
            if(selectedScene==1){
                Stage keks=new Stage();
                Parent teacherMain= FXMLLoader.load(getClass().getResource("teacherMain.fxml"));
                keks.setScene(new Scene(teacherMain));
                keks.show();
        }else
            if(selectedScene==2){
                Stage keks=new Stage();
                Parent userMain= FXMLLoader.load(getClass().getResource("userMain.fxml"));
                keks.setScene(new Scene(userMain));
                keks.show();
        }else
            if(selectedScene==3){
                Stage keks=new Stage();
                Parent userMain= FXMLLoader.load(getClass().getResource("admin.fxml"));
                keks.setScene(new Scene(userMain));
                keks.show();
            }

    }
    public static void main(String[] args) {
        launch(args);
    }
}



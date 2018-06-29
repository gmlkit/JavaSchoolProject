package eAsistentMini;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Main extends Application {
    int ActiveSceene=0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent login = FXMLLoader.load(getClass().getResource("login.fxml"));
        Parent admin= FXMLLoader.load(getClass().getResource("admin.fxml"));
        Parent teacherMain= FXMLLoader.load(getClass().getResource("teacherMain.fxml"));
        Parent userMain= FXMLLoader.load(getClass().getResource("userMain.fxml"));
        primaryStage.setTitle("eAsistentMini");
        primaryStage.setScene(new Scene(login, 300, 275));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}



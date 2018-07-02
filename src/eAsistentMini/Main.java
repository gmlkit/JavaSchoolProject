package eAsistentMini;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
public class Main extends Application {
    static Stage stage;
    static Stage keks;
    private static int currentUser;

    public int getCurrentUser() {
        return currentUser;
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage=primaryStage;
        selectedStage(0,0);
    }
    public void selectedStage(int selectedScene,int userid) throws IOException {
        currentUser=userid;
        if(selectedScene==0){
            Parent logss = FXMLLoader.load(getClass().getResource("FxmlClasses/login.fxml"));
            stage.setScene(new Scene(logss));
            stage.show();
        }else
            if(selectedScene==1){
            keks=new Stage();
                Parent teacherMain= FXMLLoader.load(getClass().getResource("FxmlClasses/teacherMain.fxml"));
                keks.setScene(new Scene(teacherMain));
                keks.show();
                keks.setTitle("Teaches panel");
                System.out.println("Main Select Scene"+userid);
                stage.hide();
                keks.setOnCloseRequest((WindowEvent e)->{
                    this.stage.show();
                });
        }else
            if(selectedScene==2){
                keks=new Stage();
                Parent userMain= FXMLLoader.load(getClass().getResource("FxmlClasses/userMain.fxml"));
                keks.setScene(new Scene(userMain));
                keks.show();
                keks.setTitle("ParentOb panel");
                System.out.println("Main Select Scene"+userid);
                stage.hide();

                keks.setOnCloseRequest((WindowEvent e)->{
                    this.stage.show();
                });
        }else
            if(selectedScene==3){
                keks=new Stage();
                Parent userMain= FXMLLoader.load(getClass().getResource("FxmlClasses/admin.fxml"));
                keks.setScene(new Scene(userMain));
                keks.show();
                keks.setTitle("Admin panel");
                System.out.println("To je admin id:"+userid);
                stage.hide();

                keks.setOnCloseRequest((WindowEvent e)->{
                    this.stage.show();
                });
            }

    }
    public static void main(String[] args) {
        launch(args);
    }
}
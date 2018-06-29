package eAsistentMini;

import eAsistentMini.Logic.AlertBox;
import eAsistentMini.Logic.DatabaseWork;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    public Button logMeIn;
    public PasswordField passwordField;
    public TextField userField;
    public void handleButtonClick1(){
        int[] logCheck=new int[2];
        logCheck[0]=0;
        if(passwordField.getText().length()==0||userField.getText().length()==0)
        {
            AlertBox.display("eAsistentMini Error","Fill up the damn fields!!!");
        }
        else{
            Main main=new Main();
            try{
            DatabaseWork sac=new DatabaseWork();
            logCheck=sac.LogIn(userField.getText(),passwordField.getText());
                System.out.println(logCheck[0]);
            if(logCheck[0]==5){
                AlertBox.display("Success","ADMIN");
                main.selectedStage(0,logCheck[1]);

            }else if(logCheck[0]==1){
//                AlertBox.display("Success","Hello Mušić!");
                main.selectedStage(1,logCheck[1]);
                System.out.println(logCheck[0]+","+logCheck[1]);

            }else if(logCheck[0]==0){
//                AlertBox.display("Success","Parent welcome to my app!");
                main.selectedStage(2,logCheck[1]);

            }else{
                AlertBox.display("eAsistentMini Error","Username or Password incorrect");
            }

        }catch (Exception e){
            System.out.println(e);
        }
        }
    }
}

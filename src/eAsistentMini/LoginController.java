package eAsistentMini;

import eAsistentMini.Logic.AlertBox;
import eAsistentMini.Logic.DatabaseWork;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    public Button logMeIn;
    public Button registerConn;
    public PasswordField passwordField;
    public TextField userField;
    public void handleButtonClick1(){
        int logCheck;
        if(passwordField.getText().length()==0||userField.getText().length()==0)
        {
            AlertBox.display("eAsistentMini Error","Fill up the damn fields!!!");
        }
        else{
            try {
            DatabaseWork sac=new DatabaseWork();
            logCheck=sac.LogIn(userField.getText(),passwordField.getText());
                System.out.println(logCheck);
            if(logCheck==0){
                AlertBox.display("Success","Parent Node");
            }else if(logCheck==1){
                AlertBox.display("Success","Hello Mušić");

            }else{
                AlertBox.display("eAsistentMini Error","Username or Password incorrect");
            }

        }catch (Exception e){
            System.out.println(e);
        }
        }
    }
    public void handleButtonClick2(){

    }
}

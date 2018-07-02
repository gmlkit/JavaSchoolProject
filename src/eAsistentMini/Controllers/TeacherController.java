package eAsistentMini.Controllers;

import eAsistentMini.Logic.AlertBox;
import eAsistentMini.Logic.DatabaseWork;
import eAsistentMini.Main;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TeacherController implements Initializable {
    public TextField gradeField;
    public Button addGradeButton;
    public ComboBox studentsCombo;
    public ComboBox classCombo;
    static private String ids;
    static private int userId=new Main().getCurrentUser();
    DatabaseWork dbw=new DatabaseWork();
    public void addButtonPressed(ActionEvent actionEvent) {

        int errors=3;
        if(studentsCombo.getSelectionModel().isEmpty()&&classCombo.getSelectionModel().isEmpty()&&gradeField.getText().length()!=0)
        {
            AlertBox.display("Fill error","Fill those fields!");
        }else if(studentsCombo.getSelectionModel().isEmpty()){
            AlertBox.display("Fill error","ObjectSet not selected!");
        }else{
            int i=-1;
            try {
                i=Integer.parseInt(gradeField.getText());
                System.out.println("I loves coke:"+i);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            if (i>0){
            try {
                System.out.println("To je moj index: "+studentsCombo.getSelectionModel().getSelectedIndex());
                System.out.println(Integer.parseInt(ids));
                errors=dbw.addGrade(i,"",Integer.parseInt(ids),3);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            if(errors==0){
               AlertBox.display("Success","Successfully added new grade!");

           }else if(errors==3){
               AlertBox.display("Error","I crashed sorry!");
           }
           }else{
                AlertBox.display("Error","Wrong input format!");
            }
        }

    }
    public void initialize(URL location, ResourceBundle resources)
    {
        System.out.println("To je id:"+userId);
        String strClass="SELECT DISTINCT pr.id AS \"first\", pr.ime AS \"seccond\"\n" +
                "\tFROM ucitelji u \n" +
                "\tINNER JOIN ucitelji_predmeti up ON up.ucitelj_id=u.id \n" +
                "\tINNER JOIN predmeti pr ON pr.id=up.predmet_id \n" +
                "\tWHERE u.id =?";

        ObservableList<String> classes=dbw.getClassesArray(userId,strClass);
        classCombo.setItems(classes);
        classCombo.getSelectionModel().selectedItemProperty().addListener((options,oldValue,newValue)->{
            ids=classCombo.getSelectionModel().getSelectedItem().toString();
            ids= ids.substring(0, ids.indexOf(" "));
            System.out.println("Jaz te mam ful rad"+ids);
            try {
                ObservableList<String> students=dbw.getStudentArray(userId,Integer.parseInt(ids));
                studentsCombo.setItems(students);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }
}

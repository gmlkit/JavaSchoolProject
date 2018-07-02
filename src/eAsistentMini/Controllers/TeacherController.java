package eAsistentMini.Controllers;

import eAsistentMini.Logic.AlertBox;
import eAsistentMini.Logic.DatabaseWork;
import eAsistentMini.Logic.Objects.Student;
import eAsistentMini.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.input.ContextMenuEvent;

import javax.security.auth.callback.Callback;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class TeacherController implements Initializable {
    public TextField gradeField;
    public Button addGradeButton;
    public ComboBox studentsCombo;
    public ComboBox classCombo;
    private String ids;
    private int ucenecId=new Main().getCurrentUser();
    DatabaseWork dbw=new DatabaseWork();
    public void addButtonPressed(ActionEvent actionEvent) {

        if(studentsCombo.getSelectionModel().isEmpty()&&classCombo.getSelectionModel().isEmpty())
        {
            AlertBox.display("Fill error","Fill those fields!");
        }else if(studentsCombo.getSelectionModel().isEmpty()){
            AlertBox.display("Fill error","Student not selected!");
        }else{
            dbw.addGrade();
        }

    }
    public void initialize(URL location, ResourceBundle resources)
    {
        System.out.println("To je id:"+ucenecId);
        String strClass="SELECT DISTINCT pr.id AS \"first\", pr.ime AS \"seccond\"\n" +
                "\tFROM ucitelji u \n" +
                "\tINNER JOIN ucitelji_predmeti up ON up.ucitelj_id=u.id \n" +
                "\tINNER JOIN predmeti pr ON pr.id=up.predmet_id \n" +
                "\tWHERE u.id =?";

        ObservableList<String> classes=dbw.getObjects(ucenecId,strClass);
        classCombo.setItems(classes);
        classCombo.getSelectionModel().selectedItemProperty().addListener((options,oldValue,newValue)->{
//            String strStudent="SELECT DISTINCT u.*FROM ucenci u \n" +
//                    "            INNER JOIN ucenci_predmeti u_p ON u_p.ucenec_id=u.id\n" +
//                    "            INNER JOIN predmeti pr ON pr.id=u_p.predmet_id\n" +
//                    "            INNER JOIN ucitelji_predmeti up ON up.predmet_id=pr.id\n" +
//                    "            INNER JOIN ucitelji uc ON uc.id=up.ucitelj_id\n" +
//                    "            WHERE(uc.id=? AND pr=?)";
            ids=classCombo.getSelectionModel().getSelectedItem().toString();
            ids= ids.substring(0, ids.indexOf(" "));
            System.out.println("Jaz te mam ful rad"+ids);
            try {
                ObservableList<String> students=dbw.getStudent(ucenecId,Integer.parseInt(ids));
                studentsCombo.setItems(students);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }



    public void initStudents(ContextMenuEvent contextMenuEvent) {


    }
}

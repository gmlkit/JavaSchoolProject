package eAsistentMini;

import eAsistentMini.Logic.DatabaseWork;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherController implements Initializable {
    public TextField gradeField;
    public Button addGradeButton;
    public ComboBox studentsCombo
    public ComboBox classCombo;

    DatabaseWork dbw=new DatabaseWork();
    public void addButtonPressed(ActionEvent actionEvent) {

    }
    public void initialize(URL location, ResourceBundle resources)
    {
        String[][] st=dbw.getStudents("test");
        System.out.println("wtf: "+st[0][0]);
        for (int i=0;st.length<i;i++){
            studentsCombo
        }
    }
}

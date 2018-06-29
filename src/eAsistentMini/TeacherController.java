package eAsistentMini;

import eAsistentMini.Logic.DatabaseWork;
import eAsistentMini.Logic.Student;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TeacherController implements Initializable {
    public TextField gradeField;
    public Button addGradeButton;
    public ComboBox studentsCombo;
    public ComboBox classCombo;

    DatabaseWork dbw=new DatabaseWork();
    public void addButtonPressed(ActionEvent actionEvent) {

    }
    public void initialize(URL location, ResourceBundle resources)
    {
        ArrayList<Student> st=dbw.getStudents("test");
        System.out.println("wtf: "+st.contains(toString()));
    }
}

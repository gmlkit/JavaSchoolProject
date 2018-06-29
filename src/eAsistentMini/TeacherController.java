package eAsistentMini;

import eAsistentMini.Logic.DatabaseWork;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TeacherController implements Initializable {
    public TextField gradeField;
    public Button addGradeButton;
    public ComboBox studentsCombo;
    public ComboBox classCombo;


    DatabaseWork dbw=new DatabaseWork();
    ComboBox<Student> comboBox=dbw.getStudents("test")
    public void addButtonPressed(ActionEvent actionEvent) {

    }
    public void initialize(URL location, ResourceBundle resources)
    {
        ArrayList<Student> st=dbw.getStudents("test");
        System.out.println("wtf: "+st.toString());
    }

}public class Student {
    private String name;
    private int id;
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }
    @Override
    public String toString(eAsistentMini.Logic.Student object) {
        return object.getName();
    }
    public eAsistentMini.Logic.Student fromString(String string) {
        return null;
    }
    public int getId(eAsistentMini.Logic.Student object){
        return object.getId();
    }
}

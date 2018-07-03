package eAsistentMini.Controllers;

import eAsistentMini.Logic.AdminLogic;
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

public class AdminController implements Initializable {
    public TextField teacherEmail;
    public TextField teacherPassword1;
    public TextField teacherPassword2;
    public ComboBox classBoxTeacher;
    public TextField className;
    public Button addClassButton;
    public TextField nameStudent;
    public TextField sNameSt;
    public ComboBox parrentSelect;
    public Button addStBtn;
    public ComboBox selectClassBoxStClass;
    public ComboBox selectStBoxStClass;
    private AdminLogic adminLogic=new AdminLogic();
    private DatabaseWork dbw=new DatabaseWork();
    private String ids;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String strClass="SELECT id FROM predmeti;";

        ObservableList<String> classes=dbw.getClasses();
        classBoxTeacher.setItems(classes);
        classBoxTeacher.getSelectionModel().selectedItemProperty().addListener((options,oldValue,newValue)->{
            ids=classBoxTeacher.getSelectionModel().getSelectedItem().toString();
            ids= ids.substring(0, ids.indexOf(" "));
            System.out.println("Jaz te mam ful rad"+ids);
            try {
                ObservableList<String> students=dbw.getStudents(Integer.parseInt(ids));
                selectStBoxStClass.setItems(students);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void lukeIAmYourFateher(ActionEvent actionEvent) {
    }

    public void killChildInYou(ActionEvent actionEvent) {
    }

    public void createClass(ActionEvent actionEvent) {
    }

    public void addHarambe(ActionEvent actionEvent) {
    }

    public void addParrentYouPsycho(ActionEvent actionEvent) {
    }
}

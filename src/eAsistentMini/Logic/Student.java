package eAsistentMini.Logic;
import java.util.ResourceBundle;
public class Student {
    private String name
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
    public String toString(Student object) {
        return object.getName();
    }
    public Student fromString(String string) {
        return null;
    }
    public int getId(Student object){
        return object.getId();
    }
}

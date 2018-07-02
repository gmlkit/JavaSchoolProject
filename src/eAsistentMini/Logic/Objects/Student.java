package eAsistentMini.Logic.Objects;

public class Student {
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

package eAsistentMini.Logic.Objects;

public class Classes {
    private String name;
    private int id;
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Classes(String name, int id) {
        this.name = name;
        this.id = id;
    }
    public String toString(Classes object) {
        return object.getName();
    }
    public Classes fromString(String string) {
        return null;
    }
    public int getId(Classes object){
        return object.getId();
    }
}
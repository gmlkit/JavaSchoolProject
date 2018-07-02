package eAsistentMini.Logic.Objects;

public class ParentOb {
    private String name;
    private int id;
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public ParentOb(String name, int id) {
        this.name = name;
        this.id = id;
    }
    public String toString(ParentOb object) {
        return object.getName();
    }
    public ParentOb fromString(String string) {
        return null;
    }
    public int getId(ParentOb object){
        return object.getId();
    }
}

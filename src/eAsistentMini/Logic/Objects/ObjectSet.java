package eAsistentMini.Logic.Objects;

public class ObjectSet {
    private String name;
    private int id;
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public ObjectSet(String name, int id) {
        this.name = name;
        this.id = id;
    }
    public String toString(ObjectSet object) {
        return object.getName();
    }
    public ObjectSet fromString(String string) {
        return null;
    }
    public int getId(ObjectSet object){
        return object.getId();
    }
}

package Domain;

public class IngredientsName {
    private int id;
    private String name;

    public IngredientsName() {
    }

    public IngredientsName(String name) {
        this.name = name;
    }

    public IngredientsName(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

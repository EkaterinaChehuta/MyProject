package Domain;

public class Recipe {
    private int id;
    private String name;
    // картинка
    private IngredientsName ingredientsName;
    private String preparation;


    public Recipe() {
    }

    public Recipe(int id, String name, IngredientsName ingredientsName, String preparation) {
        this.id = id;
        this.name = name;
        this.ingredientsName = ingredientsName;
        this.preparation = preparation;
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

    public IngredientsName getIngredientsName() {
        return ingredientsName;
    }

    public void setIngredientsName(IngredientsName ingredientsName) {
        this.ingredientsName = ingredientsName;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }
}

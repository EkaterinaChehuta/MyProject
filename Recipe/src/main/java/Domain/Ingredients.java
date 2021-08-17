package Domain;

public class Ingredients {
    private int id;
    private Product product;
    private int quantity;
    private IngredientsName ingredientsName;

    public Ingredients() {
    }

    public Ingredients(int id, Product product, int quantity, IngredientsName ingredientsName) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.ingredientsName = ingredientsName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public IngredientsName getIngredientsName() {
        return ingredientsName;
    }

    public void setIngredientsName(IngredientsName ingredientsName) {
        this.ingredientsName = ingredientsName;
    }
}

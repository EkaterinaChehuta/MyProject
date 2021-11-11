package Domain;

public class Product {
    private int id;
    private String name;
    private Indicator indicator;
    private ProductCategory productCategory;

    public Product() {
    }

    public Product(String name, Indicator indicator, ProductCategory productCategory) {
        this.name = name;
        this.indicator = indicator;
        this.productCategory = productCategory;
    }

    public Product(int id, String name, Indicator indicator, ProductCategory productCategory) {
        this.id = id;
        this.name = name;
        this.indicator = indicator;
        this.productCategory = productCategory;
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

    public Indicator getIndicator() {
        return indicator;
    }

    public void setIndicator(Indicator indicator) {
        this.indicator = indicator;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public String toString() {
        return  "\"id\": " + id +
                ", \"name\": \"" + name + "\"" +
                ", \"indicator\": \"" + indicator + "\"";
    }
}

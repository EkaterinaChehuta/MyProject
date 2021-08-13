package Domain;

public class Product {
    private int id;
    private String name;
    private Indicator indicator;

    public Product() {
    }

    public Product(String name, Indicator indicator) {
        this.name = name;
        this.indicator = indicator;
    }

    public Product(int id, String name, Indicator indicator) {
        this.id = id;
        this.name = name;
        this.indicator = indicator;
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", indicator='" + indicator + '\'' +
                '}';
    }
}

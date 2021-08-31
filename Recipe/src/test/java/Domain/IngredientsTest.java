package Domain;

public class IngredientsTest {
    private static final int TEST_ID = 1000;
    private static final ProductTest TEST_PRODUCT = new ProductTest();
    private static final int TEST_QUANTITY = 10;
    private static final IngredientsNameTest TEST_INGREDIENTS_NAME = new IngredientsNameTest();

    public static int getTestId() {
        return TEST_ID;
    }

    public static ProductTest getTestProduct() {
        return TEST_PRODUCT;
    }

    public static int getTestQuantity() {
        return TEST_QUANTITY;
    }

    public static IngredientsNameTest getTestIngredientsName() {
        return TEST_INGREDIENTS_NAME;
    }
}
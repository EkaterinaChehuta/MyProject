package Domain;

public class RecipeTest {
    private static final int TEST_ID = 1000;
    private static final String TEST_NAME = "test_name_recipe";
    // картинка
    private static final IngredientsNameTest TEST_INGREDIENTS_NAME = new IngredientsNameTest();
    private static final String TEST_PREPARATION = "test_preparation";

    public static int getTestId() {
        return TEST_ID;
    }

    public static String getTestName() {
        return TEST_NAME;
    }

    public static IngredientsNameTest getTestIngredientsName() {
        return TEST_INGREDIENTS_NAME;
    }

    public static String getTestPreparation() {
        return TEST_PREPARATION;
    }
}
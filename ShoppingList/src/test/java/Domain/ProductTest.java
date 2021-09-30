package Domain;

public class ProductTest {
    private static final int TEST_ID = 1000;
    private static final String TEST_NAME = "test_name_product";
    private static final IndicatorTest TEST_INDICATOR = new IndicatorTest();

    public static int getTestId() {
        return TEST_ID;
    }

    public static String getTestName() {
        return TEST_NAME;
    }

    public static IndicatorTest getTestIndicator() {
        return TEST_INDICATOR;
    }
}
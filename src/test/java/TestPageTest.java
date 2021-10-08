import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;


public class TestPageTest {

    private static final String CHROME_DRIVER_PATH = "chromedriver.exe";
    /**
     * Путь до тестовой странницы
      */
    private static final String TEST_HTML_URL = "file:\\\\\\"
            + Paths.get("").toAbsolutePath().toString()
            + "\\test.html";

    private static WebDriver driver;
    private static TestPage testPage;
    private final String TITLE = "Test";

    /**
     * Данные для теста селектора тем
     */
    @TestFactory
    public static Object[][] dataForThemesChoice(){
        return new Object[][]{
            {"Red", "red"},
            {"Blue", "blue"},
            {"Yellow", "yellow"},
            {"Green", "green"},
            {"Brown", "brown"}
        };
    }

    /**
     * Данные для теста селектора рекоммендаций
     */
    @TestFactory
    public static Object[][] dataForRecommendationsChoice(){
        return new Object[][]{
                {"dashboarding_content", "With our dashboarding tool you can generate data" +
                        " from all your machines and systems and visualize it in a uniform overview."},
                {"automation_content", "In digital tests, processes are run through virtually" +
                        " and thus conclusions for the production process are obtained."},
                {"optimization_content", "Analyze the technical and organizational availability" +
                        " of your machines in order to optimally design manufacturing and production processes."}
        };
    }


    @BeforeAll
    public static void init(){
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.get(TEST_HTML_URL);
        testPage = new TestPage(driver);
    }

    /**
     * Проверка заголовка страницы
     */
    @Test
    @Description("Проверка заголовка страницы")
    public void checkTitle(){
        assertEquals(TITLE, driver.getTitle(), "Wrong page!");
    }

    /**
     * Тест селектора темы
     */
    @ParameterizedTest
    @MethodSource("dataForThemesChoice")
    @Description("Тест селектора темы")
    public void checkTheThemesSelector(String themesSelector, String expectedColor){
        testPage.choiceTheme(themesSelector);
        testPage.waitThemesChange(expectedColor);
    }

    /**
     *  Тест селектора рекомендаций
     */
    @ParameterizedTest
    @MethodSource("dataForRecommendationsChoice")
    @Description("Тест селектора рекомендаций")
    public void checkTheRecommendationsSelector(String recommendationSelector, String expectedText){
        testPage.choiceRecommendation(recommendationSelector);
        testPage.waitRecommendationsChange(expectedText);
    }

    /**
     * Проверка лайка
     */
    @Test
    @Description("Проверка лайка")
    public void checkThumbUp(){
        assertTrue(testPage.getThumbUpText().contains("Like us?"), "Thumb up tab not existed");
        testPage.clickThumbUp();
        testPage.waitThumbUpApp();
    }


    @AfterAll
    public static void close() {
        driver.quit();
    }

}

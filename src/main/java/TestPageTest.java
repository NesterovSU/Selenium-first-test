import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**The functional test suite for test.html page
 * @author Sergey Nesterov
 */
public class TestPageTest {

    /**
     * Change the path to chromedriver as you have!
      */
    private final String CHROME_DRIVER_PATH = "C:\\Users\\Admin\\Downloads\\chromedriver.exe";
    /**
     * Change the test.html URL as you have!
     */
    private final String TEST_HTML_URL = "file:///C:/Users/Admin/IdeaProjects/myTest41/test.html";
    /**
     * WebDriver declaration
     */
    private WebDriver driver;
    /**
     * Page object declaration (test.html)
     */
    private TestPage testPage;
    /**
     * Expected page title
     */
    private final String TITLE = "Test";

    /**
     * Parameters set for themes selector test
     * @return array of pairs text parameter for xpath locator and expected content background color
     */
    @DataProvider
    public Object[][] dataForThemesChoice(){
        return new Object[][]{
            {"Red", "red"},
            {"Blue", "blue"},
            {"Yellow", "yellow"},
            {"Green", "green"},
            {"Brown", "brown"}
        };
    }

    /**
     * Parameters set for recommendations selector test
     * @return array of pairs value parameter for xpath locator and expected recommendation text
     */
    @DataProvider
    public Object[][] dataForRecommendationsChoice(){
        return new Object[][]{
                {"dashboarding_content", "With our dashboarding tool you can generate data" +
                        " from all your machines and systems and visualize it in a uniform overview."},
                {"automation_content", "In digital tests, processes are run through virtually" +
                        " and thus conclusions for the production process are obtained."},
                {"optimization_content", "Analyze the technical and organizational availability" +
                        " of your machines in order to optimally design manufacturing and production processes."}
        };
    }

    /**
     * Initialization WebDriver
     */
    @BeforeTest
    public void init(){
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.get(TEST_HTML_URL);
        testPage = new TestPage(driver);
    }

    /**
     * Check the page title
     */
    @Test
    public void checkTitle(){
        Assert.assertEquals(TITLE, driver.getTitle());
    }

    /**
     * Check themes selector
     * @param themesSelector text parameter for xpath locator
     * @param expectedColor expected content background color
     */
    @Test(dataProvider = "dataForThemesChoice")
    public void checkTheThemesSelector(String themesSelector, String expectedColor){
        testPage.choiceTheme(themesSelector);
        testPage.waitThemesChange(expectedColor);
    }

    /**
     * Check recommendations selector
     * @param recommendationSelector value parameter for xpath locator
     * @param expectedText expected recommendation text
     */
    @Test(dataProvider = "dataForRecommendationsChoice")
    public void checkTheRecommendationsSelector(String recommendationSelector, String expectedText){
        testPage.choiceRecommendation(recommendationSelector);
        testPage.waitRecommendationsChange(expectedText);
    }

    /**
     * Check thumb up tab
     */
    @Test
    public void checkThumbUp(){
        Assert.assertTrue(testPage.getThumbUpText().contains("Like us?"));
        testPage.clickThumbUp();
        testPage.waitThumbUp();
    }

    /**
     * Close the browser and exit the chromedriver
     */
    @AfterTest
    public void close() {
        driver.quit();
    }

}

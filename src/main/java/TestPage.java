import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**The page object from test.html page
 * @author Sergey Nesterov
 */
public class TestPage {
    /**
     * WebDriver variable declaration
     */
    private final WebDriver driver;
    /**
     * Initialization often used locators
     */
    private final By CONTENT_BODY = By.xpath("//div[@id='content']");
    private final By THUMB_UP_TAB = By.xpath("//b[@id='thumbText']");

    /**
     * Constructor
     * @param driver WebDriver reference
     */
    public TestPage(WebDriver driver){
        this.driver=driver;
    }

    /**
     * Clicked on color theme selector in navbar
     * @param color parameter for xpath locator
     */
    public void choiceTheme(String color){
        By themeSelector = By.xpath("//u[text()='Change theme:']/..//a[text()='" + color + "']");
        driver.findElement(themeSelector).click();
    }

    /**
     * Waiting theme changing to expected
     * @param expectedColor  color expected in content body
     * @exception org.openqa.selenium.TimeoutException 10 seconds later if element not find
     */
    public void waitThemesChange(String expectedColor){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.attributeContains(CONTENT_BODY, "style", "background-color: " + expectedColor));
    }

    /**
     * Click on recommendations radiobutton
     * @param value parameter for xpath locator
     */
    public void choiceRecommendation(String value) {
        By recommendationSelector = By.xpath("//input[@type='radio' and @value = '" + value + "']");
        driver.findElement(recommendationSelector).click();
    }

    /**
     * Waiting theme changing to expected
     * @param expectedText text expected in content body
     * @exception org.openqa.selenium.TimeoutException 10 seconds later if element not find
     */
    public void waitRecommendationsChange(String expectedText){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.textToBePresentInElementLocated(CONTENT_BODY,expectedText));
    }

    /**
     *
     * @return text from thumb up tab
     */
    public String getThumbUpText(){
        return driver.findElement(THUMB_UP_TAB).getText();
    }

    /**
     * Click thumb up tab
     */
    public void clickThumbUp() {
        driver.findElement(THUMB_UP_TAB).click();
    }

    /**
     *Waiting thumb up tab changing
     * @exception org.openqa.selenium.TimeoutException 10 seconds later if element not find
     */
    public void waitThumbUp() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//i[@class='fa thumb fa-thumbs-up']")));
    }
}

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestPage {

    private final WebDriver driver;
    private final By CONTENT_BODY = By.xpath("//div[@id='content']");
    private final By THUMB_UP_TAB = By.xpath("//b[@id='thumbText']");

    public TestPage(WebDriver driver){
        this.driver=driver;
    }

    /**
     * Кликаем на селектор цвета темы
     */
    public void choiceTheme(String color){
        By themeSelector = By.xpath("//u[text()='Change theme:']/..//a[text()='" + color + "']");
        driver.findElement(themeSelector).click();
    }

    /**
     * Ожидаем изменения цвета темы
     */
    public void waitThemesChange(String expectedColor){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.attributeContains(CONTENT_BODY, "style", "background-color: " + expectedColor));
    }

    /**
     * Кликаем на радио кнопку рекомендации
     */
    public void choiceRecommendation(String value) {
        By recommendationSelector = By.xpath("//input[@type='radio' and @value = '" + value + "']");
        driver.findElement(recommendationSelector).click();
    }

    /**
     * Ожидаем появление текста рекомендации в блоке context
     */
    public void waitRecommendationsChange(String expectedText){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.textToBePresentInElementLocated(CONTENT_BODY,expectedText));
    }

    /**
     * Возвращаем текст из ярлыка лайка (ожидаем: Like us?)
     */
    public String getThumbUpText(){
        return driver.findElement(THUMB_UP_TAB).getText();
    }

    /**
     * Кликаем на ярлык лайка
     */
    public void clickThumbUp() {
        driver.findElement(THUMB_UP_TAB).click();
    }

    /**
     * Ожидаем применения лайка
     */
    public void waitThumbUpApp() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//i[@class='fa thumb fa-thumbs-up']")));
    }
}

package support;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;

public class WebDriverHelper {

    public static Wait<WebDriver> wait;
    private final WebDriver driver;

    public WebDriverHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilVisible(WebElement element, Integer timeout, Integer pollingTime) {
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
                .ignoring(ElementNotVisibleException.class).ignoring(TimeoutException.class);

        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitAttributeNotEmpty(WebElement element, String attribute, Integer timeout, Integer pollingTime) {
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
                .ignoring(ElementNotVisibleException.class).ignoring(TimeoutException.class);

        wait.until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
    }

    public void waitUntilVisibilityOfAllElements(List<WebElement> element, Integer timeout, Integer pollingTime) {
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
                .ignoring(ElementNotVisibleException.class);

        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public void waitUntilInvisibilityOfAllElements(List<WebElement> element, Integer timeout, Integer pollingTime) {
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
                .ignoring(ElementNotVisibleException.class);

        wait.until(ExpectedConditions.invisibilityOfAllElements(element));
    }

    public void waitUntilAttributeContains(WebElement element, String attribute, String value, Integer timeout, Integer pollingTime) {
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
                .ignoring(ElementNotVisibleException.class);

        wait.until(ExpectedConditions.attributeContains(element, attribute, value));
    }

    public void waitUntilTextToBePresentInElement(WebElement element, Integer timeout, Integer pollingTime, String text) {
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
                .ignoring(ElementNotVisibleException.class);

        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }
}

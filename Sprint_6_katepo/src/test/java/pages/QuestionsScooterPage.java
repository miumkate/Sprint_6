package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.util.List;


public class QuestionsScooterPage {
    private WebElement element;
    private WebDriver  driver;
    private final By accordionItemQuestions = By.className("accordion__heading");
    private final By accordionFAQ = By.xpath("//div[@class='Home_FAQ__3uVm4']");
    private final By accordionItemAnswers = By.xpath("//div[@class='accordion__panel']/p");

    public QuestionsScooterPage(WebDriver driver){
        this.driver = driver;
    }

    public String[] getAnswer(int numberQuestion){
        element = driver.findElement(accordionFAQ);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

        List<WebElement> elementsQuestions = driver.findElements(accordionItemQuestions);
        elementsQuestions.get(numberQuestion).click();
        List<WebElement> elementsAnswers = driver.findElements(accordionItemAnswers);
        elementsAnswers.get(numberQuestion).click();

        String[] result = {elementsQuestions.get(numberQuestion).getText(), elementsAnswers.get(numberQuestion).getText()};
        return result;
    }
}

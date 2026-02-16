package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Objects;

public class MainScooterPage {

    private WebDriver driver;
    private final By buttonTop = By.xpath("//button[@class='Button_Button__ra12g']");
    private final By buttonBottom =  By.className("Home_FinishButton__1_cWm");
    private final By headerMain = By.xpath("//div[@class='Home_Header__iJKdX' and contains(text(),'Самокат')]");


    public MainScooterPage(WebDriver driver){
        this.driver = driver;
    }

    public String getHeaderOrder() {
        WebElement element = driver.findElement(headerMain);
        return element.getText();
    }

    public void clickButtonOrder(String whereButton){
        if (Objects.equals(whereButton, "top")){
            clickButtonTop();
        }else if(Objects.equals(whereButton, "bottom")){
            clickButtonBottom();
        }
    }


    public void clickButtonTop(){
        WebElement element = driver.findElement(buttonTop);
        element.click();
    }

    public void clickButtonBottom(){
        WebElement element = driver.findElement(buttonBottom);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }
}

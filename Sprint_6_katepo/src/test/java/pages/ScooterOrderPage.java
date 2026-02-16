package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ScooterOrderPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private WebElement element;

    //Первый экран
    private final By userName = By.xpath("//input[contains(@placeholder,'Имя')]");
    private final By userSurname = By.xpath("//input[contains(@placeholder,'Фамилия')]");
    private final By userAddress = By.xpath("//input[contains(@placeholder,'Адрес')]");
    private final By metroStation = By.xpath("//input[contains(@placeholder,'Станция метро')]");
    private final By userPhone = By.xpath("//input[contains(@placeholder,'Телефон')]");
    private final By buttonNext = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее']");
    private final By headerOrder = By.xpath("//div[@class='Order_Header__BZXOb']");
    private final By datePicker = By.xpath("//input[contains(@placeholder, 'Когда привезти самокат')]");

    // Второй экран
    private final By nextMonth = By.xpath("//button[@aria-label='Next Month']");
    private final By rentalPeriodElement = By.className("Dropdown-control");
    private final By numberOfDaysElement = By.xpath("//div[@class='Dropdown-option']");
    private final By commentForCourier = By.xpath("//input[contains(@placeholder,'Комментарий для курьера')]");
    private final By buttonOrder = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    // Третий экран
    private final By wantOrder = By.xpath("//div[@class='Order_ModalHeader__3FDaJ' and text()='Хотите оформить заказ?']");
    private final By buttonYesOrder = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");

    // Четверный экран
    private final By orderDoneHeader = By.xpath("//div[@class='Order_ModalHeader__3FDaJ']");

    public ScooterOrderPage(WebDriver driver){
        this.driver = driver;
    }

    public String getHeaderOrder() {
        element = driver.findElement(headerOrder);
        return element.getText();
    }

    public void setUserName(String name){
        element = driver.findElement(userName);
        element.sendKeys(name);
    }

    public void  setUserSurname(String surname){
        element = driver.findElement(userSurname);
        element.sendKeys(surname);
    }

    public void setUserAddress(String address){
        element = driver.findElement(userAddress);
        element.sendKeys(address);
    }

    public void setMetroStation(String metro){
        element = driver.findElement(metroStation);
        element.sendKeys(metro);
        element.sendKeys(Keys.ARROW_DOWN);
        element.sendKeys(Keys.ENTER);
    }

    public void setUserPhone(String phone){
        element = driver.findElement(userPhone);
        element.sendKeys(phone);
    }

    public void setUserProfileForOrder(String name, String surname, String address, String metro, String phone) {
        setUserName(name);
        setUserSurname(surname);
        setUserAddress(address);
        setMetroStation(metro);
        setUserPhone(phone);
    }

    public void clickButtonNext(){
        element = driver.findElement(buttonNext);
        element.click();
    }

    public void setDate(int day){
        By dayOfMonth = By.xpath(String.format("//div[contains(@class, 'react-datepicker__day react-datepicker__day--') and text()='%d']", day));
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(datePicker));
        element.click();

        if (day > 28){
            element = driver.findElement(nextMonth);
            element.click();
        }

        element = driver.findElement(dayOfMonth);
        element.click();
    }

    public void setInterval(String interval){
        element = driver.findElement(rentalPeriodElement);
        element.click();
        List<WebElement> elements = driver.findElements(numberOfDaysElement);


        for (WebElement elementz : elements) {
            if(elementz.getText().equals(interval)){
                elementz.click();
                break;
            }
        }
    }

    public void setColorScooter(String color){
        By colorScooter = By.xpath(String.format("//input[@id='%s']", color));
        element = driver.findElement(colorScooter);
        element.click();

    }

    public void setComment(String comment){
        element = driver.findElement(commentForCourier);
        element.sendKeys(comment);
    }

    public void setAboutRentForOrder(int day, String interval, String color, String comment) {
        setDate(day);
        setInterval(interval);
        setColorScooter(color);
        setComment(comment);
    }

    public void clickButtonOrder(){
        element = driver.findElement(buttonOrder);
        element.click();
    }

    public void clickYesOrder(){
        element = driver.findElement(buttonYesOrder);
        element.click();
    }

    public String  checkWantOrder(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(wantOrder));
        return element.getText();
    }

    public String getHeaderOrderInfo(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(orderDoneHeader));
        return element.getText();
    }
}

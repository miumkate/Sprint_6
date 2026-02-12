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
    private By userName = By.xpath("//input[contains(@placeholder,'Имя')]");
    private By userSurname = By.xpath("//input[contains(@placeholder,'Фамилия')]");
    private By userAddress = By.xpath("//input[contains(@placeholder,'Адрес')]");
    private By metroStation = By.xpath("//input[contains(@placeholder,'Станция метро')]");
    private By userPhone = By.xpath("//input[contains(@placeholder,'Телефон')]");
    private By buttonNext = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее']");
    private By headerOrder = By.xpath("//div[@class='Order_Header__BZXOb']");
    private By datePicker = By.xpath("//input[contains(@placeholder, 'Когда привезти самокат')]");

    // Второй экран
    private By nextMonth = By.xpath("//button[@aria-label='Next Month']");
    private By rentalPeriodElement = By.className("Dropdown-control");
    private By numberOfDaysElement = By.xpath("//div[@class='Dropdown-option']");
    private By commentForCourier = By.xpath("//input[contains(@placeholder,'Комментарий для курьера')]");
    private By buttonOrder = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    // Третий экран
    private By wantOrder = By.xpath("//div[@class='Order_ModalHeader__3FDaJ' and text()='Хотите оформить заказ?']");
    private By buttonYesOrder = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    private By buttonNoOrder = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i' and text()='Нет']");

    // Четверный экран
    private By orderDoneInfo = By.xpath("//div[@class='Order_Text__2broi']");//
    private By orderDoneHeader = By.xpath("//div[@class='Order_ModalHeader__3FDaJ']");

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
        assert element != null;
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

    public void clickNoOrder(){
        element = driver.findElement(buttonNoOrder);
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

    public String getOrderInfo(){
        element = driver.findElement(orderDoneInfo);
        return element.getText();
    }
}

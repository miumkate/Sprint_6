import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.MainScooterPage;
import pages.ScooterOrderPage;
import java.util.stream.Stream;

public class CreateOrderTest {

    private WebDriver driver;
    private final String urlQaScooterMain = "https://qa-scooter.praktikum-services.ru/";

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        // driver = new FirefoxDriver();
        driver.get(urlQaScooterMain);
    }

    private static Stream<Arguments> userProfileProvider() {
        return Stream.of(
                Arguments.of("top", "Елена", "Викторова", "Москва, Красносельская, 18", "Красные ворота", "87654412301"),
                Arguments.of( "bottom", "Василий", "Пряничный", "Москва, Большая Бронная, 129", "Тверская", "81413412221")
        );
    }

    @ParameterizedTest
    @MethodSource("userProfileProvider")
    public void firstOrderTest(String whereButtonOrder, String name, String surname, String address, String station, String phone){
        String header;
        // Step 1
        MainScooterPage mainPage = new MainScooterPage(driver);
        header = mainPage.getHeaderOrder();
        Assertions.assertTrue(header.contains("Самокат"));
        mainPage.clickButtonOrder(whereButtonOrder);

        // Step 2
        ScooterOrderPage orderPage = new ScooterOrderPage(driver);
        header = orderPage.getHeaderOrder();
        Assertions.assertTrue(header.contains("Для кого самокат"));

        orderPage.setUserProfileForOrder(name,surname, address,station, phone);
        orderPage.clickButtonNext();

        // Step 3
        GenerateDataForOrder dataForOrder = new GenerateDataForOrder();
        int dateStart = dataForOrder.getDayStartForScooter();
        String dayInterval = dataForOrder.getInterval();
        String color = dataForOrder.getColor();


        header = orderPage.getHeaderOrder();
        Assertions.assertTrue(header.contains("Про аренду"));
        orderPage.setAboutRentForOrder(dateStart,dayInterval,color,"Прикрепите, пожалуйста, звоночек на руль.");
        orderPage.clickButtonOrder();

        // Step 4
        header = orderPage.checkWantOrder();
        Assertions.assertTrue(header.contains("Хотите оформить заказ"),"Не открылось подтверждение оформления заказа.");
        orderPage.clickYesOrder();

        // Step 5
        header = orderPage.getHeaderOrderInfo();
        Assertions.assertTrue(header.contains("Заказ оформлен"),"Не открылось подтверждение создания заказа.");
    }



    @AfterEach
    void exit(){
       driver.quit();
    }
}

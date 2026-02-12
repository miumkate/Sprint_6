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
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class CreateOrderTest {

    private WebDriver driver;

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
//        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
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
        MainScooterPage mainPage = new MainScooterPage(driver,whereButtonOrder);
        header = mainPage.getHeaderOrder();
        Assertions.assertTrue(header.contains("Самокат"));
        mainPage.clickButtonOrder();

        // Step 2
        ScooterOrderPage orderPage = new ScooterOrderPage(driver);
        header = orderPage.getHeaderOrder();
        Assertions.assertTrue(header.contains("Для кого самокат"));

        orderPage.setUserProfileForOrder(name,surname, address,station, phone);
        orderPage.clickButtonNext();

        // Step 3
        header = orderPage.getHeaderOrder();
        Assertions.assertTrue(header.contains("Про аренду"));
        orderPage.setAboutRentForOrder(getDayStartForScooter(),getInterval(),getColor(),"Прикрепите, пожалуйста, звоночек на руль.");
        orderPage.clickButtonOrder();

        // Step 4
        header = orderPage.checkWantOrder();
        Assertions.assertTrue(header.contains("Хотите оформить заказ"),"Не открылось подтверждение оформления заказа.");
        orderPage.clickYesOrder();

        // Step 5
        header = orderPage.getHeaderOrderInfo();
        Assertions.assertTrue(header.contains("Заказ оформлен"),"Не открылось подтверждение создания заказа.");
    }


    Integer getDayStartForScooter(){
        LocalDate date = LocalDate.now();
        return date.getDayOfMonth()+1;
    }

    String getInterval(){
        int randomInt = (int) (Math.random() * 7);
        List<String>  interval = Arrays.asList(
                "сутки",
                "двое суток",
                "трое суток",
                "четверо суток",
                "пятеро суток",
                "шестеро суток",
                "семеро суток"
        );
        return interval.get(randomInt);
    }

    String getColor(){
        List<String> colors = Arrays.asList(
                "grey",
                "black"
        );
        Random random = new Random();
        return colors.get(random.nextInt(colors.size()));
    }

    @AfterEach
    void exit(){
       driver.quit();
    }
}

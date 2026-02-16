import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.util.HashMap;


public class QuestionMainPageTest {

    private final String urlQaScooterMain = "https://qa-scooter.praktikum-services.ru/";
    private WebDriver driver;

    private String getKeyValue(String key) {
        HashMap<String, String> map = new HashMap<>();
        map.put("Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой.");
        map.put("Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.");
        map.put("Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.");
        map.put("Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее.");
        map.put("Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.");
        map.put("Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.");
        map.put("Можно ли отменить заказ?","Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.");
        map.put("Я жизу за МКАДом, привезёте?","Да, обязательно. Всем самокатов! И Москве, и Московской области.");

        return map.get(key);
    }


    @BeforeEach
    public void start(){
       driver = new ChromeDriver();
       driver.get(urlQaScooterMain);

    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6,7})
    public void test(int numberQuestion){
        QuestionsScooterPage questionsScooterPage = new QuestionsScooterPage(driver);
        // Получить фактические данные со страницы
        String[] resultQuestionAnswer = questionsScooterPage.getAnswer(numberQuestion);
        String keyActual = resultQuestionAnswer[0];
        String valueActual = resultQuestionAnswer[1];
        // Получить ожидаемые данные
        String valueExpected = getKeyValue(keyActual);
        // Сравнить значения
        Assertions.assertTrue(valueExpected.contains(valueActual), valueExpected + "  " + valueActual);
    }

    @AfterEach
    public void exit(){
        driver.quit();
    }
}

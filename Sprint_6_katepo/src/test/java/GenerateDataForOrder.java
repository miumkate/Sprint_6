import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GenerateDataForOrder {

    public Integer getDayStartForScooter(){
        LocalDate date = LocalDate.now();
        return date.getDayOfMonth()+1;
    }

    public String getInterval(){
        int randomInt = (int) (Math.random() * 7);
        List<String> interval = Arrays.asList(
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

    public String getColor(){
        List<String> colors = Arrays.asList(
                "grey",
                "black"
        );
        Random random = new Random();
        return colors.get(random.nextInt(colors.size()));
    }

}

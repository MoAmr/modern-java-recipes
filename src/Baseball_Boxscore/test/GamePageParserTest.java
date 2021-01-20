package Baseball_Boxscore.test;

import Baseball_Boxscore.GamePageParser;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

/**
 * @author Mohammed Amr
 * @created 20/01/2021 - 23:09
 * @project Modern Java Recipes
 */
public class GamePageParserTest {

    private GamePageParser parser = new GamePageParser();

    @Test
    public void getGames() {
        parser.printGames(LocalDate.of(2017, Month.MAY, 5), 3);
    }
}

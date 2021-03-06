package Baseball_Boxscore.test;

import Baseball_Boxscore.BoxscoreRetriever;
import Baseball_Boxscore.GamePageLinksSupplier;
import Baseball_Boxscore.json.Result;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @author Mohammed Amr
 * @created 20/01/2021 - 01:17
 * @project Modern Java Recipes
 */
public class BoxscoreRetrieverTest {

    private BoxscoreRetriever retriever = new BoxscoreRetriever();

    @Test
    public void gamePattern2Result() {
        String pattern = "gid_2017_05_28_anamlb_miamlb_1/";
        Optional<Result> result = retriever.gamePattern2Result(pattern);
        assertTrue(result.isPresent());

        pattern = "gid_2017_01_01_anamlb_miamlb_1/";
        result = retriever.gamePattern2Result(pattern);
        assertFalse(result.isPresent());
    }


    @Test
    public void apply() {
        LocalDate startDate = LocalDate.of(2017, Month.MAY, 28);
        List<Result> results = retriever.apply(new GamePageLinksSupplier(startDate, 3).get());
        assertEquals(45, results.size());
    }
}

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static matcher.FileTestData.*;


public class SaxParserTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(out));
    }

    @Test
    public void fullSearch() {
        Main.main(new String[]{"-f", FILE_PATH});
        String actual = out.toString();
        FILE_MATCHER.assertMatch(actual, FULL_SEARCH);
    }

    @Test
    public void exactSearch() {
        Main.main(new String[]{"-f", FILE_PATH, "-s", "file-1498940214.xhtml"});
        String actual = out.toString().trim();
        FILE_MATCHER.assertMatch(actual, EXACT_SEARCH);
    }

    @Test
    public void maskSearch() {
        Main.main(new String[]{"-f", FILE_PATH, "-s", "‘*.java’"});
        String actual = out.toString().trim();
        FILE_MATCHER.assertMatch(actual, MASK_SEARCH);
    }

    @Test
    public void regExSearch() {
        Main.main(new String[]{"-f", FILE_PATH, "-S", "‘.*?[a-z]{4}-\\d+\\.[a-z]+’"});
        String actual = out.toString();
        FILE_MATCHER.assertMatch(actual, REGEX_SEARCH);
    }


}

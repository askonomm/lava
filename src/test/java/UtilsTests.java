import com.soynomm.bloggo.enums.TrimPos;
import com.soynomm.bloggo.Utils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UtilsTests {

    @Test
    public void testDateWithoutFormat() {
        String date = "2021-03-22";
        String expected = "Mon, 22 Mar 2021 00:00:00 +0000";
        String result = Utils.date(date);

        assertEquals(expected, result);
    }

    @Test
    public void testDateWithoutFormatShortMonth() {
        String date = "2021-3-22";
        String expected = "Mon, 22 Mar 2021 00:00:00 +0000";
        String result = Utils.date(date);

        assertEquals(expected, result);
    }

    @Test
    public void testDateWithoutFormatInvalid() {
        String date = "03-22-2021";
        String expected = "Sun, 13 Apr 0010 00:00:00 +0000";
        String result = Utils.date(date);

        assertEquals(expected, result);
    }

    @Test
    public void testDateWithoutFormatNonDate() {
        String date = "ThisIsNotADate";
        String expected = "";
        String result = Utils.date(date);

        assertEquals(expected, result);
    }

    @Test
    public void testDateWithoutFormatEmpty() {
        String date = "";
        String expected = "";
        String result = Utils.date(date);

        assertEquals(expected, result);
    }

    @Test
    public void testDateWithFormat() {
        String date = "2021-03-22";
        String format = "MMM dd, yyyy";
        String expected = "Mar 22, 2021";
        String result = Utils.date(date, format);

        assertEquals(expected, result);
    }

    @Test
    public void testDateWithFormatNonDate() {
        String date = "ThisIsNotADate";
        String format = "MMM dd, yyyy";
        String expected = "";
        String result = Utils.date(date, format);

        assertEquals(expected, result);
    }

    @Test
    public void testMarkdownToHtml() {
        String markdown = "Hello, World.";
        String expected = "<p>Hello, World.</p>\n";
        String result = Utils.markdownToHtml(markdown);

        assertEquals(expected, result);
    }

    @Test
    public void testTrimStrLeft() {
        String str = "-trimthistext-";
        String expected = "trimthistext-";
        String result = Utils.trimStr(str, TrimPos.LEFT, "-");

        assertEquals(expected, result);
    }

    @Test
    public void testTrimStrLeftNone() {
        String str = "trimthistext-";
        String expected = "trimthistext-";
        String result = Utils.trimStr(str, TrimPos.LEFT, "-");

        assertEquals(expected, result);
    }

    @Test
    public void testTrimStrRight() {
        String str = "-trimthistext-";
        String expected = "-trimthistext";
        String result = Utils.trimStr(str, TrimPos.RIGHT, "-");

        assertEquals(expected, result);
    }

    @Test
    public void testTrimStrRightNone() {
        String str = "-trimthistext";
        String expected = "-trimthistext";
        String result = Utils.trimStr(str, TrimPos.RIGHT, "-");

        assertEquals(expected, result);
    }

}
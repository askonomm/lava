import com.soynomm.bloggo.enums.TrimPos;
import com.soynomm.bloggo.Utils;
import org.junit.jupiter.api.Test;
import java.util.TimeZone;
import static org.junit.jupiter.api.Assertions.*;

public class UtilsTests {

    @Test
    public void testDateWithoutFormat() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        var date = "2021-03-22";
        var expected = "Mon, 22 Mar 2021 00:00:00 +0000";
        var result = Utils.date(date);

        assertEquals(expected, result);
    }

    @Test
    public void testDateWithoutFormatShortMonth() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        var date = "2021-3-22";
        var expected = "Mon, 22 Mar 2021 00:00:00 +0000";
        var result = Utils.date(date);

        assertEquals(expected, result);
    }

    @Test
    public void testDateWithoutFormatInvalid() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        var date = "03-22-2021";
        var expected = "Sun, 13 Apr 0010 00:00:00 +0000";
        var result = Utils.date(date);

        assertEquals(expected, result);
    }

    @Test
    public void testDateWithoutFormatNonDate() {
        var date = "ThisIsNotADate";
        var expected = "";
        var result = Utils.date(date);

        assertEquals(expected, result);
    }

    @Test
    public void testDateWithoutFormatEmpty() {
        var date = "";
        var expected = "";
        var result = Utils.date(date);

        assertEquals(expected, result);
    }

    @Test
    public void testDateWithFormat() {
        var date = "2021-03-22";
        var format = "MMM dd, yyyy";
        var expected = "Mar 22, 2021";
        var result = Utils.date(date, format);

        assertEquals(expected, result);
    }

    @Test
    public void testDateWithFormatNonDate() {
        var date = "ThisIsNotADate";
        var format = "MMM dd, yyyy";
        var expected = "";
        var result = Utils.date(date, format);

        assertEquals(expected, result);
    }

    @Test
    public void testMarkdownToHtml() {
        var markdown = "Hello, World.";
        var expected = "<p>Hello, World.</p>\n";
        var result = Utils.markdownToHtml(markdown);

        assertEquals(expected, result);
    }

    @Test
    public void testTrimStrLeft() {
        var str = "-trimthistext-";
        var expected = "trimthistext-";
        var result = Utils.trimStr(str, TrimPos.LEFT, "-");

        assertEquals(expected, result);
    }

    @Test
    public void testTrimStrLeftNone() {
        var str = "trimthistext-";
        var expected = "trimthistext-";
        var result = Utils.trimStr(str, TrimPos.LEFT, "-");

        assertEquals(expected, result);
    }

    @Test
    public void testTrimStrRight() {
        var str = "-trimthistext-";
        var expected = "-trimthistext";
        var result = Utils.trimStr(str, TrimPos.RIGHT, "-");

        assertEquals(expected, result);
    }

    @Test
    public void testTrimStrRightNone() {
        var str = "-trimthistext";
        var expected = "-trimthistext";
        var result = Utils.trimStr(str, TrimPos.RIGHT, "-");

        assertEquals(expected, result);
    }

}
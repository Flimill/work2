import org.junit.Assert;
import org.junit.Test;
import pack.Cutter;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CutterTest {
    private final String s = System.lineSeparator();

    private static void assertFileContent(String expectedContent) throws IOException {
        String content = Files.readString(Paths.get("files\\output1.txt"));
        File file = new File("files\\output1.txt");
        file.delete();
        Assert.assertEquals(content, (expectedContent));
    }

    @Test
    public void UntilTheEndOfTheLine_CountByWords() throws IOException {
        new File("files\\output1.txt");
        Cutter cutter = new Cutter(false, "files\\output1.txt",
                "files\\input.txt", "6-");
        cutter.cut();
        assertFileContent(
                "when I started really to play the guitar" + s +
                        "a musician" + s +
                        "the dream was so big" + s +
                        "because I was living in a little town" + s +
                        "broke away from school and became I musician" + s +
                        "have a bit of a chance\u0094" + s +
                        "to do is music but not only play music" + s +
                        s +
                        s +
                        "in 1969-70, they already had discotheques" + s +
                        "car and go to a discotheque and sing maybe 30 minutes" + s +
                        "7-8 songs. I would partially sleep in the car" + s +
                        "drive home and that help me for about almost 2 years" + s +
                        "I wanted to do a album with the sound of the 50s, the sound of the 60s, of the 70s and then have a sound of the future.");
    }
    @Test
    public void FromTheBeginningOfTheLine_CountByWords() throws IOException {
        new File("files\\output1.txt");
        Cutter cutter = new Cutter(false, "files\\output1.txt",
                "files\\input.txt", "-6");
        cutter.cut();
        assertFileContent(
                "When I was fifteen, sixteen when " + s +
                        "I definately wanted to become a " + s +
                        "It was almost impossible because the " + s +
                        "I didn't see any chance because " + s +
                        "[?] and when I finally broke " + s +
                        "I thought \u0093well I may have " + s +
                        "Because all I every wanted to " + s +
                        "But compose music." + s +
                        s +
                        "At that time, in Germany, in " + s +
                        "So I would take my car " + s +
                        "I think I had about 7-8 " + s +
                        "Because I didn't want to drive " + s +
                        "To survive. In the beginning, I ");
    }
    @Test
    public void FromTheBeginningOfTheLine_SpellCount() throws IOException {
        new File("files\\output1.txt");
        Cutter cutter = new Cutter(true, "files\\output1.txt",
                "files\\input3.txt", "-4");
        cutter.cut();
        assertFileContent(
                "abcd" + s +
                        "kjhk" + s +
                        "lkui" + s +
                        "nbdy" + s +
                        "lkui" + s +
                        "mlks" + s +
                        "poir" + s +
                        ";skl" + s +
                        "opoe" + s +
                        "psod" + s +
                        ";lai" + s +
                        ";lkd" + s +
                        ";irp" + s +
                        "l;sd");
    }
    @Test
    public void OnTheInterval_SpellCount() throws IOException {
        new File("files\\output1.txt");
        Cutter cutter = new Cutter(true, "files\\output1.txt",
                "files\\input2.txt", "6-12");
        cutter.cut();
        assertFileContent(
                "or not to ");
    }
    @Test
    public void GoingAbroad() throws IOException {
        new File("files\\output1.txt");
        Cutter cutter = new Cutter(false, "files\\output1.txt",
                "files\\input2.txt", "-30");
        cutter.cut();
        assertFileContent("To be, or not to be");
    }
    @Test
    public void InvalidRange() {
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            new File("files\\output1.txt");
            Cutter cutter = new Cutter(false, "files\\output1.txt",
                    "files\\input2.txt", "1234");
            cutter.cut();
        });
    }

    @Test
    public void CutOutTest() {
        String expectedString = Cutter.cutOut("abcdefghijklmnopqrstuvwxyz", 3, 6, true);
        Assert.assertEquals(expectedString, "cdef");
    }
    @Test
    public void InputFileIsEmpty() throws IOException {
        Assert.assertThrows(NullPointerException.class, () -> {
            new File("files\\output1.txt");
        Cutter cutter = new Cutter(false, "files\\output1.txt",
                "files\\input4.txt", "-2");
        cutter.cut();
        });
    }
}

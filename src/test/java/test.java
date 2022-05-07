import org.junit.Assert;
import org.junit.Test;
import pack.Cut;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class test {

    private static void assertFileContent(String name, String expectedContent) throws IOException {
        String content = Files.readString(Paths.get(name));
        File file = new File(name);
        file.delete();
        Assert.assertEquals(content, (expectedContent));
    }

    @Test
    public void method1() throws IOException {
        File test = new File("C:\\Users\\artem\\Desktop\\work2\\files\\output1.txt");
        Cut cutter = new Cut(true, false, "C:\\Users\\artem\\Desktop\\work2\\files\\output1.txt",
                "C:\\Users\\artem\\Desktop\\work2\\files\\input.txt", "6-");
        cutter.Cut();
        assertFileContent("C:\\Users\\artem\\Desktop\\work2\\files\\output1.txt",
                "when I started really to play the guitar\n" +
                        "a musician\n" +
                        "the dream was so big\n" +
                        "because I was living in a little town\n" +
                        "broke away from school and became I musician\n" +
                        "have a bit of a chance\u0094\n" +
                        "to do is music but not only play music\n" +
                        "\n" +
                        "\n" +
                        "in 1969-70, they already had discotheques\n" +
                        "car and go to a discotheque and sing maybe 30 minutes\n" +
                        "7-8 songs. I would partially sleep in the car\n" +
                        "drive home and that help me for about almost 2 years\n" +
                        "I wanted to do a album with the sound of the 50s, the sound of the 60s, of the 70s and then have a sound of the future.");
    }
    @Test
    public void method2() throws IOException {
        File test = new File("C:\\Users\\artem\\Desktop\\work2\\files\\output1.txt");
        Cut cutter = new Cut(true, false, "C:\\Users\\artem\\Desktop\\work2\\files\\output1.txt",
                "C:\\Users\\artem\\Desktop\\work2\\files\\input.txt", "-6");
        cutter.Cut();
        assertFileContent("C:\\Users\\artem\\Desktop\\work2\\files\\output1.txt",
                "When I was fifteen, sixteen when \n" +
                        "I definately wanted to become a \n" +
                        "It was almost impossible because the \n" +
                        "I didn't see any chance because \n" +
                        "[?] and when I finally broke \n" +
                        "I thought \u0093well I may have \n" +
                        "Because all I every wanted to \n" +
                        "But compose music.\n" +
                        "\n" +
                        "At that time, in Germany, in \n" +
                        "So I would take my car \n" +
                        "I think I had about 7-8 \n" +
                        "Because I didn't want to drive \n" +
                        "To survive. In the beginning, I ");
    }
    @Test
    public void method3() throws IOException {
        File test = new File("C:\\Users\\artem\\Desktop\\work2\\files\\output1.txt");
        Cut cutter = new Cut(false, true, "C:\\Users\\artem\\Desktop\\work2\\files\\output1.txt",
                "C:\\Users\\artem\\Desktop\\work2\\files\\input3.txt", "-4");
        cutter.Cut();
        assertFileContent("C:\\Users\\artem\\Desktop\\work2\\files\\output1.txt",
                "abcd\n" +
                        "kjhk\n" +
                        "lkui\n" +
                        "nbdy\n" +
                        "lkui\n" +
                        "mlks\n" +
                        "poir\n" +
                        ";skl\n" +
                        "opoe\n" +
                        "psod\n" +
                        ";lai\n" +
                        ";lkd\n" +
                        ";irp\n" +
                        "l;sd");
    }
    @Test
    public void method4() throws IOException {
        File test = new File("C:\\Users\\artem\\Desktop\\work2\\files\\output1.txt");
        Cut cutter = new Cut(false, true, "C:\\Users\\artem\\Desktop\\work2\\files\\output1.txt",
                "C:\\Users\\artem\\Desktop\\work2\\files\\input2.txt", "6-12");
        cutter.Cut();
        assertFileContent("C:\\Users\\artem\\Desktop\\work2\\files\\output1.txt",
                "or not to ");
    }
    @Test
    public void method5() throws IOException {
        File test = new File("C:\\Users\\artem\\Desktop\\work2\\files\\output1.txt");
        Cut cutter = new Cut(false, true, "C:\\Users\\artem\\Desktop\\work2\\files\\output1.txt",
                "C:\\Users\\artem\\Desktop\\work2\\files\\input2.txt", "-30");
        cutter.Cut();
        assertFileContent("C:\\Users\\artem\\Desktop\\work2\\files\\output1.txt",
                "To be, or not to be");
    }
}

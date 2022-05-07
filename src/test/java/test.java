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
        if(!content.equals(expectedContent))
            throw new AssertionError("Неверно выделены подстроки");
        else System.out.println("Верно");
    }
    public static void main(String[] args) throws IOException {
        test1();
    }
    public static void test1() throws IOException {
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
                        "I wanted to do a album with the sound of the 50s, the sound of the 60s, of the 70s and then have a sound of the future.\n");
    }
}

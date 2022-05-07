package pack;

import java.io.IOException;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cut {
    private final boolean flagW;
    private final boolean flagC;
    private final String outputFileName;
    private final String inputFileName;
    private final String range;


    public Cut(boolean flagW, boolean flagC, String outputFileName, String inputFileName, String range) {
        this.flagW = flagW;
        this.flagC = flagC;
        this.outputFileName = outputFileName;
        this.inputFileName = inputFileName;
        this.range = range;
    }


    private static Integer search(Matcher m) {
        m.find();
        return Integer.parseInt(m.group());
    }

    public void Cut() {
        int start = 0;
        int end = 0;
        Matcher m = Pattern.compile("\\d+").matcher(range);
        if (Pattern.matches("-\\d+", range)) {
            end = search(m);
        } else if (Pattern.matches("\\d+-\\d+", range)) {
            start = search(m);
            end = search(m);
        } else if (Pattern.matches("\\d+-", range)) {
            start = search(m);
        }

        if (inputFileName == null)
            readConsole(start, end);
        else readFile(start, end);
    }

    private void readConsole(int start, int end) {
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = reader.readLine();
            cutter(line, start, end);
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }


    private void readFile(int start, int end) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line = reader.readLine();
            while (line != null) {
                cutter(line, start, end);
                line = reader.readLine();
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    private void cutter(String line, int start, int end){
        int count = 0;
        String subString = "";
        if (flagC) {
            int i = 0;
            while (i < line.length()) {
                char c = line.charAt(i);
                if (c != ' ')
                    count++;
                if (count >= start && (count <= end || end == 0))
                    subString += c;
                if (count > end && end!=0)
                    break;
                i++;
            }
        } else if (flagW) {
            int i = 0;
            boolean word = false;
            while (i < line.length()) {
                char c = line.charAt(i);
                if (c != ' ' && word == false) {
                    count++;
                    word = true;
                } else if (c == ' ')
                    word = false;
                if (count >= start && (count <= end || end == 0))
                    subString += c;
                if (count > end && end!=0)
                    break;
                i++;
            }
        }
        write(subString);
    }

    private void write(String subString) {
        if (outputFileName == null)
            System.out.println(subString);
        else fileWrite(subString);
    }

    private void fileWrite(String carved) {
        try (FileWriter writer = new FileWriter(outputFileName, true)) //файл перезаписывается
        {
            writer.write(carved);
            writer.append("\n");
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }


}

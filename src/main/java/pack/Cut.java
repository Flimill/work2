package pack;

import java.io.IOException;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cut {
    private final boolean flagC;
    private final String outputFileName;
    private final String inputFileName;
    private final String range;


    public Cut(boolean flagC, String outputFileName, String inputFileName, String range) {
        this.flagC = flagC;
        this.outputFileName = outputFileName;
        this.inputFileName = inputFileName;
        this.range = range;
    }


    private static Integer search(Matcher m) {
        m.find();
        return Integer.parseInt(m.group());
    }

    public void cut() {
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
        } else throw new IllegalArgumentException("интервал должен быть вида -K, N- или N-K, где N и K целые числа");

        if (inputFileName == null)
            processInputConsole(start, end);
        else processInputFile(start, end);
        System.out.println(end);
    }

    private void processInputConsole(int start, int end) {
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = reader.readLine();
            write(cutOut(line, start, end, flagC));
        } catch (IOException ex) {

            ex.printStackTrace();
        }
    }


    private void processInputFile(int start, int end) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line = reader.readLine();
            while (line != null) {
                write(cutOut(line, start, end, flagC));
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String cutOut(String line, int start, int end, boolean flagC){
        int count = 0;
        StringBuilder subString = new StringBuilder();
        int i = 0;
        if (flagC) {
            while (i < line.length()) {
                char c = line.charAt(i);
                if (c != ' ')
                    count++;
                if (count >= start && (count <= end || end == 0))
                    subString.append(c);
                if (count > end && end!=0)
                    break;
                i++;
            }
        } else {
            boolean word = false;
            while (i < line.length()) {
                char c = line.charAt(i);
                if (c != ' ' && word == false) {
                    count++;
                    word = true;
                } else if (c == ' ')
                    word = false;
                if (count >= start && (count <= end || end == 0))
                    subString.append(c);
                if (count > end && end!=0)
                    break;
                i++;
            }
        }
        return subString.toString();
    }

    private void write(String subString) {
        if (outputFileName == null)
            System.out.println(subString);
        else fileWrite(subString);
    }

    private void fileWrite(String carved) {
        try (FileWriter writer = new FileWriter(outputFileName, true))
        {
            if(new File(outputFileName).length() != 0)
                writer.append(System.lineSeparator());
            writer.write(carved);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

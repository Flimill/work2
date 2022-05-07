package pack;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;


public class CutLauncher {
    @Option(name = "-w")
    private boolean flagW;
    @Option(name = "-c")
    private boolean flagC;
    @Option(name = "-o")
    private String outputFileName;
    @Option(name = "-i")
    private String inputFileName;
    @Option(name = "-r", required = true)
    private String range;




    public static void main(String[] args) {
        new CutLauncher().launch(args);
    }


    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
            if (flagW == flagC)
                throw new CmdLineException("Необходим лишь один флаг -w или -c");
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar wrk-1.0-SNAPSHOT-jar-with-dependencies.jar -c/-w -o OutputFileName -i InputFileName -r range");
            parser.printUsage(System.err);
            return;
        }
        Cut cuter = new Cut(flagW, flagC, outputFileName, inputFileName, range);
        cuter.Cut();
    }
}

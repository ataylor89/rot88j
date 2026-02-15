package rot88;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {

    private Rot88 rot88;

    public Main() {
        rot88 = new Rot88();
    }

    public Options buildOptions() {
        Options options = new Options();
        options.addOption(Option.builder("i")
            .longOpt("inputfile")
            .desc("The path to the input file")
            .hasArg()
            .type(String.class)
            .required(false)
            .build());
        options.addOption(Option.builder("o")
            .longOpt("outfile")
            .desc("The path to the output file")
            .hasArg()
            .type(String.class)
            .required(false)
            .build());
        return options;
    }

    public void run(String[] args) throws IOException, ParseException {
        String input = null;
        Options options = buildOptions();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        String[] positionalArgs = cmd.getArgs();
        if (positionalArgs.length == 1) {
            input = positionalArgs[0];
        }
        else if (cmd.hasOption("i")) {
            String inputFile = cmd.getOptionValue("i");
            Path path = Paths.get(inputFile);
            input = Files.readString(path);
        }
        else {
            throw new IllegalArgumentException("The command-line arguments are missing an input string or a path to an input file");
        }
        String output = rot88.rot88(input);
        if (cmd.hasOption("o")) {
            String outputFile = cmd.getOptionValue("o");
            Path path = Paths.get(outputFile);
            Files.writeString(path, output);
        }
        else {
            System.out.print(output);
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.run(args);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

}

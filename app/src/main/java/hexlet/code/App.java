package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;


@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference."
)

public final class App implements Runnable {

    @Parameters(index = "0", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"},
            description = "output format [default: stylish]")
    private String format = "stylish";

    /**
     * Runs the main logic of the application.
     * This method is invoked when the command-line application is executed.
     * It compares two configuration files and prints the difference in the specified format.
     *
     * @throws Exception if an error occurs during the generation of the difference.
     */
    @Override
    public void run() {
        try {
            String result = Differ.generate(filepath1, filepath2, format);
            System.out.println(result);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(final String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}

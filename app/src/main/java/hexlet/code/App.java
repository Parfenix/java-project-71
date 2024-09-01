package hexlet.code;

import picocli.CommandLine;


@CommandLine.Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference."
)

public class App implements Runnable {

  /**
   * The path to the first file.
   * This field is populated via the command line argument.
   */
  @CommandLine.Parameters(index = "0", description = "path to first file")
  private String filepath1;

  /**
   * The path to the second file.
   * This field is populated via the command line argument.
   */
  @CommandLine.Parameters(index = "1", description = "path to second file")
  private String filepath2;

  /**
   * This field specifies the format in which the output should be displayed.
   *   The default value is "stylish". Available formats can be specified via
   *   command line options.
   */
  @CommandLine.Option(names = {"-f", "--format"},
          description = "output format [default: stylish]")
  private String format = "stylish";

  /**Executes the application logic
   * This method generates the result based on the provided file paths and
   * outputs it to the console. If an error occurs during the execution,
   * an error message is printed to the standard error stream, and the stack
   * trace is output.
   * Subclasses that override this method should ensure to call this method's
   * implementation to preserve the original behavior unless a different
   * behavior is desired.
   **/

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

  /**
   * The entry point of the application.
   *
   * @param args Command line arguments passed to the application.
   */
  public static void main(final String[] args) {
    int exitCode = new CommandLine(new App()).execute(args);
    System.exit(exitCode);
  }
}

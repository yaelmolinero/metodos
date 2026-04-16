package code.utils;

public class Chalk {
  public static final String RESET = "\u001b[0m";

  public static final String BLACK = "\u001b[30m";
  public static final String RED = "\u001b[31m";
  public static final String GREEN = "\u001b[32m";
  public static final String YELLOW = "\u001b[33m";
  public static final String BLUE = "\u001b[34m";
  public static final String PURPLE = "\u001b[35m";
  public static final String CYAN = "\u001b[36m";
  public static final String WHITE = "\u001b[37m";
  public static final String GRAY = "\u001b[90m";

  public static final String BG_BLACK = "\u001B[40m";
  public static final String BG_RED = "\u001B[41m";
  public static final String BG_GREEN = "\u001B[42m";
  public static final String BG_YELLOW = "\u001B[43m";
  public static final String BG_BLUE = "\u001B[44m";
  public static final String BG_PURPLE = "\u001B[45m";
  public static final String BG_CYAN = "\u001B[46m";
  public static final String BG_WHITE = "\u001B[47m";

  public static final String BOLD = "\u001b[1m";

  public static String black(String text) {
    return BLACK + text + RESET;
  }
  public static String red(String text) {
    return RED + text + RESET;
  }
  public static String green(String text) {
    return GREEN + text + RESET;
  }
  public static String yellow(String text) {
    return YELLOW + text + RESET;
  }
  public static String blue(String text) {
    return BLUE + text + RESET;
  }
  public static String purple(String text) {
    return PURPLE + text + RESET;
  }
  public static String cyan(String text) {
    return CYAN + text + RESET;
  }
  public static String white(String text) {
    return WHITE + text + RESET;
  }
  public static String gray(String text) {
    return GRAY + text + RESET;
  }

  public static String bgBlack(String text) {
    return BG_BLACK + text + RESET;
  }
  public static String bgRed(String text) {
    return BG_RED + text + RESET;
  }
  public static String bgGreen(String text) {
    return BG_GREEN + text + RESET;
  }
  public static String bgYellow(String text) {
    return BG_YELLOW + text + RESET;
  }
  public static String bgBlue(String text) {
    return BG_BLUE + text + RESET;
  }
  public static String bgPurple(String text) {
    return BG_PURPLE + text + RESET;
  }
  public static String bgCyan(String text) {
    return BG_CYAN + text + RESET;
  }
  public static String bgWhite(String text) {
    return BG_WHITE + text + RESET;
  }

  public static String bold(String text) {
    return BOLD + text + RESET;
  }

  public static String rgb(String text, int red, int green, int blue) {
    return "[38:2;{" + red + "};{" + green + "};{" + blue + "}m" + text + RESET;
  }
}

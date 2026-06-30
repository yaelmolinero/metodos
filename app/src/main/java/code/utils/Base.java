package code.utils;

public class Base {
  public static double redondear(double num) {
    return redondear(num, 6);
  }

  public static double redondear(double num, int decimales) {
    double factor = Math.pow(10, decimales);
    return Math.round(num * factor) / factor;
  }

  public static void printTitle(String title) {
    int ancho = 60;
    int espaciosLaterales = (ancho - title.length()) / 2;

    System.out.print(Chalk.BOLD + Chalk.BLUE);
    System.out.println("-".repeat(ancho));

    System.out.printf("%" + (espaciosLaterales + title.length()) + "s\n", title);

    System.out.println("-".repeat(ancho));
    System.out.println(Chalk.RESET);
  }

  public static void mostrarMatriz(double matriz[][]) {
    System.out.println(Chalk.BOLD + Chalk.GREEN);

    for (int i = 0; i < matriz.length; i++) {
      double[] fila = matriz[i];
      int n = fila.length;

      System.out.print("│ ");

      for (int j = 0; j < n - 1; j++) {
        System.out.printf("%-12f ", fila[j]);
      }
      
      System.out.printf(" | %-12f │", fila[n - 1]);
      System.out.println();
    }
    System.out.println(Chalk.RESET);
  }

  static void mostrarTabla(double[][] matriz, double[] x, double[] y) {
    System.out.println();
    System.out.printf(
      Chalk.bold(Chalk.bgBlue("%-2s %-12s %-12s ")),
      "i", "Xi", "f(Xi)"
    );

    for (int i = 0; i < matriz[0].length; i ++) {
      System.out.printf(
        Chalk.bold(Chalk.bgBlue("%-12s ")),
        "#" + i + "f(Xi)"
      );
    }
    System.out.println();

    for (int i = 0; i < matriz.length; i++) {
      System.out.printf("%-2d %-12f %-12f ", i, x[i], y[i]);

      double[] fila = matriz[i];
      int n = fila.length;

      for (int j = 0; j < n; j++) {
        System.out.printf("%-12f ", fila[j]);
      }

      System.out.println();
    }
    System.out.println();
  }

  public static void mostrarCoordenadas(double xi[], double fxi[]) {
    System.out.print("Coordenadas: ");

    for (int i = 0; i < xi.length; i++) {
      System.out.printf("(%.0f, %.0f)", xi[i], fxi[i]);
      if (i < xi.length - 1) System.out.print(", ");
    }
    System.out.println();
  }
}

package code.c3;

import code.utils.Consola;
import code.utils.Chalk;

public class GaussJordan {
  int decimales = 6;
  final double[][] matrizDefault = {
    { 1, -1, 3, 13 },
    { 1, 1, 1, 11 },
    { 2, 2, -1, 7}
  };

  public double[][] evaluar(double[][] matriz) {
    int n = matriz.length;
    double alpha = 0;
    double[] x = new double[n];

    printTitle();
    System.out.println(Chalk.bgBlue("  MATRIZ INICIAL:  "));
    this.reportarMatriz(matriz);

    for (int k = 0; k < n; k++) {
      System.out.println("----------------------------------------------------");
      System.out.printf(Chalk.bgBlue("  I = %d  "), k);
      System.out.println("\n");

      for (int i = 0; i < n; i++) {
        if (i != k && matriz[i][k] != 0) {
          alpha = matriz[i][k] / matriz[k][k];

          System.out.printf(
            "alpha = %.decf / %.decf = %.decf \n".replaceAll("dec", Integer.toString(decimales)),
            matriz[i][k], matriz[k][k], alpha
          );

          for (int j = k; j <= n; j++) {
            System.out.printf(
              Chalk.bold("A%d%d = A%d%d - (alpha * A%d%d)") + " => %.decf = %.decf - %.decf * %.decf \n"
              .replaceAll("dec", Integer.toString(decimales)),
              i, j, i, j, k, j, (matriz[i][j] - alpha * matriz[k][j]), matriz[i][j], alpha, matriz[k][j]
            );

            matriz[i][j] = matriz[i][j] - alpha * matriz[k][j];
          }

          this.reportarMatriz(matriz);
        }
      }
    }

    System.out.println(Chalk.bgBlue("  Dividiendo sobre el PIVOT  "));
    for (int i = 0; i < n; i++) {
      matriz[i][n] = matriz[i][n] / matriz[i][i];
      x[i] = matriz[i][n];
      matriz[i][i] = 1;
    }

    this.reportarMatriz(matriz);
    this.muestraResultados(x);
    return matriz;
  }

  public static void main(String[] args) {
    GaussJordan run = new GaussJordan();
    Consola consola = new Consola();

    run.muestraDefault();
    boolean ans = consola.getBoolean("Usar sistema de ecuaciones por defecto");

    if (ans) {
      run.decimales = 0;
      run.evaluar(run.matrizDefault);
      return;
    }

    int cantidad = consola.getInteger("Ingresa el numero de ecuaciones", 3);
    double[][] matriz = consola.getMatriz(cantidad);

    run.evaluar(matriz);
  }

  void reportarMatriz(double[][] matriz) {
    System.out.println(Chalk.BOLD + Chalk.GREEN);

    for (int i = 0; i < matriz.length; i++) {
      double[] fila = matriz[i];
      int n = fila.length;

      for (int j = 0; j < n; j++) {
        if (j == n - 1) System.out.print(" | ");

        System.out.printf(
          "%-6.decimalf ".replace("decimal", Integer.toString(decimales)),
          fila[j]
        );
      }

      System.out.println();
    }
    System.out.println(Chalk.RESET);
  }

  void muestraResultados(double[] x) {
    for (int i = 0; i < x.length; i++) {
      System.out.println(
        Chalk.bgGreen(
          String.format(
            "  x%d = %.decf  ".replace("dec", Integer.toString(decimales)),
            i, x[i]
          )
        ));
    }
  }

  void muestraDefault() {
    System.out.println(Chalk.BOLD + Chalk.GRAY);
    System.out.println("""
      +---------- DEFAULT ----------+
      |  x - y + 3z = 13            |
      |  x + y + z = 11             |
      |  2x + 2y - z = 7            |
      +-----------------------------+
    """);
    System.out.print(Chalk.RESET);
  }

  void printTitle() {
    System.out.print(Chalk.BOLD + Chalk.BLUE);
    System.out.println("--------------------------------------------------");
    System.out.println("-------------- METODO DE GAUSS-JORDAN ------------");
    System.out.println("--------------------------------------------------");
    System.out.println(Chalk.RESET);
  }
}

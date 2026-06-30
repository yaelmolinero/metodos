package code.c3;

import code.utils.Consola;
import code.utils.Base;
import code.utils.Chalk;

public class GaussJordan extends Base {

  public double[][] evaluar(double[][] matriz) {
    int n = matriz.length;
    double alpha = 0;
    double[] x = new double[n];

    System.out.println(Chalk.bgWhite("  MATRIZ INICIAL:  "));
    mostrarMatriz(matriz);

    for (int k = 0; k < n; k++) {
      System.out.println("-".repeat(60));
      System.out.printf(Chalk.bgWhite("  I = %d  "), k);
      System.out.println("\n");

      for (int i = 0; i < n; i++) {
        if (i != k && matriz[i][k] != 0) {
          alpha = matriz[i][k] / matriz[k][k];

          System.out.printf(
            "alpha = %.6f / %.6f = %.6f \n",
            matriz[i][k], matriz[k][k], alpha
          );

          for (int j = k; j <= n; j++) {
            System.out.printf(
              Chalk.bold("A%d%d = A%d%d - (alpha * A%d%d)") + " => %.6f = %.6f - %.6f * %.6f \n",
              i, j, i, j, k, j, (matriz[i][j] - alpha * matriz[k][j]), matriz[i][j], alpha, matriz[k][j]
            );

            matriz[i][j] = matriz[i][j] - alpha * matriz[k][j];
          }

          mostrarMatriz(matriz);
        }
      }
    }

    System.out.println(Chalk.bgWhite("  Dividiendo sobre el PIVOT  "));
    for (int i = 0; i < n; i++) {
      matriz[i][n] = matriz[i][n] / matriz[i][i];
      x[i] = matriz[i][n];
      matriz[i][i] = 1;
    }

    mostrarMatriz(matriz);
    this.muestraResultados(x);
    return matriz;
  }

  public static void main(String[] args) {
    printTitle("Metodo Gauss-Jordan");

    GaussJordan run = new GaussJordan();
    Consola consola = new Consola();

    run.muestraDefault();
    if (consola.getBoolean("Usar sistema de ecuaciones por defecto")) {
      final double[][] matrizDefault = {
        { 1, -1, 3, 13 },
        { 1, 1, 1, 11 },
        { 2, 2, -1, 7}
      };
      
      run.evaluar(matrizDefault);
      return;
    }

    int cantidad = consola.getInteger("Ingresa el numero de ecuaciones", 3);
    double[][] matriz = consola.getMatriz(cantidad);

    run.evaluar(matriz);
  }

  void muestraResultados(double[] x) {
    for (int i = 0; i < x.length; i++) {
      System.out.println(
        Chalk.bgGreen(
          String.format(
            "  x%d = %.6f  ",
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
}

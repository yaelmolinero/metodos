package code.c3;

import code.utils.Consola;
import code.utils.Base;
import code.utils.Chalk;

public class GaussSeindel extends Base {
  double[][] matrizDefualt = {
    { 6, -4, 2, 4 },
    { 2, 8, -2, 12 },
    { 14, 10, -14, -18 }
  };

  public double[][] evaluar(double[][] matriz, int maxIteraciones, double tolerancia) {
    int n = matriz.length;
    boolean ok = false;

    double[] x = new double[n]; // Valores iniciales
    double[] prevX = new double[n];
    
    System.out.println("Tolerancia: " + tolerancia);
    System.out.println("Iteraciones máximas: " + maxIteraciones);
    System.out.println();
    mostrarMatriz(matriz);

    for (int k = 0; k < maxIteraciones; k++) {
      System.out.println("-".repeat(52));
      System.out.printf(Chalk.bgBlue("  I = %d  ") + "\n\n", k);
      // Copia los valores actuales de x a prevX
      System.arraycopy(x, 0, prevX, 0, n);

      for (int i = 0; i < n; i++) {
        double suma = 0;

        for (int j = 0; j < n; j++) {
          if (i != j)
            suma += redondear(matriz[i][j] * x[j]);
        }
        System.out.printf(Chalk.purple("%f") + "\n", suma);

        System.out.printf(
          "X%d = (M%d%d - suma) / A%d%d = (%f - %f) / %f = %f \n",
          i, i, n, i, i, matriz[i][n], suma, matriz[i][i], (matriz[i][n] - suma) / matriz[i][i]
        );

        x[i] = redondear((matriz[i][n] - suma) / matriz[i][i]);
        System.out.printf(Chalk.cyan("%f") + "\n", x[i]);
      }

      System.out.println();
      System.out.print(Chalk.bgBlue("  Resultados xActual:  ") + " [");
      for (int i = 0; i < n; i++) {
        System.out.printf(
          "%f%s",
          x[i], i < (n - 1) ? ", " : "]\n"
        );
      }

      System.out.print(Chalk.bgBlue("  Resultados xPrevio:  ") + " [");
      for (int i = 0; i < n; i++) {
        System.out.printf(
          "%f%s",
          prevX[i], i < (n - 1) ? ", " : "]\n"
        );
      }

      ok = verificarTolerancia(x, prevX, tolerancia);
      if (ok)
        break;
    }

    if (!ok)
      System.out.println("No se Encontro la Solucion");
    else {
      System.out.println("Convergio por tolerancia");
      for (int i = 0; i < n; i++) {
        System.out.printf(
          Chalk.bgGreen("  x%d = %f  ") + "\n",
          i, x[i]
        );
      }
    }

    return matriz;
  }

  public boolean verificarTolerancia(double[] x, double[] prevX, double tolerancia) {
    int n = x.length;
    int correcto = 0;
    double tmp = 0;

    System.out.print(Chalk.bgRed("  Errores:  ") + " [");
    for (int i = 0; i < n; i++) {
      tmp = Math.abs(x[i] - prevX[i]) / Math.abs(x[i]);

      System.out.printf(
        "%f%%%s",
        (tmp * 100), i < (n - 1) ? ", " : "]\n"
      );

      if (Math.abs(tmp) <= Math.abs(tolerancia))
        correcto++;
    }

    return correcto == n;
  }

  public static void main(String[] args) {
    printTitle("Método de Gauss-Seindel");
    Consola consola = new Consola();
    GaussSeindel run = new GaussSeindel();

    run.muestraDefault();
    if (consola.getBoolean("Usar sistema de ecuaciones por defecto")) {
      run.evaluar(run.matrizDefualt, 9, 0.01);
      return;
    }

    int cantidad = consola.getInteger("Ingresa el Numero de Ecuaciones", 3);
    double[][] matriz = consola.getMatriz(cantidad);

    double tolerancia = consola.getDouble("Ingresa la Tolerancia", false);
    // consola.sc.nextLine();
    int maxIteraciones = consola.getInteger("Ingresa el maximo de Iteraciones", 100);

    run.evaluar(matriz, maxIteraciones, tolerancia);
  }

  void muestraDefault() {
    System.out.println(Chalk.BOLD + Chalk.GRAY);
    System.out.println("""
      +------------ DEFAULT ------------+
      |  6x - 4y + 2z = 4               |
      |  2x + 8y - 2z = 12              |
      |  14x + 10y - 14z = -18          |
      +---------------------------------+
      |  Tolerancia: 0.01               |
      |  Iteraciones: 9                 |
      |  Valores iniciales: [0, 0, 0]   |
      +---------------------------------+ 
    """);
    System.out.print(Chalk.RESET);
  }
}

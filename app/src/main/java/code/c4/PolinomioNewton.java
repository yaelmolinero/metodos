package code.c4;
import code.utils.Chalk;
import code.utils.Consola;

public class PolinomioNewton {

  public double evaluar(double[] xi, double[] fxi, double x) {
    int n = xi.length - 1;
    double[][] T = new double[n + 1][n];
    double result = 0;

    for (int i = 0; i < n; i++) {
      T[i][0] = (fxi[i + 1] - fxi[i]) / (xi[i + 1] - xi[i]);
    }

    for (int j = 1; j < n; j++) {
      for (int i = 0; i < (n - j); i++) {
        T[i][j] = (T[i + 1][j - 1] - T[i][j - 1]) / (xi[i + j + 1] - xi[i]);
      }
    }
    
    showData(xi, fxi, x);
    showMatrix(T, xi, fxi);
    System.out.print(Chalk.BOLD + Chalk.BLUE);
    // System.out.println("Pn(x) = [");
    System.out.printf("Pn(x) = %.2f + ", fxi[0]);
    
    result += fxi[0];

    for (int i = 0; i < T[0].length; i++) {
      double parte = 1;
      parte *= T[0][i];

      System.out.printf("%.2f * ", T[0][i]);

      for (int j = 0; j <= i; j++) {
        System.out.printf("(x - %.2f)", xi[j]);
        parte *= (x - xi[j]);
      }

      if (i != (n - 1)) System.out.print(" + ");
      // System.out.println();

      result += parte;
    }

    // System.out.println("]\n");
    System.out.println();
    System.out.print(Chalk.RESET);

    return result;
  }

  public static void main(String[] args) {
    Consola consola = new Consola();
    double[] xValues = new double[3];
    double[] fxValues = new double[3];

    showHeader();

    for (int i = 0; i < 3; i++ ) {
      System.out.println("==== Coordenadas #" + (i + 1) + " ====");
      xValues[i] = consola.getDouble("x: ");
      fxValues[i] = consola.getDouble("y: ");

      System.out.println();
    }

    consola.sc.nextLine();
    int x = consola.getInteger("Ingrese el valor para la comprobación", 1);
    consola.close();
    
    double result = new PolinomioNewton().evaluar(xValues, fxValues, x);
    System.out.println(Chalk.bgGreen("     Pnx = " + result + "     "));
  }

  static void showHeader() {
    System.out.print(Chalk.bold(Chalk.blue(
      "----------------------------------------------\n" + 
      "----------- Interpolación de Newton ----------\n" +
      "----------------------------------------------\n"
    )));
  }

  static void showData(double[] xi, double[] fxi, double x) {
    System.out.println("==== Datos ==== \n");

    System.out.print("Coordenadas: ");
    for (int i = 0; i < xi.length; i++) {
      System.out.printf("(%.0f, %.0f)", xi[i], fxi[i]);
      if (i < xi.length - 1) System.out.print(", ");
    }
    System.out.println();
    System.out.println("x = " + x);
    System.out.println();

    for (int i = 0; i < xi.length; i++) {
      System.out.printf("x[%d] = %.2f f[%d] = %.2f \n", i, xi[i], i, fxi[i]);
    }

    System.out.println();
  }

  static void showMatrix(double[][] matriz, double[] x, double[] y) {
    System.out.println();
    System.out.printf(
      Chalk.bold(Chalk.bgBlue("%-2s %-8s %-8s ")),
      "j", "Xi", "f(Xi)"
    );

    for (int i = 0; i < matriz[0].length; i ++) {
      System.out.printf(
        Chalk.bold(Chalk.bgBlue("%-8s ")),
        "#" + i + "f(Xi)"
      );
    }
    System.out.println();

    for (int i = 0; i < matriz.length; i++) {
      System.out.printf("%-2d %-8.2f %-8.2f ", i, x[i], y[i]);

      double[] fila = matriz[i];
      int n = fila.length;

      for (int j = 0; j < n; j++) {
        System.out.printf("%-8.2f ", fila[j]);
      }

      System.out.println();
    }
    System.out.println();
  }
}

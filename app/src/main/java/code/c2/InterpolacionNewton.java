package code.c2;
import code.utils.Chalk;

public class InterpolacionNewton {

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
    
    showHeader();
    showCoordinates(xi, fxi);
    System.out.println("x = " + x);

    showMatrix(T, xi, fxi);
    
    System.out.print(Chalk.BOLD + Chalk.YELLOW);
    System.out.println("Y(x) = [");
    System.out.print(fxi[0] + " + \n");
    
    result += fxi[0];

    for (int i = 0; i < T[0].length; i++) {
      double parte = 1;
      parte *= T[0][i];

      System.out.print(T[0][i] + " * ");

      for (int j = 0; j <= i; j++) {
        System.out.printf("(x - %f)", xi[j]);
        parte *= (x - xi[j]);
      }

      if (i != (n - 1)) System.out.print(" + ");
      System.out.println();

      result += parte;
    }

    System.out.println("]\n");
    System.out.print(Chalk.RESET);

    return result;
  }

  public static void main(String[] args) {
    double[] tempX = { 1, 0, -3 };
    double[] tempFx = { 2, 4, -2 };
    int x = 3;

    double result = new InterpolacionNewton().evaluar(tempX, tempFx, x);

    System.out.println(Chalk.bgGreen("     Pnx = " + result + "     "));
  }

  static void showHeader() {
    System.out.print(Chalk.bold(Chalk.blue(
      "----------------------------------------------\n" + 
      "----------- Interpolación de Newton ----------\n" +
      "----------------------------------------------\n"
    )));
  }

  static void showCoordinates(double[] xi, double[] fxi) {
    System.out.print("Coordenadas: ");
    for (int i = 0; i < xi.length; i++) {
      System.out.printf("(%.0f, %.0f)", xi[i], fxi[i]);
      if (i < xi.length - 1) System.out.print(", ");
    }
    System.out.println();
  }

  static void showMatrix(double[][] matriz, double[] x, double[] y) {
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
}

package code.c4;
import code.utils.Base;
import code.utils.Chalk;
import code.utils.Consola;

public class PolinomioNewton extends Base {

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
    
    mostrarDatos(xi, fxi, x);
    mostrarTabla(T, xi, fxi);

    System.out.print(Chalk.BLUE);
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

      result += parte;
    }

    System.out.println();
    System.out.print(Chalk.RESET);

    return result;
  }

  public static void main(String[] args) {
    printTitle("Interpolación de Newton");
    
    Consola consola = new Consola();
    double[] xValues = new double[3];
    double[] fxValues = new double[3];

    for (int i = 0; i < 3; i++ ) {
      System.out.println(Chalk.bgBlue("  Coordenadas #" + (i + 1) + "  "));
      xValues[i] = consola.getDouble("x: ");
      fxValues[i] = consola.getDouble("y: ");

      System.out.println();
    }

    int x = consola.getInteger("Ingrese el valor para la comprobación", 1);
    
    double result = new PolinomioNewton().evaluar(xValues, fxValues, x);
    System.out.println(Chalk.bgGreen("   Pnx = " + result + "   "));
  }
}

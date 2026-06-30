package code.c4;

import code.utils.Base;
import code.utils.Chalk;
import code.utils.Consola;

public class Lagrange extends Base {

  public double evaluar(double[] xi, double[] fxi, double x) {
    int n = xi.length;
    double[] Lxi = new double[n];
    double Pnx = 0;

    mostrarDatos(xi, fxi, x);

    System.out.println(Chalk.BLUE);
    System.out.println("Plx=[");
    for (int i = 0; i < n; i++) {
      System.out.printf("  %.2f * ", fxi[i]);
  
      Lxi[i] = lagrangeix(x, i, xi, n);
      Pnx = Pnx + Lxi[i] * fxi[i];
      System.out.println();
    }

    System.out.println("]");
    System.out.println(Chalk.RESET);

    return Pnx;
  }

  public double lagrangeix(double x, int i, double[] xi, int n) {
    double Prod = 1;

    for (int j = 0; j < n; j++) {
      if (i != j) {
        System.out.printf("((x - %.2f) / (%.2f - %.2f))", xi[j], xi[i], xi[j]);
        Prod *= ((x - xi[j]) / (xi[i] - xi[j]));

        if (j == (n - 1)) System.out.print(" + ");
      }
    }

    return Prod;
  }

  public static void main(String[] args) {
    printTitle("Interpolación de Lagrange");

    Consola consola = new Consola();
    int cantidad = consola.getInteger("Número de coordenadas", 3);
    double[] xValues = new double[cantidad];
    double[] fxValues = new double[cantidad];

    for (int i = 0; i < cantidad; i++) {
      System.out.println(Chalk.bgBlue("  Coordenadas #" + (i + 1) + "  "));
      xValues[i] = consola.getDouble("x: ");
      fxValues[i] = consola.getDouble("y: ");

      System.out.println();
    }

    double num = consola.getInteger("Ingrese el valor de x", 1);
    double result = new Lagrange().evaluar(xValues, fxValues, num);
    System.out.println(Chalk.bgGreen("   Plx = " + result + "   "));
  }
}

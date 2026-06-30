package code.c2;

import code.utils.FuncionDinamica;
import code.utils.Base;
import code.utils.Chalk;
import code.utils.Consola;

public class Biseccion extends Base {

  public double evaluar(FuncionDinamica f, double a, double b, double tolerancia) {
    System.out.printf("Intervalo (%.0f, %.0f) -> a: %.0f, b: %.0f\n", a, b, a, b);
    System.out.printf("Error < %f%%\n\n", tolerancia);
    
    double medio;
    int i = 0;

    System.out.printf(
      Chalk.bold(Chalk.bgBlue(
      "%-2s %-8s %-8s %-10s %-10s %-10s %-10s")),
      "i", "a", "b", "f(a)", "f(b)", "f(c)", "E"
    );
    System.out.println();
    
    do {
      medio = redondear((a + b) / 2);
      double tempA = a, tempB = b;
      i++;

      if (f.evaluar(medio) > 0) b = medio;
      else a = medio;

      System.out.printf(
        "%-2d %-8f %-8f %-10f %-10f %-10f %-10f\n",
        i,
        tempA,
        tempB,
        f.evaluar(tempA),
        f.evaluar(tempB),
        f.evaluar(medio),
        (tempB - tempA) / 2
      );
      
      System.out.printf(Chalk.purple("%f > %f | %b") + "\n", Math.abs(b - a), Math.abs(tolerancia),  Math.abs(b - a) > Math.abs(tolerancia));
    } while (Math.abs(b - a) > Math.abs(tolerancia) && i < 16);

    return redondear(Math.abs(b - a));
  }

  public static void main(String[] args) {
    printTitle("Bisección");
    Consola consola = new Consola();

    String funStr = consola.getString("Ingrese la Funcion", "e^(3x) - 4");
    double intervaloA = consola.getInteger("Ingrese un valor para Xa: ", 0);
    double intervaloB = consola.getInteger("Ingrese un valor para Xb: ", 1);
    double err = consola.getDouble("Ingresa la tolerancia (ej. 0.01): ", false);
    
    FuncionDinamica fun = new FuncionDinamica(funStr);
    double result = new Biseccion().evaluar(fun, intervaloA, intervaloB, err);

    System.out.println();
    System.out.printf(Chalk.bgGreen("La interseccion es: %f") + "\n", result);
  }
}

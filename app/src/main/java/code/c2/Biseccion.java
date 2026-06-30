package code.c2;

import code.utils.FuncionDinamica;
import code.utils.Base;
import code.utils.Chalk;
import code.utils.Consola;

public class Biseccion extends Base {

  public double evaluar(FuncionDinamica f, double a, double b, double tolerancia, int iteracionesMax) {
    System.out.printf("Intervalo (%.0f, %.0f) -> a: %.0f, b: %.0f\n", a, b, a, b);
    System.out.printf("Error < %f%%\n\n", tolerancia);
    
    double fa = redondear(f.evaluar(a));
    double fb = redondear(f.evaluar(b));
    if (fa * fb >= 0) throw new Error("El intervalo no cumple con el método de bisección");
    
    double medio = 0;
    double fc = 0;
    double error = Double.MAX_VALUE;
    int i = 0;

    System.out.printf(
      Chalk.bold(Chalk.bgBlue(
      "%-2s %-8s %-8s %-8s %-10s %-10s %-10s %-10s")),
      "i", "a", "b", "medio", "f(a)", "f(b)", "f(c)", "E"
    );
    System.out.println();
    
    while (error > tolerancia && i < iteracionesMax) {
      double tempA = a, tempB = b;
      medio = redondear((a + b) / 2);

      fa = redondear(f.evaluar(a));
      fb = redondear(f.evaluar(b));
      fc = redondear(f.evaluar(medio));

      error = redondear((b - a) / 2.0);
      i++;

      // if (f.evaluar(medio) > 0) b = medio;
      // else a = medio;

      System.out.printf(
        "%-2d %-8f %-8f %-8f %-10f %-10f %-10f %-10f\n",
        i,
        tempA,
        tempB,
        medio,
        fa,
        fb,
        fc,
        error
      );

      if (fc == 0.0) break;
      if (fa * fc < 0) {
        b = medio;
        fb = fc;
      } else {
        a = medio;
        fa = fc;
      }
    }

    return medio;
  }

  public static void main(String[] args) {
    printTitle("Bisección");
    Consola consola = new Consola();

    String funStr = consola.getString("Ingrese la Funcion", "e^(3x) - 4");
    FuncionDinamica fun = new FuncionDinamica(funStr);
    double intervaloA = consola.getInteger("Ingrese un valor para Xa: ", 0);
    double intervaloB = consola.getInteger("Ingrese un valor para Xb: ", 1);
    double err = consola.getDouble("Ingresa la tolerancia (ej. 0.01): ", false);
    int iteracionesMax = consola.getInteger("Iteraciones máximas", 16);
    
    double result = new Biseccion().evaluar(fun, intervaloA, intervaloB, err, iteracionesMax);

    System.out.println();
    System.out.printf(Chalk.bgGreen("La interseccion es: %f") + "\n", result);
  }
}

package code.c2;

import code.utils.FuncionDinamica;
import code.utils.Chalk;
import code.utils.Consola;

public class Biseccion {

  public double evaluar(FuncionDinamica f, double a, double b, double err) {
    System.out.printf("Intervalo (%.2f, %.2f) -> a: %.2f, b: %.2f\n", a, b, a, b);
    System.out.printf("Error < %.2f%%\n\n", err * 100);
    
    double medio;
    int i = 0;

    System.out.printf(
      Chalk.bold(Chalk.bgBlue("%-2s %-8s %-8s %-10s %-10s %-10s %-10s")),
      "i", "a", "b", "f(a)", "f(b)", "f(c)", "E"
    );
    System.out.println();
    
    do {
      medio = (a + b) / 2;
      double tempA = a, tempB = b;
      i++;

      if (f.evaluar(medio) > 0) b = medio;
      else a = medio;

      System.out.printf(
        "%-2d %-8.6f %-8.6f %-10.6f %-10.6f %-10.6f %-10.6f\n",
        i,
        tempA,
        tempB,
        f.evaluar(tempA),
        f.evaluar(tempB),
        f.evaluar(medio),
        (tempB - tempA) / 2
      );
      
    } while (Math.abs(b - a) > Math.abs(err));

    return Math.abs(b - a);
  }

  public static void main(String[] args) {
    Consola consola = new Consola();

    String funStr = consola.getString("Ingrese la Funcion", "e^(3x) - 4");
    double intervaloA = consola.getDouble("Ingrese un valor para Xa: ");
    double intervaloB = consola.getDouble("Ingrese un valor para Xb: ");
    double err = consola.getDouble("Ingresa la tolerancia (ej. 0.01): ");
    
    FuncionDinamica fun = new FuncionDinamica(funStr);
    double result = new Biseccion().evaluar(fun, intervaloA, intervaloB, err);

    System.out.println();
    System.out.println(Chalk.bgGreen("La interseccion es: " + result));
  }
}

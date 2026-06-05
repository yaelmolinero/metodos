package code;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

import code.utils.Chalk;
import code.utils.Consola;

import code.c1.ErrorEjecucion;
import code.c1.ErrorLogico;
import code.c1.CompilarError;
import code.c2.Biseccion;
import code.c2.InterpolacionNewton;
import code.c3.GaussJordan;
import code.c3.GaussSeindel;
import code.c4.PolinomioNewton;

public class App {
  public static void main(String[] args) {
    Consola sc = new Consola();
    String opcion;

    while (true) {
      sc.clearScreen();
      printMenu();
      opcion = sc.getString(Chalk.yellow(">> "));
      if (opcion.equals("0")) break;

      try {
        switch (opcion) {
          case "1":
            while (true) {
              printMenuErrores();
              String op = sc.getString(Chalk.yellow(">> "));
              if (op.equals("0")) break;

              String errorCompilacionPath = "app/src/main/java/code/c1/ErrorCompilacion.java";
              String errorEjecucionPath = "app/src/main/java/code/c1/ErrorEjecucion.java";
              String errorLogicoPath = "app/src/main/java/code/c1/ErrorLogico.java";

              try {
                switch (op) {
                  case "1":
                    printCodeFile(errorEjecucionPath);
                    ErrorEjecucion.main(args);
                    break;
                    
                    case "2":
                    printCodeFile(errorCompilacionPath);
                    CompilarError.main(args);
                    break;
                    
                  case "3":
                    printCodeFile(errorLogicoPath);
                    ErrorLogico.main(args);
                    break;

                  default: throw new Error("Opción invalida, eliga nuevamente.");
                }
              } catch (ArithmeticException e) {
                System.out.print(Chalk.BOLD + Chalk.RED);
                e.printStackTrace();
                System.out.println(Chalk.RESET);
              }
            }
            break;

          case "2": Biseccion.main(args); break;
          case "3": InterpolacionNewton.main(args); break;
          case "4": GaussJordan.main(args); break;
          case "5": GaussSeindel.main(args); break;
          case "6": PolinomioNewton.main(args); break;
        
          default: throw new Error("Opción invalida, eliga nuevamente.");
        }

      } catch (ArithmeticException e) {
        System.out.print(Chalk.BOLD + Chalk.RED);
        e.printStackTrace();
        System.out.println(Chalk.RESET);
      } catch (Error e) {
        System.out.println(Chalk.bold(Chalk.red(e.getMessage())));
      }

      if (!opcion.equals("1")) {
        System.out.println();
        sc.waitUntilEnter();
      }
    }
  }

  static void printMenu() {
    System.out.print(Chalk.BOLD);
    System.out.println("""
      +-----------------------------------------------------+
      |  MENÚ PRINCIPAL                                     |
      +-----------------------------------------------------+
      |                                                     |
      |  [1] Errores                                        |
      |  [2] Método de bisección                            |
      |  [3] Interpolación Newton                           |
      |  [4] Método Gauss-Jordan                            |
      |  [5] Método Gauss-Seindel                           |
      |  [6] Polinomio de interpolación de Newton           |
      |  [7] Polinomio de interpolación de Lagrage          |
      |  [0] Salir                                          |
      |                                                     |
      +-----------------------------------------------------+
    """);
    System.out.print(Chalk.RESET  );
  }

  static void printMenuErrores() {
    System.out.println("""
      +-----------------------------------------------------+
      |  MENÚ ERRORES                                       |
      +-----------------------------------------------------+
      |  [1] Error en tiempo de  ejecución                  |
      |  [2] Error de compilación                           |
      |  [3] Error lógico                                   |
      |  [0] Regresar                                       |
      +-----------------------------------------------------+
    """);
  }

  static void printCodeFile(String path) {
    try {
      java.util.List<String> lineas = Files.readAllLines(Paths.get(path));
      int i = 1;

      System.out.println("========== CÓDIGO FUENTE ==========");
      System.out.print(Chalk.BOLD + Chalk.BLUE);
      for (String line: lineas) {
        System.out.printf("%-3d | %s\n", i++, line);
      }
      System.out.println(Chalk.RESET);

    } catch (IOException e) {
      System.out.println("No se pudo leer el archivo.");
    }
  }
}

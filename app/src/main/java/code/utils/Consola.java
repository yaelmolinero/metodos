package code.utils;
import java.util.Scanner;

public class Consola {
  public Scanner sc;

  public Consola() {
    this.sc = new Scanner(System.in);
  }

  public String getString(String label, String _default) {
    System.out.print(
      Chalk.bold(label) +
      Chalk.gray(" (default: " + _default + ") ") + ": "
    );
    String str = sc.nextLine().trim();

    return str.equals("") ? _default : str;
  }

  public String getString(String label) {
    System.out.print(Chalk.bold(label));
    String str = sc.nextLine();

    if (str.trim().equals("")) throw new Error("Ingrese una cadena valida.");
    return str;
  }

  public double getDouble(String label) {
    System.out.print(Chalk.bold(label) + ": ");
    try {
      return sc.nextDouble();
    }
    catch (Exception err) { throw new Error("Ingrese un número valido."); }
  }

  public int getInteger(String label, int _default) {
    System.out.print(
      Chalk.bold(label) +
      Chalk.gray(" (default: " + _default + ") ") + ": "
    );
    String str = sc.nextLine();

    try {
      return str.equals("") ? _default : Integer.parseInt(str);
    } catch (Exception e) { throw new Error("Ingresa un numero valido"); }
  }

  public int getInteger(String label) {
    System.out.print(Chalk.bold(label) + ": ");
    try {
      return sc.nextInt();
    } catch (Exception err) { throw new Error("Imgrese un numero valido."); }
  }

  public boolean getBoolean(String label) {
    System.out.print(Chalk.bold(label) + " (y/n): ");
    String asnwer = sc.nextLine();

    return asnwer.equals("y") ? true : false;
  }

  public double[][] getMatriz(int n) {
    double[][] matriz = new double[n][n + 1];

    for (int i = 0; i < n; i++) {
      System.out.println();
      System.out.println(Chalk.bgBlue("  Ecuacion" + (i + 1) + "  "));
      for (int j = 0; j < n + 1; j++) {
        String str =
          j == n
          ? String.format("b%d", i + 1)
          : String.format("a%d%d", i + 1, j + 1);

        matriz[i][j] = this.getDouble(str);
      }
    }

    return matriz;
  }

  public void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public void waitUntilEnter() {
    System.out.println("Presione ENTER para continuar...");
    sc.nextLine();
  }

  public void close() {
    sc.close();
  }
}

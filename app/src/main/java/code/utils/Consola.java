package code.utils;
import java.util.Scanner;

public class Consola {
  private Scanner sc;

  public Consola() {
    this.sc = new Scanner(System.in);
  }

  public String getString(String label, String defaultFun) {
    System.out.print(
      Chalk.bold(label) +
      Chalk.gray(" (default: " + defaultFun + ") ") + ": "
    );
    String str = sc.nextLine().trim();

    return str.equals("") ? defaultFun : str;
  }

  public String getString(String label) {
    System.out.print(Chalk.bold(label));
    String str = sc.nextLine();

    if (str.trim().equals("")) throw new Error("Ingresa una función valida.");
    return str;
  }

  public double getDouble(String label) {
    System.out.print(Chalk.bold(label) + ": ");
    try {
      return sc.nextDouble();
    }
    catch (Exception err) { throw new Error("Ingrese un número valido."); }
  }
}

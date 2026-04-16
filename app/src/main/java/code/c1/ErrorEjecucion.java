package code.c1;

public class ErrorEjecucion {
  public static void main(String[] args) {
    int var1 = 15, var2 = 5, var3 = 0;
    int res1 = var1 / var2;

    // Error de ejecución:
    int res2 = var1 / var3;

    System.out.println("División de var1 por var2 es: " + res1);
    System.out.println("División de var1 por var3 es: " + res2);
  }
}

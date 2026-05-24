package code.c1;
import code.utils.Chalk;

public class ErrorLogico {
  public static void main(String[] args) {
    float num =  789, residuo;
    
    // Para obtener el módulo utilizamos el simbolo %, en su lugar
    // se esta usando el simbolo /
    residuo = num / 10;
    System.out.println(Chalk.bgGreen("  El modulo es: " + residuo + "  "));
  }
}

package code.c1;
import code.utils.Chalk;

import javax.tools.*;
import java.io.File;
import java.util.Arrays;

public class CompilarError {
  public static void main(String[] args) {
    String path = "app/src/main/java/code/c1/ErrorCompilacion.java";
    File archivo = new File(path);
    
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    DiagnosticCollector<JavaFileObject> diagnosticos = new DiagnosticCollector<>();
    StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnosticos, null, null);
    Iterable<? extends JavaFileObject> archivos = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(archivo));

    JavaCompiler.CompilationTask tarea = compiler.getTask(
      null,
      fileManager,
      diagnosticos,
      null,
      null,
      archivos
    );

    boolean exito = tarea.call();

    if (exito) System.out.println("Compilación exitosa");
    else {
      System.out.print(Chalk.BOLD + Chalk.RED);
      System.out.println("Errores de compilación:");
      for (Diagnostic<?> d : diagnosticos.getDiagnostics()) {
        System.out.println("-------------------");
        System.out.println("Línea: " + d.getLineNumber() + ", Columna: " + d.getColumnNumber());
        System.out.println("Mensaje: " + d.getMessage(null));
        System.out.println("Código: " + d.getCode());
      }
      System.out.println(Chalk.RESET);
    }
  }
}
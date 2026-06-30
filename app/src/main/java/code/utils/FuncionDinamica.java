package code.utils;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;

public class FuncionDinamica {
  private Expression fun;

  public FuncionDinamica(String expression) {
    try {
      this.fun = new ExpressionBuilder(expression)
        .variable("x")
        .build();

      fun.validate();
      
    } catch (UnknownFunctionOrVariableException e) {
      throw new Error("Ingrese una función valida.");
    }
  }

  public double evaluar(double x) {
    return fun.setVariable("x", x).evaluate();
  }
}

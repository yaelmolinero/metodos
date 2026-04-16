package code.utils;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class FuncionDinamica {
  private Expression fun;

  public FuncionDinamica(String expression) {
    this.fun = new ExpressionBuilder(expression)
      .variable("x")
      .build();
  }

  public double evaluar(double x) {
    return fun.setVariable("x", x).evaluate();
  }
}

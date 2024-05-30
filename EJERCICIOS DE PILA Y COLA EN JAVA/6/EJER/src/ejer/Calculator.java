import java.util.Stack;

public class Calculator {
    public static double evaluatePostfix(String expression) {
        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch)) {
                stack.push((double)(ch - '0'));
            } else {
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                switch (ch) {
                    case '+':
                        stack.push(operand1 + operand2);
                        break;
                    case '-':
                        stack.push(operand1 - operand2);
                        break;
                    case '*':
                        stack.push(operand1 * operand2);
                        break;
                    case '/':
                        stack.push(operand1 / operand2);
                        break;
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String postfixExpression = "66+2*51-*"; 
        double result = evaluatePostfix(postfixExpression);
        System.out.println("Resultado: " + result);
    }
}


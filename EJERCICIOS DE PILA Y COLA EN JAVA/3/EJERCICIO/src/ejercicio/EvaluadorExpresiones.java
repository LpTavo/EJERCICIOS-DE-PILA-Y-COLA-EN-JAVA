import java.util.Stack;

public class EvaluadorExpresiones {

 
    public static double evaluarExpresionInfija(String expresion) {
        Stack<Double> operandos = new Stack<>();
        Stack<Character> operadores = new Stack<>();

       
        expresion = expresion.replaceAll("\\s", "");

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

            
            if (Character.isDigit(c)) {
                StringBuilder numero = new StringBuilder();
                while (i < expresion.length() && Character.isDigit(expresion.charAt(i))) {
                    numero.append(expresion.charAt(i++));
                }
                i--; 
                operandos.push(Double.valueOf(numero.toString()));
            }
          
            else if (esOperador(c)) {
                while (!operadores.isEmpty() && precedencia(c) <= precedencia(operadores.peek())) {
                    operandos.push(aplicarOperador(operadores.pop(), operandos.pop(), operandos.pop()));
                }
                operadores.push(c);
            }
            
            else if (c == '(') {
                operadores.push(c);
            }
           
            else if (c == ')') {
                while (operadores.peek() != '(') {
                    operandos.push(aplicarOperador(operadores.pop(), operandos.pop(), operandos.pop()));
                }
                operadores.pop(); 
            }
        }

       
        while (!operadores.isEmpty()) {
            operandos.push(aplicarOperador(operadores.pop(), operandos.pop(), operandos.pop()));
        }

       
        return operandos.pop();
    }

   
    private static boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    
    private static int precedencia(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    
    private static double aplicarOperador(char operador, double b, double a) {
        switch (operador) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("División por cero no permitida");
                }
                return a / b;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        String expresionInfija = "2+3*4"; 
        double resultado = evaluarExpresionInfija(expresionInfija);
        System.out.println("Resultado de la expresión infija '" + expresionInfija + "': " + resultado);
    }
}

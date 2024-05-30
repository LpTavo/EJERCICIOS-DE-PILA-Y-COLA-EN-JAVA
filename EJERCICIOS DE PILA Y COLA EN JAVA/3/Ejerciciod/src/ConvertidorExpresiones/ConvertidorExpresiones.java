import java.util.Stack;

public class ConvertidorExpresiones {

    
    private static boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    
    private static int precedencia(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

   
    public static String infijaAPostfija(String expresionInfija) {
        StringBuilder resultado = new StringBuilder();
        Stack<Character> pila = new Stack<>();

        for (char c : expresionInfija.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                resultado.append(c);
            } else if (c == '(') {
                pila.push(c);
            } else if (c == ')') {
                while (!pila.isEmpty() && pila.peek() != '(') {
                    resultado.append(pila.pop());
                }
                pila.pop(); 
            } else {
                while (!pila.isEmpty() && precedencia(c) <= precedencia(pila.peek())) {
                    resultado.append(pila.pop());
                }
                pila.push(c);
            }
        }

        while (!pila.isEmpty()) {
            resultado.append(pila.pop());
        }

        return resultado.toString();
    }

    
    public static String postfijaAInfija(String expresionPostfija) {
        Stack<String> pila = new Stack<>();

        for (char c : expresionPostfija.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                pila.push(String.valueOf(c));
            } else {
                String operand2 = pila.pop();
                String operand1 = pila.pop();
                pila.push("(" + operand1 + c + operand2 + ")");
            }
        }

        return pila.pop();
    }

    public static void main(String[] args) {
        String expresionInfija1 = "(x+y)*(z^(s+t)+u)-v";
        String expresionInfija2 = "a+((x+y)*(w+v))^(i+j)";

        String expresionPostfija1 = infijaAPostfija(expresionInfija1);
        String expresionInfijaResultado1 = postfijaAInfija(expresionPostfija1);

        String expresionPostfija2 = infijaAPostfija(expresionInfija2);
        String expresionInfijaResultado2 = postfijaAInfija(expresionPostfija2);

        System.out.println("Expresión Infija 1: " + expresionInfija1);
        System.out.println("Expresión Postfija 1: " + expresionPostfija1);
        System.out.println("Expresión Infija Recuperada 1: " + expresionInfijaResultado1);
        System.out.println();
        System.out.println("Expresión Infija 2: " + expresionInfija2);
        System.out.println("Expresión Postfija 2: " + expresionPostfija2);
        System.out.println("Expresión Infija Recuperada 2: " + expresionInfijaResultado2);
    }
}

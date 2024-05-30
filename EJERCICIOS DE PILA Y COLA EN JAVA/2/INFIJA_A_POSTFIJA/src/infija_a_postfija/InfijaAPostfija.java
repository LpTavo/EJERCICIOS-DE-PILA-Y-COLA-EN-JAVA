import java.util.Stack;

public class InfijaAPostfija {

    
    public static boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }


    public static int precedencia(char c) {
        switch (c) {
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

   
    public static String convertirInfijaAPostfija(String expresion) {
        StringBuilder resultado = new StringBuilder();
        Stack<Character> pila = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

           
            if (Character.isLetterOrDigit(c)) {
                resultado.append(c);
            }
           
            else if (c == '(') {
                pila.push(c);
            }
           
            else if (c == ')') {
                while (!pila.isEmpty() && pila.peek() != '(') {
                    resultado.append(pila.pop());
                }
                pila.pop();
            }
           
            else if (esOperador(c)) {
                while (!pila.isEmpty() && precedencia(c) <= precedencia(pila.peek())) {
                    resultado.append(pila.pop());
                }
                pila.push(c);
            }
        }

       
        while (!pila.isEmpty()) {
            if (pila.peek() == '(') {
                return "Expresión inválida";
            }
            resultado.append(pila.pop());
        }

        return resultado.toString();
    }

    public static void main(String[] args) {
        String expresionInfija = "a+b*(c^d-e)^(f+g*h)-i";
        String expresionPostfija = convertirInfijaAPostfija(expresionInfija);
        System.out.println("Expresión Infija: " + expresionInfija);
        System.out.println("Expresión Postfija: " + expresionPostfija);
    }
}


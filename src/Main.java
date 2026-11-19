import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MathEvaluator evaluator = new MathEvaluator();

        while (true) {
            System.out.print("> ");
            String expression = scanner.nextLine().trim();

            if (expression.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            try {
                double result = evaluator.calculate(expression);
                System.out.printf("%f%n", result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}

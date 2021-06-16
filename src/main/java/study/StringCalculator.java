package study;

import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;

public class StringCalculator {

    private String input;
    private LinkedList<Integer> numbers = new LinkedList<>();
    private LinkedList<String> operators = new LinkedList<>();
    private int result;

    public void input () {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        String input = sc.nextLine();

        String[] str = input.split(" ");

        for (String e : str) {
            if (e.matches("-?\\d+")) {
                operators.add(e);
            } else {
                numbers.add(Integer.parseInt(e));
            }
        }
    }

    public int calculate() {
        result = 0;

        result = numbers.poll();

        while (numbers.size()>0) {
            switch (operators.poll()) {
                case "+" :
                    result = Operation.PLUS.calculable.calculate(result, numbers.poll());
                    break;
                case "-" :
                    result = Operation.MINUS.calculable.calculate(result, numbers.poll());
                    break;
                case "*" :
                    result = Operation.MUL.calculable.calculate(result, numbers.poll());
                    break;
                case "/" :
                    result = Operation.DIV.calculable.calculate(result, numbers.poll());
                    break;
            }
        }
        return result;
    }

    public interface Calculable {
        int calculate(int e1, int e2);
    }

    enum Operation {
        PLUS((a, b) -> a + b),
        MINUS((a, b) -> a - b),
        MUL((a, b) -> a * b),
        DIV((a, b) -> a / b);

        private final Calculable calculable;

        Operation(Calculable calculable) {
            this.calculable = calculable;
        }

        public Calculable calculate() {
            return calculable;
        }
    }
}

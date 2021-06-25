package study;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.BiFunction;

public class StringCalculator {

    private static Map<String, BiFunction<Double, Double, Double>> operators = new HashMap<>();
    private LinkedList<Integer> numbers = new LinkedList<>();
    private LinkedList<String> operator = new LinkedList<>();
    private static double result = 0;

    public double load (String input) {
        String[] str = input.split(" ");

        for (String e : str) {
            if (e.matches("[+\\-*/]")) {
                operator.add(e);
            } else {
                numbers.add(Integer.parseInt(e));
            }
        }

        if (numbers.size() < 2) {
            throw new IllegalArgumentException();
        }

        if (operator.size() + 1 != numbers.size()) {
            throw new IllegalArgumentException();
        } else {
            result = numbers.poll();
            while (numbers.size() > 0) {
                result = calculate(operator.poll(), result, numbers.poll());
            }
            return result;
        }
    }

    static {
        operators.put("+", (e1, e2) -> e1 + e2);
        operators.put("-", (e1, e2) -> e1 - e2);
        operators.put("*", (e1, e2) -> e1 * e2);
        operators.put("/", (e1, e2) -> e1 / e2);
    }

    public static double calculate(String operator, double e1, double e2) {
        return operators.get(operator).apply(e1, e2);
    }

}

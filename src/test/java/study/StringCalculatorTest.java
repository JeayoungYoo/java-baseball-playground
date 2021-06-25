package study;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    private static StringCalculator scc;
    private Scanner sc;

    @Test
    void calculate() {
        String data = "4 - 1 * 3";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        sc = new Scanner(System.in);
        String input = sc.nextLine();

        assertTrue(scc.input(input) == 9);

    }

}
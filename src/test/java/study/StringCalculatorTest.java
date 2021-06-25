package study;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StringCalculatorTest {

    private StringCalculator scc;
    private Scanner sc;

    @BeforeEach
    void setUp() {
        scc = new StringCalculator();
    }

    @AfterEach
    void closeScanner() {
        sc.close();
    }

    @DisplayName("WRONG input Test")
    @ParameterizedTest
    @ValueSource(strings = "2 +")
    void calculate(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        sc = new Scanner(System.in);
        String data = sc.nextLine();

        assertThatThrownBy(() -> scc.load(data)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Input Test")
    @ParameterizedTest
    @CsvSource({
            "4 - 1 * 3,9",
            "3 / 1,3",
            "3 + 3 * 3,18"})
    void calculate(String input, double result) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        sc = new Scanner(System.in);
        String data = sc.nextLine();

        assertThat(scc.load(data)).isEqualTo(result);
    }

}
package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StringTest {

    @Test
    void replace() {
        String actual = "abc".replace("b", "d");
        assertThat(actual).isEqualTo("adc");
    }

    @Test
    void requirements1() {

        List<Integer> actual = Arrays.stream("1,2".split(",")).map(Integer::parseInt).collect(Collectors.toList());
        Integer[] arr = actual.toArray(new Integer[actual.size()]);

        assertThat(arr).contains(1, 2);

        actual = Arrays.stream("1".split(",")).map(Integer::parseInt).collect(Collectors.toList());
        arr = actual.toArray(new Integer[actual.size()]);

        assertThat(arr).containsExactly(1);
    }

    @Test
    void requirements2() {

        List<Integer> actual = Arrays.stream("(1,2)".substring(1,4).split(",")).map(Integer::parseInt).collect(Collectors.toList());
        Integer[] arr = actual.toArray(new Integer[actual.size()]);

        assertThat(arr).containsExactly(1, 2);
    }

    @Test
    @DisplayName("index를 벗어났을 때 예외발생")
    void requirements3() {

        String str = "abc";

        assertThatThrownBy(() -> {
            str.charAt(3);
        }).isInstanceOf(StringIndexOutOfBoundsException.class);
    }
}

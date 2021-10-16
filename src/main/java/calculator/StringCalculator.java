package calculator;


import java.util.Arrays;

class StringCalculator {
    public int add(String input) {
        if ( input.length() == 0 )
            return 0;
        return Arrays.stream(input.split(","))
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
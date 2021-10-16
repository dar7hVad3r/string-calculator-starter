package calculator;


import java.util.Arrays;

class StringCalculator {
    public int add(String input) {
        if ( input.length() == 0 )
            return 0;
        String regex = "[,\n]";
        return Arrays.stream(input.split(regex))
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
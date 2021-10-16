package calculator;


class StringCalculator {
    public int add(String input) {
        if ( input.length() == 0 )
            return 0;
        String[] strArr = input.split(",");
        int sum = 0;
        for ( String s : strArr )
            sum += Integer.parseInt(s);
        return sum;
    }
}
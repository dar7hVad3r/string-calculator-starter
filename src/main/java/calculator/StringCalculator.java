package calculator;


import java.util.Arrays;

class StringCalculator {
    public int add(String input) {
        if ( input.length() == 0 )
            return 0;
        StringBuilder delim = new StringBuilder("\\n,");
        StringBuilder actInput = new StringBuilder();
        if ( input.startsWith("//") ){
            delim.append(input, input.indexOf("//")+2, input.indexOf("\n"));
            actInput.append( input.substring(input.indexOf("\n")) );
        }else{
            actInput.append(input);
        }

        System.out.println( "delim" + delim.toString());
        System.out.println(actInput.toString());

        return Arrays.stream(actInput.toString().split( "["+delim+"]" ))
                .filter(i-> !i.isBlank())
                .mapToInt(Integer::parseInt)
                .sum();
    }
}


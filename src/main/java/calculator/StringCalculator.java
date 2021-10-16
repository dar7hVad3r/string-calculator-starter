package calculator;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

class StringCalculator {
    // addCalledCount variable could have been static.
    // but in problem, it expected to be called on object i.e. "public int getCalledCount();"
    // so the count returned will be how many times method is called on particular object
    private int addCalledCount;

    public StringCalculator(){
        addCalledCount = 0;
    }

    public int add(String input) {
        this.addCalledCount += 1;
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
        AtomicBoolean flag = new AtomicBoolean(false);
        List<String> negList = new ArrayList<>();
//        int sum = 0;
//        for ( String s : actInput.toString().split("["+delim+"]") ){
//            if ( !s.isBlank() && Integer.parseInt(s) < 0 ){
//                flag = true;
//                negList.add(s+" ");
//            } else if (!flag){
//                sum += s.isBlank() ? 0 : Integer.parseInt(s);
//            }
//        }
//        if (flag){
//            throw new IllegalArgumentException("negatives not allowed "+negList);
//        }
//        return sum;
        int sum = Arrays.stream(actInput.toString().split( "["+delim+"]" ))
                .filter(i-> !i.isBlank())
                .mapToInt(Integer::parseInt)
                .peek(i -> {
                    if ( i < 0 ) {
                        negList.add(String.valueOf(i));
                        flag.set(true);
                    }
                })
                .sum();
        if ( flag.get() == true ) throw new IllegalArgumentException("negatives not allowed "+negList);
        return sum;
    }
    public int GetCalledCount(){
        return this.addCalledCount;
    }
}


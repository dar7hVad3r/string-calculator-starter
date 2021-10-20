package calculator;
/*
[9:34 AM] Hardik Prajapati

 */

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
        // increment count each time method gets called
        this.addCalledCount += 1;

        // check if string is empty
        if ( input.length() == 0 )
            return 0;

        // StringBuilder objects delim and actInput for pattern matching and input filtration
        StringBuilder delim = new StringBuilder("\\n,");
        StringBuilder actInput = new StringBuilder();

        // flags for keeping track of E/O at beginning of string
        AtomicBoolean evenFlag = new AtomicBoolean(false);
        AtomicBoolean oddFlag = new AtomicBoolean(false);

        // set the flags according to starting value
        if ( input.startsWith("E") )
            evenFlag.set(true);
        else if ( input.startsWith("O") ) oddFlag.set(true);

        // check if input needs to be filtered
        if ( input.startsWith("//") ){
            delim.append(input, input.indexOf("//")+2, input.indexOf("\n")); // string between '//' and '\n' is delimiter
            actInput.append( input.substring(input.indexOf("\n")) ); // string after '\n' is input string
        }else{
            if ( !evenFlag.get() && !oddFlag.get() )
                actInput.append(input);
            else{
                if (evenFlag.get()) actInput.append(input.substring(input.indexOf("E")+1));
                else if( oddFlag.get() ) actInput.append(input.substring(input.indexOf("O")+1));
            }
        }

        // flag to verify no negative numbers, negList to keep track of multiple negative values
        AtomicBoolean flag = new AtomicBoolean(false);
        List<String> negList = new ArrayList<>();


//        Alternate code which can be used instead of using stream()
/*
        int sum = 0;
        for ( String s : actInput.toString().split("["+delim+"]") ){
            if ( !s.isBlank() && Integer.parseInt(s) < 0 ){
                flag.set(true);
                negList.add(s+" ");
            } else if (!flag.get()){
                if ( !s.isBlank() && (sum + Integer.parseInt(s)) > 1000 ) break;
                sum += s.isBlank() ? 0 : (Integer.parseInt(s)) > 1000 ? 0 : Integer.parseInt(s) ;
            }
        }
        if (flag.get()){
            throw new IllegalArgumentException("negatives not allowed "+negList);
        }
        return sum;
*/
        // iterate over each string that the delimiter has split
       int sum = Arrays.stream(actInput.toString().split( "["+delim+"]" ))
                .filter(i-> !i.isBlank())   // filter if the string is blank
                .mapToInt(Integer::parseInt)  // parse the integer value from string
                .peek(i -> {                    // check if number is negative and set flag and list if it is
                    if ( i < 0 ) {
                        negList.add(String.valueOf(i));
                        flag.set(true);
                    }
                })
                .filter(i -> i<=1000)   // filter if the number is greater than 1000
                .filter( i -> {
                    if ( !evenFlag.get() && !oddFlag.get() )
                        return true;
                    else{
                        if (evenFlag.get()) return i % 2 == 0;
                        return i % 2 != 0;
                    }
                } )
                .reduce((num1, num2)-> {
                    return num1 + num2;
                }).orElse(0);  // get addition of all the filtered values .reduce() can also be used
        if ( flag.get()  ) throw new IllegalArgumentException("negatives not allowed "+negList);


        return sum;
    }
    public int GetCalledCount(){
        return this.addCalledCount;
    }
}


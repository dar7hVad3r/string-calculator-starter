package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorShould {
    StringCalculator calculator;
    @BeforeEach
    void beginTest(){
        calculator = new StringCalculator();
    }

    @Test
    void empty_string_should_return_0(){
        assertEquals(0, calculator.add(""));
    }


}

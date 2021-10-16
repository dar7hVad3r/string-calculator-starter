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

    @Test
    void string_with_1_value_should_return_value(){
        assertEquals(1, calculator.add("1"));
    }
}

package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorShould {
    int count = 0;
    StringCalculator calculator;
    @BeforeEach
    void beginTest(){
        count++;
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

    @Test
    void string_with_comma_separated_values_should_return_addition(){
        assertEquals(3, calculator.add("1,2"));
    }

    @Test
    void string_with_unknown_number_of_values_return_addition(){
        assertEquals(45, calculator.add("1,2,3,4,5,6,7,8,9"));
    }

    @Test
    void string_with_slashN_as_delimiter(){
        assertEquals(6, calculator.add("1\n2,3"));
        assertEquals(9, calculator.add("3\n3\n3"));
    }

    @Test
    void string_with_custom_delimiter(){
        assertEquals(3, calculator.add("//;\n1;2"));
        assertEquals(3, calculator.add("//%\n1%2"));
        assertEquals(3, calculator.add("//*\n1*2"));
    }

    @Test
    void negative_number_should_throw_exception(){
        assertThrows(IllegalArgumentException.class, ()->{
            calculator.add("-1,2,3");
        }, "negatives not allowed -1");
    }

    @Test
    void multiple_negative_number_should_throw_exception(){
        assertThrows(IllegalArgumentException.class, ()->{
            calculator.add("-1,-2,3");
        }, "negatives not allowed -1 -2");
    }

    @Test
    void values_greater_than_1000_should_be_considered_0(){
        assertEquals(2, calculator.add("1001,2"));
        assertEquals(0, calculator.add("1001,2000"));
        assertEquals(1, calculator.add("1,2000"));
    }

    @Test
    void GetCallCount_should_return_number_of_times_add_called(){
        assertEquals(count-1, calculator.GetCalledCount());
    }

    @Test
    void delimiter_of_different_length_should_work(){
        assertEquals(6, calculator.add("//[***]\n1***2***3"));
        assertEquals(6, calculator.add("//[**;]\n1**;2**;3"));
        assertEquals(6, calculator.add("//[*;;]\n1*;;2*;;3"));
        assertEquals(6, calculator.add("//[;;;]\n1;;;2;;;3"));
    }

    @Test
    void multiple_delimiter_should_work(){
        assertEquals(6, calculator.add("//[*][%]\n1*2%3"));
        assertEquals(14, calculator.add("//[%][*]\n9*2%3"));
        assertEquals(7, calculator.add("//[&][@]\n2&2@3"));
    }

    @Test
    void multiple_delimiter_with_different_length_should_work(){
        assertEquals(6, calculator.add("//[***][%%]\n1***2%%3"));
        assertEquals(14, calculator.add("//[%][**]\n9**2%3"));
        assertEquals(7, calculator.add("//[##][@@@@]\n2##2@@@@3"));
    }
}

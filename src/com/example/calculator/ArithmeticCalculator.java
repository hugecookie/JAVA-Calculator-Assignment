package com.example.calculator;

import com.example.calculator.operator.Operator;
import com.example.calculator.operator.OperatorFactory;

/**
 * ✅ Operator 클래스를 활용하는 계산기
 */
public class ArithmeticCalculator {
    public static <T extends Number> double calculate(T num1, T num2, OperatorType operatorType) {
        Operator operator = OperatorFactory.getOperator(operatorType); // ✅ 연산자 객체 가져오기
        return operator.calculate(num1.doubleValue(), num2.doubleValue());
    }
}

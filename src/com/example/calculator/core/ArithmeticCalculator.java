package com.example.calculator.core;

import com.example.calculator.operator.Operator;

/**
 * ✅ OperatorFactory 없이 연산자 객체를 직접 가져오는 방식으로 변경
 */
public class ArithmeticCalculator {
    public static <T extends Number> double calculate(T num1, T num2, OperatorType operatorType) {
        Operator operator = operatorType.getOperator(); // ✅ 연산자 직접 가져오기
        return operator.calculate(num1.doubleValue(), num2.doubleValue());
    }
}

package com.example.calculator.operator;

/**
 * ✅ 덧셈 연산자 (Operator 인터페이스 구현)
 */
public class AdditionOperator implements Operator {
    @Override
    public double calculate(double num1, double num2) {
        return num1 + num2;
    }
}

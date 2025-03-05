package com.example.calculator.operator;

/**
 * ✅ 덧셈 연산을 수행하는 클래스
 */
public class AdditionOperator extends Operator {
    @Override
    public double calculate(double num1, double num2) {
        return num1 + num2;
    }
}

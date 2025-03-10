package com.example.calculator.operator;

/**
 * ✅ 거듭 제곱 연산을 수행하는 클래스
 */
public class PowerOperator implements Operator {
    @Override
    public double calculate(double num1, double num2) {
        return Math.pow(num1, num2);
    }
}
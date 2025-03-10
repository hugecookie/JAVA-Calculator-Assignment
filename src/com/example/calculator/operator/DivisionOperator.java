package com.example.calculator.operator;

/**
 * ✅ 나눗셈 연산을 수행하는 클래스
 */
public class DivisionOperator implements Operator {
    @Override
    public double calculate(double num1, double num2) {
        if (num2 == 0) throw new ArithmeticException("⚠ 0으로 나눌 수 없습니다.");
        return num1 / num2;
    }
}

package com.example.calculator.operator;

/**
 * ✅ 연산자 추상 클래스 (추상화 적용)
 * - 모든 연산자는 이 클래스를 상속받아 다형성을 적용
 */
public abstract class Operator {
    public abstract double calculate(double num1, double num2);
}

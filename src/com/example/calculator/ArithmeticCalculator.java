package com.example.calculator;

public class ArithmeticCalculator {
    public static <T extends Number> double calculate(T num1, T num2, OperatorType operator) {
        double a = num1.doubleValue();
        double b = num2.doubleValue();
        double result = 0.0;

        switch (operator) {
            case ADD -> result = a + b;
            case SUBTRACT -> result = a - b;
            case MULTIPLY -> result = a * b;
            case DIVIDE -> {
                if (b == 0) throw new ArithmeticException("⚠ 나눗셈 연산에서 분모(두 번째 정수)에 0이 입력될 수 없습니다.");
                result = a / b;
            }
        }

        // ✅ "-0.0"을 "0.0"으로 변환
        return result == -0.0 ? 0.0 : result;
    }
}

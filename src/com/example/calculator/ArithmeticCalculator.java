package com.example.calculator;

// ✅ 사칙연산을 수행하는 클래스 (제네릭 활용)
public class ArithmeticCalculator {
    /**
     * 제네릭 메서드
     * - 다양한 숫자 타입 (int, double 등)을 지원
     * - 연산 기호(OperatorType)에 따라 사칙연산을 수행
     */
    public static <T extends Number> double calculate(T num1, T num2, OperatorType operator) {
        double a = num1.doubleValue();
        double b = num2.doubleValue();

        // ✅ Switch 표현식을 사용한 사칙연산 수행
        double result = switch (operator) {
            case ADD -> a + b;
            case SUBTRACT -> a - b;
            case MULTIPLY -> a * b;
            case DIVIDE -> {
                if (b == 0) throw new ArithmeticException("⚠ 나눗셈 연산에서 분모(두 번째 정수)에 0이 입력될 수 없습니다.");
                yield a / b;
            }
        };

        return result == -0.0 ? 0.0 : result; // ✅ -0.0 방지 처리
    }
}

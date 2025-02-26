package com.example.calculator;

import java.util.Stack;

public class Calculator {
    private Stack<Double> results; // 최신 결과를 저장하는 스택 (LIFO)

    public Calculator() {
        results = new Stack<>();
    }

    // 연산 수행 후 결과 저장
    public <T extends Number> double calculate(T num1, T num2, OperatorType operator) {
        double result = ArithmeticCalculator.calculate(num1, num2, operator);
        results.push(result == -0.0 ? 0.0 : result); // ✅ -0.0을 0.0으로 변환하여 저장
        return result;
    }

    // 최신 연산 결과 삭제
    public void removeLatestResult() {
        if (!results.isEmpty()) {
            results.pop();
            System.out.println("✅ 최신 연산 결과가 삭제되었습니다.");
        } else {
            System.out.println("⚠ 삭제할 연산 결과가 없습니다.");
        }
    }

    // 저장된 연산 결과 반환 (방어적 복사 적용)
    public Stack<Double> getResults() {
        return new Stack<>() {{ addAll(results); }}; // ✅ Stack에 있는 요소를 복사
    }
}

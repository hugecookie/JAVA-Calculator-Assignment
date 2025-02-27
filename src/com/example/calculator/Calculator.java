package com.example.calculator;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Calculator {
    private Stack<Double> results; // 최신 결과를 저장하는 스택 (LIFO)

    public Calculator() {
        results = new Stack<>();
    }

    public <T extends Number> double calculate(T num1, T num2, OperatorType operator) {
        double result = ArithmeticCalculator.calculate(num1, num2, operator);
        results.push(result == -0.0 ? 0.0 : result);
        return result;
    }

    public void removeLatestResult() {
        if (!results.isEmpty()) {
            results.pop();
            System.out.println("✅ 최신 연산 결과가 삭제되었습니다.");
        } else {
            System.out.println("⚠ 삭제할 연산 결과가 없습니다.");
        }
    }

    public Stack<Double> getResults() {
        return new Stack<>() {{ addAll(results); }};
    }

    // ✅ 특정 값보다 큰 연산 결과 조회 (람다 & 스트림 적용)
    public List<Double> filterResultsGreaterThan(double threshold) {
        return results.stream()
                .filter(value -> value > threshold)
                .collect(Collectors.toList());
    }
}

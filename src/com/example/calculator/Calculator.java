package com.example.calculator;

import java.util.LinkedList;
import java.util.Queue;

public class Calculator {
    // 연산 결과를 저장하는 큐 (FIFO 방식 - 먼저 저장된 데이터를 먼저 삭제)
    private LinkedList<Double> results;

    // 생성자
    public Calculator() {
        results = new LinkedList<>();
    }

    // 사칙연산을 수행하고 결과 반환
    public double calculate(int num1, int num2, char operator) {
        double result = 0;
        boolean validOperation = true;

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    System.out.println("⚠ 나눗셈 연산에서 분모(두 번째 정수)에 0이 입력될 수 없습니다.");
                    validOperation = false;
                } else {
                    result = (double) num1 / num2;
                    result = Math.round(result * 100.0) / 100.0; // 소수점 둘째 자리 반올림
                }
                break;
            default:
                System.out.println("⚠ 올바른 연산자를 입력하세요 (+, -, *, /).");
                validOperation = false;
        }

        // 유효한 연산일 경우 결과 저장
        if (validOperation) {
            results.add(result);
        }

        return result;
    }

    // 연산 결과를 반환하는 Getter 메서드 (캡슐화 적용)
    public Queue<Double> getResults() {
        return new LinkedList<>(results); // 방어적 복사
    }

    // 연산 결과를 설정하는 Setter 메서드 (캡슐화 적용)
    public void setResults(Queue<Double> newResults) {
        this.results = new LinkedList<>(newResults);
    }

    // 가장 오래된 저장된 연산 결과를 삭제하는 메서드
    public void removeOldestResult() {
        if (!results.isEmpty()) {
            results.poll(); // FIFO 방식으로 가장 오래된 값 삭제
            System.out.println("✅ 가장 오래된 연산 결과를 삭제했습니다.");
        } else {
            System.out.println("⚠ 삭제할 연산 결과가 없습니다.");
        }
    }

    // 가장 최신 저장된 연산 결과를 삭제하는 메서드
    public void removeLatestResult() {
        if (!results.isEmpty()) {
            results.removeLast(); // LIFO 방식으로 가장 최신 값 삭제
            System.out.println("✅ 가장 최신 연산 결과를 삭제했습니다.");
        } else {
            System.out.println("⚠ 삭제할 연산 결과가 없습니다.");
        }
    }
}

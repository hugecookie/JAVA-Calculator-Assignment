package com.example.calculator;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * ✅ 사칙연산 결과를 저장하고 관리하는 계산기 클래스
 * - 최신 연산 결과를 스택(Stack)에 저장 (LIFO 방식)
 * - 연산 수행 및 결과 저장
 * - 특정 값보다 큰 연산 결과 필터링 (Stream 사용)
 * - 모든 연산 기록 삭제 기능 추가
 */
public class Calculator {
    private Stack<Double> results; // 최신 결과를 저장하는 스택 (LIFO - Last In First Out)

    /**
     * ✅ 생성자
     * - 연산 결과를 저장할 `Stack<Double>`을 초기화
     */
    public Calculator() {
        results = new Stack<>();
    }

    /**
     * ✅ 사칙연산을 수행하고 결과를 스택에 저장하는 메서드 (제네릭 활용)
     * @param num1 첫 번째 숫자 (제네릭 타입)
     * @param num2 두 번째 숫자 (제네릭 타입)
     * @param operator 사칙연산 기호 (Enum `OperatorType` 사용)
     * @return 연산 결과 (double)
     */
    public <T extends Number> double calculate(T num1, T num2, OperatorType operator) {
        double result = ArithmeticCalculator.calculate(num1, num2, operator);
        results.push(result == -0.0 ? 0.0 : result); // ✅ -0.0 방지 처리
        return result;
    }

    /**
     * ✅ 최신 연산 결과 삭제 (LIFO 방식 - Stack 사용)
     * - 스택이 비어 있지 않다면 가장 최근 저장된 연산 결과를 삭제
     * - 비어 있을 경우 메시지 출력
     */
    public void removeLatestResult() {
        if (!results.isEmpty()) {
            results.pop(); // 스택의 마지막 값 제거
            System.out.println("✅ 최신 연산 결과가 삭제되었습니다.");
        } else {
            System.out.println("⚠ 삭제할 연산 결과가 없습니다.");
        }
    }

    /**
     * ✅ 저장된 연산 결과 반환 (방어적 복사 적용)
     * - Stack의 직접 접근을 방지하고, 새로운 Stack 객체로 복사하여 반환
     * - 원본 데이터가 변경되지 않도록 보장
     * @return 현재 저장된 연산 결과 목록 (Stack<Double>)
     */
    public Stack<Double> getResults() {
        return new Stack<>() {{ addAll(results); }}; // ✅ 기존 Stack을 복사하여 반환 (방어적 복사)
    }

    /**
     * ✅ 특정 값보다 큰 연산 결과 조회 (Stream 활용)
     * - Stack 내부 데이터를 Stream으로 변환하여 필터링 수행
     * - Java 8 Stream API를 사용하여 특정 기준(threshold)보다 큰 값만 반환
     * @param threshold 기준이 되는 값
     * @return 기준값(threshold)보다 큰 연산 결과 리스트
     */
    public List<Double> filterResultsGreaterThan(double threshold) {
        return results.stream() // ✅ Stack을 Stream으로 변환
                .filter(value -> value > threshold) // ✅ 특정 값보다 큰 값만 필터링
                .collect(Collectors.toList()); // ✅ 결과를 List로 변환 후 반환
    }

    /**
     * ✅ 모든 연산 기록 삭제 기능 추가
     * - 저장된 연산 결과를 초기화하여 전체 기록 삭제
     */
    public void clearResults() {
        results.clear(); // ✅ 스택 비우기
        System.out.println("🗑 모든 연산 기록이 삭제되었습니다.");
    }
}

package com.example.calculator.core;

import com.example.calculator.utils.FileHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

/**
 * ✅ 사칙연산 결과를 저장하고 관리하는 계산기 클래스
 * - 연산 수행 및 결과 저장
 * - 특정 값보다 크거나 작은 연산 결과 조회
 * - 범위 내 검색 기능 추가
 * - 정렬 기능 추가 (오름차순, 내림차순)
 * - 모든 연산 기록 삭제 기능 추가
 */
public class Calculator {
    private List<Double> results; // ✅ Stack → List로 변경 (FIFO 방식)

    /**
     * ✅ 생성자 - 기존 연산 기록 불러오기
     */
    public Calculator() {
        results = FileHandler.loadResultsFromFile(); // ✅ 기존 연산 기록 불러오기
    }

    /**
     * ✅ 사칙연산을 수행하고 결과를 리스트에 저장하는 메서드 (제네릭 활용)
     */
    public <T extends Number> double calculate(T num1, T num2, OperatorType operator) {
        double result = operator.getOperator().calculate(num1.doubleValue(), num2.doubleValue());
        results.add(result == -0.0 ? 0.0 : result); // ✅ -0.0 방지 처리
        FileHandler.saveResultsToFile(results); // ✅ 파일 저장 기능을 FileHandler에서 호출
        return result;
    }

    /**
     * ✅ 최신 연산 결과 삭제 (FIFO 방식 - List 사용)
     */
    public void removeLatestResult() {
        if (!results.isEmpty()) {
            results.remove(results.size() - 1);
            FileHandler.saveResultsToFile(results); // ✅ 파일 저장 반영
            System.out.println("✅ 최신 연산 결과가 삭제되었습니다.");
        } else {
            System.out.println("⚠ 삭제할 연산 결과가 없습니다.");
        }
    }

    /**
     * ✅ 모든 연산 기록 삭제 기능 추가
     */
    public void clearResults() {
        results.clear();
        FileHandler.saveResultsToFile(results); // ✅ 파일 저장 반영
        System.out.println("🗑 모든 연산 기록이 삭제되었습니다.");
    }

    /**
     * ✅ 저장된 연산 결과 반환 (방어적 복사 적용)
     */
    public List<Double> getResults() {
        return new ArrayList<>(results);
    }

    /**
     * ✅ 특정 값보다 크거나 작은 연산 결과 조회
     */
    public List<Double> filterResults(double threshold, String condition) {
        return results.stream()
                .filter(value -> switch (condition) {
                    case "greater" -> value > threshold;
                    case "less" -> value < threshold;
                    default -> false;
                })
                .collect(Collectors.toList());
    }

    /**
     * ✅ 범위 검색 기능 추가
     */
    public List<Double> filterResultsByRange(double min, double max) {
        return results.stream()
                .filter(value -> value >= min && value <= max)
                .collect(Collectors.toList());
    }

    /**
     * ✅ 연산 결과 정렬 기능 추가
     */
    public List<Double> sortResults(String order) {
        return results.stream()
                .sorted(order.equals("asc") ? Comparator.naturalOrder() : Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    /**
     * ✅ 특정 값과 동일한 연산 결과 검색
     */
    public List<Double> filterResultsByEqual(double target) {
        return results.stream()
                .filter(value -> Double.compare(value, target) == 0)
                .collect(Collectors.toList());
    }
}

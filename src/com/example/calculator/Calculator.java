package com.example.calculator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.Comparator;

/**
 * ✅ 사칙연산 결과를 저장하고 관리하는 계산기 클래스
 * - 최신 연산 결과를 List에 저장
 * - 연산 수행 및 결과 저장
 * - 특정 값보다 크거나 작은 연산 결과 조회
 * - 범위 내 검색 기능 추가
 * - 정렬 기능 추가 (오름차순, 내림차순)
 * - 모든 연산 기록 삭제 기능 추가
 * - 프로그램 종료 후에도 연산 기록 유지 (파일 저장 추가)
 */
public class Calculator {
    private List<Double> results; // ✅ Stack → List로 변경 (FIFO 방식)
    private static final String FILE_PATH = "results.txt"; // ✅ 연산 결과 저장 파일

    /**
     * ✅ 생성자 - 연산 결과를 저장할 `List<Double>`을 초기화 & 기존 데이터 불러오기
     */
    public Calculator() {
        results = new ArrayList<>();
        loadResultsFromFile(); // ✅ 기존 연산 기록 불러오기
    }

    /**
     * ✅ 사칙연산을 수행하고 결과를 리스트에 저장하는 메서드 (제네릭 활용)
     * @param num1 첫 번째 숫자 (제네릭 타입)
     * @param num2 두 번째 숫자 (제네릭 타입)
     * @param operator 사칙연산 기호 (Enum `OperatorType` 사용)
     * @return 연산 결과 (double)
     */
    public <T extends Number> double calculate(T num1, T num2, OperatorType operator) {
        double result = ArithmeticCalculator.calculate(num1, num2, operator);
        results.add(result == -0.0 ? 0.0 : result); // ✅ -0.0 방지 처리
        saveResultsToFile(); // ✅ 연산 결과 저장
        return result;
    }

    /**
     * ✅ 최신 연산 결과 삭제 (FIFO 방식 - List 사용)
     * - 리스트가 비어 있지 않다면 가장 최근 저장된 연산 결과를 삭제
     * - 비어 있을 경우 메시지 출력
     */
    public void removeLatestResult() {
        if (!results.isEmpty()) {
            results.remove(results.size() - 1);
            saveResultsToFile(); // ✅ 연산 결과 저장
            System.out.println("✅ 최신 연산 결과가 삭제되었습니다.");
        } else {
            System.out.println("⚠ 삭제할 연산 결과가 없습니다.");
        }
    }

    /**
     * ✅ 저장된 연산 결과 반환 (방어적 복사 적용)
     * - List의 직접 접근을 방지하고, 새로운 List 객체로 복사하여 반환
     * - 원본 데이터가 변경되지 않도록 보장
     * @return 현재 저장된 연산 결과 목록 (List<Double>)
     */
    public List<Double> getResults() {
        return new ArrayList<>(results);
    }

    /**
     * ✅ 특정 값보다 큰/작은 연산 결과 조회
     * - 조건에 따라 특정 값보다 큰 값(greater) 또는 작은 값(less) 필터링
     * @param threshold 기준값
     * @param condition "greater" → 큰 값 조회, "less" → 작은 값 조회
     * @return 필터링된 결과 목록
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
     * ✅ **범위 검색 기능 추가**
     * - 최소값과 최대값을 입력하면 해당 범위 내에 있는 연산 결과 반환
     * @param min 최소값
     * @param max 최대값
     * @return 지정된 범위 내의 연산 결과 목록
     */
    public List<Double> filterResultsByRange(double min, double max) {
        return results.stream()
                .filter(value -> value >= min && value <= max)
                .collect(Collectors.toList());
    }

    /**
     * ✅ 연산 결과 정렬 기능 추가
     * - "asc" 입력 시 오름차순 정렬
     * - "desc" 입력 시 내림차순 정렬
     * @param order 정렬 방식 ("asc" 또는 "desc")
     * @return 정렬된 연산 결과 목록
     */
    public List<Double> sortResults(String order) {
        return results.stream()
                .sorted(order.equals("asc") ? Comparator.naturalOrder() : Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    /**
     * ✅ 특정 값과 동일한 연산 결과 검색
     * - 사용자가 입력한 기준값을 `double`로 변환 후 리스트 내 값과 비교
     */
    public List<Double> filterResultsByEqual(double target) {
        return results.stream()
                .filter(value -> Double.compare(value, target) == 0) // ✅ 정확한 일치 비교
                .collect(Collectors.toList());
    }

    /**
     * ✅ 모든 연산 기록 삭제 기능 추가
     * - 저장된 연산 결과를 초기화하여 전체 기록 삭제
     */
    public void clearResults() {
        results.clear();
        saveResultsToFile(); // ✅ 연산 결과 저장
        System.out.println("🗑 모든 연산 기록이 삭제되었습니다.");
    }

    /**
     * ✅ 연산 결과를 파일에 저장하는 기능 추가
     */
    public void saveResultsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            results.stream()
                    .map(String::valueOf)  // ✅ Double을 String으로 변환
                    .forEach(line -> {
                        try {
                            writer.write(line);
                            writer.newLine();
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);  // ✅ 예외를 래핑하여 처리
                        }
                    });
        } catch (IOException e) {
            System.out.println("⚠ 연산 결과 저장 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * ✅ 프로그램 실행 시 파일에서 기존 연산 결과 불러오는 기능 추가
     */
    private void loadResultsFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            results = reader.lines()
                    .map(line -> {
                        try {
                            return Double.parseDouble(line);
                        } catch (NumberFormatException e) {
                            System.out.println("⚠ 잘못된 데이터 형식: " + line);
                            return null;  // 잘못된 데이터는 무시
                        }
                    })
                    .filter(Objects::nonNull)  // null 값 제거
                    .collect(Collectors.toList());  // 리스트로 변환
        } catch (IOException e) {
            System.out.println("⚠ 연산 결과 불러오기 중 오류 발생: " + e.getMessage());
        }
    }
}

package com.example.calculator;

import java.util.List;
import java.util.Scanner;

/**
 * ✅ 검색 및 정렬 기능을 담당하는 클래스
 * - 특정 값보다 크거나 작은 값 검색
 * - 특정 값과 동일한 값 검색
 * - 범위 내 검색 기능 추가
 * - 연산 결과 정렬 기능 추가
 */
public class SearchAndSortService {
    private final Calculator calculator; // ✅ Calculator 인스턴스 사용

    public SearchAndSortService(Calculator calculator) {
        this.calculator = calculator;
    }

    /**
     * ✅ 검색 및 정렬 메뉴 실행
     */
    public void searchAndSortMenu(Scanner sc) {
        while (true) {
            System.out.println("\n========= 검색 및 정렬 기능 =========");
            System.out.println("1. 특정 값보다 큰 값 검색 (greater)");
            System.out.println("2. 특정 값보다 작은 값 검색 (less)");
            System.out.println("3. 특정 값과 동일한 값 검색 (equal)");
            System.out.println("4. 연산 결과 오름차순 정렬 (asc)");
            System.out.println("5. 연산 결과 내림차순 정렬 (desc)");
            System.out.println("6. 특정 범위 내 값 검색");
            System.out.println("🚪 'exit' 입력 시 메인 메뉴로 이동");
            System.out.print("👉 원하는 기능을 선택하세요: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> filterResultsByCondition(sc, "greater"); // ✅ Calculator에서 검색 기능 불러오기
                case "2" -> filterResultsByCondition(sc, "less");
                case "3" -> filterResultsByEqual(sc);
                case "4" -> System.out.println("✅ 오름차순 정렬된 결과: " + calculator.sortResults("asc"));
                case "5" -> System.out.println("✅ 내림차순 정렬된 결과: " + calculator.sortResults("desc"));
                case "6" -> filterResultsByRange(sc);
                case "exit" -> {
                    return;
                }
                default -> System.out.println("⚠ 올바른 번호를 입력하세요 (1~6 또는 'exit').");
            }
        }
    }

    /**
     * ✅ 특정 값보다 크거나 작은 결과 검색 기능
     */
    private void filterResultsByCondition(Scanner sc, String condition) {
        while (true) {
            System.out.print("기준값을 입력하세요 ('n' 입력 시 뒤로 가기): ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("n")) return;

            try {
                double threshold = Double.parseDouble(input);
                List<Double> filteredResults = calculator.filterResults(threshold, condition); // ✅ Calculator에서 데이터 가져오기
                System.out.println("✅ 검색 결과: " + filteredResults);
                return;
            } catch (NumberFormatException e) {
                System.out.println("⚠ 올바른 숫자를 입력하세요.");
            }
        }
    }

    /**
     * ✅ 특정 값과 정확히 동일한 값 검색
     */
    private void filterResultsByEqual(Scanner sc) {
        while (true) {
            System.out.print("기준값을 입력하세요 ('n' 입력 시 뒤로 가기): ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("n")) return;

            try {
                double target = Double.parseDouble(input);
                List<Double> filteredResults = calculator.filterResultsByEqual(target); // ✅ Calculator에서 데이터 가져오기
                System.out.println("✅ 검색 결과: " + filteredResults);
                return;
            } catch (NumberFormatException e) {
                System.out.println("⚠ 올바른 숫자를 입력하세요.");
            }
        }
    }

    /**
     * ✅ 특정 범위 내 값 검색 기능
     */
    private void filterResultsByRange(Scanner sc) {
        while (true) {
            System.out.print("최소값을 입력하세요 ('n' 입력 시 뒤로 가기): ");
            String minInput = sc.nextLine().trim();
            if (minInput.equalsIgnoreCase("n")) return;

            System.out.print("최대값을 입력하세요: ");
            String maxInput = sc.nextLine().trim();

            try {
                double min = Double.parseDouble(minInput);
                double max = Double.parseDouble(maxInput);

                List<Double> filteredResults = calculator.filterResultsByRange(min, max); // ✅ Calculator에서 데이터 가져오기
                System.out.println("✅ 검색 결과: " + filteredResults);
                return;
            } catch (NumberFormatException e) {
                System.out.println("⚠ 올바른 숫자를 입력하세요.");
            }
        }
    }
}

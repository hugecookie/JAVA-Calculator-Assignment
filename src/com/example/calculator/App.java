package com.example.calculator;

import java.util.List;
import java.util.Scanner;

/**
 * ✅ 메인 실행 클래스 (메뉴 기반 인터페이스 추가)
 * - 사용자 입력을 받아 계산기 기능 수행
 * - 초기 화면에서 번호를 입력하면 해당 기능으로 이동
 * - "clear" 또는 5 입력 시 모든 연산 기록 삭제
 */
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calculator = new Calculator();

        while (true) {
            // ✅ 사용자에게 기능 선택 요청
            System.out.println("\n========= 계산기 프로그램 =========");
            System.out.println("1. 저장된 연산 결과 조회");
            System.out.println("2. 특정 값보다 큰 결과 조회");
            System.out.println("3. 새로운 연산 수행");
            System.out.println("4. 최신 연산 결과 삭제");
            System.out.println("5. 모든 연산 기록 삭제");
            System.out.println("🚪 'exit' 입력 시 프로그램 종료");
            System.out.print("👉 원하시는 기능의 번호를 입력하세요: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    showResults(calculator);
                    break;
                case "2":
                    filterResultsByThreshold(sc, calculator);
                    break;
                case "3":
                    performCalculation(sc, calculator);
                    break;
                case "4":
                    deleteLatestResult(calculator);
                    break;
                case "5":
                case "clear": // 🔹 clear 명령어 추가
                    clearAllResults(calculator);
                    break;
                case "exit":
                    System.out.println("🚪 계산기를 종료합니다.");
                    sc.close();
                    return;
                default:
                    System.out.println("⚠ 올바른 번호를 입력하세요 (1~5 또는 'exit').");
            }
        }
    }

    /**
     * ✅ 현재 저장된 연산 결과 출력
     */
    private static void showResults(Calculator calculator) {
        System.out.println("\n📜 현재 저장된 연산 결과: " + calculator.getResults());
    }

    /**
     * ✅ 특정 값보다 큰 연산 결과 조회 (숫자 입력 검증 추가)
     */
    private static void filterResultsByThreshold(Scanner sc, Calculator calculator) {
        while (true) {
            System.out.print("결과값 중 어떤 값보다 큰 값만 조회할까요? (숫자 입력, 'n' 입력 시 뒤로 가기): ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("n")) {
                return; // 뒤로 가기
            }

            try {
                double threshold = Double.parseDouble(input);
                List<Double> filteredResults = calculator.filterResultsGreaterThan(threshold);
                System.out.println("✅ " + threshold + "보다 큰 연산 결과: " + filteredResults);
                return;
            } catch (NumberFormatException e) {
                System.out.println("⚠ 올바른 숫자를 입력하세요.");
            }
        }
    }

    /**
     * ✅ 사용자 입력을 받아 사칙연산 수행
     */
    private static void performCalculation(Scanner sc, Calculator calculator) {
        Number num1 = getValidNumber(sc, "첫 번째 숫자를 입력하세요 (또는 'exit' 입력 시 뒤로 가기): ");
        if (num1 == null) return;

        Number num2 = getValidNumber(sc, "두 번째 숫자를 입력하세요 (또는 'exit' 입력 시 뒤로 가기): ");
        if (num2 == null) return;

        OperatorType operator = getValidOperator(sc);

        double result = calculator.calculate(num1, num2, operator);
        System.out.println("✅ 결과: " + result);
    }

    /**
     * ✅ 최신 연산 결과 삭제
     */
    private static void deleteLatestResult(Calculator calculator) {
        calculator.removeLatestResult();
    }

    /**
     * ✅ 모든 연산 기록 삭제 (clear 기능)
     */
    private static void clearAllResults(Calculator calculator) {
        calculator.clearResults();
        System.out.println("🗑 모든 연산 기록이 삭제되었습니다.");
    }

    /**
     * ✅ 숫자 입력을 검증하는 메서드
     */
    private static Number getValidNumber(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                return null;
            }

            try {
                return input.contains(".") ? Double.parseDouble(input) : Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("⚠ 올바른 숫자를 입력하세요.");
            }
        }
    }

    /**
     * ✅ 유효한 연산자 입력을 검증하는 메서드
     */
    private static OperatorType getValidOperator(Scanner sc) {
        while (true) {
            System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");
            String input = sc.nextLine().trim();

            try {
                return OperatorType.fromSymbol(input);
            } catch (IllegalArgumentException e) {
                System.out.println("⚠ 올바른 연산자를 입력하세요 (+, -, *, /).");
            }
        }
    }
}

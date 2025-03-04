package com.example.calculator;

import java.util.List;
import java.util.Scanner;

/**
 * ✅ 메인 실행 클래스 (메뉴 기반 인터페이스 추가)
 * - 사용자 입력을 받아 계산기 기능 수행
 * - 검색 및 정렬 기능을 별도 메뉴에서 수행할 수 있도록 개선
 * - 프로그램 종료 시 연산 기록을 자동 저장하도록 추가
 */
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calculator = new Calculator(); // ✅ 기존 데이터 불러오기 포함

        Runtime.getRuntime().addShutdownHook(new Thread(() -> saveResultsOnExit(calculator))); // ✅ 종료 시 자동 저장

        while (true) {
            // ✅ 사용자에게 기능 선택 요청
            System.out.println("\n========= 계산기 프로그램 =========");
            System.out.println("1. 저장된 연산 결과 조회");
            System.out.println("2. 특정 값보다 큰 결과 조회");
            System.out.println("3. 새로운 연산 수행");
            System.out.println("4. 최신 연산 결과 삭제");
            System.out.println("5. 모든 연산 기록 삭제");
            System.out.println("6. 검색 및 정렬 기능");
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
                    clearAllResults(calculator);
                    break;
                case "6":
                    searchAndSortMenu(sc, calculator);
                    break;
                case "exit":
                    System.out.println("🚪 계산기를 종료합니다.");
                    sc.close();
                    return;
                default:
                    System.out.println("⚠ 올바른 번호를 입력하세요 (1~6 또는 'exit').");
            }
        }
    }

    /**
     * ✅ 프로그램 종료 시 연산 기록을 자동 저장하는 기능
     * @param calculator 연산 결과가 저장된 Calculator 객체
     */
    private static void saveResultsOnExit(Calculator calculator) {
        calculator.saveResultsToFile(); // ✅ 종료 전 파일에 저장
        System.out.println("💾 연산 기록이 저장되었습니다. 다음 실행 시 불러올 수 있습니다.");
    }

    /**
     * ✅ 검색 및 정렬 기능 메뉴 추가
     */
    private static void searchAndSortMenu(Scanner sc, Calculator calculator) {
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

            String searchChoice = sc.nextLine().trim();

            switch (searchChoice) {
                case "1":
                    filterResultsByCondition(sc, calculator, "greater");
                    break;
                case "2":
                    filterResultsByCondition(sc, calculator, "less");
                    break;
                case "3":
                    filterResultsByCondition(sc, calculator, "equal");
                    break;
                case "4":
                    System.out.println("✅ 오름차순 정렬된 결과: " + calculator.sortResults("asc"));
                    break;
                case "5":
                    System.out.println("✅ 내림차순 정렬된 결과: " + calculator.sortResults("desc"));
                    break;
                case "6":
                    filterResultsByRange(sc, calculator);
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("⚠ 올바른 번호를 입력하세요 (1~6 또는 'exit').");
            }
        }
    }

    /**
     * ✅ 특정 값보다 크거나 작은 결과 검색 기능
     */
    private static void filterResultsByCondition(Scanner sc, Calculator calculator, String condition) {
        while (true) {
            System.out.print("기준값을 입력하세요 ('n' 입력 시 뒤로 가기): ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("n")) {
                return;
            }

            try {
                double threshold = Double.parseDouble(input);
                List<Double> filteredResults = calculator.filterResults(threshold, condition);
                System.out.println("✅ 검색 결과: " + filteredResults);
                return;
            } catch (NumberFormatException e) {
                System.out.println("⚠ 올바른 숫자를 입력하세요.");
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
     * ✅ 특정 값보다 큰 연산 결과 조회 (이전 기능 유지)
     */
    private static void filterResultsByThreshold(Scanner sc, Calculator calculator) {
        filterResultsByCondition(sc, calculator, "greater");
    }

    /**
     * ✅ 사용자 입력을 받아 사칙연산 수행
     */
    private static void performCalculation(Scanner sc, Calculator calculator) {
        Number num1 = getValidNumber(sc, "첫 번째 숫자를 입력하세요 (또는 'exit' 입력 시 뒤로 가기): ");
        if (num1 == null) return;

        Number num2;
        OperatorType operator;

        while (true) {
            num2 = getValidNumber(sc, "두 번째 숫자를 입력하세요 (또는 'exit' 입력 시 뒤로 가기): ");
            if (num2 == null) return;

            operator = getValidOperator(sc);

            if (operator == OperatorType.DIVIDE && num2.doubleValue() == 0) {
                System.out.println("⚠ 0으로 나누기는 불가능합니다. 다시 입력하세요.");
                continue;
            }
            break;
        }

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
     * ✅ 모든 연산 기록 삭제
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

    /**
     * ✅ 특정 범위 내 값 검색 기능
     * - 최소값과 최대값을 입력하면 해당 범위 내에 있는 연산 결과 반환
     */
    private static void filterResultsByRange(Scanner sc, Calculator calculator) {
        while (true) {
            System.out.print("최소값을 입력하세요 ('n' 입력 시 뒤로 가기): ");
            String minInput = sc.nextLine().trim();

            if (minInput.equalsIgnoreCase("n")) return;

            System.out.print("최대값을 입력하세요: ");
            String maxInput = sc.nextLine().trim();

            try {
                double min = Double.parseDouble(minInput);
                double max = Double.parseDouble(maxInput);

                List<Double> filteredResults = calculator.filterResultsByRange(min, max);
                System.out.println("✅ 검색 결과: " + filteredResults);
                return;
            } catch (NumberFormatException e) {
                System.out.println("⚠ 올바른 숫자를 입력하세요.");
            }
        }
    }
}

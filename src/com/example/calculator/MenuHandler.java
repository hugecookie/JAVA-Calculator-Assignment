package com.example.calculator;

import java.util.Scanner;

/**
 * ✅ 사용자 입력 및 메뉴 관리 클래스
 * - 프로그램 실행, 사용자 입력 처리
 * - 검색 및 정렬 기능은 `SearchAndSortService`에서 수행
 * - 프로그램 종료 시 연산 기록 자동 저장
 */
public class MenuHandler {
    private final Calculator calculator; // ✅ 계산기 객체 (연산 및 결과 저장)
    private final SearchAndSortService searchAndSortService; // ✅ 검색 및 정렬 기능 관리
    private final Scanner sc; // ✅ 사용자 입력을 위한 Scanner 객체

    /**
     * ✅ 생성자
     * - `Calculator` 객체와 `SearchAndSortService` 객체를 초기화
     * - 프로그램 종료 시 연산 기록을 자동 저장하는 기능 추가
     */
    public MenuHandler() {
        this.calculator = new Calculator(); // ✅ 기존 연산 기록 불러오기 포함
        this.searchAndSortService = new SearchAndSortService(calculator);
        this.sc = new Scanner(System.in);
        Runtime.getRuntime().addShutdownHook(new Thread(this::saveResultsOnExit)); // ✅ 종료 시 자동 저장
    }

    /**
     * ✅ 프로그램 실행 (메인 메뉴)
     * - 사용자가 기능을 선택하면 해당 기능을 실행
     */
    public void start() {
        String choice;

        do {
            System.out.println("\n========= 계산기 프로그램 =========");
            System.out.println("1. 저장된 연산 결과 조회");
            System.out.println("2. 새로운 연산 수행");
            System.out.println("3. 최신 연산 결과 삭제");
            System.out.println("4. 모든 연산 기록 삭제");
            System.out.println("5. 검색 및 정렬 기능");
            System.out.println("🚪 'exit' 입력 시 프로그램 종료");
            System.out.print("👉 원하시는 기능의 번호를 입력하세요: ");

            choice = sc.nextLine().trim();

            if (!"exit".equalsIgnoreCase(choice)) {
                switch (choice) {
                    case "1" -> showResults();
                    case "2" -> performCalculation();
                    case "3" -> deleteLatestResult();
                    case "4" -> clearAllResults();
                    case "5" -> searchAndSortService.searchAndSortMenu(sc);
                    default -> System.out.println("⚠ 올바른 번호를 입력하세요 (1~5 또는 'exit').");
                }
            }
        } while (!"exit".equalsIgnoreCase(choice));

        System.out.println("🚪 계산기를 종료합니다.");
    }


    /**
     * ✅ 프로그램 종료 시 연산 기록을 자동 저장하는 기능
     * - 사용자가 프로그램을 종료하면, `Calculator` 객체의 데이터를 파일에 저장
     */
    private void saveResultsOnExit() {
        calculator.saveResultsToFile(); // ✅ 연산 기록 저장
        System.out.println("💾 연산 기록이 저장되었습니다. 다음 실행 시 불러올 수 있습니다.");
    }

    /**
     * ✅ 저장된 연산 결과 조회
     * - 현재까지 저장된 연산 결과를 출력
     */
    private void showResults() {
        System.out.println("\n📜 현재 저장된 연산 결과: " + calculator.getResults());
    }

    /**
     * ✅ 새로운 연산 수행
     * - 사용자가 두 개의 숫자와 연산자를 입력하면 계산 수행 후 결과 저장
     * - 나눗셈의 경우 0으로 나누는 예외 처리 포함
     */
    private void performCalculation() {
        Number num1 = getValidNumber("첫 번째 숫자를 입력하세요 (또는 'exit' 입력 시 뒤로 가기): ");
        if (num1 == null) return;

        Number num2;
        OperatorType operator;

        while (true) {
            num2 = getValidNumber("두 번째 숫자를 입력하세요 (또는 'exit' 입력 시 뒤로 가기): ");
            if (num2 == null) return;

            operator = getValidOperator();

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
     * - 저장된 연산 결과 리스트에서 가장 마지막 결과를 삭제
     */
    private void deleteLatestResult() {
        calculator.removeLatestResult();
    }

    /**
     * ✅ 모든 연산 기록 삭제
     * - 저장된 연산 결과 리스트를 초기화
     */
    private void clearAllResults() {
        calculator.clearResults();
        System.out.println("🗑 모든 연산 기록이 삭제되었습니다.");
    }

    /**
     * ✅ 숫자 입력 검증 메서드
     * - 사용자가 숫자를 입력할 때 유효한 값인지 확인
     * - 'exit' 입력 시 뒤로 가기 기능 포함
     * @param prompt 사용자 입력 메시지
     * @return 입력된 숫자 (`Number` 타입)
     */
    private Number getValidNumber(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();

            if ("exit".equalsIgnoreCase(input)) return null;

            try {
                return input.contains(".") ? Double.parseDouble(input) : Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("⚠ 올바른 숫자를 입력하세요.");
            }
        }
    }

    /**
     * ✅ 연산자 입력 검증 메서드
     * - 사용자가 올바른 사칙연산 기호를 입력했는지 확인
     * @return 입력된 연산자 (`OperatorType` 타입)
     */
    private OperatorType getValidOperator() {
        while (true) {
            // ✅ 동적으로 연산자 기호 출력
            System.out.print("사칙연산 기호를 입력하세요 (" + OperatorType.getAllSymbols() + "): ");
            String input = sc.nextLine().trim();

            try {
                return OperatorType.fromSymbol(input);
            } catch (IllegalArgumentException e) {
                System.out.println("⚠ 올바른 연산자를 입력하세요 (" + OperatorType.getAllSymbols() + ").");
            }
        }
    }
}

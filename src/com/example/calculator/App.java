package com.example.calculator;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calculator = new Calculator();

        while (true) {
            System.out.println("\n현재 저장된 연산 결과: " + calculator.getResults());

            // ✅ 특정 값보다 큰 결과 조회 기능 추가
            System.out.print("결과값 중 어떤 값보다 큰 값만 조회할까요? (숫자 입력시 검색, 'n' 입력 시 건너뛰기): ");
            String input = sc.nextLine().trim();

            if (!input.equalsIgnoreCase("n")) { // 사용자가 "n"을 입력하지 않았을 경우에만 실행
                try {
                    double threshold = Double.parseDouble(input); // 입력 값을 숫자로 변환
                    List<Double> filteredResults = calculator.filterResultsGreaterThan(threshold); // 특정 값보다 큰 결과 필터링
                    System.out.println("✅ " + threshold + "보다 큰 연산 결과: " + filteredResults);
                } catch (NumberFormatException e) { // 숫자로 변환할 수 없는 경우 예외 처리
                    System.out.println("⚠ 올바른 숫자를 입력하세요."); // 사용자에게 올바른 입력 요청
                }
            }

            // 첫 번째 숫자 입력 (exit 입력 시 종료)
            Number num1 = getValidNumber(sc, "첫 번째 숫자를 입력하세요 (또는 'exit' 입력 시 종료): ");
            if (num1 == null) {
                System.out.println("계산기를 종료합니다.");
                break;
            }

            // 두 번째 숫자 입력 (exit 입력 시 종료)
            Number num2 = getValidNumber(sc, "두 번째 숫자를 입력하세요 (또는 'exit' 입력 시 종료): ");
            if (num2 == null) {
                System.out.println("계산기를 종료합니다.");
                break;
            }

            // 연산자 입력
            OperatorType operator = getValidOperator(sc);

            // 연산 수행 및 결과 출력
            double result = calculator.calculate(num1, num2, operator);
            System.out.println("결과: " + result);

            // 연산 결과 삭제 여부 확인 (최신 결과만 삭제 가능)
            String deleteInput = getValidDeleteChoice(sc);
            if (deleteInput.equals("1")) {
                calculator.removeLatestResult();
            }
        }

        sc.close();
    }

    // ✅ 숫자 입력을 검증하는 메서드 (제네릭 활용, 'exit' 입력 시 종료)
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

    // ✅ 유효한 연산자 입력을 검증하는 메서드
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

    // ✅ 삭제 여부를 입력받는 메서드 (최신 결과 삭제만 가능)
    private static String getValidDeleteChoice(Scanner sc) {
        while (true) {
            System.out.print("저장된 결과를 삭제하시겠습니까? (1: 최신 삭제, n: 삭제 안 함): ");
            String input = sc.nextLine().trim().toLowerCase();

            if (input.equals("1") || input.equals("n")) {
                return input;
            }

            System.out.println("⚠ 올바른 선택지를 입력하세요 (1, n).");
        }
    }
}

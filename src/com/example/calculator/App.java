package com.example.calculator;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calculator = new Calculator();

        while (true) {
            System.out.println("\n현재 저장된 연산 결과: " + calculator.getResults());

            // 첫 번째 숫자 입력 (exit 입력 시 종료)
            int num1 = getValidInteger(sc, "첫 번째 숫자를 입력하세요 (또는 'exit' 입력 시 종료): ");
            if (num1 == Integer.MIN_VALUE) {
                System.out.println("계산기를 종료합니다.");
                break;
            }

            // 두 번째 숫자 입력 (exit 입력 시 종료)
            int num2 = getValidInteger(sc, "두 번째 숫자를 입력하세요 (또는 'exit' 입력 시 종료): ");
            if (num2 == Integer.MIN_VALUE) {
                System.out.println("계산기를 종료합니다.");
                break;
            }

            // 사칙연산 기호 입력 (유효한 연산자만 허용)
            char operator = getValidOperator(sc);

            // 연산 수행
            double result = calculator.calculate(num1, num2, operator);
            System.out.println("결과: " + result);

            // 연산 결과 삭제 여부 확인 (올바른 입력값만 허용)
            String deleteInput = getValidDeleteChoice(sc);
            if (deleteInput.equals("1")) {
                calculator.removeLatestResult();
            } else if (deleteInput.equals("2")) {
                calculator.removeOldestResult();
            }
        }

        sc.close();
    }

    // 유효한 정수 입력 받기 (exit 입력 시 종료)
    private static int getValidInteger(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();

            // "exit" 입력 시 프로그램 종료 신호 반환
            if (input.equalsIgnoreCase("exit")) {
                return Integer.MIN_VALUE;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("⚠ 올바른 숫자를 입력하세요.");
            }
        }
    }

    // 유효한 연산자 입력 받기 (잘못된 값 입력 시 다시 요청)
    private static char getValidOperator(Scanner sc) {
        while (true) {
            System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");
            String input = sc.nextLine().trim();

            // 입력된 값이 유효한 연산자인지 확인
            if (input.length() == 1 && "+-*/".contains(input)) {
                return input.charAt(0);
            }

            System.out.println("⚠ 올바른 연산자를 입력하세요 (+, -, *, /).");
        }
    }

    // 유효한 삭제 선택지 입력 받기 (잘못된 값 입력 시 다시 요청)
    private static String getValidDeleteChoice(Scanner sc) {
        while (true) {
            System.out.print("저장된 결과를 삭제하시겠습니까? (1: 최신 삭제, 2: 가장 오래된 삭제, n: 삭제 안 함): ");
            String input = sc.nextLine().trim().toLowerCase();

            // 유효한 입력값인지 확인
            if (input.equals("1") || input.equals("2") || input.equals("n")) {
                return input;
            }

            System.out.println("⚠ 올바른 선택지를 입력하세요 (1, 2, n).");
        }
    }
}

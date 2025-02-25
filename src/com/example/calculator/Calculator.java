package com.example.calculator;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            // 첫 번째 숫자 입력 (양의 정수만 허용)
            int num1 = getPositiveInteger(sc, "첫 번째 숫자를 입력하세요 (또는 'exit' 입력 시 종료): ");
            if (num1 == -1) {
                System.out.println("계산기를 종료합니다.");
                break;
            }

            // 두 번째 숫자 입력 (양의 정수만 허용)
            int num2 = getPositiveInteger(sc, "두 번째 숫자를 입력하세요: ");

            // 연산자 입력
            System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");
            char operator;
            while (true) {
                String input = sc.nextLine().trim();
                if (input.length() == 1 && "+-*/".contains(input)) {
                    operator = input.charAt(0);
                    break;
                }
                System.out.print("⚠ 올바른 연산자를 입력하세요 (+, -, *, /): ");
            }

            double result = 0;
            boolean validOperation = true;

            // 연산 수행
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
                        result = (double) num1 / num2; // 실수 나눗셈 수행
                        result = Math.round(result * 100.0) / 100.0; // 소수점 둘째 자리 반올림
                    }
                    break;
                default:
                    System.out.println("⚠ 올바른 연산자를 입력하세요 (+, -, *, /).");
                    validOperation = false;
            }

            // 결과 출력
            if (validOperation) {
                System.out.println("결과: " + result);
            }

            System.out.println(); // 줄 바꿈
        }

        sc.close(); // 리소스 정리
    }

    // 양의 정수를 입력받는 메서드
    private static int getPositiveInteger(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();

            // 종료 조건
            if (input.equalsIgnoreCase("exit")) {
                return -1; // 종료 신호
            }

            try {
                int number = Integer.parseInt(input);
                if (number >= 0) {
                    return number;
                } else {
                    System.out.println("⚠ 양의 정수만 입력 가능합니다.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠ 올바른 숫자를 입력하세요.");
            }
        }
    }
}

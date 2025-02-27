package com.example.calculator;

// ✅ 연산자 타입을 Enum으로 관리
public enum OperatorType {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private final String symbol; // 연산자를 문자열로 저장하는 필드

    /**
     * 생성자
     * OperatorType Enum이 생성될 때, 해당 기호를 저장함
     * ex) ADD("+") → symbol 필드에 "+"가 저장됨
     */
    OperatorType(String symbol) {
        this.symbol = symbol;
    }

    /**
     * getSymbol()
     * 저장된 연산 기호를 반환하는 메서드
     * ex) OperatorType.ADD.getSymbol() → "+"
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * fromSymbol()
     * 문자열로 입력된 연산 기호를 OperatorType(Enum)으로 변환하는 메서드
     * ex) fromSymbol("+") → OperatorType.ADD
     */
    public static OperatorType fromSymbol(String symbol) {
        for (OperatorType op : values()) {
            if (op.symbol.equals(symbol)) {
                return op;
            }
        }
        throw new IllegalArgumentException("⚠ 지원하지 않는 연산자입니다: " + symbol);
    }
}

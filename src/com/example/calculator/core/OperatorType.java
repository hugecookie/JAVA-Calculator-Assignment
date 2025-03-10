package com.example.calculator.core;

import com.example.calculator.operator.*;
import com.example.calculator.operator.Operator;

/**
 * ✅ OperatorType에 Operator 객체 추가하여 팩토리 패턴 제거
 */
public enum OperatorType {
    ADD("+", new AdditionOperator()),
    SUBTRACT("-", new SubtractionOperator()),
    MULTIPLY("*", new MultiplicationOperator()),
    DIVIDE("/", new DivisionOperator()),
    POWER("^", new PowerOperator()),
    MODULO("%", new ModuloOperator()),
    FLOOR_DIV("//", new FloorDivisionOperator());

    private final String symbol;
    private final Operator operator;

    /**
     * 생성자
     * OperatorType(Enum)이 생성될 때 연산 기호와 Operator 객체를 저장
     */
    OperatorType(String symbol, Operator operator) {
        this.symbol = symbol;
        this.operator = operator;
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
     * getOperator()
     * 저장된 Operator 객체를 반환하는 메서드
     * ex) OperatorType.ADD.getOperator() → AdditionOperator 객체
     */
    public Operator getOperator() {
        return operator;
    }

    /**
     * fromSymbol()
     * 문자열 기호를 OperatorType으로 변환하는 메서드
     */
    public static OperatorType fromSymbol(String symbol) {
        for (OperatorType op : values()) {
            if (op.symbol.equals(symbol)) {
                return op;
            }
        }
        throw new IllegalArgumentException("⚠ 지원하지 않는 연산자입니다: " + symbol);
    }

    /**
     * getAllSymbols()
     * 지원하는 모든 연산 기호를 문자열로 반환하는 메서드
     * ex) "+, -, *, /, ^, %, //"
     */
    public static String getAllSymbols() {
        StringBuilder symbols = new StringBuilder();
        for (OperatorType op : values()) {
            if (!symbols.isEmpty()) {
                symbols.append(", ");
            }
            symbols.append(op.getSymbol());
        }
        return symbols.toString();
    }
}

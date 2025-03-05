package com.example.calculator.operator;

import com.example.calculator.OperatorType;

/**
 * ✅ OperatorType을 기반으로 적절한 Operator 객체 반환
 * 입력된 기호에 따라 계산하는 클래스를 다르게 생성
 */
public class OperatorFactory {
    public static Operator getOperator(OperatorType type) {
        return switch (type) {
            case ADD -> new AdditionOperator();
            case SUBTRACT -> new SubtractionOperator();
            case MULTIPLY -> new MultiplicationOperator();
            case DIVIDE -> new DivisionOperator();
        };
    }
}

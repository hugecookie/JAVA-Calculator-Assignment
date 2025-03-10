package com.example.calculator.core;

import com.example.calculator.service.MenuHandler;

/**
 * ✅ 메인 실행 클래스
 * - 프로그램 실행 (`main` 메서드만 담당)
 */
public class App {
    public static void main(String[] args) {
        MenuHandler menuHandler = new MenuHandler(); // ✅ 메뉴 관리 클래스 실행
        menuHandler.start(); // ✅ 프로그램 시작
    }
}

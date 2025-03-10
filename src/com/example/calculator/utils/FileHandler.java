package com.example.calculator.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ✅ 파일 입출력 관련 로직을 분리한 FileHandler 클래스
 */
public class FileHandler {
    private static final String FILE_PATH = "results.txt";

    /**
     * ✅ 연산 결과를 파일에 저장하는 기능
     */
    public static void saveResultsToFile(List<Double> results) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (double result : results) {
                writer.write(result + "\n");
            }
        } catch (IOException e) {
            System.out.println("⚠ 연산 결과 저장 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * ✅ 프로그램 실행 시 파일에서 기존 연산 결과 불러오는 기능
     */
    public static List<Double> loadResultsFromFile() {
        List<Double> loadedResults = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                loadedResults.add(Double.parseDouble(line));
            }
        } catch (IOException e) {
            System.out.println("⚠ 연산 결과 불러오기 중 오류 발생: " + e.getMessage());
        }
        return loadedResults;
    }
}

---

# **🧮 Java 기반 콘솔 계산기 프로그램**

### **1. 프로젝트 개요**
이 프로젝트는 **객체 지향 설계(OOP) 및 개방-폐쇄 원칙(OCP)**을 적용한 **자바 기반 콘솔 계산기**입니다.  
사칙연산을 수행하며, 연산자별 클래스를 분리하고 **팩토리 패턴을 적용하여 유지보수성과 확장성을 개선**했습니다.

---

## **2. 주요 기능**
- **사칙연산(+, -, *, /) 지원**
- **팩토리 패턴을 활용하여 객체 생성**
- **연산 결과 저장 및 조회 기능**
- **검색 및 정렬 기능**
- **거듭 제곱(`^`), 몫(`//`), 나머지(`%`) 연산 추가**
- **프로그램 종료 후에도 연산 기록 유지 (파일 저장 기능 포함)**
- **OCP(개방-폐쇄 원칙) 준수 → 새로운 연산 추가 시 기존 코드 수정 없음**

---

## **3. 프로젝트 구조**
```plaintext
📂 calculator-assignments
 ┣ 📂 src
 ┃ ┣ 📂 com.example.calculator
 ┃ ┃ ┣ 📜 results.txt             # 연산 결과 저장 파일
 ┃ ┃ ┣ 📜 App.java                # 메인 실행 파일
 ┃ ┃ ┣ 📜 MenuHandler.java        # 사용자 입력 및 메뉴 관리
 ┃ ┃ ┣ 📜 Calculator.java         # 연산 수행 및 결과 저장
 ┃ ┃ ┣ 📜 OperatorType.java       # 연산자 Enum
 ┃ ┃ ┣ 📜 SearchAndSortService.java # 검색 및 정렬 기능
 ┃ ┣ 📂 com.example.calculator.operator
 ┃ ┃ ┣ 📜 Operator.java           # 연산자 인터페이스
 ┃ ┃ ┣ 📜 AdditionOperator.java   # 덧셈 연산 클래스
 ┃ ┃ ┣ 📜 SubtractionOperator.java # 뺄셈 연산 클래스
 ┃ ┃ ┣ 📜 MultiplicationOperator.java # 곱셈 연산 클래스
 ┃ ┃ ┣ 📜 DivisionOperator.java   # 나눗셈 연산 클래스
 ┃ ┃ ┣ 📜 PowerOperator.java      # 거듭 제곱 연산 클래스
 ┃ ┃ ┣ 📜 ModuloOperator.java     # 나머지 연산 클래스
 ┃ ┃ ┣ 📜 FloorDivisionOperator.java # 몫 연산 클래스
 ┃ ┗ 📂 com.example.calculator.utils
 ┃ ┃ ┣ 📜 FileHandler.java        # 연산 결과 파일 관리
 ┗ 📜  README.md                   # 프로젝트 설명 파일
```

---

## **4. 개발 환경**
| 환경 | 버전 |
|------|------|
| **JDK** | 17+ |
| **IDE** | IntelliJ IDEA, VS Code 등 |
| **빌드 시스템** | 없음 (CLI 기반 실행) |
| **OS** | macOS, Windows, Linux 지원 |

> **⚠ 주의:** JDK 11 이하 버전에서는 일부 최신 문법이 지원되지 않을 수 있습니다.

---

## **5. 실행 방법**
### **1️⃣ 프로젝트 클론 및 실행**
```bash
# Git 저장소 초기화 및 프로젝트 클론
git init
git clone https://github.com/hugecookie/calculator-assignments.git
cd calculator-assignments

# 이후 App.java 실행하면 끝!
```

---

## **6. 사용 예시**
```bash
========= 계산기 프로그램 =========
1. 저장된 연산 결과 조회
2. 새로운 연산 수행
3. 최신 연산 결과 삭제
4. 모든 연산 기록 삭제
5. 검색 및 정렬 기능
🚪 'exit' 입력 시 프로그램 종료
👉 원하시는 기능의 번호를 입력하세요: 2
첫 번째 숫자를 입력하세요 (또는 'exit' 입력 시 뒤로 가기): 2000
두 번째 숫자를 입력하세요 (또는 'exit' 입력 시 뒤로 가기): 3
사칙연산 기호를 입력하세요 (+, -, *, /, ^, %, //): //
✅ 결과: 666.0
```

---

## **7. 트러블슈팅**
### **✅ 1. 연산자 입력 범위 제한**
- **문제:** 기존에는 `+`, `-`, `*`, `/`만 지원
- **해결:** `OperatorType.getAllSymbols()`을 이용하여 동적으로 사용 가능한 연산자 리스트 출력

```java
System.out.print("사칙연산 기호를 입력하세요 (" + OperatorType.getAllSymbols() + "): ");
```

### **✅ 2. 프로그램 종료 시 연산 결과 저장 문제**
- **문제:** 프로그램 종료 후 연산 기록이 사라짐
- **해결:** `FileHandler`를 추가하여 파일 저장 기능을 개선

```java
Runtime.getRuntime().addShutdownHook(new Thread(this::saveResultsOnExit));
```

---

## **8. 객체 지향 원칙(OOP) 적용**
이 프로젝트는 **객체 지향 프로그래밍(OOP) 원칙**을 준수하여 설계되었습니다.

| 원칙 | 설명 |
|------|--------------------------------|
| **추상화** | `Operator` 인터페이스를 활용한 연산자 구조 |
| **다형성** | 연산자 클래스를 `Operator` 타입으로 관리 |
| **OCP 원칙 준수** | 새로운 연산 추가 시 기존 코드 수정 없음 |
| **팩토리 패턴** | 연산자 객체를 동적으로 생성 |

---

## **9. 라이선스**
이 프로젝트는 **MIT 라이선스**를 따릅니다. 자유롭게 사용하고 개선할 수 있습니다.

---

## **10. 문의**
- **이메일**: `phrpp5770@gmail.com`
- **GitHub Issues**: `https://github.com/hugecookie/calculator-assignments/issues`
- **Pull Request(PR) 환영합니다!** 😃

---

### **11. 마무리**
✔ **이 프로젝트는 객체 지향 원칙(OCP, 다형성, 팩토리 패턴)을 적용하여 확장성을 높였습니다.**  
✔ **거듭 제곱(`^`), 몫(`//`), 나머지(`%`) 연산이 추가되었으며, 유지보수성과 확장성이 뛰어난 구조를 제공합니다.**  
✔ **앞으로 연산 기능을 확장하고, Spring을 활용한 API 서비스로 확장할 계획입니다.**

🔗 **Velog 기술 블로그에서 더 자세한 내용을 확인하세요!**  
👉 [프로젝트 과정 확인하기](https://velog.io/@hyang_do/series/Spring6%EA%B8%B0%EA%B3%BC%EC%A0%9C%EB%AA%A8%EC%9D%8C)
---
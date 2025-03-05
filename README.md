## **Java 기반 콘솔 계산기 프로그램**  

### **프로젝트 개요**  
이 프로젝트는 **자바(Java) 기반의 콘솔 계산기 프로그램**으로, 사칙연산을 수행하고 연산 결과를 관리하는 기능을 제공합니다.  
또한 **검색 및 정렬 기능**이 추가되어 특정 값 이상의 결과를 필터링하거나 정렬하여 쉽게 확인할 수 있습니다.  
사용자가 프로그램을 종료해도 연산 결과가 저장되므로, 다음 실행 시에도 기록을 유지할 수 있습니다.  

---

## **주요 기능**
### **✅ 기본 연산 기능**
- **사칙연산 (+, -, *, /) 지원**
- **제네릭을 활용한 숫자 타입(`int`, `double` 등) 지원**
- **나눗셈 시 0으로 나누는 예외 처리 포함**

### **✅ 연산 결과 관리**
- **최근 연산 결과 조회**
- **최신 연산 결과 삭제**
- **모든 연산 기록 삭제**
- **프로그램 종료 후에도 연산 기록 유지 (파일 저장 기능 추가)**  

### **✅ 검색 및 정렬 기능**
- **특정 값보다 큰 결과 검색** (greater)  
- **특정 값보다 작은 결과 검색** (less)  
- **특정 값과 동일한 값 검색** (equal)  
- **특정 범위 내 값 검색**  
- **연산 결과 오름차순 / 내림차순 정렬**  

---

## **사용 방법**
### **1️. 프로그램 실행**
app 파일 실행

### **2️. 메인 메뉴에서 기능 선택**
```bash
========= 계산기 프로그램 =========
1. 저장된 연산 결과 조회
2. 새로운 연산 수행
3. 최신 연산 결과 삭제
4. 모든 연산 기록 삭제
5. 검색 및 정렬 기능
🚪 'exit' 입력 시 프로그램 종료
👉 원하시는 기능의 번호를 입력하세요: 
```
사용자는 원하는 기능의 **번호를 입력**하면 해당 기능이 실행됩니다.

### **3️. 사칙연산 수행 예제**
```bash
첫 번째 숫자를 입력하세요 (또는 'exit' 입력 시 뒤로 가기): 10
두 번째 숫자를 입력하세요 (또는 'exit' 입력 시 뒤로 가기): 5
사칙연산 기호를 입력하세요 (+, -, *, /): +
✅ 결과: 15.0
```

### **4️. 검색 및 정렬 기능 예제**
```bash
========= 검색 및 정렬 기능 =========
1. 특정 값보다 큰 값 검색 (greater)
2. 특정 값보다 작은 값 검색 (less)
3. 특정 값과 동일한 값 검색 (equal)
4. 연산 결과 오름차순 정렬 (asc)
5. 연산 결과 내림차순 정렬 (desc)
6. 특정 범위 내 값 검색
🚪 'exit' 입력 시 메인 메뉴로 이동
👉 원하는 기능을 선택하세요: 1
기준값을 입력하세요 ('n' 입력 시 뒤로 가기): 10
✅ 검색 결과: [15.0, 20.5, 45.0]
```

### **5️. 프로그램 종료 후에도 연산 기록 유지**
프로그램을 종료하면 연산 결과가 파일(`results.txt`)에 자동으로 저장됩니다.  
다음 실행 시 기록을 자동으로 불러와서 유지할 수 있습니다.  

---

## **💾 프로젝트 구조**
```bash
📂 calculator-assignments
 ┣ 📂 src
 ┃ ┣ 📂 com.example.calculator
 ┃ ┃ ┣ 📜 App.java                # 메인 실행 파일
 ┃ ┃ ┣ 📜 MenuHandler.java        # 사용자 입력 및 메뉴 관리
 ┃ ┃ ┣ 📜 Calculator.java         # 연산 및 결과 관리
 ┃ ┃ ┣ 📜 SearchAndSortService.java # 검색 및 정렬 기능
 ┃ ┃ ┣ 📜 OperatorType.java       # 연산자 Enum
 ┃ ┃ ┗ 📜 ArithmeticCalculator.java # 연산 수행 로직
 ┣ 📜 README.md                   # 프로젝트 설명 파일
 ┣ 📜 results.txt                  # 연산 결과 저장 파일
 ┗ 📜 CalculatorApp.jar            # 실행 가능한 JAR 파일
```

---

## **개발 환경**
- **언어**: Java 17+
- **IDE**: IntelliJ IDEA, VS Code 등

---

## **실행 및 빌드 방법**
### **1️. 소스 코드 컴파일 및 실행**
```bash
javac -d bin src/com/example/calculator/*.java
java -cp bin com.example.calculator.App
```

### **2️. JAR 파일 빌드 및 실행**
```bash
jar cfe CalculatorApp.jar com.example.calculator.App -C bin .
java -jar CalculatorApp.jar
```

---

## **기여 방법**
### **프로젝트 클론**
```bash
git clone https://github.com/내-깃허브-아이디/calculator-assignments.git
cd calculator-assignments
```

### **새로운 기능 추가**
```bash
git checkout -b feature/new-feature
```
> 새로운 브랜치를 생성한 후 기능을 개발하세요.

### **변경 사항 커밋**
```bash
git add .
git commit -m "feat: 새로운 기능 추가"
git push origin feature/new-feature
```

### **Pull Request(PR) 요청**
GitHub에서 **Pull Request(PR)**을 생성하고 코드 리뷰를 요청하세요.

---

## **라이선스**
이 프로젝트는 **MIT 라이선스**를 따릅니다. 자유롭게 사용하고 개선할 수 있습니다.

---

## **문의**
- **이메일**: `phrpp5770@gmail.com`
- **GitHub Issues**: `https://github.com/hugecookie/calculator-assignments/issues`
- **Pull Request(PR) 환영합니다!** 😃

---

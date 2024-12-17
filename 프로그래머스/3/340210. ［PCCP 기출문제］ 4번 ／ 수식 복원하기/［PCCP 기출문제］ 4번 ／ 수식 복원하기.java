import java.util.*;

class Solution {
    
    private static List<String> qFirstOperandList = new ArrayList<>();
    private static List<String> qSecondOperandList = new ArrayList<>();
    private static List<String> qOperatorList = new ArrayList<>();
    private static List<String> firstOperandList = new ArrayList<>();
    private static List<String> secondOperandList = new ArrayList<>();
    private static List<String> operatorList = new ArrayList<>();
    private static List<String> answerList = new ArrayList<>();
    private static Set<String>[] answerSet;
    
        
    public String[] solution(String[] expressions) {
        
        StringTokenizer st;
        int maxDigit = 0;
        
        for(String expression: expressions) {
            st = new StringTokenizer(expression);
            String op1 = st.nextToken();
            String op = st.nextToken();
            String op2 = st.nextToken();
            st.nextToken();
            String answer = st.nextToken();
            
            maxDigit = Math.max(maxDigit, findMaxDigit(op1));
            maxDigit = Math.max(maxDigit, findMaxDigit(op2));
            if (answer.equals("X")) {
                qFirstOperandList.add(op1);
                qOperatorList.add(op);
                qSecondOperandList.add(op2);
                continue;
            }
            
            maxDigit = Math.max(maxDigit, findMaxDigit(answer));
            firstOperandList.add(op1);
            operatorList.add(op);
            secondOperandList.add(op2);   
            answerList.add(answer);
        }
        
        int qSize = qOperatorList.size();
        answerSet = new HashSet[qSize];
        for (int i = 0; i < qSize; i++) {
            answerSet[i] = new HashSet<>();
        }
        
        for (int number = maxDigit + 1; number < 10; number++) {
            System.out.println(number);
            checkOp(number);
        }
        
        
        String[] answer = new String[qSize];
        for (int i = 0; i < qSize; i++) {
            StringBuilder result = new StringBuilder();
            
            result.append(qFirstOperandList.get(i)).append(" ").append(qOperatorList.get(i)).append(" ");
            result.append(qSecondOperandList.get(i)).append(" = ");
            
            if (answerSet[i].size() == 1) {
                for (String s: answerSet[i]) {
                    result.append(s);
                }
            }else{
                result.append("?");
            }
            answer[i] = result.toString();
        }
        return answer;
    }
    
    private static void checkOp(int number) {        
        for (int i = 0; i < operatorList.size(); i++) {
            int value = calculate(firstOperandList.get(i), secondOperandList.get(i), operatorList.get(i), number);  
            int answer = Integer.parseInt(answerList.get(i), number);
            if (answer != value) return;
        }
        
        for (int i = 0; i < qOperatorList.size(); i++) {
            int value = calculate(qFirstOperandList.get(i), qSecondOperandList.get(i), qOperatorList.get(i), number);
            answerSet[i].add(Integer.toString(value, number));
        }
    }
    
    private static int calculate(String firstOperand, String secondOperand, String operator, int number) {
        int op1 = Integer.parseInt(firstOperand, number);
        int op2 = Integer.parseInt(secondOperand, number);
            
        if(operator.equals("+")) {
            return op1 + op2;
        }
        return op1 - op2;
    }
    
    private static int findMaxDigit(String number) {
        int maxDigit = 0;
        for (char ch: number.toCharArray()) {
            maxDigit = Math.max(maxDigit, ch - 48);
        }
        return maxDigit;
    }
}

// expressions 반복
    // operands와 value의 max digit 구하기
            // 끝이 X면 qFirstOperandList, qSecondOperandList, qOperatorList에 추가
            // 끝이 X가 아니면 firstOperandList, secondOperandList, operatorList, valueList에 추가
// maxDigit보다 큰 number로 순회
    // operandList 순회
        // 값 계산
        // 유효하지 않으면 return;
    // 끝까지 순회했으면 question 값 계산, n진수 변환, answerSet[]에 추가
// answerSet 크기가 1이면 그 값 그대로 수식에 만들기, 1 보다 크면 ?
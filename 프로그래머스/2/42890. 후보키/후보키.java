import java.util.*;

class Solution {
    
    private static int columnSize;
    private static List<int[]> combination;
    
    public int solution(String[][] relation) {
        // 후보키의 사이즈별로 계산
        columnSize = relation[0].length;
        int rowSize = relation.length;
        
        List<String> candidates = new ArrayList<>();
        
        for (int s = 1; s <= columnSize; s++){
            
            // 후보키의 가능한 조합을 계산 (O(n^4))
            combination = new ArrayList<>();
            
            comb(0, 0, s, new ArrayList<>());
            
            // 해당 후보 키가 구분이 되는지 계산 (필드를 더해서 set에 넣고 크기 같은지 확인) O(20)    
            for (int l = 0; l < combination.size(); l++){
                Set<String> sets= new HashSet<>();
                int[] keys = combination.get(l);
                for (int r = 0; r < rowSize; r++){
                    StringBuilder key = new StringBuilder();
                    for (int c = 0; c < s; c++){
                        key.append(relation[r][keys[c]]);
                        key.append(" ");
                    }
                    sets.add(key.toString());
                }

                if (sets.size() == rowSize){
                    // list에 담기
                    StringBuilder r = new StringBuilder();
                    for (int i = 0; i < keys.length; i++){
                        r.append(keys[i]);
                    }
                    candidates.add(r.toString());
                }
            }
        }
        
        boolean[] visited = new boolean[candidates.size()];
        for (int i = 0; i < candidates.size(); i++){
            String candidate = candidates.get(i);
            for (int j = i+1; j < candidates.size(); j++){
                if (!visited[j]){
                    int cnt = 0;
                    for (char c : candidate.toCharArray()){
                        // 모두 담겨 있으면 false 
                        // 모두 담겨 있지 않으면 true
                        if (candidates.get(j).indexOf(c) > -1){
                            cnt++;
                        }
                    }
                    if (cnt == candidate.length()){
                        visited[j] = true;
                    }
                }
            }
        }
        
        int count = 0;
        for (int i = 0; i < visited.length; i++){
            if (!visited[i]){
                count++;
            }
        }
        
        return count;
        
    }
    
    private void comb(int current, int start, int size, List<Integer> combList){
        if (size == current){
            int[] a = new int[size];
            for (int i = 0; i < size; i++){
                a[i] = combList.get(i);
            }
            combination.add(a);
            return;
        }
        for (Integer i = start; i < columnSize; i++){
            combList.add(i);
            comb(current+1, i+1, size, combList);
            combList.remove(i);
        }
    }
}

// 요약
// 후보키의 멤버는 후보키가 아니어야 함, 후보키는 유일
// 후보키의 최대 개수 구하기

// relation 컬럼은 (1-8)
// relation 은 [1-20][1-8] 이고 각 문자열은 (1-8) 알파벳과 숫자

// O(10^ 4 * )

// 14:28 - 
    
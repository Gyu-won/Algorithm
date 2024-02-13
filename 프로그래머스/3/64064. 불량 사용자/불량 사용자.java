import java.util.*;
import java.util.regex.*;

class Solution {
    
    private static boolean[] visited;
    private static List<String> results = new ArrayList<>();
    private static Set<String> bannedList = new HashSet<>();
    
    public int solution(String[] user_id, String[] banned_id) {
        
        visited = new boolean[user_id.length];
        
        for (int i = 0; i < banned_id.length; i++){
            String oldStr = banned_id[i];
            StringBuilder str = new StringBuilder();
            for (int j = 0; j < oldStr.length(); j++){
                char c = oldStr.charAt(j);
                if (c == '*'){
                    str.append("[a-z0-9]");
                }else{
                    str.append(c);
                }
            }
            banned_id[i] = str.toString();
        }
        
        dfs(user_id, banned_id, 0);
     
        return bannedList.size();
    }
    
    private void dfs(String[] user_id, String[] banned_id, int current){
        if (current == banned_id.length){
            Collections.sort(results);
            StringBuilder result = new StringBuilder();
            for (String s: results){
                result.append(s);
                result.append(" ");
            }
            bannedList.add(result.toString());
            return;
        }
        Pattern pattern = Pattern.compile(banned_id[current]);
        for (int i = 0; i < user_id.length; i++){
            if (!visited[i]){
                Matcher matcher = pattern.matcher(user_id[i]);
                if (matcher.matches()){
                    visited[i] = true;
                    results.add(user_id[i]);
                    dfs(user_id, banned_id, current + 1);
                    results.remove(user_id[i]);
                    visited[i] = false;
                }
            }
        }
    }
}

// 11:14 - 

// 요약
// 문자 하나 이사을 * 로 가림
// 제외되어야 하는 아이디 목록을 구하기
// user_id, banned_id 크기 (1-8)
// 알파벳 소문자와 숫자로만 구성

// banned id에 하나씩 넣으면서 count 하면 될듯 (dfs)
// visited 배열 + 정렬해서 set에 추가

// 시간복잡도 8 의 8승 = 2의 24승
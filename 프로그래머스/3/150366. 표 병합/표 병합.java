import java.util.*;

class Solution {
    
    private String[][] table = new String[50][50];
    private int[] parent = new int[2500];
    
    // 1000개 이하, 표 크기는 50x50
    public String[] solution(String[] commands) {
        
        for (int i = 0; i < 2500; i++) {
            parent[i] = i;
        }
        
        StringTokenizer st;
        List<String> result = new ArrayList<>();
        for (String commandLine: commands) {
            st = new StringTokenizer(commandLine);
            String command = st.nextToken();
            if (command.equals("UPDATE")) {
                String p1 = st.nextToken();
                String p2 = st.nextToken();
                if (st.hasMoreTokens()) {
                    String value = st.nextToken();    
                    update(Integer.parseInt(p1), Integer.parseInt(p2), value);
                } else {
                    update(p1, p2);    
                }
            } else if (command.equals("MERGE")) {
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                int r2 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());
                merge(r1, c1, r2, c2);
            } else if (command.equals("UNMERGE")) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                unMerge(r, c);
            } else {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                result.add(print(r, c));
            }
        }
        return result.stream()
            .toArray(String[]::new);
    }
    
    private int toKey(int r, int c) {
        return (r - 1) * 50 + (c - 1);
    }
    
    private int[] toPos(int key)
    {
        return new int[] {key / 50 , key % 50};
    }    
    
    private int find(int key) {
        if (parent[key] == key) {
            return key;
        }
        return parent[key] = find(parent[key]);
    }
    
    private void union(int key1, int key2) {
        int p1 = find(key1);
        int p2 = find(key2);
        
        if (p1 < p2) {
            parent[p2] = p1;
            int[] pos = toPos(p1);
            table[pos[0]][pos[1]] = toValue(p1, p2);
            int[] deletePos = toPos(p2);
            table[deletePos[0]][deletePos[1]] = null;
        } else if (p1 > p2) {
            parent[p1] = p2;
            int[] pos = toPos(p2);
            table[pos[0]][pos[1]] = toValue(p1, p2);
            int[] deletePos = toPos(p1);
            table[deletePos[0]][deletePos[1]] = null;
        }
    }
    
    private String toValue(int setKey, int deleteKey) {
        int[] setPos = toPos(setKey);
        int[] deletePos = toPos(deleteKey);
        
        String v1 = table[setPos[0]][setPos[1]];
        String v2 = table[deletePos[0]][deletePos[1]];
        if (v1 == null) {
            return v2;
        }
        return v1;
    }
    
    // 값 변경 -> union/find로 인덱스 구하기, 해당 인덱스의 값 변경
    private void update(int r, int c, String value) {
        int key = toKey(r, c);
        int[] valuePos = toPos(find(key));
        table[valuePos[0]][valuePos[1]] = value;
    }
    
    // 값으로 변경 -> 모든 값 순회하며 변경
    private void update(String v1, String v2) {
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (table[i][j] != null && table[i][j].equals(v1)) {
                    table[i][j] = v2;
                }
            }
        }
    }
    
    // 병합 -> union/find로 작은걸로 합치기, 병합 되는거는 ""로 변경
    private void merge(int r1, int c1, int r2, int c2) {
        union(toKey(r1, c1), toKey(r2, c2));
    }
    
    // 병합 해지 -> union/find 값으로 r, c로 하고, 해당 값은 ""로 변경, parent에 해당하는 모든 노드들을 자기 자신으로 바꾸기
    private void unMerge(int r, int c) {
        int p = find(toKey(r, c));
        int[] pos = toPos(p);
        
        String value = table[pos[0]][pos[1]];
        
        List<Integer> unMergeList = new ArrayList<>();
        for (int i = 0; i < 2500; i++) {
            if (find(i) == p) {
                unMergeList.add(i);
            }
        }
        for (int i: unMergeList) {
            parent[i] = i;
        }
        
        table[pos[0]][pos[1]] = null;
        table[r- 1][c - 1] = value;
    }
    
    // print: parent 값으로 출력, "" 면 EMPTY
    private String print(int r, int c) {
        int p = find(toKey(r, c));
        int[] pos = toPos(p);
        return table[pos[0]][pos[1]] == null ? "EMPTY" : table[pos[0]][pos[1]];
    }
}

// 위치로 값 변경
// 값으로 값 변경
// 병합: 같은셀 -> 무시, 하나만 값 가지면 그걸로 병합, 둘다 있으면 앞에껄로,
// 병합 해지: 해당 셀의 모든 병합 해지
// print: 셀 값 출력, 없으면 EMPTY


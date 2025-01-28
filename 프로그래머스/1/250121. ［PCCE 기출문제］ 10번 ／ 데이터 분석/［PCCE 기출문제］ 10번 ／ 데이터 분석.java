import java.util.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        int selectionIdx = Field.getIdxFromName(ext);
        int sortIdx = Field.getIdxFromName(sort_by);
        
        // 뽑기 O(500)
        Map<Integer, int[]> sortValueMap = new HashMap<>();
        for (int[] row: data) {
            if (row[selectionIdx] < val_ext) {
                sortValueMap.put(row[sortIdx], row);
            }
        }
        
        // 정렬 O(log500)
        List<Integer> sortKeys = new ArrayList<>(sortValueMap.keySet());
        Collections.sort(sortKeys);
        
        // int 형으로 변환
        int[][] answer = new int[sortKeys.size()][4];
        int idx = 0;
        for (int key: sortKeys) {
            answer[idx++] = sortValueMap.get(key);
        }
        return answer;
    }
    
    static enum Field {
        CODE("code", 0),
        DATE("date", 1),
        MAXIMUM("maximum", 2),
        REMAIN("remain", 3);
        
        private final String name;
        private final int idx;
        
        Field(String name, int idx){
            this.name = name;
            this.idx = idx;
        }
        
        public static int getIdxFromName(String name) {
            for (Field field: Field.values()) {
                if (field.name.equals(name)) {
                    return field.idx; 
                }
            }
            return -1;
        }
    }
}

// 22:53

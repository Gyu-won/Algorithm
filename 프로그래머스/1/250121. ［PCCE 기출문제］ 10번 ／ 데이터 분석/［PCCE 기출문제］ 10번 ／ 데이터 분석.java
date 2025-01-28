import java.util.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        int selectionIdx = Field.getIdxFromName(ext);
        int sortIdx = Field.getIdxFromName(sort_by);
        
        // 뽑기 O(500)
        List<int[]> selectedRow = new ArrayList<>();
        for (int[] row: data) {
            if (row[selectionIdx] < val_ext) {
                selectedRow.add(row);
            }
        }
        
        // 정렬 O(log500)
        selectedRow.sort((a, b) -> a[sortIdx] - b[sortIdx]);
        
        // int 형으로 변환
        return selectedRow.toArray(new int[selectedRow.size()][4]);
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

class Solution {
    
    private static int maxDifference = 0;
    private static int[] answer = new int[]{-1};
    
    public int[] solution(int n, int[] info) {      
        // 라이언이 쏘는 화살을 구하기 dfs
        dfs(n, n, 0, new int[11], info);
        
        return answer;
    }
    
    private void dfs(int n, int left, int valid, int[] lion, int[] info){
        if (valid == 10){
            lion[valid] = left;
            
            // 점수 계산하기
            calculateScore(lion, info);
            return;
        }
        
        for (int i = 0; i <= left; i++){
            lion[valid] = i;
            dfs(n, left - i, valid + 1, lion, info);
        }
    }
    
    private void calculateScore(int[] lion, int[] info){
        // 10점부터 시작
        int lionScore = 0, appeachScore = 0;
        for (int i = 0; i < 11; i++){
            if (info[i] == 0 && lion[i] == 0){
                continue;
            }
            
            if (info[i] < lion[i]){
                lionScore += 10 - i;
            }else{
                appeachScore += 10 - i;
            }
        }
        
        // 라이언 점수가 더 크고, 점수차가 기존보다 크면 같으면 update
        if (appeachScore < lionScore){
            int difference = lionScore - appeachScore;
            if (maxDifference < difference || (maxDifference == difference && isValid(lion))){
                answer = new int[11];
                for (int i = 0; i < 11; i++){
                    // System.out.print(lion[i]);
                    answer[i] = lion[i];
                }
                System.out.println();
                maxDifference = difference;
            }
        }
    }
    
    private boolean isValid(int[] lion){
        if (answer.length == 1){
            return true;
        }
        
        for (int i = 0; i < 11; i++){
            if (lion[10 - i] == answer[10 - i]){
                continue;
            }
            return answer[10 - i] < lion[10 - i];
        }
        return false;
    }
}

// k 점을 많이 맞춘 사람이 k 점 가져감, 같으면 어피치가 가져감, 0번 맞췄으면 아무도 안가져감
// 최종점수 같으면 어피치 우승

// 라이언이 가장 큰 점수 차이로 우승하기 위해 맞춰야하는 과녁 점수 10 - 0으로 보고 리턴
// 라이언 우승 못하면 [-1] 리턴
// 가장 큰 점수차이로 우승할 수 있는 방법 여러개면 가장 낮은 점수를 더 많이 맞힌 경우 리턴

// n (1-10)
// O(n!) 가능 
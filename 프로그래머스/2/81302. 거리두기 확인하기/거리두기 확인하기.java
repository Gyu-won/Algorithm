class Solution {
    
    private static final int[] dr = new int[]{-1, -1, 0, 1, 1, 1, 0, -1, -2, 0, 2, 0};
    private static final int[] dc = new int[]{0, 1, 1, 1, 0, -1, -1, -1, 0, 2, 0, -2};
    
    public int[] solution(String[][] places) {
        int[] answers = new int[5];
        
        for (int n = 0; n < 5; n++){
            // char[][] Structure 생성
            char[][] structure = new char[5][5];
            for (int i = 0; i < 5; i++){
                for (int j = 0; j < 5; j++){
                    structure[i][j] = places[n][i].charAt(j);
                }
            }
            
            // 하나씩 보면서 12 방향 확인 (나가는 범위 체크)
            boolean exitFlag = false;
            for (int i = 0; i < 5; i++){
                for (int j = 0; j < 5; j++){
                    if (structure[i][j] == 'P'){
                        for (int d = 0; d < 12; d++){
                            int r = i + dr[d];
                            int c = j + dc[d];
                            if (r >= 0 && r < 5 && c >= 0 && c < 5){
                                if (d < 8){
                                    if (d % 2 == 0 && structure[r][c] == 'P'){
                                        exitFlag = true;
                                        break;
                                    }
                                    if (d % 2 == 1 && structure[r][c] == 'P'){
                                        // 0-7 중 홀수는 P이고, d+1, d-1 %8 한것이 모두 X가 아니면 0 
                                        int pr = i + dr[(d+1)% 8];
                                        int pc = j + dc[(d+1)% 8];
                                        int nr = i + dr[(d-1)% 8];
                                        int nc = j + dc[(d-1)% 8];
                                        if (structure[pr][pc] == 'X' && structure[nr][nc] == 'X'){
                                            continue;
                                        }
                                        exitFlag = true;
                                        break;
                                    }
                                }else{
                                    // 8-11은 두개 더한거 / 2의 위치가 X가 아니면 0
                                    if (structure[r][c] == 'P' && structure[(i+r)/2][(j+c)/2] != 'X'){
                                        exitFlag = true;
                                        break;
                                    }
                                }    
                            }     
                        }                                                
                    }
                    if (exitFlag){
                        break;
                    }
                }
                if (exitFlag){
                    break;
                }
            }
            if (exitFlag){
                answers[n] = 0;
            }else{
                answers[n] = 1;
            }       
        }
        
        
        
        return answers;
    }
}

// 요약
// 대기실은 5개, 각 대기실은 5x5 크기,, 맨해튼 거리 2이하로 못앉음, 파티션 있으면 가능
// 사람은 P, 테이블은 O, 파티션은 x
// 거리두기 지키면 1, 안지키면 0 리턴

// O(5번 * 25칸 * 12방향 * )

// 19:43


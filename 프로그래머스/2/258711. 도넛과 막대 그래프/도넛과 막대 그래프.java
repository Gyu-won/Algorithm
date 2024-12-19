class Solution {
    public int[] solution(int[][] edges) {
        
        int numberOfNode = 0;
        int[][] edgeInfo = new int[1000001][2];
        for (int[] edge: edges) {
            int out = edge[0];
            int in = edge[1];
            
            edgeInfo[out][0]++;
            edgeInfo[in][1]++;
            numberOfNode = Math.max(numberOfNode, out);
            numberOfNode = Math.max(numberOfNode, in);
        }
        
        int[] graphInfo = new int[4];
        for(int i = 1; i <= numberOfNode; i++) {
            if (edgeInfo[i][0] == 0 && edgeInfo[i][1] != 0) {
                graphInfo[2]++;
                continue;
            }
            if (edgeInfo[i][0] >= 2) {
                if (edgeInfo[i][1] == 0) {
                    graphInfo[0] = i;
                    continue;
                }
                graphInfo[3]++;
            }
        }
        int numberOfGraph = edgeInfo[graphInfo[0]][0];
        graphInfo[1] = numberOfGraph - graphInfo[2] - graphInfo[3];
        
        return graphInfo;
    }
}

// edges 돌면서 edgeInfo int[][2] 들어오기/나가기 생성
    // 나가는거면 0++, 들어오는거면 1++
    // 최댓값 numberOfEdge 구하기
// edgeInfo 순회하기
    // 8자 구하기: [0]이 2이상, [1]이 0보다 큼
    // 막대 구하기: [0]이 0인 것 도넛 개수
    // 정점 구하기: [0]이 2이상, [1]이 0이면 정점
// 도넛 구하기: 정점 연결에서 8자, 막대 빼기

// 결과: 생성한 정점의 번호, 도넛 모양 그래프의 수, 막대 모양 그래프의 수, 8자 모양 그래프의 수를 순서대로 1차원 정수 배열에 담아 return
// O(nlogn)

// 나가는게 2개 이상, 자신에게 들어오는게 없으면 정점 번호
// 도넛모양: dfs시 자신에게 돌아옴
// 막대모양: dfs시 자신에게 안 돌아옴
// 8자 모양: dfs시 나가는 길이 2개인 것 존재
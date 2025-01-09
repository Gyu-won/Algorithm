class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int maxHealth = health;
        
        // lastAttackTime 구하기 O(1)
        int lastAttackTime = attacks[attacks.length-1][0];
        
        // 시간 흐름 O(lastAttackTime)
        int attackIdx = 0, consecutiveSkill = 0;
        for (int time = 1; time <= lastAttackTime; time++) {
            // 공격 여부 확인 attackIdx
            if (time == attacks[attackIdx][0]) {
                // 공격: 연속 성공 초기화, 체력 깎기
                consecutiveSkill = 0;
                health -= attacks[attackIdx][1];
                attackIdx++;
            } else {
                // 공격 X: 연속 성공++, 체력 회복, 연속 여부 확인 후 추가 체력
                consecutiveSkill++;
                consecutiveSkill %= bandage[0];
                health += bandage[1];
                if (consecutiveSkill == 0) {
                    health += bandage[2];
                }
            }
                
            if (health <= 0) {
                // 체력 0 이면 return
                return -1;
            }
            
            // 체력 min(health, maxHealth update)
            health = Math.min(health, maxHealth);
        }
        
        return health;
    }
}

// 붕대감기: t초 동안 x 체력 회복, t초 연속 감으면 y 추가 체력
// 공격: 기술 취소, 피해량만큼 채력 줄어듦
// bandage: 시전시간, 초당 회복량, 추가회복량
// 모든 공격 끝난 후 남은 체력 리턴, 죽으면 -1

// 구현
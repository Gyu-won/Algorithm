class Solution
{
    
    private static int a, b;
    
    public int solution(int n, int aInput, int bInput)
    {
        a = aInput;
        b = bInput;
        
        int temp = n;
        int totalRound = 0;
        while (temp > 1) {
            temp /= 2;
            totalRound++;
        }
        
        return totalRound - countRestRound(0, n, 0);
    }
    
    private int countRestRound(int start, int end, int count) {
        int middle = (start + end) / 2;
    
        if (a <= middle && b <= middle) {
            return countRestRound(start, middle, count + 1);        
        } else if (a > middle && b > middle) {
            return countRestRound(middle, end, count + 1);
        } else {
            return count;
        }
    }
}


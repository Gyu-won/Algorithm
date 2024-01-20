import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {

    private static int ticketCounts;
    private static boolean[] used;
    private static String[][] tickets;
    private static List<String> possibleRoutes = new ArrayList<>();

    public String[] solution(String[][] input) {
        ticketCounts = input.length;

        // 티켓 사용여부 배열
        used = new boolean[ticketCounts];
        tickets = new String[ticketCounts][];
        System.arraycopy(input, 0, tickets, 0, ticketCounts);

        dfs("ICN", "ICN");

        Collections.sort(possibleRoutes);
        return possibleRoutes.get(0).split(" ");
    }

    private void dfs(String depart, String route) {
        if (useAllTicket()) {
            possibleRoutes.add(route);
            return;
        }
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i][0].equals(depart) && !used[i]) {
                used[i] = true;
                dfs(tickets[i][1], route + " " + tickets[i][1]);
                used[i] = false;
            }
        }
    }

    private boolean useAllTicket() {
        for (boolean useFlag : used) {
            if (!useFlag) {
                return false;
            }
        }
        return true;
    }
}
package sample;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Order> orders = new ArrayList<>();
    private static int noOrders;

    public static void main(String[] args) {
        input();
        int sumPenalty = 0;
        while (orders.size() != 0) {
            sumPenalty += nextOrderGreedy();
        }
        System.out.println("Total Penalty : " + sumPenalty);
    }

    public static void input() {
        Scanner scanner = new Scanner(System.in);

        noOrders = scanner.nextInt();

        for (int i = 0; i < noOrders; i++) {
            int days = scanner.nextInt();
            int penalty = scanner.nextInt();
            orders.add(new Order(i + 1, days, penalty));
        }
    }

    public static int nextOrderGreedy() {
        Order best = null;
        int leastPenalty = Integer.MAX_VALUE;
        for (Order o : orders) {
            int penalty = 0;
            for (Order i : orders) {
                if (i.num != o.num)
                    penalty += o.days * i.penalty;
            }
            if (penalty < leastPenalty) {
                best = o;
                leastPenalty = penalty;
            }
        }

        orders.remove(best);
        System.out.println(best.toString() + " " + "Penalty after this order : " + leastPenalty);

        return leastPenalty;
    }
}

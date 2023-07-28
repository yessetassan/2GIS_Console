package src;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class CalculateDijkstra {
    FeedBack feedBack;
    int size = -1;
    final double[] time = {1.0, 1.0, 1.0, 1.0, 1.0, 0.9, 0.8, 0.3, 0.4, 0.4, 0.4, 0.5, 0.5, 0.5, 0.4, 0.5, 0.5, 0.4, 0.5, 0.6, 0.8, 0.9, 0.9, 1.0};
    double speed_walk = 5.0;
    double speed_car = 60.0;
    double speed_bus = 40.0;
    double speed_metro = 90.0;

    public CalculateDijkstra(FeedBack feedBack) {
        this.feedBack = feedBack;
        size = feedBack.size;
    }

    public double calculate_for_walk(int from, int to) {

        double[] dp = new double[feedBack.V];

        Arrays.fill(dp, Double.MAX_VALUE);

        dp[from] = 0.0;

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return Double.compare(dp[a], dp[b]);
        });
        pq.add(from);

        while (!pq.isEmpty()) {

            int c = pq.remove();
            if (c == to) break;

            for (Edge e : feedBack.list_of_edges[c] ) {
                int t = c != e.u ? e.u : e.v;
                double spend = (e.weight / speed_walk);

                if (e.walk && dp[t] > dp[c] + spend) {
                    dp[t] = dp[c] + spend;
                    pq.add(t);
                }
            }
        }

        return dp[to];
    }

    public double calculate_for_car(int from, int to, int hour, int minute) {

        double[] dp = new double[size];
        Arrays.fill(dp, Double.MAX_VALUE);

        dp[from] = 0.0;

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return Double.compare(dp[a], dp[b]);
        });

        pq.add(from);

        while (!pq.isEmpty()) {

            int c = pq.remove();

            if (c == to) break;

            for (Edge e : feedBack.list_of_edges[c]) {
                int total = minute + (int) (dp[c] * 60);
                int current_hour = (hour + (total / 60)) % 24;
                double average = Double.parseDouble(String.format("%.2f",( (e.queue + time[current_hour]) / 2)).replace(",","."));

                if (e.car) {

                    int t = c != e.u ? e.u : e.v;
                    double spend = (e.weight / (speed_car * average));
                    if (dp[t] > dp[c] + spend) {
                        dp[t] = dp[c] + spend;
                        pq.add(t);
                    }
                } else if (e.walk) {
                    int t = c != e.u ? e.u : e.v;
                    double spend = (e.weight / speed_walk);
                    if (dp[t] > dp[c] + spend) {
                        dp[t] = dp[c] + spend;
                        pq.add(t);
                    }
                }

            }
        }
        return dp[to];
    }

    public double calculate_for_bus(int from, int to, int hour, int minute) {

        double[] dp = new double[size];
        Arrays.fill(dp, Double.MAX_VALUE);
        dp[from] = 0.0;

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return Double.compare(dp[a], dp[b]);
        });

        pq.add(from);

        while (!pq.isEmpty()) {

            int c = pq.remove();

            if (c == to) break;

            for (Edge e : feedBack.list_of_edges[c]) {
                int total = minute + (int) (dp[c] * 60);
                int current_hour = (hour + (total / 60)) % 24;
                double average = Double.parseDouble(String.format("%.2f",( (e.queue + time[current_hour]) / 2)).replace(",","."));
                if (e.bus) {
                    int t = c != e.u ? e.u : e.v;
                    double spend = (e.weight / (speed_bus * average));
                    if (dp[t] > dp[c] + spend) {
                        dp[t] = dp[c] + spend;
                        pq.add(t);
                    }
                } else if (e.walk) {
                    int t = c != e.u ? e.u : e.v;
                    double spend = (e.weight / speed_walk);
                    if (dp[t] > dp[c] + spend) {
                        dp[t] = dp[c] + spend;
                        pq.add(t);
                    }
                }

            }
        }


        return dp[to];
    }

    public double calculate_for_metro(int from, int to, int hour, int minute) {

        double[] dp = new double[size];
        Arrays.fill(dp, Double.MAX_VALUE);

        from = 60;
        to = 67;

        dp[from] = 0.0;

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return Double.compare(dp[a], dp[b]);
        });

        pq.add(from);

        while (!pq.isEmpty()) {

            int c = pq.remove();

            if (c == to) break;

            for (Edge e : feedBack.list_of_edges[c]) {
                int total = minute + (int) (dp[c] * 60);
                int current_hour = (hour + (total / 60)) % 24;
                double average = Double.parseDouble(String.format("%.2f",( (e.queue + time[current_hour]) / 2)).replace(",","."));
                if (e.metro) {
                    int t = c != e.u ? e.u : e.v;
                    double spend = (e.weight / (speed_metro * average));
                    if (dp[t] > dp[c] + spend) {
                        dp[t] = dp[c] + spend;
                        pq.add(t);
                    }
                }

            }
        }


        return dp[to];
    }

}
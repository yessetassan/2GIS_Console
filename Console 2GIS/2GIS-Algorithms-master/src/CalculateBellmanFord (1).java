package src;

import java.util.ArrayList;
import java.util.Arrays;

class CalculateBellmanFord {
    FeedBack feedBack;
    int V;
    int E;
    double speed_walk = 5.0;
    double speed_car = 60.0;
    double speed_bus = 40.0;
    double speed_metro = 90.0;
    double[] time = {1.0, 1.0, 1.0, 1.0, 1.0, 0.9, 0.8, 0.3, 0.4, 0.4,0.4, 0.5, 0.5, 0.5, 0.4, 0.5, 0.5, 0.4, 0.5, 0.6, 0.8, 0.9, 0.9, 1.0};

    public CalculateBellmanFord(FeedBack feedBack) {
        this.feedBack = feedBack;
        this.V = feedBack.V;
        this.E = feedBack.E;
    }

    public double calculate_for_walk(int from, int to) {

        double[] dp = new double[V];
        Arrays.fill(dp, Double.MAX_VALUE);

        dp[from] = 0.0;
        for (int i = 0; i < V - 1; i++) {
            for (int j = 0; j < V; j++) {
                if (dp[j] == Double.MAX_VALUE) continue;
                for (Edge e : feedBack.list_of_edges[j]) {
                    int t = j != e.u ? e.u : e.v;
                    double spend = (e.weight / speed_walk);
                    if (e.walk && dp[t] > dp[j] + spend) {
                        dp[t] = dp[j] + spend;
                    }
                }
            }

        }

        return dp[to];
    }

    public double calculate_for_car(int from, int to, int hour, int minute) {

        double[] dp = new double[feedBack.size];

        Arrays.fill(dp, Double.MAX_VALUE);

        dp[from] = 0.0;
        for (int i = 0; i < V - 1; i++) {
            for (int j = 0; j < V; j++) {
                if (dp[j] == Double.MAX_VALUE) continue;
                for (Edge e : feedBack.list_of_edges[j]) {
                    int total = minute + (int) (dp[j] * 60);
                    int current_hour = (hour + (total / 60)) % 24;
                    double average = Double.parseDouble(String.format("%.2f",( (e.queue + time[current_hour]) / 2)).replace(",","."));
                    if (e.car) {
                        int t = j != e.u ? e.u : e.v;
                        double spend = (e.weight / (speed_car * average));
                        if (dp[t] > dp[j] + spend) {
                            dp[t] = dp[j] + spend;
                        }
                    } else if (e.walk) {
                        int t = j != e.u ? e.u : e.v;
                        double spend = (e.weight / speed_walk);
                        if (dp[t] > dp[j] + spend) {
                            dp[t] = dp[j] + spend;
                        }
                    }
                }
            }

        }

        return dp[to];
    }

    public double calculate_for_bus(int from, int to, int hour, int minute) {

        double[] dp = new double[feedBack.size];

        Arrays.fill(dp, Double.MAX_VALUE);

        dp[from] = 0.0;
        for (int i = 0; i < V - 1; i++) {
            for (int j = 0; j < V; j++) {
                if (dp[j] == Double.MAX_VALUE) continue;
                for (Edge e : feedBack.list_of_edges[j]) {
                    int total = minute + (int) (dp[j] * 60);
                    int current_hour = (hour + (total / 60)) % 24;
                    double average = Double.parseDouble(String.format("%.2f",( (e.queue + time[current_hour]) / 2)).replace(",","."));
                    if (e.bus) {
                        int t = j != e.u ? e.u : e.v;
                        double spend = (e.weight / (speed_bus * average));
                        if (dp[t] > dp[j] + spend) {
                            dp[t] = dp[j] + spend;
                        }
                    } else if (e.walk) {
                        int t = j != e.u ? e.u : e.v;
                        double spend = (e.weight / speed_walk);
                        if (dp[t] > dp[j] + spend) {
                            dp[t] = dp[j] + spend;
                        }
                    }
                }
            }

        }

        return dp[to];
    }

    public double calculate_for_metro(int from, int to, int hour, int minute) {

        double[] dp = new double[feedBack.size];

        Arrays.fill(dp, Double.MAX_VALUE);
        from = 60;
        to = 67;
        dp[from] = 0.0;
        for (int i = 0; i < V - 1; i++) {
            for (int j = 0; j < V; j++) {
                if (dp[j] == Double.MAX_VALUE) continue;

                for (Edge e : feedBack.list_of_edges[j]) {
                    int total = minute + (int) (dp[j] * 60);
                    int current_hour = (hour + (total / 60)) % 24;
                    double average = Double.parseDouble(String.format("%.2f",( (e.queue + time[current_hour]) / 2)).replace(",","."));
                    if (e.metro) {
                        int t = j != e.u ? e.u : e.v;
                        double spend = (e.weight / (speed_metro * average));
                        if (dp[t] > dp[j] + spend) {
                            dp[t] = dp[j] + spend;
                        }
                    }
                }
            }

        }


        return dp[to];
    }

}

package src;

import src.CalculateBellmanFord;
import src.CalculateDijkstra;

import java.time.LocalTime;
import java.util.Scanner;

public class Center {
    public static Trie trie;
    public static FeedBack feedBack;
    public static String welcome_message = "Hello, Welcome to the 2GIS console !";
    public static String from_fill = "Enter your current location ! You'll get all prefix matches locations and his unique id \n---------------------------------------------------------------------------------------------------------------------";
    public static String from_to = "Location where you want to go! You'll get all prefix matches locations and his unique id \n---------------------------------------------------------------------------------------------------------------------";

    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 2; i++) {
            System.out.printf("\n");
        }

        System.out.println(welcome_message);
        System.out.printf("\n");
        feedBack = new FeedBack();

        trie = feedBack.getRoot();
        System.out.println(from_fill);
        int ind_from = LocationDeterminer(-1);
        System.out.println(from_to);
        int ind_to = LocationDeterminer(-1);

        for (int i = 0; i < 2; i++) {
            System.out.printf("\n");
        }

        System.out.println("Congrats!!!");
        System.out.println("Current Location is : " + feedBack.location_name[ind_from]);
        System.out.println("To Go : " + feedBack.location_name[ind_to]);
        for (int i = 0; i < 2; i++) {
            System.out.printf("\n");
        }
        System.out.printf("Choose 2 type of input\n1.Local Time\n2.You give input");
        System.out.printf("\n");
        int input = in.nextInt();
        LocalTime time = LocalTime.now();
        int hour = time.getHour();
        int minute = time.getMinute();
        for (int i = 0; i < 1; i++) {
            System.out.println("\n");
        }
        if (input == 2) {
            System.out.println("Enter in HH::MM format");
            hour = in.nextInt();
            minute = in.nextInt();
        }


        CalculateDijkstra calculateDijkstra = new CalculateDijkstra(feedBack);
        long tdw = System.currentTimeMillis();
        double dw = calculateDijkstra.calculate_for_walk(ind_from, ind_to);
        tdw = System.currentTimeMillis() - tdw;
        long tdc = System.currentTimeMillis();
        double dc = calculateDijkstra.calculate_for_car(ind_from, ind_to, hour, minute);
        tdc = System.currentTimeMillis() - tdc;
        long tdb = System.currentTimeMillis();
        double db = calculateDijkstra.calculate_for_bus(ind_from, ind_to, hour, minute);
        tdb = System.currentTimeMillis() - tdb;
        long tdm = System.currentTimeMillis();
        double dm = calculateDijkstra.calculate_for_metro(ind_from, ind_to, hour, minute);
        tdm = System.currentTimeMillis() - tdm;
        CalculateBellmanFord calculateBellmanFord = new CalculateBellmanFord(feedBack);
        long tbw = System.currentTimeMillis();
        double bw = calculateBellmanFord.calculate_for_walk(ind_from, ind_to);
        tbw = System.currentTimeMillis() - tbw;
        long tbc = System.currentTimeMillis();
        double bc = calculateBellmanFord.calculate_for_car(ind_from, ind_to, hour, minute);
        tbc = System.currentTimeMillis() - tbc;
        long tbb = System.currentTimeMillis();
        double bb = calculateBellmanFord.calculate_for_bus(ind_from, ind_to, hour, minute);
        tbb = System.currentTimeMillis() - tbb;
        long tbm = System.currentTimeMillis();
        double bm = calculateBellmanFord.calculate_for_metro(ind_from, ind_to, hour, minute);
        tbm = System.currentTimeMillis() - tbm;

        for (int i = 0; i < 2; i++) {
            System.out.println("\n");
        }

        System.out.printf("-----------------------------------------------------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("      Overview Time Complexity for each transport with Dijkstra and Bellman Ford Algorithm              %n");
        System.out.printf("-----------------------------------------------------------------------------------------------------------------------------------------------------------%n");
        for (int i = 0; i < 1; i++) {
            System.out.println("\n");
        }
        System.out.printf("%-25s  %-25s  %-25s %n", "Algorithm / Transport Type", "Dijkstra", "Bellman Ford");
        int d_walk_hour = (hour + (minute + (int) (dw * 60)) / 60) % 24, d_walk_minute = (minute + (int) (dw * 60)) % 60;
        int b_walk_hour = (hour + (minute + (int) (bw * 60)) / 60) % 24, b_walk_minute = (minute + (int) (bw * 60)) % 60;
        System.out.printf("\n");
        System.out.printf("%-25s  %-25s  %-25s %n", "Walk(Time Format)", (hour > 9 ? hour : 0 + "" + hour) + " : " + (minute > 9 ? minute : 0 + "" + minute) + " - " + (d_walk_hour > 9 ? d_walk_hour : 0 + "" + d_walk_hour) + " : " + (d_walk_minute > 9 ? d_walk_minute : 0 + "" + d_walk_minute), (hour > 9 ? hour : 0 + "" + hour) + " : " + (minute > 9 ? minute : 0 + "" + minute) + " - " + (b_walk_hour > 9 ? b_walk_hour : 0 + "" + b_walk_hour) + " : " + (b_walk_minute > 9 ? b_walk_minute : 0 + "" + b_walk_minute));
        System.out.printf("\n");
        System.out.printf("%-25s  %-25s  %-25s %n", "Walk(Time Take)", tdw + " ms", tbw + " ms");
        System.out.printf("\n");
        int d_car_hour = (hour + (minute + (int) (dc * 60)) / 60) % 24, d_car_minute = (minute + (int) (dc * 60)) % 60;
        int b_car_hour = (hour + (minute + (int) (bc * 60)) / 60) % 24, b_car_minute = (minute + (int) (bc * 60)) % 60;
        System.out.printf("%-25s  %-25s  %-25s %n", "Car(Time Format)", (hour > 9 ? hour : 0 + "" + hour) + " : " + (minute > 9 ? minute : 0 + "" + minute) + " - " + (d_car_hour > 9 ? d_car_hour : 0 + "" + d_car_hour) + " : " + (d_car_minute > 9 ? d_car_minute : 0 + "" + d_car_minute), (hour > 9 ? hour : 0 + "" + hour) + " : " + (minute > 9 ? minute : 0 + "" + minute) + " - " + (b_car_hour > 9 ? b_car_hour : 0 + "" + b_car_hour) + " : " + (b_car_minute > 9 ? b_car_minute : 0 + "" + b_car_minute));
        System.out.printf("\n");
        System.out.printf("%-25s  %-25s  %-25s %n", "Car(Time Take)", tdc + " ms", tbc + " ms");
        System.out.printf("\n");
        int d_bus_hour = (hour + (minute + (int) (db * 60)) / 60) % 24, d_bus_minute = (minute + (int) (db * 60)) % 60;
        int b_bus_hour = (hour + (minute + (int) (bb * 60)) / 60) % 24, b_bus_minute = (minute + (int) (bb * 60)) % 60;
        System.out.printf("%-25s  %-25s  %-25s %n", "Bus(Time Format)", (hour > 9 ? hour : 0 + "" + hour) + " : " + (minute > 9 ? minute : 0 + "" + minute) + " - " + (d_bus_hour > 9 ? d_bus_hour : 0 + "" + d_bus_hour) + " : " + (d_bus_minute > 9 ? d_bus_minute : 0 + "" + d_bus_minute), (hour > 9 ? hour : 0 + "" + hour) + " : " + (minute > 9 ? minute : 0 + "" + minute) + " - " + (b_bus_hour > 9 ? b_bus_hour : 0 + "" + b_bus_hour) + " : " + (b_bus_minute > 9 ? b_bus_minute : 0 + "" + b_bus_minute));
        System.out.printf("\n");
        System.out.printf("%-25s  %-25s  %-25s %n", "Bus(Time Take)", tdb + " ms", tbb + " ms");
        System.out.printf("\n");
        int d_metro_hour = (hour + (minute + (int) (dm * 60)) / 60) % 24, d_metro_minute = (minute + (int) (dm * 60)) % 60;
        int b_metro_hour = (hour + (minute + (int) (bm * 60)) / 60) % 24, b_metro_minute = (minute + (int) (bm * 60)) % 60;
        System.out.printf("%-25s  %-25s  %-25s %n", "Metro(Time Format)", (hour > 9 ? hour : 0 + "" + hour) + " : " + (minute > 9 ? minute : 0 + "" + minute) + " - " + (d_metro_hour > 9 ? d_metro_hour : 0 + "" + d_metro_hour) + " : " + (d_metro_minute > 9 ? d_metro_minute : 0 + "" + d_metro_minute), (hour > 9 ? hour : 0 + "" + hour) + " : " + (minute > 9 ? minute : 0 + "" + minute) + " - " + (b_metro_hour > 9 ? b_metro_hour : 0 + "" + b_metro_hour) + " : " + (b_metro_minute > 9 ? b_metro_minute : 0 + "" + b_metro_minute));
        System.out.printf("\n");
        System.out.printf("%-25s  %-25s  %-25s %n", "Metro(Time Take)", tdm + " ms", tbm + " ms");


    }

    public static int LocationDeterminer(int index) {

        Scanner in = new Scanner(System.in);
        String s = "";
        while (index == -1) {
            System.out.println("Type for Location");
            s = in.nextLine().trim();
            System.out.println();
            Trie cur = feedBack.search(feedBack.getRoot(), s, 0);

            if (cur == null) {
                System.out.println("We get nowhere. Try again !!!");
                continue;
            } else {
                System.out.println("We get a few options like \n");
                for (Integer l : cur.list) {
                    System.out.println(feedBack.location_name[l] + " : " + l);
                }
                System.out.println();
                System.out.println("If u want choose one of them input location id or -1 for otherwise");
                System.out.println();
                index = Integer.parseInt(in.nextLine());

            }

        }

        return index;
    }
}


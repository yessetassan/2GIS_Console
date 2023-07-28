package src;

import src.Edge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

class FeedBack {
    public static Trie root = null;
    String[] location_name;
    int size = -1;
    String fill_in = "C:\\Users\\Home\\Desktop\\advalgo\\src\\Fill_in";
    String relation = "C:\\Users\\Home\\Desktop\\advalgo\\src\\RelationShip (5)";
    public int E;
    public int V;
    ArrayList<Edge>[] list_of_edges;
    public FeedBack() throws Exception {

        root = new Trie();
        fill_out_edges();
        list_of_edges = new ArrayList[size];

        for (int i = 0; i < size; i++) {
            list_of_edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < size; i++) {
            root = put(root, location_name[i], i, 0);
        }

        BufferedReader reader = new BufferedReader(new FileReader(relation));
        E = Integer.parseInt(reader.readLine().trim());
        while (reader.ready()) {
            String line = reader.readLine().trim();
            String[] edges = line.split("[ ]");
            int v = Integer.parseInt(edges[0]);
            int w = Integer.parseInt(edges[1]);
            double weight = Double.parseDouble(edges[2]);
            double queue = Double.parseDouble(edges[3]);;
            boolean walk = Integer.parseInt(edges[4]) == 1;
            boolean car = Integer.parseInt(edges[5]) == 1;
            boolean bus = Integer.parseInt(edges[6]) == 1;
            boolean metro = Integer.parseInt(edges[7]) == 1;
            Edge edge = new Edge(v, w, weight,queue,walk,car,bus,metro);
            list_of_edges[v].add(edge);
            list_of_edges[w].add(edge);
        }


    }

    public Trie getRoot() {
        return root;
    }

    private void fill_out_edges() throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader(fill_in));
        size = Integer.parseInt(new String(reader.readLine().trim()));
        V = size;
        location_name = new String[size];
        while (reader.ready()) {
            String line = reader.readLine().trim();
            String[] names = line.split("[,]");
            location_name[Integer.parseInt(names[0])] = names[1].trim();
        }

    }


    public Trie put(Trie x, String s, int index, int i) {

        if (x == null) x = new Trie();
        x.list.add(index);

        if (i == s.length()) {
            return x;
        }


        char c = s.charAt(i);
        int go = -1;

        if (c - 'a' >= 0) go = c - 'a';
        else if (c - 'A' >= 0) go = c - 'A';
        else go = c;

        if (c == ' ') go = 129;
        x.next[go] = put(x.next[go], s, index, i + 1);

        return x;
    }

    public Trie search(Trie x, String s, int i) {

        if (x == null) {
            return x;
        }

        if (s.length() == i) return x;

        char c = s.charAt(i);
        int go = -1;
        if (c - 'a' >= 0) go = c - 'a';
        else if (c - 'A' >= 0) go = c - 'A';
        else go = c;

        if (c == ' ') go = 129;

        return search(x.next[go], s, i + 1);
    }

}

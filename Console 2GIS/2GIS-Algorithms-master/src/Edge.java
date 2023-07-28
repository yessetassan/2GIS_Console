package src;

class Edge {
    int u;
    int v;
    double weight;
    double queue;
    boolean walk;
    boolean car;
    boolean bus;
    boolean metro;
    public Edge(int u, int v, double weight,double queue, boolean walk,boolean car, boolean bus,boolean metro) {
        this.u = u;
        this.v = v;
        this.weight = weight;
        this.queue = queue;
        this.walk =walk;
        this.car = car;
        this.bus = bus;
        this.metro = metro;
    }
}

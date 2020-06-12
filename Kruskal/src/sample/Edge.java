package sample;

public class Edge {
    int i;
    int j;

    int weight;

    Edge(int i, int j, int weight){
        this.j = j;
        this.i = i;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    @Override
    public String toString() {
        return "{" +
                "i=" + i +
                ", j=" + j +
                ", weight=" + weight +
                '}';
    }
}

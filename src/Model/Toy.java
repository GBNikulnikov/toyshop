package Model;

@SuppressWarnings("ALL")
public class Toy implements Comparable<Toy> {
    public int id;
    public String name;
    public int quantity;
    public int weightFactor;

    public Toy(int id, String name, int quantity, int weightFactor) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.weightFactor = weightFactor;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getWeightFactor() {
        return weightFactor;
    }
    public void setWeightFactor(int weightFactor) {
        this.weightFactor = weightFactor;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public int getQuantity() {return quantity;}

    public void setQuantity(int quantity) {this.quantity = quantity;}


    @Override
    public String toString() {
        return "Id игрушки:" + id + "; Название: " + name + "; Кол-во " + quantity + "; Вес(%): " + weightFactor;
    }

    @Override
    public int compareTo(Toy o) {
        return o.getWeightFactor() - this.getWeightFactor();
    }
}

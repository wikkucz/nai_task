import java.util.List;

public class Iris {
    private List<Double> characteristics;
    private int type;

    public Iris(List<Double> characteristics, int type) {
        this.characteristics = characteristics;
        this.type = type;
    }

    public List<Double> getCharacteristics() {
        return characteristics;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Double characteristic : characteristics) {
            str.append(characteristic).append(",");
        }
        str.append(" " + type);
        return str.toString();
    }
}

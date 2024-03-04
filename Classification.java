import java.util.*;

public class Classification {
    static int isRight;
    static int isFalse;
    private int columns;
    private List<Iris> trainingList;
    private List<Iris> testingList;
    private double size;
    private double percent;
    private int numFeatures;
    private double threshold;
    Random r = new Random();
    double random = r.nextDouble()/100;

    private double[] weights;
    private double learningRate;

    public Classification(List<Iris> trainingList, List<Iris> testingList, int columns, double learningRate) {
        this.trainingList = trainingList;
        this.testingList = testingList;
        this.columns = columns;
        this.numFeatures = columns -1;
        weights = new double[numFeatures];
        this.learningRate = learningRate;
    }


    public void train(int numEpochs) {
        for (int i = 0; i < numEpochs; i++) {
            double d = 0;
            Collections.shuffle(trainingList);
            double error = 0.0;
            for (Iris iris : trainingList) {
                double predicted = predict(iris.getCharacteristics()); // obliczenie wyjścia perceptronu
                double expected = iris.getType(); // wartość oczekiwana
                double delta = expected - predicted; // obliczenie błędu
                d = delta;
                error += Math.abs(delta); // suma błędów

                // aktualizacja wag
                for (int j = 0; j < weights.length; j++) {
                    weights[j] += learningRate * delta * iris.getCharacteristics().get(j);

                }
            }
            System.out.println("Epoka " + i + " Error: " + error);
            threshold += learningRate * d;
            if (error == 0.0) {
                break; // przerwanie uczenia, gdy błąd wynosi 0
            }
        }
    }

    public int predict(List<Double> features) {
        double dotProduct = 0.0;
        for (int i = 0; i < weights.length; i++) {
            dotProduct += weights[i] * features.get(i);
        }
        return (dotProduct >= threshold) ? 1 : -1; // klasyfikacja
    }


    public int getIsRight() {
        return isRight;
    }

    public int getIsFalse() {
        return isFalse;
    }

    public double accuracy(){
        size = testingList.size();
        percent = isRight / size * 100;
        return percent;
    }
}

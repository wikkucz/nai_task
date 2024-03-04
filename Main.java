import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class Main {

    static File trainingFile = new File("iris_training.txt");
    static File testingFile = new File("iris_test.txt");
    static String irisType = "Iris-versicolor";

    public static void main(String[] args) {

        List<String> list2 = new ArrayList<>();
        List<Iris> trainingList = new ArrayList<>();
        List<Iris> testingList = new ArrayList<>();
        int columns = 0;
        Random r = new Random();
        double random = r.nextDouble()/5;

        String question = JOptionPane.showInputDialog("Jeśli chcesz podać własne dane wpisz 0, jeśli wczytać dane z pliku testowego wpisz 1");

        if(question.equals("0")){

            String input = JOptionPane.showInputDialog("Podaj dane (separatorem musi być spacja) według przykładu 5,1 3,5 1,4 0,2");

            //wczytanie danych z pliku treningowego
            FileOperations fileOperations = new FileOperations(trainingFile, trainingList);
            fileOperations.readFile();

            String[] splited = input.trim().split("\s");
            columns = splited.length;
            List<Double> characteristics = new ArrayList<>();

            for (int i = 0; i < columns; i++) {
                characteristics.add(Double.parseDouble(splited[i].replaceAll(",", ".")));
            }
            Iris iris = new Iris(characteristics, 0);
            testingList.add(iris);

            Classification classification = new Classification(trainingList, testingList,columns, random);
            classification.train(1000);
            if (classification.predict(iris.getCharacteristics()) == 1)
                System.out.println("To jest " + irisType);
            else System.out.println("To nie jest " + irisType);

        } else if(question.equals("1")) {

            //wczytanie danych z pliku treningowego
            FileOperations fileOperations = new FileOperations(trainingFile, trainingList);
            fileOperations.readFile();

            //wczytanie danych z pliku testowego
            if (testingFile.exists()) {
                try {
                    list2 = Files.readAllLines(testingFile.toPath());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if (list2.isEmpty())
                    return;
            }
            for (String line : list2) {
                String[] splited = line.trim().split("\t");
                columns = splited.length;
                List<Double> characteristics = new ArrayList<>();
                String species = "";
                int type;
                for (int i = 0; i < columns - 1; i++) {
                    characteristics.add(Double.parseDouble(splited[i].replaceAll(",", ".")));
                }
                species = splited[columns - 1].trim();
                if(species.equals(irisType)) {
                    type = 1;
                } else type = -1;
                Iris iris = new Iris(characteristics, type);
                testingList.add(iris);
            }

            Classification classification = new Classification(trainingList, testingList, columns, random);
            classification.train(1000);

            for (Iris iris : testingList) {
                int predictedLabel = classification.predict(iris.getCharacteristics());
                System.out.println("Wynik: " + predictedLabel + " , powinno być: " + iris.getType());
                if(predictedLabel == iris.getType()) Classification.isRight++;
                else Classification.isFalse++;
            }

            System.out.println("Liczba prawidłowo zaklasyfikowanych przykładów: " + classification.getIsRight());
            System.out.println("Liczba nieprawidłowo zaklasyfikowanych przykładów: " + classification.getIsFalse());
            System.out.println("Dokładność eksperymentu: " + classification.accuracy() + "%");

        } else System.out.println("Podałeś złą liczbę");
    }
}
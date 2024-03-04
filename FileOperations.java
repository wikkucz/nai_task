import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileOperations {
    private File trainingFile;
    private List<String> list = new ArrayList<>();
    private List<Iris> trainingList;
    static int fileSize;
    private int columns = 0;

    public FileOperations(File trainingFile, List<Iris> trainingList) {
        this.trainingFile = trainingFile;
        this.trainingList = trainingList;
    }

    public List<Iris> readFile(){
        //wczytanie danych z pliku treningowego
        if (trainingFile.exists()) {
            try {
                list = Files.readAllLines(trainingFile.toPath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (list.isEmpty())
                System.out.println("Plik jest pusty");
        }
        for (String line : list) {
            String[] splited = line.trim().split("\t");
            columns = splited.length;
            List<Double> charcteristics = new ArrayList<>();
            String species = "";
            int type;
            for (int i = 0; i < columns - 1; i++) {
                charcteristics.add(Double.parseDouble(splited[i].replaceAll(",", ".")));
            }
            species = splited[columns - 1].trim();
            if(species.equals(Main.irisType)) {
                type = 1;
            } else type = -1;
            Iris iris = new Iris(charcteristics, type);
            trainingList.add(iris);
        }
        fileSize = trainingList.size();
        return trainingList;
    }
}

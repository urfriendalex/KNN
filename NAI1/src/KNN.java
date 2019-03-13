import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class KNN {

    public static List<Vector> storeFile(File file) throws  IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        String irisClass;
        List<Vector> vectors = new ArrayList<>();
        while((line = br.readLine()) != null) {
            int i;
            String[] lineSplit = line.split(",");
            double[] vectorValues = new double[lineSplit.length - 1];
            for ( i = 0; i < vectorValues.length; i++) {
                vectorValues[i] = Double.parseDouble(lineSplit[i]);
            }
            irisClass = lineSplit[i];
            vectors.add(new Vector(vectorValues,irisClass));
        }
        br.close();
        return vectors;
    }

    public static void KNNAlg(List<Vector> test,int k) {
        int wrong = 0;
        for (Vector testVector : test) {
            testVector.setPredictedClass(mostFrequent(testVector.getDistances(),k));
         //   System.out.println("Original class is: " + testVector.getIrisClass() + ", tested one is: " + testVector.getPredictedClass()); //log
            if(!testVector.getIrisClass().isEmpty() && !testVector.getIrisClass().equals(testVector.getPredictedClass())) {
                wrong++;
                System.out.println("Original class is: " + testVector.getIrisClass() + ", predicted one is: " + testVector.getPredictedClass()); //log
            }
        }
        double res = 100-(((double)wrong/(double)test.size())*100);
        System.out.println("Accuracy with " + k + " nearest neighbours is: " + res);
    }

     private static double getDistance(double[] vector1, double[] vector2) {
        double sum = 0;
        for (int i = 0; i < vector1.length; i++)
            sum += Math.pow(vector1[i] - vector2[i], 2);
        return Math.sqrt(sum);
    }

    private static String mostFrequent(List<Distance> list, int k){
        return  list.stream()
                .sorted()
                .limit(k)
                .map(Distance::getTo)
                .map(Vector::getIrisClass)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getKey()
                ;
    }

    public static Vector scanVector(){
        Scanner scanner = new Scanner(System.in);

        ArrayList<Double> inputVector = new ArrayList<>();

        while (scanner.hasNextDouble()) {
            inputVector.add(scanner.nextDouble());
        }
        double[] inputDoubles = new double[inputVector.size()];
        for (int i = 0; i < inputDoubles.length; i++) {
            inputDoubles[i] = inputVector.get(i);
        }
        return new Vector(inputDoubles);
    }

    public static void calculateDistances(List<Vector> test, List<Vector> train){
        for (Vector testVector : test) {
            for (Vector trainVector : train) {
                testVector.addDistance(new Distance(testVector, trainVector,
                        getDistance(testVector.getValues(), trainVector.getValues())));
            }
        }
    }
    public static void classifyVector(Vector inputVector){
        // TODO: 2019-03-13  
    }
}

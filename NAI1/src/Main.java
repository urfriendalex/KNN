import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String args[]) throws IOException {
        String testFileName = "/Users/tobeurdeath/Desktop/untitled/study/NAI/iris/test.txt";
        String trainFileName = "/Users/tobeurdeath/Desktop/untitled/study/NAI/iris/train.txt";

        File testFile = new File(testFileName);
        File trainFile = new File(trainFileName);

        List<Vector> testList = KNN.storeFile(testFile);
        List<Vector> trainList = KNN.storeFile(trainFile);

        System.out.println("Enter the k: ");
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        System.out.println("You have chosen:  " + k);

        KNN.calculateDistances(testList,trainList);
        KNN.KNNAlg(testList,k);

        System.out.println("=======================");

        System.out.println("Plotting accuracy versus k...");
        for (int i = 1; i <=120 ; i++) {
            KNN.KNNAlg(testList,i);
            System.out.println();
        }

        System.out.println("=======================");

        System.out.println("Enter the values of the vector\t the number of values need to be as regards training file");

        testList.clear();
        testList.add(KNN.scanVector());

        KNN.calculateDistances(testList,trainList);
        KNN.KNNAlg(testList,k);
    }
}

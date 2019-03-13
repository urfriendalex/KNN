import java.util.*;

public class Vector {

    private double[] values;
    private String IrisClass;
    private List<Distance> distances;
    private String predictedClass;

    public Vector(double[] values) {
        this.values = values;
        IrisClass="";
        distances = new ArrayList<>();
    }

    public Vector(double[] values, String irisClass) {
        this.values = values;
        IrisClass = irisClass;
        distances = new ArrayList<>();
    }

    public List<Distance> getDistances() {
        return distances;
    }

    public double[] getValues() {
        return values;
    }

    public String getIrisClass() {
        return IrisClass;
    }

    public void addDistance(Distance distance){
        distances.add(distance);
        distances.sort(Distance::compareTo);
    }

    public String getPredictedClass() {
        return predictedClass;
    }

    public void setPredictedClass(String predictedClass) {
        this.predictedClass = predictedClass;
    }

    @Override
    public String toString() {
        return IrisClass + " " + Arrays.toString(values);
    }

}

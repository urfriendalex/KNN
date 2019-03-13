public class Distance implements Comparable<Distance>{
    Vector from;
    Vector to;

    double distance;

    public Distance(Vector from, Vector to, double distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public Vector getFrom() {
        return from;
    }

    public Vector getTo() {
        return to;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return to.getIrisClass() + " - " + from.getIrisClass() + ": " + distance;
    }

    @Override
    public int compareTo(Distance otherDistance) {
        return (Double.compare(this.getDistance(), otherDistance.getDistance()));
    }
}

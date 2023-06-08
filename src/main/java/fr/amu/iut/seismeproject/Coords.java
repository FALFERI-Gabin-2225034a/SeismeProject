package fr.amu.iut.seismeproject;

public class Coords {
    private double X;
    private double Y;

    public Coords() {
        X = 0;
        Y = 0;
    }

    public Coords(double X, double Y) {
        this.X = X;
        this.Y = Y;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }

    @Override
    public String toString() {
        return "Coords{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }
}

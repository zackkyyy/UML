package model;


public class Boat {
    private double lenght;
    private BoatType type;


    public Boat(double length, BoatType type) {
        setLenght(length);
        this.type = type;
    }

    //setters and getters
    public void setLenght(double lenght) {
        //check if the length is a valid number
        if (lenght < 0) {
            throw new IllegalArgumentException();
        } else
            this.lenght = lenght;
    }

    public void setType(BoatType type) {
        this.type = type;
    }

    public double getLength() {
        return lenght;
    }

    public BoatType getBoatType() {
        return type;
    }


}

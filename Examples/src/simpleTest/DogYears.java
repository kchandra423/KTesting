package simpleTest;

public class DogYears {

    public int convertToHumanYears(int dog) {
        return round(0.188097 * dog - 1.46026);
    }

    public int convertToDogYears(int human) {
        return round(5.32585 * human + 7.7666);
    }

    public int round(double d) {
        d += 0.5;
        return (int) d;
    }


}
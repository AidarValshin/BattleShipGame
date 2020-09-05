package structure;

public enum Ship {
    BATTLESHIP(4, 1, 2),
    CRUISER(3, 2, 3),
    DESTROYER(2, 3, 4),
    BOAT(1, 4, 5);

    private int length;
    private int maxCount;
    private int numberInField;

    Ship(int length, int maxCount, int numberInField) {
        this.length = length;
        this.maxCount = maxCount;
        this.numberInField = numberInField;  //+ 50 if horizontal
    }

    public static Ship getShipByNumberInField(int number) {
        for (Ship ship : Ship.values()) {
            if (ship.numberInField == number) {
                return ship;
            }
        }
        return null;
    }

    public int getLength() {
        return length;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public int getNumberInField() {
        return numberInField;
    }

    @Override
    public String toString() {
        return this.name() + " with length: " + this.length + " cels and  count for using: " + this.maxCount;
    }
}

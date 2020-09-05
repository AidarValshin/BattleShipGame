package functions.performingField;

import structure.Field;
import structure.Ship;

public class DeleteShip {
    public static void deleteShip(Field field, int x, int y, int axis, Ship ship) {
        if (axis == Field.getHorizontal()) {
            for (int i = x; i < x + ship.getLength(); i++) {
                field.getField()[y][i] = Field.getClearCell();
            }
            if (axis == Field.getVertical()) { //vertically
                for (int i = y; i < y + ship.getLength(); i++) {
                    field.getField()[i][x] = Field.getClearCell();
                }
            }
        }
    }
}

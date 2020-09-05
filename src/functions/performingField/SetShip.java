package functions.performingField;

import structure.Field;
import structure.Ship;

public class SetShip {
    public static boolean setShip(Field field, int x, int y, int axis, Ship ship) { //axis 1 - Field.getVertical(); 2 - gorizontal
        if (x > -1 && y > -1) {
            if (x < Field.getSizeField() && y < Field.getSizeField()) {
                if (axis == Field.getHorizontal()) {
                    return setShipHorizontal(field.getField(), x, y, ship);
                }
                if (axis == Field.getVertical()) { //vertically
                    return setShipVertical(field.getField(), x, y, ship);
                }
            }
        }
        return false;
    }

    private static boolean setShipVertical(int[][] field, int x, int y, Ship ship) {
        int max = y + ship.getLength() - 1;
        if (max < Field.getSizeField()) {
            int maxSide = max + 1;
            if (max < 9) {
                max++;
            }
            for (int i = y; i < max + 1; i++)
                if (field[i][x] != Field.getClearCell()) {
                    return false;
                }
            if (y > 0 && field[y - 1][x] != Field.getClearCell()) {
                return false;
            }
            if (x > 0) {
                for (int i = y; i < maxSide; i++)
                    if (field[i][(x - 1)] != Field.getClearCell()) {
                        return false;
                    }
            }
            if (x < 9) {
                for (int i = y; i < maxSide; i++)
                    if (field[i][x + 1] != Field.getClearCell()) {
                        return false;
                    }
            }
            for (int i = y; i < y + ship.getLength(); i++) {
                field[i][x] = (ship.getNumberInField() + Field.getAddVertical());
            }
            return true;
        }
        return false;
    }

    private static boolean setShipHorizontal(int[][] field, int x, int y, Ship ship) {
        int max = x + ship.getLength() - 1;
        if (max < Field.getSizeField()) {
            int maxSide = max + 1;
            if (max < 9) {
                max++;
            }
            for (int i = x; i < max + 1; i++)
                if (field[y][i] != Field.getClearCell()) {
                    return false;
                }
            if (x > 0 && field[y][x - 1] != Field.getClearCell()) {
                return false;
            }
            if (y > 0) {
                if (max < 9)
                    for (int i = x; i < maxSide; i++)
                        if (field[y - 1][i] != Field.getClearCell()) {
                            return false;
                        }
            }
            if (y < 9) {
                for (int i = x; i < maxSide; i++)
                    if (field[y + 1][i] != Field.getClearCell()) {
                        return false;
                    }
            }
            for (int i = x; i < x + ship.getLength(); i++) {
                field[y][i] = ship.getNumberInField();
            }
            return true;
        }
        return false;
    }
}

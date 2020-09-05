package functions.performingField;

import structure.Field;
import structure.Ship;

public class Shot {
    public static int shoot(Field field, int x, int y) {
        if (x < 0 || y < 0 || x > Field.getSizeField() - 1 || y > Field.getSizeField() - 1) {
            return Field.getResultWrongArguments();
        }
        if (field.getField()[y][x] > Field.getAddHit()) {
            return Field.getResultShotBefore();
        } else if (field.getField()[y][x] == Field.getResultMiss()) {
            return Field.getResultShotBefore();
        } else if (field.getField()[y][x] == Field.getClearCell()) {
            field.getField()[y][x] = Field.getResultMiss();
            return Field.getResultMiss();
        } else {
            int type = field.getField()[y][x];
            int axis;
            if (type > Field.getAddVertical()) {
                axis = Field.getVertical();
            } else {
                axis = Field.getHorizontal();
            }
            Ship ship = Ship.getShipByNumberInField(type - ((Field.getHorizontal() - axis) * Field.getAddVertical()));
            return hitShip(field, ship, axis, type, x, y);
        }
    }

    private static int hitShip(Field field, Ship ship, int axis, int type, int x, int y) {
        field.getField()[y][x] += Field.getAddHit();
        if (ship == Ship.BOAT) {
            field.getField()[y][x] += Field.getAddSink();
            field.setCountShips(field.getCountShips() - 1);
            if (field.getCountShips() == 0) {
                return Field.getResultAllSink();
            }
            return Field.getResultSink();
        } else {
            int count = ship.getLength() - 1;

            if (axis == Field.getHorizontal()) {
                return hitShipBigHorizontal(field, ship, count, type, x, y);
            } else { //Field.getVertical()
                return hitShipBigVertical(field, ship, count, type, x, y);
            }
        }
    }

    private static int hitShipBigHorizontal(Field field, Ship ship, int count, int type, int x, int y) {
        int i = x - 1;
        while (i > -1 && field.getField()[y][i] > Field.getAddHit()) {
            count--;
            i--;
        }
        i = x + 1;
        while (i < Field.getSizeField() && field.getField()[y][i] > Field.getAddHit()) {
            count--;
            i++;
        }
        if (count == 0) {
            field.setCountShips(field.getCountShips() - 1);
            if (field.getCountShips() == 0) {
                return Field.getResultAllSink();
            }
            i = x - 1;
            while (i > -1 && field.getField()[y][i] > Field.getAddHit()) {
                field.getField()[y][i] += Field.getAddSink();
                i--;
            }
            i = x + 1;
            while (i < Field.getSizeField() && field.getField()[y][i] > Field.getAddHit()) {
                field.getField()[y][i] += Field.getAddSink();
                i++;
            }
            field.getField()[y][x] += Field.getAddSink();
            return Field.getResultSink();
        } else {
            return Field.getResultHit();
        }
    }

    private static int hitShipBigVertical(Field field, Ship ship, int count, int type, int x, int y) {
        int i = y - 1;
        while (i > -1 && field.getField()[i][x] > Field.getAddHit()) {
            count--;
            i--;
        }
        i = y + 1;
        while (i < Field.getSizeField() && field.getField()[i][x] > Field.getAddHit()) {
            count--;
            i++;
        }
        if (count == 0) {
            field.setCountShips(field.getCountShips() - 1);
            if (field.getCountShips() == 0) {
                return Field.getResultAllSink();
            }
            i = y - 1;
            while (i > -1 && field.getField()[i][x] > Field.getAddHit()) {
                field.getField()[i][x] += Field.getAddSink();
                i--;
            }
            i = y + 1;
            while (i < Field.getSizeField() && field.getField()[i][x] > Field.getAddHit()) {
                field.getField()[i][x] += Field.getAddSink();
                i++;
            }
            field.getField()[y][x] += Field.getAddSink();
            return Field.getResultSink();
        } else {
            return Field.getResultHit();
        }
    }
}

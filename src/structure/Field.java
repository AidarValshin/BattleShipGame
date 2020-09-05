package structure;

import java.io.Serializable;

/*
    ship  - hor -vertical - hit(h)-hit(ver) -sink(hor)- sink(vert)
 BATTLESHIP 2-12-22-32-52-62
 CRUISER    3-13-23-33-53-63
 DESTROYER  4-14-24-34-54-64
 BOAT       5-15-25-35-55-65
 +          0+10(vert)+20(hit hor)+20(hit vert)+30(sink hor) +30(sink ver)
 miss -1
 */
public class Field implements Serializable {
    private static final int addVertical = 10;
    private static final int addHit = 20;
    private static final int addSink = 30;
    private static final int resultHit = 1;
    private static final int resultSink = 2;
    private static final int resultMiss = -1;
    private static final int resultShotBefore = -10;
    private static final int resultWrongArguments = -50;
    private static final int resultAllSink = 1000;
    private static final int clearCell = 0;
    private static final int vertical = 1;
    private static final int horizontal = 2;
    private static int sizeField = 10;
    private int[][] field = new int[sizeField][sizeField];
    // counts for analyzing
    private int countShips = Ship.BATTLESHIP.getMaxCount() + Ship.CRUISER.getMaxCount() +
            Ship.DESTROYER.getMaxCount() + Ship.BOAT.getMaxCount();

    public static int getSizeField() {
        return sizeField;
    }

    public static int getAddVertical() {
        return addVertical;
    }

    public static int getAddHit() {
        return addHit;
    }

    public static int getAddSink() {
        return addSink;
    }

    public static int getResultHit() {
        return resultHit;
    }

    public static int getResultSink() {
        return resultSink;
    }

    public static int getResultMiss() {
        return resultMiss;
    }

    public static int getResultShotBefore() {
        return resultShotBefore;
    }

    public static int getResultWrongArguments() {
        return resultWrongArguments;
    }

    public static int getResultAllSink() {
        return resultAllSink;
    }

    public static int getClearCell() {
        return clearCell;
    }

    public static int getVertical() {
        return vertical;
    }

    public static int getHorizontal() {
        return horizontal;
    }

    public int getCountShips() {
        return countShips;
    }

    /*
        public void deleteShip(int x, int y, int axis, Ship ship) {
            if (axis == horizontal) {
                for (int i = x; i < x + ship.getLenghth(); i++) {
                    field[y][i] = clearCell;
                }
                if (axis == vertical) { //vertically
                    for (int i = y; i < y + ship.getLenghth(); i++) {
                        field[i][x] = clearCell;
                    }
                }
            }
        }

        private boolean setShipVertical(int x, int y, Ship ship) {
            int max = y + ship.getLenghth() - 1;
            if (max < sizeField) {
                int maxSide = max + 1;
                if (max < 9) {
                    max++;
                }
                for (int i = y; i < max + 1; i++)
                    if (field[i][x] != clearCell) {
                        return false;
                    }
                if (y > 0 && field[y - 1][x] != clearCell) {
                    return false;
                }
                if (x > 0) {
                    for (int i = y; i < maxSide; i++)
                        if (field[i][(x - 1)] != clearCell) {
                            return false;
                        }
                }
                if (x < 9) {
                    for (int i = y; i < maxSide; i++)
                        if (field[i][x + 1] != clearCell) {
                            return false;
                        }
                }
                for (int i = y; i < y + ship.getLenghth(); i++) {
                    field[i][x] = (ship.getNumberInField() + addVertical);
                }
                return true;
            }
            return false;
        }

        private boolean setShipHorizontal(int x, int y, Ship ship) {
            int max = x + ship.getLenghth() - 1;
            if (max < sizeField) {
                int maxSide = max + 1;
                if (max < 9) {
                    max++;
                }
                for (int i = x; i < max + 1; i++)
                    if (field[y][i] != clearCell) {
                        return false;
                    }
                if (x > 0 && field[y][x - 1] != clearCell) {
                    return false;
                }
                if (y > 0) {
                    if (max < 9)
                        for (int i = x; i < maxSide; i++)
                            if (field[y - 1][i] != clearCell) {
                                return false;
                            }
                }
                if (y < 9) {
                    for (int i = x; i < maxSide; i++)
                        if (field[y + 1][i] != clearCell) {
                            return false;
                        }
                }
                for (int i = x; i < x + ship.getLenghth(); i++) {
                    field[y][i] = ship.getNumberInField();
                }
                return true;
            }
            return false;
        }

        public int shoot(int x, int y) {
            if (x < 0 || y < 0 || x > sizeField - 1 || y > sizeField - 1) {
                return resultWrongArguments;
            }
            if (field[y][x] > addHit) {
                return resultShotBefore;
            } else if (field[y][x] == resultMiss) {
                return resultShotBefore;
            } else if (field[y][x] == clearCell) {
                field[y][x] = resultMiss;
                return resultMiss;
            } else {
                int type = field[y][x];
                int axis;
                if (type > addVertical) {
                    axis = vertical;
                } else {
                    axis = horizontal;
                }
                Ship ship = Ship.getShipByNumberInField(type - ((horizontal - axis) * addVertical));
                return hitShip(ship, axis, type, x, y);
            }
        }

        private int hitShip(Ship ship, int axis, int type, int x, int y) {
            field[y][x] += addHit;
            if (ship == Ship.BOAT) {
                field[y][x] += addSink;
                --countShips;
                if (countShips == 0) {
                    return resultAllSink;
                }
                return resultSink;
            } else {
                int count = ship.getLenghth() - 1;

                if (axis == horizontal) {
                    return hitShipBigHorizontal(ship, count, type, x, y);
                } else { //vertical
                    return hitShipBigVertical(ship, count, type, x, y);
                }
            }
        }

        private int hitShipBigHorizontal(Ship ship, int count, int type, int x, int y) {
            int i = x - 1;
            while (i > -1 && field[y][i] > addHit) {
                count--;
                i--;
            }
            i = x + 1;
            while (i < sizeField && field[y][i] > addHit) {
                count--;
                i++;
            }
            if (count == 0) {
                countShips--;
                if (countShips == 0) {
                    return resultAllSink;
                }
                i = x - 1;
                while (i > -1 && field[y][i] > addHit) {
                    field[y][i] += addSink;
                    i--;
                }
                i = x + 1;
                while (i < sizeField && field[y][i] > addHit) {
                    field[y][i] += addSink;
                    i++;
                }
                field[y][x] += addSink;
                return resultSink;
            } else {
                return resultHit;
            }
        }

        private int hitShipBigVertical(Ship ship, int count, int type, int x, int y) {
            int i = y - 1;
            while (i > -1 && field[i][x] > addHit) {
                count--;
                i--;
            }
            i = y + 1;
            while (i < sizeField && field[i][x] > addHit) {
                count--;
                i++;
            }
            if (count == 0) {
                countShips--;
                if (countShips == 0) {
                    return resultAllSink;
                }
                i = y - 1;
                while (i > -1 && field[i][x] > addHit) {
                    field[i][x] += addSink;
                    i--;
                }
                i = y + 1;
                while (i < sizeField && field[i][x] > addHit) {
                    field[i][x] += addSink;
                    i++;
                }
                field[y][x] += addSink;
                return resultSink;
            } else {
                return resultHit;
            }
        }
    */
    public void setCountShips(int countShips) {
        this.countShips = countShips;
    }

    public int[][] getField() {
        return field;
    }

    public boolean changeField(int x, int y, int number) {
        if (x > -1 && y > -1) {
            if (x < sizeField && y < sizeField) {
                field[y][x] = number;
                return true;
            }
        }
        return false;
    }
}

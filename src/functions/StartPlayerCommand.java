package functions;

import functions.performingField.DeleteShip;
import functions.performingField.SetShip;
import structure.*;

public class StartPlayerCommand implements ICommand {

    @Override
    public void execute(Field field, IParameterReader reader, IMessageWriter writer) {
        writer.writeln("Hello, lets start.");
        writer.writeln("You need to place your ships:");
        ShowRealFieldCommand showMyField = new ShowRealFieldCommand();
        for (Ship ship : Ship.values()) {
            writer.writeln(ship.toString());
        }

        writer.writeln("The ships can only be placed vertically or horizontally.");
        writer.writeln("Ships must not overlap each other and be placed nearby");
        writer.writeln("Required place between ships - 1 cell");
        writer.writeln("Once the guessing begins, the players may not move the ships.");
        Boolean flag;
        int count;
        int ace;
        int x = 0;
        int y = 0;
        for (Ship ship : Ship.values()) {
            showMyField.execute(field, reader, writer);
            writer.writeln("Chose place for " + ship.toString());
            count = ship.getMaxCount();
            for (int i = count; i > 0; i--) {
                flag = false;
                while (!flag) {
                    if (ship.getNumberInField() != Ship.BOAT.getNumberInField()) {
                        writer.writeln("Chose vertically (1)  or horizontally (2) ");
                        ace = reader.readInt();
                    } else {
                        ace = 1;
                    }
                    if (ace == 1 || ace == 2) {
                        writer.writeln("Chose the leftist postion (x) 0 to 9");
                        x = reader.readInt();
                        writer.writeln("Chose the highest  postion (y) 0 to 9");
                        y = reader.readInt();
                        flag = SetShip.setShip(field, x, y, ace, ship);
                        if (!flag) {
                            writer.writeln("You write wrong coordinates");
                        }
                    } else {
                        writer.writeln("You write wrong axis ");
                    }
                    if (flag) {
                        showMyField.execute(field, reader, writer);
                        int agree;
                        do {
                            writer.writeln("Do you agree (1) or not (2)");
                            agree = reader.readInt();
                            if (agree == 2) {
                                flag = false;
                                DeleteShip.deleteShip(field, x, y, ace, ship);
                            } else if (agree == 1) {
                                flag = true;
                            }
                        } while (agree != 1 && agree != 2);
                    }
                    if (!flag) {
                        showMyField.execute(field, reader, writer);
                    }
                }
            }
        }
        writer.writeln("START!");
    }
}


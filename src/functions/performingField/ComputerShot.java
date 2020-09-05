package functions.performingField;

import structure.Field;
import structure.IMessageWriter;
import structure.IParameterReader;

public class ComputerShot {
    private static int x = 0;
    private static int y = 0;

    public static int shoot(Field field, IParameterReader reader, IMessageWriter writer) {
        int result = Shot.shoot(field, x++, y);
        // writer.writeln(result);
        if (x == Field.getSizeField()) {
            x = 0;
            y++;
        }
        return result;
    }
}


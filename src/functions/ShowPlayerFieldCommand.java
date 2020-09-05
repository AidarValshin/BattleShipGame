package functions;

import structure.Field;
import structure.ICommand;
import structure.IMessageWriter;
import structure.IParameterReader;

public class ShowPlayerFieldCommand implements ICommand {
    @Override
    public void execute(Field field, IParameterReader reader, IMessageWriter writer) {
        writer.write("   ");
        for (int x = 0; x < 10; x++) {
            writer.write(x);
            writer.write("  ");
        }
        writer.write("\n");
        for (int x = 0; x < 10; x++) {
            writer.write("___");
        }
        writer.writeln("_");
        int y = 0;
        for (int[] i : field.getField()) {
            writer.write(y++);
            writer.write(" |");
            for (int j : i) {
                if (j < Field.getClearCell()) {
                    writer.write(j);
                } else if (j >= Field.getClearCell()) {
                    if (j < Field.getAddHit()) {
                        writer.write(Field.getClearCell());
                    } else if (j > Field.getAddHit() && j <
                            (Field.getAddHit() + Field.getAddSink())) {
                        writer.write(Field.getResultHit());
                    } else if (j > (Field.getAddHit() + Field.getAddSink())
                            && j < (10 + Field.getAddVertical()
                            + Field.getAddHit() + Field.getAddSink())) {
                        writer.write(Field.getResultSink());
                    }
                    writer.write(" ");
                }
                writer.write(" ");
            }
            writer.writeln();
        }
    }
}

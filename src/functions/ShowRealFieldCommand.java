package functions;

import structure.Field;
import structure.ICommand;
import structure.IMessageWriter;
import structure.IParameterReader;

public class ShowRealFieldCommand implements ICommand {
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
                writer.write(j);
                if (j >= 0 && j < 10) {
                    writer.write(" ");
                }
                writer.write(" ");
            }
            writer.writeln();
        }
    }

}

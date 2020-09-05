package functions;

import structure.Field;
import structure.ICommand;
import structure.IMessageWriter;
import structure.IParameterReader;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SaveFieldCommand implements ICommand {

    @Override
    public void execute(Field field, IParameterReader reader, IMessageWriter writer) {
        writer.writeln("Write numberfor saving");
        String name = "field" + reader.readInt();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name))) {
            oos.writeObject(field);
        } catch (Exception ex) {
            writer.writeln(ex.getMessage());
        }
    }
}

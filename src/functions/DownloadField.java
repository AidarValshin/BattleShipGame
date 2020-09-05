package functions;

import structure.Field;
import structure.IMessageWriter;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class DownloadField {

    public static Field execute(IMessageWriter writer, String name) {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name))) {
            Field field = (Field) ois.readObject();
            return field;
        } catch (Exception ex) {
            writer.writeln(ex.getMessage());
            return null;
        }
    }
}

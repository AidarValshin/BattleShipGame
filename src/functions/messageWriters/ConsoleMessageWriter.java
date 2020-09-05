package functions.messageWriters;

import structure.IMessageWriter;

public class ConsoleMessageWriter implements IMessageWriter {
    public void write(String message) {
        System.out.print(message);
    }

    public void write(int message) {
        System.out.print(message);
    }

    public void writeln(String message) {
        System.out.println(message);
    }

    public void writeln(int message) {
        System.out.println(message);
    }

    public void writeln() {
        System.out.print("\n");
    }
}

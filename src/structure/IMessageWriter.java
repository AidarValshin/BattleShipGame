package structure;

// Interface for showing messages
public interface IMessageWriter {
    void write(String Message);  // write message

    void write(int Message);  // write message

    void writeln(String Message);  // write message and move to new line

    void writeln(int Message);  // write message and move to new line

    void writeln();  // move to new line
}

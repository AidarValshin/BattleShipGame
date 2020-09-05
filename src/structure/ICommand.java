package structure;

// Interface for executing commands
public interface ICommand {
    void execute(Field field, IParameterReader reader, IMessageWriter writer);    // execute command
}

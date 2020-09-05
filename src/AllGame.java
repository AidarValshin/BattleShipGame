import functions.messageWriters.ConsoleMessageWriter;
import functions.parameterReaders.ConsoleParameterReader;
import structure.Game;

public class AllGame {
    public static void main(String[] args) {
        ConsoleMessageWriter writer = new ConsoleMessageWriter();
        ConsoleParameterReader reader = new ConsoleParameterReader();
        Game game = new Game(reader, writer);
        game.play();
    }
}

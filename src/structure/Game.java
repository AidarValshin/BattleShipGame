package structure;

import functions.DownloadField;
import functions.ShowPlayerFieldCommand;
import functions.StartPlayerCommand;
import functions.performingField.ComputerShot;
import functions.performingField.Shot;

public class Game {
    private Field userField;
    private Field computerField;
    private IParameterReader reader;
    private IMessageWriter writer;
    private ShowPlayerFieldCommand showPlayerFieldCommand = new ShowPlayerFieldCommand();

    public Game(IParameterReader reader, IMessageWriter writer) {
        this.reader = reader;
        this.writer = writer;
        userField = new Field();
        computerField = DownloadField.execute(writer, "field1");
    }

    public Game(IParameterReader reader, IMessageWriter writer, String name) {
        this.reader = reader;
        this.writer = writer;
        userField = new Field();
        computerField = DownloadField.execute(this.writer, name);
    }

    public void play() {
        StartPlayerCommand startPlayerCommand = new StartPlayerCommand();
        startPlayerCommand.execute(this.userField, this.reader, this.writer);
        int result = 0;
        while (result != Field.getResultAllSink()) {
            result = userGame();
            if (result == Field.getResultAllSink()) {
                writer.writeln("Game over,you win!");
                return;
            }
            result = computerGame();
        }
        writer.writeln("Game over,the computer wins!");
    }

    private int userGame() {
        int result;
        do {
            writer.writeln("Now  you are shooting");
            writer.writeln("Field of computer");
            showPlayerFieldCommand.execute(computerField, reader, writer);
            writer.writeln("Choose x for shoot");
            int x = reader.readInt();
            writer.writeln("Choose y for shoot");
            int y = reader.readInt();
            result = Shot.shoot(computerField, x, y);
            if (result == Field.getResultWrongArguments()) {
                writer.writeln("Wrong arguments!Repeat, please!");
            } else if (result == Field.getResultShotBefore()) {
                writer.writeln("These coordinate is shot before!Repeat, please!");
            } else if (result == Field.getResultSink()) {
                writer.writeln("You sink 1 ship!");
            } else if (result == Field.getResultHit()) {
                writer.writeln("You shot 1 ship!");
            }
        } while (result == Field.getResultShotBefore()
                || result == Field.getResultWrongArguments()
                || result == Field.getResultSink()
                || result == Field.getResultHit());
        showPlayerFieldCommand.execute(computerField, reader, writer);
        return result;
    }

    private int computerGame() {
        int result;
        do {
            writer.writeln("Now  the computer is shooting");
            result = ComputerShot.shoot(userField, reader, writer);
            if (result == Field.getResultSink()) {
                writer.writeln("Computer sinks 1 ship!");
            } else if (result == Field.getResultHit()) {
                writer.writeln("Computer shots 1 ship!");
            }
            writer.writeln("Your field");
            showPlayerFieldCommand.execute(userField, reader, writer);
        } while (result == Field.getResultShotBefore()
                || result == Field.getResultWrongArguments()
                || result == Field.getResultSink()
                || result == Field.getResultHit());
        return result;
    }
}

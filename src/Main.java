import functions.ShowPlayerFieldCommand;
import functions.ShowRealFieldCommand;
import functions.messageWriters.ConsoleMessageWriter;
import functions.parameterReaders.ConsoleParameterReader;

public class Main {
    public static void main(String[] args) {
        ShowRealFieldCommand showRealFieldCommand = new ShowRealFieldCommand();
        ShowPlayerFieldCommand showPlayerFieldCommand = new ShowPlayerFieldCommand();
        ConsoleMessageWriter writer = new ConsoleMessageWriter();
        ConsoleParameterReader reader = new ConsoleParameterReader();

  /*   StartPlayerCommand startPlayerCommand = new StartPlayerCommand();
        Field field=new Field();
        startPlayerCommand.execute(field,reader,writer);
        SaveFieldCommand saveFieldCommand=new SaveFieldCommand();
        saveFieldCommand.execute(field,reader,writer);

   */

  /*
      //  Field field = DownloadField.execute(writer, "field1");
        showRealFieldCommand.execute(field, reader, writer);
     int i=0;
        while (i!=Field.getResultAllSink()){
            writer.writeln("Choose x for shoot");
            int x = reader.readInt();
            writer.writeln("Choose y for shoot");
            int y = reader.readInt();
            i=Shot.shoot(field,x,y);
            writer.writeln(i);
            showRealFieldCommand.execute(field,reader,writer);
        }
               */
        int result;
  /*      for (int i = 0; i < 5; i++) {
            writer.writeln("Choose x for shoot");
            int x = reader.readInt();
            writer.writeln("Choose y for shoot");
            int y = reader.readInt();
            result = Shot.shoot(field, x, y);
            writer.writeln(result);
            showRealFieldCommand.execute(field, reader, writer);
        }

   */
/*
        for (int i = 0; i < 40; i++) {
            ComputerShot.shoot(field, reader, writer);
            showRealFieldCommand.execute(field, reader, writer);
        }
        showPlayerFieldCommand.execute(field, reader, writer);
     */
    }
}

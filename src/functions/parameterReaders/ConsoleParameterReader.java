package functions.parameterReaders;

import structure.IParameterReader;

import java.util.Scanner;

public class ConsoleParameterReader implements IParameterReader {
    private static Scanner in = new Scanner(System.in);

    public int readInt() {
        while (!in.hasNextInt()) {
            System.out.println("It is not int! Repeat,please!");
            in.next();
        }
        return in.nextInt();
    }

    @Override
    public String readString() {
        return in.nextLine();
    }
}

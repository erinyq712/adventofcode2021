package se.nyquist;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {

    private static void showHelp() {
        System.out.println("Usage: java " + Main.class.getName() + " <input file>");
    }

    private static String inputFile = "01input.txt";

    public static void main(String[] args) {
	    if (args.length > 0) {
            inputFile = args[0];
        }
        File file = new File(inputFile);
        if (! file.exists()) {
            showHelp();
            exit(1);
        }
        DataHandler handler = new DataHandler();
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()) {
                int depth = Integer.parseInt(scanner.next());
                handler.add(depth);
            }
            handler.printStats();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            exit(1);
        }
    }
}

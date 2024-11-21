package JavaTuring;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UniversalTuringMachine {
    private final Map<String, Map<Symbol, Configuration>> head;
    private String currentState;
    private final Tape tape;
    private final int sizeLimit;
    public static final int MAX_TAPE = 999;

    public UniversalTuringMachine(Tape tape, int sizeLimit) {
        this.head = new HashMap<>();
        this.tape = tape;
        this.sizeLimit = sizeLimit;
    }

    public static UniversalTuringMachine fromStandardDescription(String description, Tape tape, int sizeLimit) {
        boolean foundInit = false;

        UniversalTuringMachine utm = new UniversalTuringMachine(tape, sizeLimit);

        for (String line : description.split(";")) {
            line = line.trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("-");
            if (parts.length != 5) {
                throw new IllegalArgumentException("Invalid configuration format: " + line);
            }

            try {
                String state = parts[0].trim();
                Symbol readSymbol = Symbol.valueOf(parts[1].trim());  // Directly parse JavaTuring.Symbol enum
                Symbol writeSymbol = Symbol.valueOf(parts[2].trim());
                Direction direction = parts[3].trim().equals("R") ?
                        Direction.RIGHT : Direction.LEFT;
                String nextState = parts[4].trim();

                if (!foundInit){utm.currentState = state;foundInit = true;}
                Configuration configuration = new Configuration(readSymbol, writeSymbol, direction, nextState);
                utm.addConfiguration(state, configuration);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid symbol in configuration: " + line);
            }
        }

        return utm;
    }

    public static UniversalTuringMachine fromStandardDescription(File file, Tape tape, int sizeLimit){
        String fileContent = "";
        try {
            Scanner scanner = new Scanner(file);

            scanner.useDelimiter("\\Z");

            fileContent = scanner.next();

            fileContent = fileContent.split("#########")[1];

            fileContent = fileContent.replace('\t', ' ');
            fileContent = fileContent.replace('\n', ' ');



            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return UniversalTuringMachine.fromStandardDescription(fileContent, tape, sizeLimit);

    }

    public static UniversalTuringMachine fromStandardDescription(File file, Tape tape){
        return fromStandardDescription(file, tape, MAX_TAPE);
    }

    public static UniversalTuringMachine fromStandardDescription(String description, Tape tape) {
        return fromStandardDescription(description, tape, MAX_TAPE);
    }

    public void addConfiguration(String state, Configuration configuration) {
        head.putIfAbsent(state, new HashMap<>());
        head.get(state).put(configuration.getReadSymbol(), configuration);
    }

    public void run() {
        int steps = 0;

        while (!currentState.equals("HALT")) {
            if (tape.size() >= sizeLimit) {
                System.out.println("Halting: JavaTuring.Tape size limit " + sizeLimit + " reached");
                System.out.println("Steps taken: " + steps);
                return;
            }

            Symbol currentSymbol = tape.read();  // Now returns JavaTuring.Symbol

            // Map now uses JavaTuring.Symbol instead of char
            Configuration configuration = head.get(currentState).get(currentSymbol);
            if (configuration == null) {
                System.out.println("No configuration defined for state " + currentState +
                        " and symbol " + currentSymbol);
                System.out.println("Steps taken: " + steps);
                return;
            }

            tape.write(configuration.getWriteSymbol());

            switch (configuration.getMoveDirection()) {
                case LEFT:
                    tape.left();
                    break;
                case RIGHT:
                    tape.right();
                    break;
                case STAY:
                    break;
            }

            currentState = configuration.getNextState();
//            System.out.println("CURRENT STATE: " + configuration.getNextSignature());
            steps++;
        }

        System.out.println("Machine halted normally after " + steps + " steps");
    }

    public Tape getTape(){
        return tape;
    }

}


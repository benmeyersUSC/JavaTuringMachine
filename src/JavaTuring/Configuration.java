package JavaTuring;

/**
 * Configuration for a Turing Machine configuration
 */
class Configuration {
    private final Symbol readSymbol;  // Changed from char to JavaTuring.Symbol
    private final Symbol writeSymbol;
    private final Direction moveDirection;
    private final String nextState;


    public Configuration(Symbol readSymbol, Symbol writeSymbol, Direction moveDirection, String nextState) {
        this.readSymbol = readSymbol;
        this.writeSymbol = writeSymbol;
        this.moveDirection = moveDirection;
        this.nextState = nextState;
    }

    // Updated getter to return JavaTuring.Symbol
    public Symbol getReadSymbol() { return readSymbol; }
    public Symbol getWriteSymbol() { return writeSymbol; }
    public Direction getMoveDirection() { return moveDirection; }
    public String getNextState() { return nextState; }
    public String getNextSignature(){ return nextState + "-" + readSymbol;}
}
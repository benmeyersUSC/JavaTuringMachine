package JavaTuring;

import java.util.Arrays;

/**
 * JavaTuring.Tape of a Turing Machine
 *
 * @author Ben Meyers
 * email: bemeyers@usc.edu
 * Date Created: 10/23/24
 */

public class Tape {
    private char[] values;
    private int head = 0;
    private int size;
    private final String tapeFill;

    public Tape(int size, String tapeFill){
        this.tapeFill = tapeFill;
        this.size = size;
        values = new char[this.size];
        Arrays.fill(values, Symbol.valueOf(tapeFill).val);
    }

    public Tape(String tapeFill){this(54, tapeFill);}

    public Tape(){this(54, "S0");}

    public Symbol read() {  // Changed to return JavaTuring.Symbol
        char val = values[head];
        // Convert char to corresponding JavaTuring.Symbol
        for (Symbol s : Symbol.values()) {
            if (s.val == val) return s;
        }
        return Symbol.S0;  // Default to blank symbol if no match
    }

    public void write(Symbol s){
        values[head] = s.val;
    }

    public void right() {
        if (head + 1 == size) {
            // Extend the tape to the right
            size += 10;
            char[] old = values;
            values = new char[size];
            for (int i = 0; i < size; i++) {
                if (i < size - 10) {
                    values[i] = old[i];
                } else {
                    values[i] = Symbol.valueOf(tapeFill).val;
                }
            }
        }
        head++; // Move head right in all cases
    }

    public void left() {
        if (head == 0) {
            // Extend the tape to the left
            size += 10;
            char[] old = values;
            values = new char[size];
            for (int i = 0; i < size; i++) {
                if (i < 10) {
                    values[i] = Symbol.valueOf(tapeFill).val;  // Fill new left portion with blanks
                } else {
                    values[i] = old[i-10];  // Shift old contents right
                }
            }
            head = 9;  // Move head to the position after the new section
        } else {
            head--;  // Move head left if we're not at the beginning
        }
    }

    public String toString(){
        int tabs = -1;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; i++){
            if (i%(81) == 0){str.append("\n");
                if(i==0){str.append("|");};
                for(int z = 0; z <= tabs;z++){str.append(" ");}/*tabs++;*/
                if(i != 0){str.append("|");}}
            if (i == head){/*str.append("<<<").append(values[i]).append(">>>|");*/str.append("\u001B[30;42m<<<").append(values[i]).append(">>>\u001B[0m|");}
            else{str.append(values[i]).append("|");}
        }
        return str.toString();
    }

    public int size(){
        return size;
    }

}

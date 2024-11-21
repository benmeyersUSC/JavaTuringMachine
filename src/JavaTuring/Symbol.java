package JavaTuring;

/**
 * Symbols for a Turing Machine
// * @author Ben Meyers
// * email: bemeyers@usc.edu
// * Date Created: 10/23/24
// */

 public enum Symbol {
    S0(' '),
    S1('0'),
    S2('1'),
    S3('R'),
    S4('L'),
    S5('N'),
    S6('D'),
    S7('A'),
    S8('S'),
    S9(';'),
    S10(':'),
    SENTINEL('@'),
    V1('x'),
    V2('y'),
    V3('z'),
    ASTR('*');

    public final char val;

    Symbol(char val) {
        this.val = val;
    }
}
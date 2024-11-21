package JavaTuring;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Ben Meyers
 * email: bemeyers@usc.edu
 * Date Created: 10/23/24
 */
public class Main {
    public static void main(String[] args) {

//        UniversalTuringMachine isOdd = UniversalTuringMachine.fromStandardDescription(
//                new File("/Users/benmeyers/Desktop/TuringMachine/src/isOdd.javaturing"),
//                new Tape("S0"));
//        isOdd.run();
//        System.out.println(isOdd.getTape());
//
//        UniversalTuringMachine art = UniversalTuringMachine.fromStandardDescription(
//                new File("/Users/benmeyers/Desktop/TuringMachine/src/art.javaturing"),
//                new Tape("ASTR"));
//        art.run();
//        System.out.println(art.getTape());

        UniversalTuringMachine counting = UniversalTuringMachine.fromStandardDescription(
                new File("/Users/benmeyers/Desktop/TuringMachine/src/doubling.javaturing"),
                new Tape("S0"), 2727);
        counting.run();
        System.out.println(counting.getTape());

    }
}

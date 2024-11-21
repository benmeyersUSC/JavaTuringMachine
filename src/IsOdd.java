//
// * Turing machine to calculate alternating 0 and 1 (1/3 in binary, isOdd(x), others)
// *
// * @author Ben Meyers
// * email: bemeyers@usc.edu
// * Date Created: 10/23/24
// */
public class IsOdd {
    private final int MAX_TAPE = 90;
    private JavaTuring.Tape tape ;

    public IsOdd(){
        tape = new JavaTuring.Tape();
    }

    public void run(){
        q1();
        System.out.println(tape);
    }

    //Q1-S0-S0-R-Q2
    public void q1(){
        if (tape.read() == JavaTuring.Symbol.S0){
            tape.write(JavaTuring.Symbol.S0);
            tape.right();
        }
        if (tape.size() < MAX_TAPE){q2();}
    }

    //Q2-S0-S1-R-Q3
    public void q2(){
        if (tape.read() == JavaTuring.Symbol.S0){
            tape.write(JavaTuring.Symbol.S1);
            tape.right();
        }
        if (tape.size() < MAX_TAPE){q3();}
    }

    //Q3-S0-S0-R-Q4
    public void q3(){
        if (tape.read() == JavaTuring.Symbol.S0){
            tape.write(JavaTuring.Symbol.S0);
            tape.right();
        }
        if (tape.size() < MAX_TAPE){q4();}
    }

    //Q4-S0-S2-R-Q1
    public void q4(){
        if (tape.read() == JavaTuring.Symbol.S0){
            tape.write(JavaTuring.Symbol.S2);
            tape.right();
        }
        if (tape.size() < MAX_TAPE){q1();}
    }

}

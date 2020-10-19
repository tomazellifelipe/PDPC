import java.util.Random;

public class Sequence {

    private Random r = new Random();
    int[] sequence;

    public Sequence(int size) {
        createSequence(size);
    }

    private void createSequence(int size) {
        sequence = new int[size];
        appendAllElements();
    }

    private void appendAllElements() {
        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = randomInt();
        }
    }

    private int randomInt() {
        return r.nextInt(10000000);
    }

    public int[] getSequence() {
        return sequence;
    }
}

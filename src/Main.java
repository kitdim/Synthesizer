import java.util.Scanner;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

public class Main {
    private static final byte C = 60; // do
    private static final byte D = 62; // re
    private static final byte E = 64; // mi
    private static final byte F = 65; // fa
    private static final byte G = 67; // sol
    private static final byte A = 69; // la
    private static final byte B = 70; // si

    public static void main(String[] args) throws MidiUnavailableException, InterruptedException, InvalidMidiDataException {
        Synthesizer synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();
        System.out.println("Please enter some notes: ");
        Scanner scanner = new Scanner(System.in);
        String note = scanner.nextLine().toUpperCase().trim();
        System.out.println("You enter" + note);
        Receiver receiver = synthesizer.getReceiver();

        while (!note.equals("EXIT"))
        {
            byte noteId = convertToId(note);
            playNote(receiver, noteId);
            note = scanner.nextLine().toUpperCase().trim();
        }

        synthesizer.close();
        scanner.close();
    }

    private static void playNote(Receiver receiver, byte noteId) throws InvalidMidiDataException, InterruptedException {
        ShortMessage message = new ShortMessage();
        message.setMessage(ShortMessage.NOTE_ON, noteId, 100);

        receiver.send(message, -1);
        Thread.sleep(1000);

        message.setMessage(ShortMessage.NOTE_OFF, noteId, 100);
        receiver.send(message, -1);
    }

    private static byte convertToId(String note) {
        switch (note) {
            case "A":
                return A;
            case "B":
                return B;
            case "C":
                return C;
            case "D":
                return D;
            case "E":
                return E;
            case "F":
                return F;
            case "G":
                return G;
            default:
                System.out.println("Entered incorrect note: " + note);
                return A;
        }
    }
}
import java.util.Random;
import java.util.Scanner;

public class App {
    // Method to check the answer. Returns an integer array [c, cp].
    static int[] checkAnswer(String input, String answer) {
        int c = 0;
        int cp = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == answer.charAt(i)) {
                cp += 1;
            } else if (answer.contains("" + input.charAt(i))) {
                c += 1;
            }
        }
        return new int[]{c, cp};
    }
    public static void main(String[] args) {

        int attemptsLeft = 10;
        Random rand = new Random();

        String answer = "";
        for (int i = 0; i < 4; i++) {
            answer = answer + String.valueOf(rand.nextInt(8));
        }
        
        System.out.println("Guess a 4-digit number where all digits are between 0 to 7. You have " + attemptsLeft + " attempts");

        String input = "";
        Scanner scan = new Scanner(System.in);
        while (!input.equals("exit")) {
            input = scan.nextLine();
            if (input.equals("answer")) {
                System.out.println(answer);
            } else if (input.equals(answer)) {
                System.out.println("You win!");
            } else {
                int[] answerPos = App.checkAnswer(input, answer);
                int c = answerPos[0];
                int cp = answerPos[1];
                attemptsLeft -= 1;
                System.out.printf("C: %d, CP: %d. Attempts Left: %d\n", c, cp, attemptsLeft);
            }
            
        }
    }
}
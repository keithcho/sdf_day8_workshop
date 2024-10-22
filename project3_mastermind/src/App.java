import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {
    // Method to check the answer. Returns an integer array [c, cp].
    static int[] checkAnswer(String input, String answer) {

        List<String> storedChars = new ArrayList<>();
        int c = 0;
        int cp = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == answer.charAt(i)) {
                cp++;
                storedChars.add(String.valueOf(answer.charAt(i)));
            }
        }
        for (int i = 0; i < input.length(); i++) {
            if (answer.contains("" + input.charAt(i))) {
                if (storedChars.contains(String.valueOf(input.charAt(i)))) {
                    storedChars.remove(String.valueOf(input.charAt(i)));
                } else {
                    c++;
                }
            }
        }

        return new int[]{c, cp};
    }
    public static void main(String[] args) {

        int attemptsLeft = 12;
        Random rand = new Random();

        String answer = "";
        for (int i = 0; i < 4; i++) {
            answer = answer + String.valueOf(rand.nextInt(1,7));
        }
        
        System.out.println("Guess a 4-digit number where all digits are between 1 to 6. You have " + attemptsLeft + " attempts");

        String input = "";
        Scanner scan = new Scanner(System.in);
        while (!input.equals("exit") && attemptsLeft > 0) {
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
        System.out.println("Game over! Answer: " + answer);
    }
}
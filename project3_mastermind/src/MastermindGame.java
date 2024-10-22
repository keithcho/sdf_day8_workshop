import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MastermindGame {

    // Set the length of the code (e.g., 4)
    private static final int CODE_LENGTH = 4;
    
    // Set the range of digits to use in the code (e.g., 0-9)
    private static final int DIGIT_RANGE = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Generate random secret code
        int[] secretCode = generateSecretCode();
        
        // Number of attempts allowed
        int attempts = 10;
        
        System.out.println("Welcome to Mastermind!");
        System.out.println("You need to guess a " + CODE_LENGTH + "-digit code with digits between 0 and " + (DIGIT_RANGE - 1));
        System.out.println("You have " + attempts + " attempts to guess the correct code.");

        for (int i : secretCode) {
            System.out.printf("%d", i);
        }
        System.out.println();
        
        boolean guessedCorrectly = false;
        
        while (attempts > 0 && !guessedCorrectly) {
            System.out.println("\nAttempts remaining: " + attempts);
            System.out.print("Enter your guess: ");
            
            // Get player's guess
            int[] guess = getPlayerGuess(scanner);
            
            // Provide feedback on the guess
            int[] feedback = evaluateGuess(guess, secretCode);
            int correctPosition = feedback[0];
            int correctDigit = feedback[1];
            
            if (correctPosition == CODE_LENGTH) {
                guessedCorrectly = true;
                System.out.println("Congratulations! You've guessed the code correctly!");
            } else {
                System.out.println("Correct digits in correct position: " + correctPosition);
                System.out.println("Correct digits but in wrong position: " + correctDigit);
            }
            
            attempts--;
        }
        
        if (!guessedCorrectly) {
            System.out.println("\nYou've run out of attempts! The correct code was: " + Arrays.toString(secretCode));
        }
        
        scanner.close();
    }

    // Generate a random secret code
    private static int[] generateSecretCode() {
        Random random = new Random();
        int[] code = new int[CODE_LENGTH];
        for (int i = 0; i < CODE_LENGTH; i++) {
            code[i] = random.nextInt(DIGIT_RANGE);
        }
        return code;
    }

    // Get player's guess and convert it into an int array
    private static int[] getPlayerGuess(Scanner scanner) {
        int[] guess = new int[CODE_LENGTH];
        String input = scanner.next();
        for (int i = 0; i < CODE_LENGTH; i++) {
            guess[i] = Character.getNumericValue(input.charAt(i));
        }
        return guess;
    }

    // Evaluate the guess and return feedback: [correctPosition, correctDigit]
    private static int[] evaluateGuess(int[] guess, int[] secretCode) {
        boolean[] codeUsed = new boolean[CODE_LENGTH];
        boolean[] guessUsed = new boolean[CODE_LENGTH];
        int correctPosition = 0;
        int correctDigit = 0;

        // First pass: Count correct digits in correct position
        for (int i = 0; i < CODE_LENGTH; i++) {
            if (guess[i] == secretCode[i]) {
                correctPosition++;
                codeUsed[i] = true;
                guessUsed[i] = true;
            }
        }

        // Second pass: Count correct digits in wrong position
        for (int i = 0; i < CODE_LENGTH; i++) {
            if (!guessUsed[i]) {
                for (int j = 0; j < CODE_LENGTH; j++) {
                    if (!codeUsed[j] && guess[i] == secretCode[j]) {
                        correctDigit++;
                        codeUsed[j] = true;
                        break;
                    }
                }
            }
        }

        return new int[] { correctPosition, correctDigit };
    }
}

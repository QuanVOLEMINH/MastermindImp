
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MastermindGame {
    private final static List<String> colours = new ArrayList<>(Arrays.asList("R", "B", "J", "O", "V", "N"));
    private String hiddenPegs;
    private Player player;
    private int numberOfHoles;
    private int numberOfTurns;
    private List<String> infos;

    public MastermindGame(int numberOfHoles, int numberOfTurns) {
        this.numberOfHoles = numberOfHoles;
        this.numberOfTurns = numberOfTurns;
        player = new Player();
        infos = new ArrayList<String>();
    }

    public void play() {
        boolean isPlaying = true;
        printConstructions();
        getPlayerName();
        hiddenPegs = generateHiddenPegs();
        System.out.println(hiddenPegs);
        int count = 0;
        while (isPlaying) {
            String guess = player.makeGuess();
            if (isGuessValid(guess)) {
                count++;
                List<Integer> results = checkGuess(hiddenPegs, guess);
                String rowInfo = guess + " | " + results.get(0) + " | " + results.get(1) + " | " + count + "/" + numberOfTurns;
                infos.add(rowInfo);
                printResult(infos);
                if (results.get(0) == 4) {
                    System.out.println("Congratulations " + player.getName() + ". You won after " + count + " turns.");
                    isPlaying = false;
                }
                else if (count == numberOfTurns){
                    System.out.println("You lost.");
                    isPlaying = false;
                }
            } else {
                System.err.println("Error, please make another guess!");
            }
        }

    }

    private void printResult(List<String> infos) {
        System.out.println("----------");
        infos.forEach(System.out::println);
        System.out.println("----------");
    }

    private void getPlayerName() {
        System.out.println("Enter your name then press Enter:");
        Scanner sc = new Scanner(System.in);
        String playerName = sc.nextLine();
        player.setName(playerName);
    }

    private String generateHiddenPegs() {
        String pattern = "";
        for (int i = 0; i < numberOfHoles; i++) {
            pattern = pattern + colours.get((int) (Math.random() * colours.size()));
        }
        return pattern;
    }

    private void printConstructions() {
        System.out.println("-- Rule -- ");
        System.out.println("-- Make a guess that contains " + numberOfHoles+ " characters from " + colours.toString() +" without space --");
        System.out.println("-- You have " + numberOfTurns + " turns --");
    }

    private boolean isGuessValid(String guess) {
        if (guess.length() != numberOfHoles) {
            return false;
        } else {
            boolean valid = true;
            for (int i = 0; i < guess.length(); i++) {
                if (colours.indexOf(String.valueOf(guess.charAt(i))) == -1) {
                    valid = false;
                }
            }
            return valid;
        }
    }

    private List<Integer> checkGuess(String pattern, String guess) {
        List<Integer> results;
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < numberOfHoles; i++) {
            if (guess.charAt(i) == pattern.charAt(i)) {
                bulls++;
            } else {
                for (int j = 0; j < numberOfHoles; j++) {
                    if (pattern.charAt(j) == guess.charAt(i)) {
                        cows++;
                        break;
                    }
                }
            }
        }
        results = Arrays.asList(bulls, cows);
        return results;
    }
}

import java.util.Scanner;

public class Player {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String makeGuess() {
        System.out.println("===========");
        System.out.println("Your guess: ");
        Scanner sc = new Scanner(System.in);
        String guess = sc.nextLine();
        return guess;
    }
}

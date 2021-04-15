package lt.niko;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        Integer[] redArray = new Integer[]{1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
        Integer[] blackArray = new Integer[]{2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};

        int wins = 0;
        int losses = 0;

        boolean continueGame = true;

        System.out.println("Enter your balance");
        int balance = scanner.nextInt();



        while (continueGame) {


            String colorBet;
            int chipsOnColor = 0;
            int numOfBets;
            int betSize;

            do{

                System.out.println("Your balance is: " + balance);

                System.out.println("Enter bet size");
                betSize = scanner.nextInt();

                System.out.println("Would you like to Color Bet? Type in: red / black / no");
                colorBet = scanner.next();

                if (!colorBet.equals("no")) {
                    System.out.println("How many chips on " + colorBet);
                    chipsOnColor = scanner.nextInt();
                }

                System.out.println("Enter the number of bets");
                numOfBets = scanner.nextInt();
            } while ((chipsOnColor + numOfBets) * betSize > balance);


            int[] betArray = new int[numOfBets];

            for (int b = 0; b < betArray.length; b++) {
                int betInput;
                do {
                    System.out.println("Place bet " + (b + 1) + " on a number:");
                    betInput = scanner.nextInt();
                }
                while (betInput > 37);

                betArray[b] = betInput;
            }
            System.out.println("you bet on numbers: " + Arrays.toString(betArray));

            Integer spinResult = random.nextInt(37);

            balance = balance - chipsOnColor * betSize;

            for (int t = 0; t < betArray.length; t++) {
                balance = balance - betSize;
                if (betArray[t] == spinResult) {
                    balance = balance + betSize * 35;
                    wins++;
                } else {
                    losses++;
                }
            }

            if (colorBet.equals("red") && Arrays.asList(redArray).contains(spinResult)) {
                System.out.println("spin result is " + spinResult + " red");
                balance = balance + (chipsOnColor * betSize * 2);
            }

            if (colorBet.equals("black") && Arrays.asList(blackArray).contains(spinResult)) {
                System.out.println("spin result is " + spinResult + " black");
                balance = balance + (chipsOnColor * betSize * 2);
            }


            System.out.println("remaining balance: " + balance);

            System.out.println("Continue? yes / no");
            String continueAnswer = scanner.next();
            if (continueAnswer.equals("yes")) {
                if (balance < 100) {
                    System.out.println("Top up your balance");
                    balance = balance + scanner.nextInt();
                }
                continueGame = true;
            } else {
                continueGame = false;
            }

        }
    }
}

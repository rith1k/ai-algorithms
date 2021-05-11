import java.util.Arrays;
import java.util.Scanner;

public class Utility {
    public static int randomDecider, percentage, value, input, probability, winnings;
    public static double result;
    public static boolean flag = true;

    public static void main(String[] args) {
        receiveInputs();
    }

    private static void placeBet(int percentage, int value) {
        randomDecider = (int) (Math.random() * 10);
        int[] winningDecider = new int[10];
        System.out.println(winningDecider.length + " prob+" + percentage);
        double winningProbability = (double) percentage * winningDecider.length / 100;
        System.out.println(winningProbability);
        for (int i = 0; i < winningProbability; i++)
            winningDecider[(int) (Math.random() * winningDecider.length)] = 1;

        System.out.println("r: "+randomDecider);

        System.out.println(Arrays.toString(winningDecider));
        if (input == 1) {
            result = ((double) percentage / 100) * value + ((double) 1 - (double) percentage / 100) * 0;
            System.out.println("Expected Utility: " + result);
            int res = Arrays.binarySearch(winningDecider, randomDecider);

            boolean test = res > 0 ? true : false;


            if (test) {
                winnings += value;
                System.out.println("Congratulations!! You won! Your winnings are: " + winnings);
            } else {
                System.out.println("Sorry! You didn't win the bet!");

            }

        }
        if (input == 2) {
            if (value == 1) {
                result = ((double) percentage / 100) * 50 + ((double) 1 - (double) percentage / 100) * 0;
                int res = Arrays.binarySearch(winningDecider, randomDecider);
                boolean test = res > 0 ? true : false;
                if (test) {
                    winnings += 50;
                    System.out.println("Congratulations!! You won! Your winnings are: " + winnings);
                } else {
                    System.out.println("Sorry! You didn't win the bet!");

                }

            } else {

                result = ((double) percentage / 100) * value + ((double) 1 - (double) percentage / 100) * 0;
                int res = Arrays.binarySearch(winningDecider, randomDecider);
                boolean test = res > 0 ? true : false;
                if (test) {
                    winnings += 5;
                    System.out.println("Congratulations!! You won! Your winnings are: " + winnings);
                } else {
                    System.out.println("Sorry! You didn't win the bet!");

                }
            }
            System.out.println("Expected Utility: " + result);


        }
    }

    private static void receiveInputs() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter which game you want to play? Game 1 or Game 2 [Enter Integer]");

        input = Integer.parseInt(scanner.nextLine());  // Read user input
        System.out.println("You have chosen game : " + input);  // Output user input
        while (flag) {
            if (input == 1) {
                System.out.println("Please enter the winning percentage: ");
                percentage = Integer.parseInt(scanner.nextLine());  // Read user input
                System.out.println("Please enter the total value for the bet: ");
                value = Integer.parseInt(scanner.nextLine());  // Read user input
                System.out.println("Total value is: " + value + ", and odds are: " + percentage);  // Output user input
                placeBet(percentage, value);
                System.out.println("Do you wish to continue? or quit? [Enter true or false]");
                flag = scanner.nextBoolean();
                if (!flag) {
                    break;
                } else if (flag) {
                    receiveInputs();
                }
            }

            if (input == 2) {
                System.out.println("You have chosen Game 2!\nYou are presented with 2 options:\n[1]Bet 1: Have 10% chance of winning 50$ (or)\n[2] Bet 2: Have 90% of winning 5$.\nMake your choice.");
                value = Integer.parseInt(scanner.nextLine());
                System.out.println("Your choice is: " + value);
                if (value == 1)
                    percentage = 10;
                else
                    percentage = 90;

                placeBet(percentage, value);
                System.out.println("Do you wish to continue? or quit?  [Enter true or false]");
                flag = scanner.nextBoolean();
                if (!flag) {
                    break;
                } else if (flag) {
                    receiveInputs();
                }
            }

        }

    }

}

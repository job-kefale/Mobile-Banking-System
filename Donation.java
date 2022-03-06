import java.util.Scanner;

public class Donation extends Account {
    static int aamount;

    public static void chooseArea() {
        System.out.println("********************DONATION AREAS*************************");

        System.out.println("1.Covid 19");
        System.out.println("2.Mekedonia Humanitarian Associaton");
        System.out.println("3.Wolkite Kenema Fc");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1: {
                int amount = donate();// returned value from donate method

                System.out.println("Dear customer you have donated ETB " + amount
                        + " successfully \nto covid 19 prevention team.your current balance \nis ETB "
                        + balance + " Thank you for Banking with us.\n");
                ifBack();
                break;

            }
            case 2: {
                int amount = donate();// returned value from donate method

                System.out.println("Dear customer you have donated ETB " + amount
                        + " successfully \nto Mekedoniya Humaniterian Association .your current balance \nis ETB "
                        + balance + " Thank you for Banking with us.\n");

                ifBack();
                break;
            }
            case 3: {
                int amount = donate();// returned value from donate method

                System.out.println("Dear customer you have donated ETB " + amount
                        + " successfully \nto Wolkite Kenema FC.your current balance \nis ETB "
                        + balance + " Thank you for Banking with us.\n");
                ifBack();
                break;

            }
            default: {
                System.out.println("you have entered wrong number please try again");
                chooseArea();
                break;

            }
        }

        sc.close();
    }

    // reieving amount to be donated and passing it each cases in the switch above
    public static int donate() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("eneter amount - ");
            int amount;
            amount = sc.nextInt();

            if (amount > 1) {
                UtilityPaymnet.userUpdateBalance(amount); // calling update method from utility class
                sc.close();
                ClearScreen.cls();// calling clear screen before going to message
                aamount = amount;

            } else {
                System.out.println("Amount must be greater than 1 ETB.Try again");
                donate();
            }

            sc.close();

        } catch (Exception e) {

            System.out.println("The amount you entered must be an number, please enter again");
            donate();
        }

        return aamount;
    }
}

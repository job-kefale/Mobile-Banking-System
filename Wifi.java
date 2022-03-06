import java.util.Scanner;

public class Wifi extends UtilityPaymnet {
    int modemno;
    int amount;

    @Override
    public void payment() {
        System.out.println("Enter WIFI modem identification number please - ");

        Scanner sc = new Scanner(System.in);
        modemno = sc.nextInt();
        System.out.println("Choose speed");
        System.out.println("1.One MB");
        System.out.println("2.Two MB");
        System.out.println("3.Three MB");
        int choice = sc.nextInt();

        switch (choice) {
            case 1: {
                amount = 499;
                userUpdateBalance(amount);
                message();
                break;
            }
            case 2: {
                amount = 699;
                userUpdateBalance(amount);
                message();
                break;
            }

            case 3: {
                amount = 999;
                userUpdateBalance(amount);
                message();
                break;
            }

        }

        sc.close();
    }

    @Override
    public void message() {
        System.out.println("Dear customer you have successfuly paid DStv utility payment ETB "
                + amount + " \nwith card number " + modemno + ".your current balance\nis ETB "
                + balance + ".Thank You for Banking with us.");

    }
}

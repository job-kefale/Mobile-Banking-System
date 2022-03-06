import java.util.Scanner;
import java.util.regex.Pattern;

public class AirlineTiket extends UtilityPaymnet {
    String pnr;// passenger name record
    int amount;

    @Override
    public void payment() {
        System.out.print("Enter your PNR code please - ");
        Scanner sc = new Scanner(System.in);

        try {
            pnr = sc.next();
            ClearScreen.cls();// calling clear screen before going to enter amount

            if (Pattern.matches("[a-zA-Z]+", pnr) == true && pnr.length() == 6) {
                System.out.print("Enter amount - ");
                amount = sc.nextInt();
                ClearScreen.cls();// calling clear screen before going to message

                if (amount > 1) {
                    userUpdateBalance(amount);
                    message();
                } else {
                    System.out.println("wrong amount entered,please try again");
                    payment();
                }
            } else {
                System.out.println("The passenger name record code must be a string with 6 characters");
                payment();
            }
        } catch (Exception e) {
            System.out.println("The value you entered is mismatched,please try again");
            payment();
        }
        sc.close();
    }

    @Override
    void message() {

        System.out.println("Dear customer you have successfuly paid \nAirline ticket reservation payment ETB "
                + amount + " \nwith PNR code " + "\"" + pnr + "\"." + "your current balance\nis ETB "
                + balance);
        ifBack();

    }

}

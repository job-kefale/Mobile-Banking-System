import java.util.Scanner;

public class ElectricBill extends UtilityPaymnet {

    int identificationno;
    int amount;

    @Override
    public void payment() {
        System.out.print("Enter your identification number please - ");

        Scanner sc = new Scanner(System.in);
        try {
            identificationno = sc.nextInt();
            ClearScreen.cls();// calling clear screen before going to entering amount

            if (identificationno > 100000 && identificationno < 999999) {
                System.out.print("Enter amount - ");
                amount = sc.nextInt();
                ClearScreen.cls();// calling clear screen before going to message

                if (amount > 1) {
                    userUpdateBalance(amount);
                    message();
                } else {
                    System.out.println("wrong Amount entered,please try again");
                    payment();
                }
            } else {
                System.out.println("Electric bill number must contain 6 digits and that sarted from 1,Try again");
                payment();
            }
        } catch (Exception e) {
            System.out.println("The value you entered must be number");
            payment();
        }

        sc.close();
    }

    @Override
    void message() {

        System.out.println("Dear customer you have successfuly paid Electric utility payment ETB "
                + amount + " \nwith identification number " + identificationno + ".\nyour current balance is ETB "
                + balance);
        ifBack();
    }

}

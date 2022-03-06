import java.util.Scanner;

public class DSTV extends UtilityPaymnet {
    int CardNo;
    int amount;

    @Override
    public void payment() {
        try {
            System.out.print("Enter DSTV card number please - ");

            Scanner sc = new Scanner(System.in);
            CardNo = sc.nextInt();
            ClearScreen.cls();// calling clear screen before going to choose package
            if (CardNo > 100000 && CardNo < 999999) {
                System.out.println("********************DSTV PACKAGES*************************");

                System.out.println("1.Gojo package");
                System.out.println("2.Meda Package");
                System.out.println("3.Beteseb Package");
                System.out.println("4.premium package");
                int choice = sc.nextInt();
                ClearScreen.cls();// calling clear screen before going to choose service in the switch

                switch (choice) {
                    case 1: {
                        amount = 299;
                        userUpdateBalance(amount);
                        message();
                        break;

                    }
                    case 2: {
                        amount = 499;
                        userUpdateBalance(amount);
                        message();
                        break;

                    }

                    case 3: {
                        amount = 799;
                        userUpdateBalance(amount);
                        message();
                        break;

                    }
                    case 4: {
                        amount = 1000;
                        userUpdateBalance(amount);
                        message();
                        break;

                    }
                }

                sc.close();

            } else {
                System.out.println("Card number must contain 6 digits and that sarted from 1,Try again");
                payment();
            }

            sc.close();

        } catch (Exception e) {

            System.out.println("The card neumber you entered must be a number, please enter again");
            payment();
        }

    }

    @Override
    public void message() {
        System.out.println("Dear customer you have successfuly paid DStv utility payment ETB "
                + amount + " \nwith card number " + CardNo + ".\nyour current balance is ETB "
                + balance);
        ifBack();
    }

}

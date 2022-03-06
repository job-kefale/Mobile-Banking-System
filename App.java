import java.util.Scanner;
import java.util.regex.Pattern;

class App {
    static String globalPhone;

    public static void main(String args[]) throws Exception {

        callLogin();

    }

    public static void callLogin() {
        Scanner sc = new Scanner(System.in);

        Login login = new Login();
        System.out.print("phone number -- ");
        String phonenumber = sc.nextLine();

        if (Pattern.matches("[0-9]+", phonenumber) == true && phonenumber.length() == 10) {
            globalPhone = phonenumber;
        }

        else {
            System.out.println("Incorrect format of phone number is entered please try again.");
            callLogin();
        }

        System.out.print("pin -- ");
        String pin = sc.nextLine();
        if (Pattern.matches("[0-9]+", pin) == true && pin.length() == 4) {

        }

        else {
            System.out.println("Incorrect format of pin number is entered please try again.");
            callLogin();
        }
        ClearScreen.cls();// calling clear screen before going to login class

        login.checkAuthentication(phonenumber, pin);
        menu();

        sc.close();
    }

    public static void menu() {
        MyAccount myaccount = new MyAccount();
        MoneyTransfer moneytransfer = new MoneyTransfer();// to be written in polimorphic way
        Airtime airtime = new Airtime();
        // UtilityPaymnet utility = new UtilityPaymnet(); can not instantiate object
        // because it is abstract class

        System.out.println("********************************************************************************");
        System.out.println("********************************************************************************");
        System.out.println("************************WELCOME TO MUDAY MOBILE BANKING*************************");
        System.out.println("********************************************************************************");
        System.out.println("********************************************************************************");
        System.out.println("****MAIN MENU*******************************************************************\n");

        System.out.println("1.My Account");
        System.out.println("2.Money Transfer");
        System.out.println("3.Topup Airtime");
        System.out.println("4.Utility payment");
        System.out.println("5.Exchange Rate");
        System.out.println("6.Donation");
        System.out.println("7.Exit");
        Scanner sc = new Scanner(System.in);

        try {
            int c = sc.nextInt();
            ClearScreen.cls();// calling clear screen before going to choose service in the switch

            switch (c) {
                case 1: {
                    myaccount.display();

                    break;
                }
                case 2: {

                    moneytransfer.transfer();
                    break;
                }
                case 3: {
                    airtime.buyAirtime();
                    break;
                }
                case 4: {
                    UtilityPaymnet.utiltiyMenu();
                    break;

                }
                case 5: {
                    ExchangeRate.viewExchangeRate();
                    break;
                }
                case 6: {
                    Donation.chooseArea();
                    break;
                }

            }
        } catch (Exception e) {

            ClearScreen.cls();// calling clear screen before going to choose service in the switch again
                              // because of error
            System.out.println("Wrong choice entered, please try again");
            menu();
        }
        sc.close();

    }

}
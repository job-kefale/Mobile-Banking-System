import java.util.Scanner;
import java.util.regex.Pattern;
import java.sql.*;

public class MoneyTransfer extends Account {
    public void transfer() {
        String userAccount = "";
        try {
            DbConnection db = new DbConnection();
            Connection con = db.Connection();

            Statement stmt = con.createStatement();
            ResultSet results = stmt
                    .executeQuery("SELECT account.accno" + " FROM (account " +
                            "INNER JOIN user ON (account.accno = user.accno))" +
                            " WHERE`phoneno`='" + phonenumber + "'");// selecting logged user account number for
                                                                     // updating it
            results.next();
            userAccount = results.getString(1);
            // System.out.println(userAccount);

        }

        catch (Exception e) {
            System.out.println(e);
        } finally {

            Scanner sc = new Scanner(System.in);

            System.out.println("please enter account number");
            String acc = sc.nextLine();
            ClearScreen.cls();// calling clear screen before going to

            if (Pattern.matches("[0-9]+", acc) == true && acc.length() == 6) {
                if (acc.equals(userAccount)) {
                    System.out.println("You can not transfer to your own Account.good bye");

                } else {
                    accountFinder(acc);// calling account finder for checking
                                       // if there is an account number with this id

                }

            }

            else {
                System.out.println("Incorrect format of account number is entered, please try again.");
                transfer();
            }

            sc.close();

        }
    }

    public void accountFinder(String accountno) {
        Scanner sc = new Scanner(System.in);
        try {
            DbConnection db = new DbConnection();
            Connection con = db.Connection();

            Statement stmt = con.createStatement();
            ResultSet results = stmt
                    .executeQuery("SELECT CONCAT(firstname, ' ', lastname) as fullname" + " FROM (user " +
                            "INNER JOIN account ON (account.accno = user.accno))" +
                            " WHERE user.accno =" + accountno);

            results.next();
            // for (int i = 0;i)
            System.out.println("Payment to other \n1: " + (results.getString(1)) + "-ETB-" + "????");
            // System.out.println((results.getString(1)));
            int num = sc.nextInt();
            ClearScreen.cls();// calling clear screen before going to

            if (num == 1) {

                System.out.println((results.getString(1)) + "\nRequest Debit Acc: " + name + "\ncredit Acc: "
                        + (results.getString(1)) + "-ETB-" + "????\nPlease enter Amount");

                int amount = sc.nextInt(); // inserting money to be transfer
                ClearScreen.cls();// calling clear screen before going to

                if (amount > 1) {
                    UtilityPaymnet.userUpdateBalance(amount); // calling update method from utility class
                    sc.nextLine();

                    System.out.println((results.getString(1)) + "\nRequest Debit Acc: " + name + "\ncredit Acc: "
                            + (results.getString(1)) + "-ETB-" + "????\nPlease enter Reason");

                    String reason = sc.nextLine();
                    ClearScreen.cls();// calling clear screen before going to confirmation message

                    System.out.println("\nCONFIRMATION \nConfirm request for " + (results.getString(1)) +
                            "\nRequest Debit Acc: " + name + "\ncredit Acc: " + (results.getString(1)) +
                            "\nAmount: " + amount + "\nReason: " + reason + "\nplease confirm \n1: YES \n2: NO ");

                    int confirm = sc.nextInt();
                    ClearScreen.cls();// calling clear screen before going to confirm

                    if (confirm == 1) {

                        ClearScreen.cls();// calling clear screen before backing to menu of app class
                        updateBalance(accountno, amount);// calls the updated method in account class;
                        // userUpdateBalance();// updating the loged in user in the database and sending
                        // message

                        ifBack();
                    } else if (confirm == 2) {
                        System.out.println("bye bye");
                    }

                    else {
                        accountFinder(accountno);

                    }
                    sc.close();
                    ClearScreen.cls();// calling clear screen before going to message

                } else {
                    System.out.println("Amount must be greater than 1 ETB.Try again");
                    accountFinder(accountno);
                }

                sc.close();

            }
        } catch (Exception e) {

            System.out.println("The amount you entered must be a number, please enter again");
            System.out.println(e);
            accountFinder(accountno);

        }
    }
}
import java.util.Scanner;
import java.util.regex.Pattern;
import java.sql.*;

public class Airtime extends Account {

    static int amount;

    public void buyAirtime() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Mobile Number - ");
        String mobilenum = sc.nextLine();
        ClearScreen.cls();// calling clear screen before going to entering amount

        if (Pattern.matches("[0-9]+", mobilenum) == true && mobilenum.length() == 10) {
            System.out.println("Enter amount - ");
            try {
                amount = sc.nextInt();
                ClearScreen.cls();// calling clear screen before going to confirmation request

                if (amount > 1) {
                    System.out.println("confirmation request " + "\nRecharged mobile number - "
                            + mobilenum + "\namount- " + amount + "\n->press 1 to confirm\n->press 2 to exit");

                    int confirm = sc.nextInt();
                    ClearScreen.cls();// calling clear screen before going to display message

                    if (confirm == 1) {
                        userUpdateBalance();

                    } else if (confirm == 2) {
                        System.out.println("you have canceled the service good bye ");
                        System.exit(0);
                    } else {

                        buyAirtime();
                    }
                } else {
                    System.out.println("Wrong amount entered,please try again");
                    buyAirtime();
                }

            } catch (Exception e) {
                System.out.println("The value you entered must be integer number,please try again");
                buyAirtime();

            }
        } else {
            System.out.println("Incorrect format of phone number is entered please try again.");
            buyAirtime();
        }

        sc.close();
    }

    public static void userUpdateBalance() {
        // this.amount = amount;
        try {
            DbConnection db = new DbConnection();
            Connection con = db.Connection();

            Statement stmt = con.createStatement();
            ResultSet results = stmt
                    .executeQuery("SELECT account.accno" + " FROM (account " +
                            "INNER JOIN user ON (account.accno = user.accno))" +
                            " WHERE`phoneno`='" + phonenumber + "'");// selecting users account number for updating it
            results.next();
            String userAccount = results.getString(1);
            if (balance > amount) {
                balance -= amount;

                PreparedStatement statement = con
                        .prepareStatement("update account set balance = ? where accno = ?");
                statement.setInt(1, balance);
                statement.setString(2, userAccount);
                statement.executeUpdate();
                // messaging
                System.out.println("Dear customer your Account " + userAccount + ",\nhas been debited with ETB "
                        + amount + ".\nyour current balance is ETB " + balance);
                ifBack();
            } else {
                System.out.println("Dear customer your balance is insufficient, please try again");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}

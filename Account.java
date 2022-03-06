import java.sql.*;
import java.util.Scanner;

public class Account {
    public static String phonenumber;
    public static String name;
    public static int balance;
    public static int insAmount;

    // method that retrives Full name of the logged user by using phonenumber
    public String name(String phonenumber) {
        Account.phonenumber = phonenumber;

        try {
            DbConnection db = new DbConnection();
            Connection con = db.Connection();

            Statement stmt = con.createStatement();
            ResultSet results = stmt
                    .executeQuery("SELECT CONCAT(firstname, ' ', lastname)AS FullName FROM `user` WHERE`phoneno`='"
                            + phonenumber + "'");

            results.next();
            name = (results.getString(1));

        } catch (Exception e) {
            System.out.println(e);
        }
        return phonenumber; // why it returns phone number?

    }

    // method that retrives balance of the logged user by using phonenumber
    public void balance() {
        try {
            DbConnection db = new DbConnection();
            Connection con = db.Connection();

            Statement stmt = con.createStatement();
            ResultSet results = stmt
                    .executeQuery("SELECT balance" + " FROM (account " +
                            "INNER JOIN user ON (account.accno = user.accno))" +
                            " WHERE`phoneno`='" + phonenumber + "'");

            results.next();
            balance = (results.getInt(1));

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    // method for backing to the main menu after terminating its work in each class
    public static void ifBack() {
        // App app = new App();// object for menu method in App
        System.out.println(
                "\nThank you for banking with us. \n->press 1 to back to main menu \n->press any key to exit ");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        ClearScreen.cls();// calling clear screen before going to menu
        if (x == 1) {
            App.menu();
        } else {
            System.exit(0);
        }

        sc.close();

    }
    // method for updating to the recivers balance on the database in for the money
    // transfer class

    public static void updateBalance(String reciverAcc, int insertedAmount) {

        insAmount = insertedAmount;

        try {
            DbConnection db = new DbConnection();
            Connection con = db.Connection();

            Statement stmt = con.createStatement();
            ResultSet results = stmt
                    .executeQuery("select balance from account where accno =" + reciverAcc);
            results.next();

            int recieverBalance = results.getInt(1);

            recieverBalance += insertedAmount;
            PreparedStatement statement = con
                    .prepareStatement("update account set balance = ? where accno = ?");
            statement.setInt(1, recieverBalance);
            statement.setString(2, reciverAcc);
            statement.executeUpdate();
            // System.out.println(recieverBalance);
            stmt.close();
            con.close();
            userUpdateBalance();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    // method for updating to the logged user balance on the database and sending
    // message for the user

    public static void userUpdateBalance() {

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
            if (balance > insAmount) {
                balance -= insAmount;

                PreparedStatement statement = con
                        .prepareStatement("update account set balance = ? where accno = ?");
                statement.setInt(1, balance);
                statement.setString(2, userAccount);
                statement.executeUpdate();
                // messaging
                System.out.println("Dear customer your Account " + userAccount + ",\nhas been debited with ETB "
                        + insAmount + ".\nyour current balance is ETB " + balance + ".\nThank You for Banking with us");
            } else {
                System.out.println("Dear customer your balance is insufficient, please try again");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}

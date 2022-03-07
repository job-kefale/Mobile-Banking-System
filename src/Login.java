import java.sql.*;

public class Login {
    // public static String phone = "";

    public void checkAuthentication(String phonenumber, String pass) {
        boolean check = false;

        try {

            DbConnection db = new DbConnection();
            Connection con = db.Connection();

            Statement stmt = con.createStatement();
            ResultSet results = stmt.executeQuery("select phoneno, pin  from user");
            while (results.next()) {

                String phonenum = results.getString("phoneno"); // saving phonenum from DB

                String password = results.getString("pin");
                // checking for login
                if (phonenumber.equals(phonenum) && pass.equals(password)) {
                    // phone = phoneno;//phone num for transfering to Account
                    check = true;
                    break;
                } else {

                    check = false;

                }

            }
            if (check) {
                System.out.println("successfully login");
                Account account = new Account();
                account.name(phonenumber);
                account.balance();

                // MyAccount myaccount = new MyAccount();
                // myaccount.display();

            } else {
                System.out.println("check your phone number or pin\n try again");
                App.callLogin();

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}

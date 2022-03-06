import java.util.Scanner;
import java.sql.*;

abstract class UtilityPaymnet extends Account {

    public static void utiltiyMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.println("1.Dstv Payment");
        System.out.println("2.Electric Bill");
        System.out.println("3.Water Bill");
        System.out.println("4.Wifi Bill");
        System.out.println("5.Airline Ticket  Payment");
        int choice = sc.nextInt();
      

        switch (choice) {
            case 1: {
                DSTV dstv = new DSTV();
                dstv.payment();
                break;

            }
            case 2: {
                ElectricBill electricBill = new ElectricBill();
                electricBill.payment();
                break;
            }
            case 3: {
                WaterBill Waterbill = new WaterBill();
                Waterbill.payment();
                break;
            }
            case 4: {
                Wifi wifi = new Wifi();
                wifi.payment();
                break;
            }
            case 5: {
                AirlineTiket airlineTiket = new AirlineTiket();
                airlineTiket.payment();
                break;
            }
            default: {
                System.out.println("you have entered wrong number, please try again");
                utiltiyMenu();
                break;
            }

        }

        sc.close();
    }

    public static void userUpdateBalance(int amount) {
   
      
            DbConnection db = new DbConnection();
            Connection con = db.Connection();

            Statement stmt = con.createStatement();
            ResultSet results = stmt
                    .executeQuery("SELECT account.accno" + " FROM (account " +
                            "INNER JOIN user ON (account.accno = user.accno))" +
                            " WHERE`phoneno`='" + phonenumber + "'");
            results.next();
            String userAccount = results.getString(1);
            if (balance > amount) {
                balance -= amount;

                PreparedStatement statement = con
                        .prepareStatement("update account set balance = ? where accno = ?");
                statement.setInt(1, balance);
                statement.setString(2, userAccount);
                statement.executeUpdate();
            }
         

    }

    abstract void message();

    abstract void payment();

}

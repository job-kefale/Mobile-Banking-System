import java.sql.*;

public class ExchangeRate {
    static String name;
    static String buy;
    static String sell;

    public static void viewExchangeRate() {

        try {
            DbConnection db = new DbConnection();
            Connection con = db.Connection();

            Statement stmt = con.createStatement();
            ResultSet results = stmt
                    .executeQuery("SELECT name,buy,sell from exchangerate    ");
            System.out.println("********************TODAY'S EXCHANGE RATE IN ETB*************************");

            System.out.printf("%-8s%8s%11s%n", "Currency", "Buy", "Sell");

            while (results.next()) {
                name = (results.getString(1));
                buy = (results.getString(2));
                sell = (results.getString(3));

                System.out.printf("%-8s%10s%10s%n", name, buy, sell);
            }
            Account.ifBack();

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}

public class MyAccount extends Account {
    public void display() {
        System.out.println("********************ACCOUNT INFORMATIONS*************************");
        System.out.println(name);
        System.out.println("your available balance is ETB " + balance);
        ifBack();
    }

}

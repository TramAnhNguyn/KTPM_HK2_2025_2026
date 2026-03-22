package iuh.fit.se;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Stock stock = new Stock();

        stock.addObserver(new Investor("A"));
        stock.addObserver(new Investor("B"));

        stock.setPrice(100);
        stock.setPrice(120);
    }
}

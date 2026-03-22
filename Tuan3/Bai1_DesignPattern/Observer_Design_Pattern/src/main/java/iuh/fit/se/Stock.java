package iuh.fit.se;

import java.util.ArrayList;
import java.util.List;

public class Stock {
    private List<Observer> observers = new ArrayList<>();
    private double price;

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void setPrice(double price) {
        this.price = price;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer o : observers) {
            o.update(price);
        }
    }
}

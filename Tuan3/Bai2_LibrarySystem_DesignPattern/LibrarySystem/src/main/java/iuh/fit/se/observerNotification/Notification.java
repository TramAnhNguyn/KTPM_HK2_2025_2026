package iuh.fit.se.observerNotification;

import java.util.ArrayList;
import java.util.List;

public class Notification {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void notifyAllObservers(String msg) {
        for (Observer o : observers) {
            o.update(msg);
        }
    }
}

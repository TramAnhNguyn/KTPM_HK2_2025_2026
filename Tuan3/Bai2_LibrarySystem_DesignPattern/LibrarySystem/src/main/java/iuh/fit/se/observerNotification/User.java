package iuh.fit.se.observerNotification;

public class User implements Observer{
    public void update(String msg) {
        System.out.println("User nhận: " + msg);
    }
}

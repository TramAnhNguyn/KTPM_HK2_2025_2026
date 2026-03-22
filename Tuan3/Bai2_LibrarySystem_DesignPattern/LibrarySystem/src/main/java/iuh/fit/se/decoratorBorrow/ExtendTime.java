package iuh.fit.se.decoratorBorrow;

import java.sql.Time;

public class ExtendTime extends BorrowDecorator{
    public ExtendTime(Borrow b) {
        super(b);
    }

    public void borrow() {
        super.borrow();
        System.out.println("Gia hạn thời gian");
    }
}

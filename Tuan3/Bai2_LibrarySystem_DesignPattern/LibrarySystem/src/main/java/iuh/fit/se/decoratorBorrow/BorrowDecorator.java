package iuh.fit.se.decoratorBorrow;

abstract class BorrowDecorator implements  Borrow {
    protected Borrow borrow;

    public BorrowDecorator(Borrow borrow) {
        this.borrow = borrow;
    }

    public void borrow() {
        borrow.borrow();
    }
}

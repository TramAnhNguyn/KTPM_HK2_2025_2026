package iuh.fit.se.taxSystem;

public class LuxuryTaxStrategy implements TaxStrategy{
    @Override
    public double calculateTax(double price) {
        return price * 0.5; // Luxury Tax is 50%
    }
}

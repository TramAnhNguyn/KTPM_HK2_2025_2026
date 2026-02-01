package iuh.fit.se.taxSystem;

public class ConsumptionTaxStrategy implements TaxStrategy{
    @Override
    public double calculateTax(double price) {
        return price * 0.05; // Consumption Tax is 5%
    }
}

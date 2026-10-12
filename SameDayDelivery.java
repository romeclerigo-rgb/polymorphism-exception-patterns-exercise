
public class SameDayDelivery implements DeliveryStrategy {

    @Override
    public double calculateFee(double baseFee) {
        return (baseFee * 2) + 100.00;
    }
}

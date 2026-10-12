
public class ExpressDelivery implements DeliveryStrategy {

    @Override
    public double calculateFee(double baseFee) {
        return baseFee * 1.5;
    }
}

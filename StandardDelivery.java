
public class StandardDelivery implements DeliveryStrategy {

    @Override
    public double calculateFee(double baseFee) {
        return baseFee;
    }
}

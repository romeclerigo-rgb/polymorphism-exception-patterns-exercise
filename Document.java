
public class Document extends Parcel {

    public Document(String trackingNumber, String receiverName,
                    double actualWeight, String deliveryService) {
        super(trackingNumber, receiverName, actualWeight, deliveryService);
    }

    @Override
    public String getParcelType() {
        return "Document";
    }

    @Override
    public double getChargeableWeight() {
        return getActualWeight();
    }

    @Override
    public double getBaseFee() {
        return 50.00;
    }
}

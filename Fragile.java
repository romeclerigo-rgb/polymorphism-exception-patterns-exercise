
public class Fragile extends Box {
    private String handlingNote;

    public Fragile(String trackingNumber, String receiverName,
                   double actualWeight, String deliveryService,
                   int length, int width, int height,
                   String handlingNote) {
        super(trackingNumber, receiverName, actualWeight,
              deliveryService, length, width, height);

        this.handlingNote = handlingNote;
    }

    public String getHandlingNote() {
        return handlingNote;
    }

    @Override
    public String getParcelType() {
        return "Fragile";
    }

    @Override
    public double getBaseFee() {
        return super.getBaseFee() + 100.00;
    }
}


public class Box extends Parcel {
    private int length;
    private int width;
    private int height;

    public Box(String trackingNumber, String receiverName,
               double actualWeight, String deliveryService,
               int length, int width, int height) {
        super(trackingNumber, receiverName, actualWeight, deliveryService);
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getVolumetricWeight() {
        return (double) length * width * height / 3500;
    }

    @Override
    public String getParcelType() {
        return "Box";
    }

    @Override
    public double getChargeableWeight() {
        double chargeableWeight = Math.max(
            getActualWeight(), getVolumetricWeight()
        );

        return Math.ceil(chargeableWeight);
    }

    @Override
    public double getBaseFee() {
        return getChargeableWeight() * 40.00;
    }
}

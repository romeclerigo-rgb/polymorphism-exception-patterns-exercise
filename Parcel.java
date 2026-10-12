
public abstract class Parcel {
    private String trackingNumber;
    private String receiverName;
    private double actualWeight;
    private String deliveryService;
    private String status;

    public Parcel(String trackingNumber, String receiverName,
                  double actualWeight, String deliveryService) {
        this.trackingNumber = trackingNumber;
        this.receiverName = receiverName;
        this.actualWeight = actualWeight;
        this.deliveryService = deliveryService;
        this.status = "Pending";
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public double getActualWeight() {
        return actualWeight;
    }

    public String getDeliveryService() {
        return deliveryService;
    }

    public String getStatus() {
        return status;
    }

    public void setDeliveryService(String deliveryService) {
        this.deliveryService = deliveryService;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public abstract String getParcelType();

    public abstract double getChargeableWeight();

    public abstract double getBaseFee();
}

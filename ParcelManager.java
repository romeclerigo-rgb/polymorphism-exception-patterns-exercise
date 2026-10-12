
import java.util.ArrayList;

public class ParcelManager {
    private static ParcelManager instance;
    private ArrayList<Parcel> parcels;

    private ParcelManager() {
        parcels = new ArrayList<>();
    }

    public static ParcelManager getInstance() {
        if (instance == null) {
            instance = new ParcelManager();
        }

        return instance;
    }

    public boolean isEmpty() {
        return parcels.isEmpty();
    }

    public boolean trackingNumberExists(String trackingNumber) {
        for (Parcel parcel : parcels) {
            if (parcel.getTrackingNumber().equals(trackingNumber)) {
                return true;
            }
        }

        return false;
    }

    public Parcel findParcel(String trackingNumber) {
        for (Parcel parcel : parcels) {
            if (parcel.getTrackingNumber().equals(trackingNumber)) {
                return parcel;
            }
        }

        return null;
    }

    public void addParcel(Parcel parcel) {
        parcels.add(parcel);
    }

    public ArrayList<Parcel> getParcels() {
        return new ArrayList<>(parcels);
    }

    public DeliveryStrategy getDeliveryStrategy(String deliveryService) {
        if (deliveryService.equals("Standard")) {
            return new StandardDelivery();
        } else if (deliveryService.equals("Express")) {
            return new ExpressDelivery();
        } else if (deliveryService.equals("Same Day")) {
            return new SameDayDelivery();
        }

        return null;
    }

    public double calculateDeliveryFee(Parcel parcel) {
        DeliveryStrategy strategy =
            getDeliveryStrategy(parcel.getDeliveryService());

        if (strategy == null) {
            return 0.00;
        }

        return strategy.calculateFee(parcel.getBaseFee());
    }
}

package muratk.mkeczane.Models;

/**
 * Created by Murat on 08/10/15.
 */
public class NobetciEczaneler {
    private String PharmacyAddress;
    private String PharmacyName;
    private String PharmacyNumber;
    private String IlceName;
    private String ColorCode;

    public String getPharmacyAddress() {
        return PharmacyAddress;
    }

    public void setPharmacyAddress(String pharmacyAddress) {
        PharmacyAddress = pharmacyAddress;
    }

    public String getPharmacyName() {
        return PharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        PharmacyName = pharmacyName;
    }

    public String getPharmacyNumber() {
        return PharmacyNumber;
    }

    public void setPharmacyNumber(String pharmacyNumber) {
        PharmacyNumber = pharmacyNumber;
    }

    public String getIlceName() {
        return IlceName;
    }

    public void setIlceName(String ilceName) {
        IlceName = ilceName;
    }

    public String getColorCode() {
        return ColorCode;
    }

    public void setColorCode(String colorCode) {
        ColorCode = colorCode;
    }

    public NobetciEczaneler() {
    }

    public NobetciEczaneler(String pharmacyAddress, String pharmacyName, String pharmacyNumber, String ilceName, String colorCode) {
        PharmacyAddress = pharmacyAddress;
        PharmacyName = pharmacyName;
        PharmacyNumber = pharmacyNumber;
        IlceName = ilceName;
        ColorCode = colorCode;
    }
}

package muratk.mkeczane.Models;

/**
 * Created by Murat on 08/10/15.
 */
public class EczaneIlceler {
    private String RegionId;
    private String RegionName;

    public String getRegionId() {
        return RegionId;
    }

    public void setRegionId(String regionId) {
        RegionId = regionId;
    }

    public String getRegionName() {
        return RegionName;
    }

    public void setRegionName(String regionName) {
        RegionName = regionName;
    }

    public EczaneIlceler() {
    }

    public EczaneIlceler(String regionId, String regionName) {
        RegionId = regionId;
        RegionName = regionName;
    }
}

package muratk.mkeczane.Models;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Murat on 08/10/15.
 */
public interface ServiceIller {
    @GET("/CityList")
    void GetIller(Callback<List<EczaneIller>> callback);
}

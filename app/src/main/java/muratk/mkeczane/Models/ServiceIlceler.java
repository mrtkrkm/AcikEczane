package muratk.mkeczane.Models;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Murat on 08/10/15.
 */
public interface ServiceIlceler {
    @GET("/Regions/{IlId}")
    void GetIlceler(@Path("IlId") String ilId, Callback<List<EczaneIlceler>> callback);
}

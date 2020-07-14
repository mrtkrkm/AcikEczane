package muratk.mkeczane.Models;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Murat on 08/10/15.
 */
public interface ServiceNobetciEczaneler {
    @GET("/Pharmacies/{IlceId}")
    void GetNobetciEczaneler(@Path("IlceId") String ilceId, Callback<List<NobetciEczaneler>> callback);
}

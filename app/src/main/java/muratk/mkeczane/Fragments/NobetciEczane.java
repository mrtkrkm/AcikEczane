package muratk.mkeczane.Fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.List;

import muratk.mkeczane.Adapters.EczaneAdapter;
import muratk.mkeczane.Libraries.Database;
import muratk.mkeczane.Models.EczaneIlceler;
import muratk.mkeczane.Models.EczaneIller;
import muratk.mkeczane.Models.NobetciEczaneler;
import muratk.mkeczane.Models.ServiceIlceler;
import muratk.mkeczane.Models.ServiceIller;
import muratk.mkeczane.Models.ServiceNobetciEczaneler;
import muratk.eczanem.R;
import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NobetciEczane extends Fragment {

    List<EczaneIlceler> nobetciEczaneIlcelers;
    ArrayList<NobetciEczaneler> nobetciEczanelers;
    ArrayList<String> colorCodes;
    private MaterialSpinner spinner;
    ListView listView;
    List<EczaneIller> listIl;
    ArrayList<String> il_names;
    ArrayList<String> il_ids;
    LinearLayout linearLayout_loading;
    LinearLayout linearLayout_not_found;
    TextView txt_message;
    View v;
    InterstitialAd mInterstitialAd;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_nobetci_eczane, container, false);

        AdView mAdView = (AdView) v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        if (!internetErisimi()){
            mAdView.setVisibility(v.GONE);
        }

        mInterstitialAd = new InterstitialAd(v.getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-8034656764343756/7244400027");
        requestNewInterstitial();

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    requestNewInterstitial();
                }
            }
        }, 7000);

        linearLayout_loading = (LinearLayout) v.findViewById(R.id.eczaneler_linear);
        linearLayout_not_found = (LinearLayout) v.findViewById(R.id.eczaneler_linear_not_found);
        txt_message = (TextView) v.findViewById(R.id.txt_error_message);

        Database db = new Database(v.getContext());
        listIl = db.GetIller();

        if (listIl.size() == 0){
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://eu-aws.imobilecode.com/EczaneService/EczaneService.svc").build();

            ServiceIller service = restAdapter.create(ServiceIller.class);
            service.GetIller(new Callback<List<EczaneIller>>() {
                @Override
                public void success(List<EczaneIller> eczaneIllers, Response response) {
                    listIl = eczaneIllers;
                    il_names = new ArrayList<String>();
                    il_ids = new ArrayList<String>();
                    for (int i = 0; i < listIl.size(); i++){
                        il_names.add(listIl.get(i).getName());
                        il_ids.add(Integer.toString(listIl.get(i).getId()));
                        Database db = new Database(v.getContext());
                        if (i == 0){
                            db.AddIl(Integer.toString(listIl.get(i).getId()), listIl.get(i).getName(), "1");
                        }
                        else {
                            db.AddIl(Integer.toString(listIl.get(i).getId()), listIl.get(i).getName(), "0");
                        }
                    }
                    GetIlceler("1");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, il_names);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner = (MaterialSpinner) v.findViewById(R.id.spinner);
                    spinner.setAdapter(adapter);

                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            linearLayout_loading.setVisibility(v.VISIBLE);
                            linearLayout_not_found.setVisibility(v.VISIBLE);
                            if (position != -1){
                                try {
                                    Database db = new Database(v.getContext());
                                    db.UpdateNotSelected("1");
                                    db.UpdateSelected(il_ids.get(position));
                                    GetIlceler(il_ids.get(position));
                                }
                                catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                @Override
                public void failure(RetrofitError retrofitError) {

                }
            });
        }
        else {

            GetIlceler(Integer.toString(db.GetIllerByID("1").get(0).getId()));

            il_names = new ArrayList<String>();
            il_ids = new ArrayList<String>();
            for (int i = 0; i < listIl.size(); i++){
                il_names.add(listIl.get(i).getName());
                il_ids.add(Integer.toString(listIl.get(i).getId()));
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, il_names);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner = (MaterialSpinner) v.findViewById(R.id.spinner);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    linearLayout_loading.setVisibility(v.VISIBLE);
                    linearLayout_not_found.setVisibility(v.VISIBLE);
                    if (position != -1){
                        try {
                            Database db = new Database(v.getContext());
                            db.UpdateNotSelected(Integer.toString(db.GetIllerByID("1").get(0).getId()));
                            db.UpdateSelected(il_ids.get(position));
                            GetIlceler(il_ids.get(position));
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        return v;
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    public void GetIlceler(String il_id){
        listView = (ListView) v.findViewById(R.id.list_nobetci_eczane);
        nobetciEczanelers = new ArrayList<NobetciEczaneler>();
        colorCodes = new ArrayList<String>();
        colorCodes.add("#098dca");
        colorCodes.add("#db4437");
        colorCodes.add("#009688");
        colorCodes.add("#2d566b");
        colorCodes.add("#227586");
        colorCodes.add("#871e6a");
        colorCodes.add("#ab1656");
        colorCodes.add("#607d8b");
        colorCodes.add("#FF9800");
        colorCodes.add("#ff4081");
        colorCodes.add("#9c27b0");
        colorCodes.add("#0f88bc");
        colorCodes.add("#c53929");
        colorCodes.add("#00796B");
        colorCodes.add("#5d747f");
        colorCodes.add("#F57C00");

        try {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://eu-aws.imobilecode.com/EczaneService/EczaneService.svc").build();

            ServiceIlceler service = restAdapter.create(ServiceIlceler.class);
            service.GetIlceler(il_id, new Callback<List<EczaneIlceler>>() {
                @Override
                public void success(List<EczaneIlceler> ilcelers, Response response) {
                    nobetciEczaneIlcelers = ilcelers;
                    GetEczaneler(0);
                }

                @Override
                public void failure(RetrofitError error) {
                    txt_message.setText("Eczaneler getirilirken bir hata oluştu.\n\nLütfen internet bağlantınızı kontrol ediniz.");
                    linearLayout_loading.setVisibility(v.GONE);
                    linearLayout_not_found.setVisibility(v.VISIBLE);
                }
            });
        }
        catch (Exception e){
            txt_message.setText("Seçtiğiniz ilde Nöbetçi Eczane bulamadık.\n\nÖzür Dileriz...");
            linearLayout_loading.setVisibility(v.GONE);
            linearLayout_not_found.setVisibility(v.VISIBLE);
            e.printStackTrace();
        }
    }

    public void GetEczaneler(final int index){
        try {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://eu-aws.imobilecode.com/EczaneService/EczaneService.svc").build();

            ServiceNobetciEczaneler service = restAdapter.create(ServiceNobetciEczaneler.class);
            service.GetNobetciEczaneler(nobetciEczaneIlcelers.get(index).getRegionId(), new Callback<List<NobetciEczaneler>>() {
                @Override
                public void success(List<NobetciEczaneler> eczanelers, Response response) {
                    if (index == nobetciEczaneIlcelers.size() - 1) {
                        for (int i = 0; i < eczanelers.size(); i++){
                            eczanelers.get(i).setColorCode(colorCodes.get(index%16));
                            eczanelers.get(i).setIlceName(nobetciEczaneIlcelers.get(index).getRegionName());
                            nobetciEczanelers.add(eczanelers.get(i));
                        }
                        linearLayout_loading.setVisibility(v.GONE);
                        linearLayout_not_found.setVisibility(v.GONE);
                        EczaneAdapter adapter = new EczaneAdapter(v.getContext(), nobetciEczanelers, nobetciEczaneIlcelers);
                        listView.setAdapter(adapter);
                    } else {
                        for (int i = 0; i < eczanelers.size(); i++){
                            eczanelers.get(i).setColorCode(colorCodes.get(index%16));
                            eczanelers.get(i).setIlceName(nobetciEczaneIlcelers.get(index).getRegionName());
                            nobetciEczanelers.add(eczanelers.get(i));
                        }
                        GetEczaneler(index + 1);
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    txt_message.setText("Eczaneler getirilirken bir hata oluştu.\n\nLütfen internet bağlantınızı kontrol ediniz.");
                    linearLayout_loading.setVisibility(v.GONE);
                    linearLayout_not_found.setVisibility(v.VISIBLE);
                }
            });
        }
        catch (Exception e){
            txt_message.setText("Seçtiğiniz ilde Nöbetçi Eczane bulamadık.\n\nÖzür Dileriz...");
            linearLayout_loading.setVisibility(v.GONE);
            linearLayout_not_found.setVisibility(v.VISIBLE);
            e.printStackTrace();
        }
    }

    public boolean internetErisimi() {
        ConnectivityManager conMgr = (ConnectivityManager) getActivity().getSystemService (Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}

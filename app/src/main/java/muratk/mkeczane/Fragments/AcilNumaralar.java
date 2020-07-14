package muratk.mkeczane.Fragments;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

import muratk.mkeczane.Adapters.AcilAdapter;
import muratk.mkczanem.R;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class AcilNumaralar extends Fragment {

    private MaterialDialog mMaterialDialog;
    ArrayList<String> colorCodes;
    View v;
    InterstitialAd mInterstitialAd;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_acil_numaralar, container, false);

        AdView mAdView = (AdView) v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        if (!internetErisimi()){
            mAdView.setVisibility(v.GONE);
        }

        mInterstitialAd = new InterstitialAd(v.getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-8034656764343756/5767666827");
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

        final String[] arrAcilNumaralar = {"Alo Doktorum Yanımda", "Alo Gürültü", "Alo RTÜK", "Alo Trafik", "Alo Tüketici", "Alo Zabıta", "Arıza İhbar", "Cenaze Hizmetleri", "Çevre Bilgi", "Doğalgaz Arıza", "Fonotel", "Jandarma İmdat", "Kablo TV Arıza", "Kan Bilgi Merkezi", "Kodlu Arama", "Orman Yangın İhbarı", "Polis İmdat", "Radyo - TV Arıza", "Sağlık Danışmanı", "Şehirlerarası Kayıt", "Tele Bilgi", "Telefon Arıza", "Telekom Hizmet Danışma", "Türk Telekom Borç Sorgulama", "Uyuşturucu Bilgi", "Yangın İhbar", "Turkcell Bilinmeyen Numaralar Servisi", "İnsan Ticareti Mağdurlarına Yardım", "Alo Emniyet Danışma", "Alo Post", "Alo Sahil Güvenlik", "Alo Turizm Bilgi", "Alo Valilik", "Ankesör Arıza", "Arıza Takip", "Çağrı Servisi", "Data Arıza", "Elektrik Arıza", "Hızır Acil Servis", "İş ve İşçi Bulma Kurumu", "Kadın ve Sosyal Hizmetler", "Kod Danışma", "Milletlerarası Kayıt", "Posta Kodu Danışma", "Ruhsal Bunalım Danışma", "Su Arıza", "Tele Bilgi", "Türk Telekom Bilinmeyen Numaralar", "Teleks Arıza", "Vergi Danışma", "Yerinde Olmayan Abone", "Vodafone Bilinmeyen Numaralar", "Avea Bilinmeyen Numaralar"};
        final String[] arrAcilNo = {"113", "176", "178", "154", "175", "153", "102", "188", "181", "187", "141", "156", "126", "173", "168", "177", "155", "125", "184", "131", "146", "121", "161", "163", "171", "110", "11832", "157", "174", "169", "158", "170", "179", "122", "101", "133", "124", "186", "112", "180", "183", "199", "115", "119", "182", "185", "144", "118", "123", "189", "134", "11842", "11855"};

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
        colorCodes.add("#0f88bc");
        colorCodes.add("#c53929");
        colorCodes.add("#00796B");
        colorCodes.add("#5d747f");
        colorCodes.add("#F57C00");

        ListView listView = (ListView) v.findViewById(R.id.list_acil_numaralar);
        listView.setAdapter(new AcilAdapter(v.getContext(), arrAcilNumaralar, arrAcilNo, colorCodes));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                mMaterialDialog = new MaterialDialog(new ContextThemeWrapper(getContext(), R.style.MyAlertDialog))
                        .setTitle("Uyarı")
                        .setMessage(arrAcilNumaralar[position] + " (" + arrAcilNo[position] + ")" + " aranacak devam etmek istiyor musunuz ?")
                        .setPositiveButton("Evet", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:" + arrAcilNo[position]));
                                getContext().startActivity(callIntent);
                                mMaterialDialog.dismiss();
                            }
                        })
                        .setNegativeButton("Hayır", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                            }
                        });
                mMaterialDialog.show();
            }
        });

        return v;
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
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

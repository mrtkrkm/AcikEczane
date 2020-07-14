package muratk.mkeczane.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import muratk.mkeczane.Models.EczaneIlceler;
import muratk.mkeczane.Models.NobetciEczaneler;
import muratk.eczanem.R;
import me.drakeet.materialdialog.MaterialDialog;

public class EczaneAdapter extends BaseAdapter {

    private Context ctx;
    private List<NobetciEczaneler> nobetciEczanelers;
    private List<EczaneIlceler> nobetciEczaneIlcelers;
    private MaterialDialog mMaterialDialog;

    public EczaneAdapter(Context ctx, List<NobetciEczaneler> nobetciEczanelers, List<EczaneIlceler> nobetciEczaneIlcelers){
        this.ctx = ctx;
        this.nobetciEczanelers = nobetciEczanelers;
        this.nobetciEczaneIlcelers = nobetciEczaneIlcelers;
    }

    @Override
    public int getCount() {
        return nobetciEczanelers.size();
    }

    @Override
    public Object getItem(int position) {
        return nobetciEczanelers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String str_name = nobetciEczanelers.get(position).getPharmacyName();
        String str_number = nobetciEczanelers.get(position).getPharmacyNumber();
        str_number = "0 (" + str_number.substring(0, 3) + ") " + str_number.substring(3, 6) + " " + str_number.substring(6, 8) + " " + str_number.substring(8);
        String str_adres = nobetciEczanelers.get(position).getPharmacyAddress() + "\n\n" + nobetciEczanelers.get(position).getIlceName();

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.eczane_layout, parent, false);
        }

        LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.linear_ust_bolum);
        linearLayout.setBackgroundColor(Color.parseColor(nobetciEczanelers.get(position).getColorCode()));

        TextView txt_name = (TextView) convertView.findViewById(R.id.txt_eczane_name);
        txt_name.setText(str_name);

        TextView txt_phone = (TextView) convertView.findViewById(R.id.txt_eczane_phone);
        txt_phone.setText(str_number);

        TextView txt_adres = (TextView) convertView.findViewById(R.id.txt_eczane_adres);
        txt_adres.setText(str_adres);

        final String tel = "tel:0" + nobetciEczanelers.get(position).getPharmacyNumber();
        final String tel_disp = "0" + nobetciEczanelers.get(position).getPharmacyNumber();
        txt_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog = new MaterialDialog(new ContextThemeWrapper(ctx, R.style.MyAlertDialog))
                        .setTitle("Uyarı")
                        .setMessage(str_name + " (" + tel_disp + ")" + " aranacak devam etmek istiyor musunuz ?")
                        .setPositiveButton("Evet", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse(tel));
                                ctx.startActivity(callIntent);
                                mMaterialDialog.dismiss();
                            }
                        })
                        .setNmkativeButton("Hayır", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                            }
                        });
                mMaterialDialog.show();
            }
        });

        return convertView;
    }
}

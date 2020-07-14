package muratk.mkeczane.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import muratk.eczanem.R;

public class AcilAdapter extends BaseAdapter {

    private Context ctx;
    private String[] acil_name;
    private String[] acil_phone;
    private ArrayList<String> colorCode;

    public AcilAdapter(Context ctx, String[] acil_name, String[] acil_phone, ArrayList<String> colorCode){
        this.ctx = ctx;
        this.acil_name = acil_name;
        this.acil_phone = acil_phone;
        this.colorCode = colorCode;
    }

    @Override
    public int getCount() {
        return colorCode.size();
    }

    @Override
    public Object getItem(int position) {
        return colorCode.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String str_name = acil_name[position];
        final String str_phone = "Telefon Numarası : " + acil_phone[position];
        final String color_code = colorCode.get(position);

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.acil_layout, parent, false);
            convertView.setPadding(10, 10, 10, 10);
        }

        TextView txt_color = (TextView) convertView.findViewById(R.id.acil_ind);
        txt_color.setBackgroundColor(Color.parseColor(color_code));

        TextView txt_color_right = (TextView) convertView.findViewById(R.id.acil_ind_right);
        txt_color_right.setBackgroundColor(Color.parseColor(color_code));

        TextView txt_name = (TextView) convertView.findViewById(R.id.txt_acil_name);
        txt_name.setText(str_name);

        TextView txt_phone = (TextView) convertView.findViewById(R.id.txt_acil_phone);
        txt_phone.setText(str_phone);

        return convertView;
    }
}

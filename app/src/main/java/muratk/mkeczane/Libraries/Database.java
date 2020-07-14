package muratk.mkeczane.Libraries;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import muratk.mkeczane.Models.EczaneIller;

/**
 * Created by landforce on 09.08.2014.
 */
public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "iller";

    private static final String TABLE_NAME_IL = "tbl_il";
    private static String ilID = "il_id";
    private static String ilName = "il_name";
    private static String ilSelected = "selected_il";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_IL = "CREATE TABLE " + TABLE_NAME_IL + "("
                + ilID + " TEXT,"
                + ilName + " TEXT,"
                + ilSelected + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_IL);
    }

    public void AddIl(String il_id, String il_name, String il_selected) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ilID, il_id);
        values.put(ilName, il_name);
        values.put(ilSelected, il_selected);
        db.insert(TABLE_NAME_IL, null, values);
        db.close();
    }

    public ArrayList<EczaneIller> GetIller() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME_IL;
        Cursor cursor = db.rawQuery(selectQuery, null);

        ArrayList<EczaneIller> iller = new ArrayList<EczaneIller>();

        if (cursor.moveToFirst()) {
            do {
                EczaneIller obj = new EczaneIller();
                obj.setId(Integer.parseInt(cursor.getString(0)));
                obj.setName(cursor.getString(1));
                obj.setSelected(cursor.getString(2));

                iller.add(obj);
            } while (cursor.moveToNext());
        }
        db.close();
        return iller;
    }

    public ArrayList<EczaneIller> GetIllerByID(String il_selected) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME_IL + " WHERE selected_il = '" + il_selected + "'" ;
        Cursor cursor = db.rawQuery(selectQuery, null);

        ArrayList<EczaneIller> iller = new ArrayList<EczaneIller>();

        if (cursor.moveToFirst()) {
            do {
                EczaneIller obj = new EczaneIller();
                obj.setId(Integer.parseInt(cursor.getString(0)));
                obj.setName(cursor.getString(1));
                obj.setSelected(cursor.getString(2));

                iller.add(obj);
            } while (cursor.moveToNext());
        }
        db.close();
        return iller;
    }

    public void UpdateSelected (String il_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ilSelected, "1");
        db.update(TABLE_NAME_IL, values, ilID + " = ?",
                new String[]{il_id});
                db.close();
    }

    public void UpdateNotSelected (String il_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ilSelected, "0");
        db.update(TABLE_NAME_IL, values, ilID + " = ?",
                new String[]{il_id});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }
}
package databse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dd.app.computall.objects.Desktop;


public class DBManager {

    private SQLiteDatabase database;
    private DBDesigner dbHelper;

    public DBManager(Context context) {
        dbHelper = new DBDesigner(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

    public void add(Desktop d) {
        //(String make, String model, int ram, int storage, int screen, int price, String cpu, int id)
        ContentValues values = new ContentValues();
        values.put("make", d.make);
        values.put("model", d.model);
        values.put("ram",  d.ram);
        values.put("storage", d.storage);
        values.put("screen", d.screen);
        values.put("price", d.price);
        values.put("cpu", d.cpu);
        values.put("id", d.id);

        database.insert("desktopList", null, values);
    }

    public void up(Desktop d) {
        //(String make, String model, int ram, int storage, int screen, int price, String cpu, int id)
        ContentValues values = new ContentValues();
        values.put("make", d.make);
        values.put("model", d.model);
        values.put("ram",  d.ram);
        values.put("storage", d.storage);
        values.put("screen", d.screen);
        values.put("price", d.price);
        values.put("cpu", d.cpu);

        database.insert("desktopList", null, values);
    }

    public List<Desktop> getAll() {
        List<Desktop> desktopList = new ArrayList<Desktop>();
        Cursor cursor = database.rawQuery("SELECT * FROM desktopList", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //Donation d = toDonation(cursor);
            //donations.add(d);
            cursor.moveToNext();
        }
        cursor.close();
        return desktopList;
    }
    /*
        private Donation toDonation(Cursor cursor) {
            //Donation pojo = new Donation();
            //pojo.id = cursor.getInt(0);
            pojo.amount = cursor.getInt(1);
            pojo.method = cursor.getString(2);
            return pojo;
        }
    */


    public void reset() {
        database.delete("donations", null, null);
    }
}

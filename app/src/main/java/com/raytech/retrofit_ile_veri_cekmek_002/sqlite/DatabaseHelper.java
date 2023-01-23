package com.raytech.retrofit_ile_veri_cekmek_002.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.raytech.retrofit_ile_veri_cekmek_002.model.Address;
import com.raytech.retrofit_ile_veri_cekmek_002.model.Company;
import com.raytech.retrofit_ile_veri_cekmek_002.model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users_db";
    private static final int DATABASE_VERSION = 1;

    //tablo adları
    private static final String TABLE_NAME = "users";
    private static final String TABLE_ADDRESS = "address";
    private static final String TABLE_COMPANY = "company";

    //user modeli için sütunlar
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_WEBSITE = "website";

    private static final String COLUMN_ADDRESS_ID = "address_id";
    private static final String COLUMN_COMPANY_ID = "company_id";

    //adress modeli için sütunlar
    private static final String COLUMN_ADDRESS_STREET = "street";
    private static final String COLUMN_ADDRESS_SUITE = "suite";
    private static final String COLUMN_ADDRESS_CITY = "city";
    private static final String COLUMN_ADDRESS_ZIPCODE = "zipcode";

    //company modeli için sütunlar
    private static final String COLUMN_COMPANY_NAME = "name";
    private static final String COLUMN_COMPANY_CATCHPHRASE = "catchPhrase";
    private static final String COLUMN_COMPANY_BS = "bs";


    private static final String CREATE_TABLE_USERS =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_USERNAME + " TEXT,"
                    + COLUMN_EMAIL + " TEXT,"
                    + COLUMN_PHONE + " TEXT,"
                    + COLUMN_WEBSITE + " TEXT,"
                    + "FOREIGN KEY (" + COLUMN_ADDRESS_ID + ") REFERENCES " + TABLE_ADDRESS + "(" + COLUMN_ID + "),"
                    + "FOREIGN KEY (" + COLUMN_COMPANY_ID + ") REFERENCES " + TABLE_COMPANY + "(" + COLUMN_ID + ")"
                    + ")";

    private static final String CREATE_TABLE_ADDRESS =
            "CREATE TABLE " + TABLE_ADDRESS + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY,"
                    + COLUMN_ADDRESS_STREET + " TEXT,"
                    + COLUMN_ADDRESS_SUITE + " TEXT,"
                    + COLUMN_ADDRESS_CITY + " TEXT,"
                    + COLUMN_ADDRESS_ZIPCODE + " TEXT"
                    + ")";

    private static final String CREATE_TABLE_COMPANY =
            "CREATE TABLE " + TABLE_COMPANY + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY,"
                    + COLUMN_COMPANY_NAME + " TEXT,"
                    + COLUMN_COMPANY_CATCHPHRASE + " TEXT,"
                    + COLUMN_COMPANY_BS + " TEXT"
                    + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_ADDRESS);
        db.execSQL(CREATE_TABLE_COMPANY);
        onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDRESS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPANY);
        //tabloları tekrar oluştur
        onCreate(db);
    }

    public void addUsersWithAddressAndCompany(List<User> users) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            for (User user : users) {
                //kullanıcıları ekle
                ContentValues userValues = new ContentValues();
                userValues.put(COLUMN_ID, user.getId());
                userValues.put(COLUMN_NAME, user.getName());
                userValues.put(COLUMN_USERNAME, user.getUsername());
                userValues.put(COLUMN_EMAIL, user.getEmail());
                userValues.put(COLUMN_PHONE, user.getPhone());
                userValues.put(COLUMN_WEBSITE, user.getWebsite());

                //adresi ekle
                Address address = user.getAddress();
                long addressId = addAddressToDatabase(address);
                userValues.put(COLUMN_ADDRESS_ID, addressId);

                // şirket ekle
                Company company = user.getCompany();
                long companyId = addCompanyToDatabase(company);
                userValues.put(COLUMN_COMPANY_ID, companyId);

                db.insert(TABLE_NAME, null, userValues);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
    }


    public long addAddressToDatabase(Address address) {
        SQLiteDatabase db = null;
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ADDRESS_STREET, address.getStreet());
        values.put(COLUMN_ADDRESS_SUITE, address.getSuite());
        values.put(COLUMN_ADDRESS_CITY, address.getCity());
        values.put(COLUMN_ADDRESS_ZIPCODE, address.getZipcode());
        long addressId = db.insert(TABLE_ADDRESS, null, values);

        return addressId;
    }


    public long addCompanyToDatabase(Company company) {

        SQLiteDatabase db = null;
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COMPANY_NAME, company.getName());
        values.put(COLUMN_COMPANY_CATCHPHRASE, company.getCatchPhrase());
        values.put(COLUMN_COMPANY_BS, company.getBs());
        long companyId = db.insert(TABLE_COMPANY, null, values);
        return companyId;

    }
}


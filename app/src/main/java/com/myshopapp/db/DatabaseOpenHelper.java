package com.myshopapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.myshopapp.entity.HomeBanner;
import com.myshopapp.entity.HomeDetails;
import com.myshopapp.entity.HomeView;

import java.sql.SQLException;

/**
 * Created by Administrator on 2015/10/21.
 */
public class DatabaseOpenHelper extends OrmLiteSqliteOpenHelper{
    private static DatabaseOpenHelper helper;
    private static String DB_NAME="=.db";
    private static int DB_VERSION=1;

    private  DatabaseOpenHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    public static DatabaseOpenHelper getInstance(Context context){
        synchronized (DatabaseOpenHelper.class){
            if (helper==null){
                synchronized (DatabaseOpenHelper.class){
                    if (helper==null){
                        helper=new DatabaseOpenHelper(context);
                    }
                }
            }
        }
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, HomeBanner.class);
            TableUtils.createTable(connectionSource, HomeView.class);
            TableUtils.createTable(connectionSource, HomeDetails.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Dao<HomeView,Integer> hmDao=null;
    public Dao<HomeView,Integer> getHmViewDao(){
        try {
            if (hmDao==null){
                hmDao=getDao(HomeView.class);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hmDao;
    }
    private Dao<HomeBanner,Integer> hbDao=null;
    public Dao<HomeBanner,Integer> getHbDao(){
        try {
            if (hbDao==null){
                hbDao=getDao(HomeBanner.class);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hbDao;
    }
    private Dao<HomeDetails,Integer> hdDao=null;
    public Dao<HomeDetails,Integer> getHdDao(){
        try {
            if (hdDao==null){
                hdDao=getDao(HomeDetails.class);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hdDao;
    }
    @Override
    public void close() {
        super.close();
        if (hmDao!=null){
            hmDao=null;
        }
        if (hdDao!=null){
            hdDao=null;
        }
        if (hbDao!=null){
            hbDao=null;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }
}

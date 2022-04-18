package dinhphuc.com.example.expensemanagement.dao;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.jetbrains.annotations.NotNull;

import dinhphuc.com.example.expensemanagement.entity.Chi;
import dinhphuc.com.example.expensemanagement.entity.LoaiChi;
import dinhphuc.com.example.expensemanagement.entity.LoaiThu;
import dinhphuc.com.example.expensemanagement.entity.Thu;
import dinhphuc.com.example.expensemanagement.entity.kehoach;
import dinhphuc.com.example.expensemanagement.entity.user;

@Database(entities = {LoaiChi.class, LoaiThu.class, Thu.class, Chi.class, kehoach.class, user.class} , version = 14)
public abstract class  AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract KehoachDao kehoachDao() ;
    public abstract LoaiChiDao loaiChiDao() ;
    public abstract LoaiThuDao loaiThuDao() ;
    public abstract ThuDao thuDao() ;
    public  abstract  ChiDao chiDao();
    private static  AppDatabase INSTANCE ;

    private static RoomDatabase.Callback    callback = new Callback() {
        @Override
        public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            new PopulateData(INSTANCE).execute() ;

        }
    };


    public  static  AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "personnal_db")
                        .fallbackToDestructiveMigration()
                        .addCallback(callback)
                        .build();
            }
        }
        return INSTANCE;
    }

    public static synchronized AppDatabase getINSTANCE (Context context){
        if (INSTANCE == null)
        {
            INSTANCE=Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"personal_db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public  static  class  PopulateData extends AsyncTask<Void, Void, Void> {

        private  LoaiChiDao loaiChiDao ;
        private  LoaiThuDao loaiThuDao ;
        private  ThuDao thuDao ;
        public PopulateData(AppDatabase db) {

            loaiChiDao = db.loaiChiDao() ;
            loaiThuDao = db.loaiThuDao() ;
            thuDao = db.thuDao() ;

        }

        @Override
        protected Void doInBackground(Void... voids) {
            String [] loaichis = new String [] {"Ăn Uống", "Mua sắm" , "Game"} ;
            for (String it: loaichis) {
                LoaiChi lc = new LoaiChi();
                lc.ten = it ;
                loaiChiDao.insert(lc);
            }
            String [] loaithus = new String [] {"Lương", "Thưởng"} ;
            for (String it: loaithus) {
                LoaiThu lt = new LoaiThu();
                lt.ten = it ;
                loaiThuDao.insert(lt);
            }
            Thu thu = new Thu();
            thu.ten = "Luong thang 1" ;
            thu.sotien = 10000000;
            thu.ltid = 2 ;
            thu.ghichu = "";
            thu.ngay = "";
            thuDao.insert(thu);


//            Chi chi = new Chi();
//            chi.ten = "Luong thang 1" ;
//            chi.sotien = 10000000;
//            chi.ltid = 2 ;
//            chi.ghichu = "";
//            thuDao.insert(thu);

            return null;
        }

    }
}





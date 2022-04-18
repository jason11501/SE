package dinhphuc.com.example.expensemanagement.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import dinhphuc.com.example.expensemanagement.LoginActivity;
import dinhphuc.com.example.expensemanagement.MainActivity;
import dinhphuc.com.example.expensemanagement.dao.AppDatabase;
import dinhphuc.com.example.expensemanagement.dao.LoaiChiDao;
import dinhphuc.com.example.expensemanagement.dao.LoaiChiDao;
import dinhphuc.com.example.expensemanagement.entity.LoaiChi;
import dinhphuc.com.example.expensemanagement.entity.LoaiChi;

public class LoaiChiRepository {
    private LoaiChiDao mLoaiChiDao ;
    private LiveData<List<LoaiChi>> mAllLoaiChi ;



    public LoaiChiRepository(Application application) {
        this.mLoaiChiDao = AppDatabase.getDatabase(application).loaiChiDao() ;
        mAllLoaiChi = mLoaiChiDao.finalAll(MainActivity.id) ;
    }

    public LiveData<List<LoaiChi>> getAllLoaiChi() {
        return mAllLoaiChi;
    }

    public void insert (LoaiChi loaiChi) {
        new InsertAsyncTask(mLoaiChiDao).execute(loaiChi) ;
    }
    public void delete (LoaiChi loaiChi) {
        new DeleteAsyncTask(mLoaiChiDao).execute(loaiChi) ;
    }

    public void update (LoaiChi loaiChi) {
        new UpdateAsyncTask(mLoaiChiDao).execute(loaiChi) ;
    }



    class  InsertAsyncTask  extends AsyncTask<LoaiChi , Void , Void> {
        private LoaiChiDao mLoaiChiDao ;
        public InsertAsyncTask(LoaiChiDao loaiChiDao) {
            this.mLoaiChiDao = loaiChiDao ;

        }

        @Override
        protected Void doInBackground(LoaiChi... loaiChis) {
            mLoaiChiDao.insert(loaiChis[0]);
            return null;
        }
    }

    class  DeleteAsyncTask  extends AsyncTask<LoaiChi , Void , Void> {
        private LoaiChiDao mLoaiChiDao ;
        public DeleteAsyncTask(LoaiChiDao loaiChiDao) {
            this.mLoaiChiDao = loaiChiDao ;

        }

        @Override
        protected Void doInBackground(LoaiChi... loaiChis) {
            mLoaiChiDao.delete(loaiChis[0]);
            return null;
        }
    }

    class  UpdateAsyncTask  extends AsyncTask<LoaiChi , Void , Void> {
        private LoaiChiDao mLoaiChiDao ;
        public UpdateAsyncTask(LoaiChiDao loaiChiDao) {
            this.mLoaiChiDao = loaiChiDao ;

        }

        @Override
        protected Void doInBackground(LoaiChi... loaiChis) {
            mLoaiChiDao.update(loaiChis[0]);
            return null;
        }
    }
}

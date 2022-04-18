package dinhphuc.com.example.expensemanagement.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import dinhphuc.com.example.expensemanagement.LoginActivity;
import dinhphuc.com.example.expensemanagement.MainActivity;
import dinhphuc.com.example.expensemanagement.dao.AppDatabase;
import dinhphuc.com.example.expensemanagement.dao.ChiDao;
import dinhphuc.com.example.expensemanagement.entity.Chi;

public class ChiRepository {
    private ChiDao mChiDao;
    private LiveData<List<Chi>> mAllChi;


    public ChiRepository(Application application) {
        this.mChiDao = AppDatabase.getDatabase(application).chiDao();
        mAllChi = mChiDao.finalAll(MainActivity.id);
    }

    public LiveData<List<Chi>> getAllChi() {
        return mAllChi;
    }

    public void insert(Chi Chi) {
        new ChiRepository.InsertAsyncTask(mChiDao).execute(Chi);
    }
    public void delete(Chi Chi) {
        new ChiRepository.DeleteAsyncTask(mChiDao).execute(Chi);
    }
    public void update(Chi Chi) {
        new ChiRepository.UpdateAsyncTask(mChiDao).execute(Chi);
    }


    class InsertAsyncTask extends AsyncTask<Chi, Void, Void> {
        private ChiDao mChiDao;

        public InsertAsyncTask(ChiDao ChiDao) {
            this.mChiDao = ChiDao;

        }


        @Override
        protected Void doInBackground(Chi... Chis) {
            mChiDao.insert(Chis[0]);
            return null;
        }

    }

    class DeleteAsyncTask extends AsyncTask<Chi, Void, Void> {
        private ChiDao mChiDao;

        public DeleteAsyncTask(ChiDao ChiDao) {
            this.mChiDao = ChiDao;

        }


        @Override
        protected Void doInBackground(Chi... Chis) {
            mChiDao.delete(Chis[0]);
            return null;
        }


    }

    class UpdateAsyncTask extends AsyncTask<Chi, Void, Void> {
        private ChiDao mChiDao;

        public UpdateAsyncTask(ChiDao ChiDao) {
            this.mChiDao = ChiDao;

        }


        @Override
        protected Void doInBackground(Chi... Chis) {
            mChiDao.update(Chis[0]);
            return null;
        }
    }
}

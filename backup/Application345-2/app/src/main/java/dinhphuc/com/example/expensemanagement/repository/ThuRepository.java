package dinhphuc.com.example.expensemanagement.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import dinhphuc.com.example.expensemanagement.LoginActivity;
import dinhphuc.com.example.expensemanagement.MainActivity;
import dinhphuc.com.example.expensemanagement.dao.AppDatabase;
import dinhphuc.com.example.expensemanagement.dao.ThuDao;
import dinhphuc.com.example.expensemanagement.entity.LoaiThu;
import dinhphuc.com.example.expensemanagement.entity.Thu;

public class ThuRepository {
    private ThuDao mThuDao;
    private LiveData<List<Thu>> mAllThu;


    public ThuRepository(Application application) {
        this.mThuDao = AppDatabase.getDatabase(application).thuDao();
        mAllThu = mThuDao.finalAll(MainActivity.id);
    }

    public LiveData<List<Thu>> getAllThu() {
        return mAllThu;
    }

    public void insert(Thu Thu) {
        new ThuRepository.InsertAsyncTask(mThuDao).execute(Thu);
    }
    public void delete(Thu Thu) {
        new ThuRepository.DeleteAsyncTask(mThuDao).execute(Thu);
    }
    public void update(Thu Thu) {
        new ThuRepository.UpdateAsyncTask(mThuDao).execute(Thu);
    }


    class InsertAsyncTask extends AsyncTask<Thu, Void, Void> {
        private ThuDao mThuDao;

        public InsertAsyncTask(ThuDao ThuDao) {
            this.mThuDao = ThuDao;

        }


        @Override
        protected Void doInBackground(Thu... Thus) {
            mThuDao.insert(Thus[0]);
            return null;
        }

    }

    class DeleteAsyncTask extends AsyncTask<Thu, Void, Void> {
        private ThuDao mThuDao;

        public DeleteAsyncTask(ThuDao ThuDao) {
            this.mThuDao = ThuDao;

        }


        @Override
        protected Void doInBackground(Thu... Thus) {
            mThuDao.delete(Thus[0]);
            return null;
        }


    }

    class UpdateAsyncTask extends AsyncTask<Thu, Void, Void> {
        private ThuDao mThuDao;

        public UpdateAsyncTask(ThuDao ThuDao) {
            this.mThuDao = ThuDao;

        }


        @Override
        protected Void doInBackground(Thu... Thus) {
            mThuDao.update(Thus[0]);
            return null;
        }
    }
}

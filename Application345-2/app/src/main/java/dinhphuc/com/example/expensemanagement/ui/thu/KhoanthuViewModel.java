package dinhphuc.com.example.expensemanagement.ui.thu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import dinhphuc.com.example.expensemanagement.entity.LoaiThu;
import dinhphuc.com.example.expensemanagement.entity.Thu;
import dinhphuc.com.example.expensemanagement.repository.LoaiThuRepository;
import dinhphuc.com.example.expensemanagement.repository.ThuRepository;

public class KhoanthuViewModel extends AndroidViewModel {
    private LoaiThuRepository mLoaiThuRepository;
    private ThuRepository mThuRepository  ;
    private LiveData<List<Thu>> mAllThu ;
    private LiveData<List<LoaiThu>> mAllLoaiThu ;


    public KhoanthuViewModel(@NonNull Application application) {
        super(application);
        mThuRepository = new ThuRepository(application) ;
        mAllThu = mThuRepository.getAllThu()  ;
        mLoaiThuRepository = new LoaiThuRepository(application) ;
        mAllLoaiThu = mLoaiThuRepository.getAllLoaiThu()  ;
    }

    public LiveData<List<LoaiThu>> getAllLoaiThu() {
        return mAllLoaiThu;
    }

    public LiveData<List<Thu>> getmAllThu() {
        return mAllThu;
    }

    public void insert(Thu thu) {
        mThuRepository.insert(thu) ;
    }
    public void delete (Thu thu){
        mThuRepository.delete(thu);
    }

    public void update (Thu thu){
        mThuRepository.update(thu);
    }


}
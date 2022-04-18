package dinhphuc.com.example.expensemanagement.ui.thu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import dinhphuc.com.example.expensemanagement.entity.LoaiThu;
import dinhphuc.com.example.expensemanagement.repository.LoaiThuRepository;

public class LoaithuViewModel extends AndroidViewModel {
    private LoaiThuRepository mLoaiThuRepository  ;
    private LiveData<List<LoaiThu>> mAllLoaiThu ;

    public LoaithuViewModel(@NonNull Application application) {
        super(application);
        mLoaiThuRepository = new LoaiThuRepository(application) ;
        mAllLoaiThu = mLoaiThuRepository.getAllLoaiThu()  ;
    }

    public LiveData<List<LoaiThu>> getAllLoaiThu() {
        return mAllLoaiThu;
    }

    public void insert(LoaiThu loaiThu) {
        mLoaiThuRepository.insert(loaiThu) ;
    }
    public void delete (LoaiThu loaiThu){
        mLoaiThuRepository.delete(loaiThu);
    }

    public void update (LoaiThu loaiThu){
        mLoaiThuRepository.update(loaiThu);
    }

}
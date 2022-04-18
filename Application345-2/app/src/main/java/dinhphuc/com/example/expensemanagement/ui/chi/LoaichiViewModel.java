package dinhphuc.com.example.expensemanagement.ui.chi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import dinhphuc.com.example.expensemanagement.entity.LoaiChi;
import dinhphuc.com.example.expensemanagement.entity.LoaiChi;
import dinhphuc.com.example.expensemanagement.repository.LoaiChiRepository;

public class LoaichiViewModel extends AndroidViewModel {
    private LoaiChiRepository mLoaiChiRepository  ;
    private LiveData<List<LoaiChi>> mAllLoaiChi ;

    public LoaichiViewModel(@NonNull Application application) {
        super(application);
        mLoaiChiRepository = new LoaiChiRepository(application) ;
        mAllLoaiChi = mLoaiChiRepository.getAllLoaiChi()  ;
    }

    public LiveData<List<LoaiChi>> getmAllLoaiChi() {
        return mAllLoaiChi;
    }

    public void insert(LoaiChi loaiChi) {
        mLoaiChiRepository.insert(loaiChi) ;
    }
    public void delete (LoaiChi loaiChi){
        mLoaiChiRepository.delete(loaiChi);
    }

    public void update (LoaiChi loaiChi){
        mLoaiChiRepository.update(loaiChi);
    }
}
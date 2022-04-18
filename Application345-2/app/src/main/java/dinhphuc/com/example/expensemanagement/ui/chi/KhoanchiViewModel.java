package dinhphuc.com.example.expensemanagement.ui.chi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import dinhphuc.com.example.expensemanagement.entity.LoaiChi;
import dinhphuc.com.example.expensemanagement.entity.Chi;
import dinhphuc.com.example.expensemanagement.repository.LoaiChiRepository;
import dinhphuc.com.example.expensemanagement.repository.ChiRepository;

public class KhoanchiViewModel extends AndroidViewModel {
    private LoaiChiRepository mLoaiChiRepository;
    private ChiRepository mChiRepository  ;
    private LiveData<List<Chi>> mAllChi ;
    private LiveData<List<LoaiChi>> mAllLoaiChi ;


    public KhoanchiViewModel(@NonNull Application application) {
        super(application);
        mChiRepository = new ChiRepository(application) ;
        mAllChi = mChiRepository.getAllChi()  ;
        mLoaiChiRepository = new LoaiChiRepository(application) ;
        mAllLoaiChi = mLoaiChiRepository.getAllLoaiChi()  ;
    }

    public LiveData<List<LoaiChi>> getAllLoaiChi() {
        return mAllLoaiChi;
    }

    public LiveData<List<Chi>> getAllChi() {
        return mAllChi;
    }

    public void insert(Chi Chi) {
        mChiRepository.insert(Chi) ;
    }
    public void delete (Chi Chi){
        mChiRepository.delete(Chi);
    }

    public void update (Chi Chi){
        mChiRepository.update(Chi);
    }


}
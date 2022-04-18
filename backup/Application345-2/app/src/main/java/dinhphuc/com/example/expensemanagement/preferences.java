package dinhphuc.com.example.expensemanagement;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.zip.Inflater;

import dinhphuc.com.example.expensemanagement.dao.AppDatabase;
import dinhphuc.com.example.expensemanagement.dialog.changepwd_dialog;
import dinhphuc.com.example.expensemanagement.dialog.changeusr_dialog;
import dinhphuc.com.example.expensemanagement.entity.user;

public class preferences extends PreferenceActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prferences);

        Preference myPref = (Preference) findPreference("ChangePwd");
        myPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                List<user> Lu = AppDatabase.getINSTANCE(preferences.this).userDao().getUserById(MainActivity.id);
                user u = Lu.get(0);

                changepwd_dialog changepwdDialog=new changepwd_dialog(preferences.this,u);
                changepwdDialog.show();
                // open browser or intent here
                return true;
            }
        });

        Preference yourPref = (Preference) findPreference("ChangeUsr");
        yourPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                List<user> Lu = AppDatabase.getINSTANCE(preferences.this).userDao().getUserById(MainActivity.id);
                user u = Lu.get(0);

                changeusr_dialog changeusrDialog=new changeusr_dialog(preferences.this,u);
                changeusrDialog.show();
                // open browser or intent here
                return true;
            }
        });

        Preference reset = (Preference) findPreference("reset");
        reset.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                AlertDialog.Builder builder= new AlertDialog.Builder(preferences.this);
                builder.setTitle("Bạn có chắc muốn xóa toàn bộ dữ liệu không");
                builder.setMessage("Hãy đưa ra lựa chọn");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppDatabase.getINSTANCE(preferences.this).thuDao().deleteAll();
                        AppDatabase.getINSTANCE(preferences.this).chiDao().deleteAll();
                        AppDatabase.getINSTANCE(preferences.this).loaiChiDao().deleteAll();
                        AppDatabase.getINSTANCE(preferences.this).loaiThuDao().deleteAll();
                        AppDatabase.getINSTANCE(preferences.this).kehoachDao().deleteAll();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                return true;
            }
        });

    }
}

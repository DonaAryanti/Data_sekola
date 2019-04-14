package com.example.database.ui.main;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.database.Entity.AppDatabase;
import com.example.database.Entity.DataSekolah;
import com.example.database.R;

public class MainActivity extends AppCompatActivity {

    private EditText edt1, edt2, edt3, edt4;
    private Button btn_1, btn_2;
    private String st_1, st_2, st_3, st_4;
    AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1 = findViewById(R.id.et_jml_siswa);
        edt2 = findViewById(R.id.et_jml_guru);
        edt3 = findViewById(R.id.et_nama_sekolah);
        edt4 = findViewById(R.id.et_alamat_sekolah);
        btn_1 = findViewById(R.id.btn_tambahkan);
        btn_2 = findViewById(R.id.btn_lihat);
        appDatabase = AppDatabase.iniDb(getApplicationContext());
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input();
                Intent x = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(x);
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(x);
            }
        });
    }
    public void input(){
        st_1 = edt1.getText().toString();
        st_2 = edt2.getText().toString();
        st_3 = edt3.getText().toString();
        st_4 = edt4.getText().toString();
        final DataSekolah dataSekolah = new DataSekolah();
        dataSekolah.setJml_siswa(st_1);
        dataSekolah.setJml_guru(st_2);
        dataSekolah.setNama_sekolah(st_3);
        dataSekolah.setAlamat(st_4);
        new InsertData(appDatabase, dataSekolah).execute();
    }
    class InsertData extends AsyncTask<Void, Void, Long> {
        private AppDatabase database;
        private DataSekolah dataSekolah;

        public InsertData(AppDatabase database, DataSekolah dataSekolah) {
            this.database = database;
            this.dataSekolah = dataSekolah;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return database.dao().insertData(dataSekolah);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            Toast.makeText(getApplicationContext(), "sukses", Toast.LENGTH_SHORT).show();

        }

    }

}

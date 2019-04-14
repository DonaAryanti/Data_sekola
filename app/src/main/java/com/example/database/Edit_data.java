package com.example.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.database.Adapter.Adapter;
import com.example.database.Entity.AppDatabase;
import com.example.database.Entity.DataSekolah;
import com.example.database.Presenter.Presenter;
import com.example.database.ui.main.Main2Activity;
import com.example.database.ui.main.MainContact;

public class Edit_data extends AppCompatActivity  implements MainContact.view {
    private AppDatabase appDatabase;
    private Presenter presenter;
    private Adapter adapter;
    private EditText edt1, edt2, edt3, edt4;
    private String st_1, st_2, st_3, st_4;
    private int id;
    private Button btn1;
    private boolean edit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        edt1 = findViewById(R.id.et_jml_siswa);
        edt2 = findViewById(R.id.et_jml_guru);
        edt3 = findViewById(R.id.et_nama_sekolah);
        edt4 = findViewById(R.id.et_alamat_sekolah);
        btn1 = findViewById(R.id.btn_tambahkan);
        presenter = new Presenter(this);
        appDatabase = AppDatabase.iniDb(getApplicationContext());
        st_1 = getIntent().getStringExtra("jml_siswa");
        st_2 = getIntent().getStringExtra("jml_guru");
        st_3 = getIntent().getStringExtra("nama");
        st_4 = getIntent().getStringExtra("alamat");
        id = getIntent().getIntExtra("id", 99);
        edt1.setText(st_1);
        edt2.setText(st_2);
        edt3.setText(st_3);
        edt4.setText(st_4);
        btn1.setOnClickListener(this);
    }

    @Override
    public void resetForm() {
        edt1.setText("");
        edt2.setText("");
        edt3.setText("");
        edt4.setText("");
        btn1.setText("Tambahkan");
    }

    @Override
    public void sukses() {
        Toast.makeText(getApplicationContext(), "sukses", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), Main2Activity.class));
    }

    @Override
    public void editData(DataSekolah item) {
        edt1.setText(item.getJml_siswa());
        edt2.setText(item.getJml_guru());
        edt3.setText(item.getNama_sekolah());
        edt4.setText(item.getAlamat());
        edit = true;
        btn1.setText("Update");
    }

    @Override
    public void onClick(View v) {
        String J_S, J_G, N_S, AL;
        J_S = edt1.getText().toString();
        J_G = edt2.getText().toString();
        N_S = edt3.getText().toString();
        AL = edt4.getText().toString();
        if(v ==  btn1){
            if(J_S.equals("") || J_G.equals("") || N_S.equals("") || AL.equals("")) {
                Toast.makeText(this, " isi Mohon isi semua data", Toast.LENGTH_SHORT).show();
            } else { presenter.editData(J_S, J_G, N_S, AL, id, appDatabase);
                edit = false; }
            resetForm();
        }
    }
}


package com.example.database.ui.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.database.Adapter.Adapter;
import com.example.database.Entity.AppDatabase;
import com.example.database.Entity.DataSekolah;
import com.example.database.Presenter.Presenter;
import com.example.database.R;

import java.util.List;

public class Main2Activity extends AppCompatActivity implements MainContact.hapus {
    private AppDatabase appDatabase;
    private Adapter adapter;
    private Presenter presenter;
    View view;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        presenter = new Presenter(this);
        recyclerView = findViewById(R.id.rc_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        appDatabase = AppDatabase.iniDb(getApplicationContext());

        readData(appDatabase);

    }
    //public void getData(List<DataSekolah> list) {
    //  adapter = new Adapter(getApplicationContext(), list);
    //  recyclerView.setAdapter(adapter);
    //}

    public void readData(AppDatabase database) {
        List list;
        list = database.dao().getData();
        //view.getData(list);
        adapter = new Adapter(getApplicationContext(), list, this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void sukses() {
        Toast.makeText(getApplicationContext(), "Data Berhasil di hapus", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), Main2Activity.class));
    }

    @Override
    public void deleteData(final DataSekolah item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Menghapus Data")
                .setMessage("Anda yakin ingin menghapus data ini?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // resetForm();
                        presenter.deleteData(item, appDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}

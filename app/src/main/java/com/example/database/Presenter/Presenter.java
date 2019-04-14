package com.example.database.Presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.database.Entity.AppDatabase;
import com.example.database.Entity.DataSekolah;
import com.example.database.ui.main.MainContact;

public class Presenter implements MainContact.datapresenter {
    MainContact.view view;
    MainContact.hapus viewH;
    public Presenter(MainContact.view view) {
        this.view = view;
    }

    public Presenter(MainContact.hapus viewH) {
        this.viewH = viewH;
    }

    class EditData extends AsyncTask<Void, Void, Integer> {
        private AppDatabase database;
        private DataSekolah dataSekolah;
        public EditData(AppDatabase database, DataSekolah dataSekolah) {
            this.database = database;
            this.dataSekolah = dataSekolah; }

        @Override
        protected Integer doInBackground(Void... voids) {
            return database.dao().updateData(dataSekolah); }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.d("integer db", "onPostExecute: " + integer);
            view.sukses(); }}

    @Override
    public void editData(String jml_siswa, String jml_guru, String nama_sekolah, String alamat, int id, AppDatabase database) {
        final DataSekolah dataSekolah = new DataSekolah();
        dataSekolah.setJml_siswa(jml_siswa);
        dataSekolah.setJml_guru(jml_guru);
        dataSekolah.setNama_sekolah(nama_sekolah);
        dataSekolah.setAlamat(alamat);
        dataSekolah.setId(id);
        new EditData(database, dataSekolah).execute(); }


    class DeleteData extends AsyncTask<Void, Void, Void>{
        private AppDatabase database;
        private DataSekolah dataSekolah;
        Context context;
        public DeleteData(AppDatabase database, DataSekolah dataSekolah) {
            this.database = database;
            this.dataSekolah = dataSekolah; }

        @Override
        protected Void doInBackground(Void... voids) {
            database.dao().deleteData(dataSekolah);
            return  null; }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            viewH.sukses(); }}

    @Override
    public void deleteData(DataSekolah dataSekolah, AppDatabase database) {
        new DeleteData(database,dataSekolah).execute();
    }
}


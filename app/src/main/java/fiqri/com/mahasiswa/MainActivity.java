package fiqri.com.mahasiswa;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fiqri.com.mahasiswa.adapter.MyAdapter;
import fiqri.com.mahasiswa.model.DataMahasiswa;
import fiqri.com.mahasiswa.model.response.GetMahasiswa;
import fiqri.com.mahasiswa.model.response.InsertMahasiswa;
import fiqri.com.mahasiswa.network.ConfigRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_mahasiswa)
    RecyclerView rvMahasiswa;
    private EditText edtNim, edtNama, edtJurusan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ProgressDialog dialog =
                ProgressDialog.show(this, "", "Loading", false);


        ConfigRetrofit.service.read().enqueue(new Callback<GetMahasiswa>() {
            @Override
            public void onResponse(Call<GetMahasiswa> call, Response<GetMahasiswa> response) {
                dialog.dismiss();
                int status = response.body().getStatus();
                Log.d("Status", "status" + response.body().getStatus());

                if (status == 1) {
                    List<DataMahasiswa> data = response.body().getDatanya();
                    MyAdapter adapter = new MyAdapter(data);
                    rvMahasiswa.setAdapter(adapter);
                    rvMahasiswa.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                }
            }


            @Override
            public void onFailure(Call<GetMahasiswa> call, Throwable t) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this, "Tidak Ada Koneksi",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogInflater();
            }
        });
    }


    private void dialogInflater() {

        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.inputan_mahasiswa, null);

        edtNim = v.findViewById(R.id.edt_nim_inputan);
        edtNama = v.findViewById(R.id.edt_nama_inputan);
        edtJurusan = v.findViewById(R.id.edt_jurusan_inputan);

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setView(v);
        dialog.setCancelable(false);
        dialog.setTitle("Masukan Data");


        dialog.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialogInterface, int i) {

                String nim = edtNim.getText().toString().trim();
                String nama = edtNama.getText().toString().trim();
                String jurusan = edtJurusan.getText().toString().trim();

                ConfigRetrofit.service.insert(nim, nama, jurusan)
                        .enqueue(new Callback<InsertMahasiswa>() {
                            @Override
                            public void onResponse(Call<InsertMahasiswa> call, Response<InsertMahasiswa> response) {
                                String msg = response.body().getPesan();
                                int status = response.body().getStatus();

                                if (status == 1) {
                                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                                    dialogInterface.dismiss();
                                    recreate();

                                } else {
                                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                                }
                            }


                            @Override
                            public void onFailure(Call<InsertMahasiswa> call, Throwable t) {
                                Log.d("Server Error", t.getMessage());
                            }
                        });
            }
        });


        dialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.show();

    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}

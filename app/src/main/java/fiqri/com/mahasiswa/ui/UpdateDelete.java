package fiqri.com.mahasiswa.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fiqri.com.mahasiswa.MainActivity;
import fiqri.com.mahasiswa.R;
import fiqri.com.mahasiswa.model.response.InsertMahasiswa;
import fiqri.com.mahasiswa.network.ConfigRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDelete extends AppCompatActivity {

    @BindView(R.id.edt_nim)
    EditText edtNim;
    @BindView(R.id.edt_nama)
    EditText edtNama;
    @BindView(R.id.edt_jurusan)
    EditText edtJurusan;
    @BindView(R.id.btn_update)
    Button btnUpdate;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        ButterKnife.bind(this);

        id = getIntent().getStringExtra("id");
        String nim = getIntent().getStringExtra("nim");
        String nama = getIntent().getStringExtra("nama");
        String jurusan = getIntent().getStringExtra("jurusan");

        edtNim.setText(nim);
        edtNama.setText(nama);
        edtJurusan.setText(jurusan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @OnClick(R.id.btn_update)
    public void onViewClicked(View v) {
        updateData();
    }


    private void updateData() {

        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("Confirmation");
        dialog.setMessage("Are you sure for update this data?");
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yess",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialogInterface, int i) {

                        String nim = edtNim.getText().toString().trim();
                        String nama = edtNama.getText().toString().trim();
                        String jurusan = edtJurusan.getText().toString().trim();

                        ConfigRetrofit.service.update(id, nim, nama, jurusan)
                                .enqueue(new Callback<InsertMahasiswa>() {
                                    @Override
                                    public void onResponse(Call<InsertMahasiswa> call,
                                                           Response<InsertMahasiswa> response) {

                                        String msg = response.body().getPesan();
                                        int status = response.body().getStatus();

                                        if (status == 1) {
                                            Toast.makeText(UpdateDelete.this, msg,
                                                    Toast.LENGTH_SHORT).show();

                                            dialogInterface.dismiss();
                                            startActivity(new Intent(UpdateDelete.this,
                                                    MainActivity.class));

                                            finish();
                                        }
                                    }


                                    @Override
                                    public void onFailure(Call<InsertMahasiswa> call, Throwable t) {

                                    }
                                });
                    }
                });


        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        dialog.show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_delete, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int idMenu = item.getItemId();

        if (idMenu == R.id.btn_delete) {

            AlertDialog dialog = new AlertDialog
                    .Builder(UpdateDelete.this)
                    .create();

            dialog.setTitle("Confirmation");
            dialog.setMessage("Are you sure want delete this data ?");
            dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            ConfigRetrofit.service.delete(id)
                                    .enqueue(new Callback<InsertMahasiswa>() {
                                        @Override
                                        public void onResponse(Call<InsertMahasiswa> call,
                                                               Response<InsertMahasiswa> response) {

                                            String msg = response.body().getPesan();
                                            int status = response.body().getStatus();

                                            if (status == 1) {
                                                Toast.makeText(UpdateDelete.this, msg,
                                                        Toast.LENGTH_SHORT).show();

                                                startActivity(new Intent(UpdateDelete.this,
                                                        MainActivity.class));

                                                finish();

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<InsertMahasiswa> call, Throwable t) {

                                        }
                                    });

                            dialogInterface.dismiss();

                        }
                    });

            dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

            dialog.show();

        }

        return super.onOptionsItemSelected(item);

    }
}

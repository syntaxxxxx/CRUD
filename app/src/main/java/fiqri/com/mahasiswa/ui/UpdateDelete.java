package fiqri.com.mahasiswa.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fiqri.com.mahasiswa.R;

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
    public void onViewClicked() {


    }
}

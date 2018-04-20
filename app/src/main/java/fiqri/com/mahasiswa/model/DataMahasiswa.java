package fiqri.com.mahasiswa.model;

import com.google.gson.annotations.SerializedName;

public class DataMahasiswa {

	@SerializedName("mahasiswa_jurusan")
	private String mahasiswaJurusan;

	@SerializedName("mahasiswa_nama")
	private String mahasiswaNama;

	@SerializedName("mahasiswa_id")
	private String mahasiswaId;

	@SerializedName("mahasiswa_nim")
	private String mahasiswaNim;

	public void setMahasiswaJurusan(String mahasiswaJurusan){
		this.mahasiswaJurusan = mahasiswaJurusan;
	}

	public String getMahasiswaJurusan(){
		return mahasiswaJurusan;
	}

	public void setMahasiswaNama(String mahasiswaNama){
		this.mahasiswaNama = mahasiswaNama;
	}

	public String getMahasiswaNama(){
		return mahasiswaNama;
	}

	public void setMahasiswaId(String mahasiswaId){
		this.mahasiswaId = mahasiswaId;
	}

	public String getMahasiswaId(){
		return mahasiswaId;
	}

	public void setMahasiswaNim(String mahasiswaNim){
		this.mahasiswaNim = mahasiswaNim;
	}

	public String getMahasiswaNim(){
		return mahasiswaNim;
	}
}
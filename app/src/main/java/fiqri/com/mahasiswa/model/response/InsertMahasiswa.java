package fiqri.com.mahasiswa.model.response;

import com.google.gson.annotations.SerializedName;

public class InsertMahasiswa{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("status")
	private int status;

	public String getPesan(){
		return pesan;
	}

	public int getStatus(){
		return status;
	}
}
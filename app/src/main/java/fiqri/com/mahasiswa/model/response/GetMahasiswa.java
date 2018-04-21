package fiqri.com.mahasiswa.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import fiqri.com.mahasiswa.model.DataMahasiswa;

public class GetMahasiswa{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("datanya")
	private List<DataMahasiswa> datanya;

	@SerializedName("status")
	private int status;

	public String getPesan(){
		return pesan;
	}

	public List<DataMahasiswa> getDatanya(){
		return datanya;
	}

	public int getStatus(){
		return status;
	}
}
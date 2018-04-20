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

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setDatanya(List<DataMahasiswa> datanya){
		this.datanya = datanya;
	}

	public List<DataMahasiswa> getDatanya(){
		return datanya;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}
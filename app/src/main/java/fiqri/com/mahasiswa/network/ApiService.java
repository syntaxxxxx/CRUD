package fiqri.com.mahasiswa.network;

import fiqri.com.mahasiswa.model.response.GetMahasiswa;
import fiqri.com.mahasiswa.model.response.InsertMahasiswa;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    // sesuaikan dengan function yang ada di web service
    @GET("getMahasiswa")
    Call<GetMahasiswa> read();


    @FormUrlEncoded
    @POST("insertMahasiswa")
    Call<InsertMahasiswa> insert(@Field("nim") String nim,
                                 @Field("name") String nama,
                                 @Field("majors") String jurusan);

    @FormUrlEncoded
    @POST("updateMahasiswa")
    Call<InsertMahasiswa> update(@Field("id") String id,
                                 @Field("nim") String nim,
                                 @Field("name") String nama,
                                 @Field("majors") String jurusan);

    @FormUrlEncoded
    @POST("deleteMahasiswa")
    Call<InsertMahasiswa> delete(@Field("id") String id);
}

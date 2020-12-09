package project.android.ecommers.Repositry

import io.reactivex.Single
import project.android.ecommers.model.Data_Main_Data
import project.android.ecommers.model.LoginModel
import project.android.ecommers.model.ModelDetails
import project.android.ecommers.model.ProfileModel
import project.android.shoponlineproject.model.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("getDetails.php")
    @FormUrlEncoded
    fun Show_Main_List(@Field("code")code:String): Single<Data_Main_Data>

    @POST("login.php")
    @FormUrlEncoded
    fun Get_Login(@Field("mobile")mobile:String,@Field("password")password:String):Single<LoginModel>

    @POST("sigin.php")
    @FormUrlEncoded
    fun GetRegister(@Field("name")name:String,@Field("mobile")mobile:String,@Field("email")email:String,
                    @Field("password")password: String,@Field("Image")Image: String,@Field("Imagename")Imagename: String):Single<LoginModel>

    @POST("User_info.php")
    @FormUrlEncoded
    fun GetUserInfo(@Field("user_id")user_id:String):Single<ProfileModel>

    @POST(" getslider.php")
    @FormUrlEncoded
    fun Get_Details(@Field("idpost")idpost:String):Single<ModelDetails>

    @POST("Get_priceCart.php")
    @FormUrlEncoded
    fun Get_priceCart(@Field("user")user:String):Single<Cost>

    @POST("Get_Record_Cart.php")
    @FormUrlEncoded
    fun Get_Cart(@Field("user")user:String):Single<Model_Cart>

    @POST("Addcart.php")
    @FormUrlEncoded
    fun ADD_Cart(@Field("product")product:String,@Field("user")user:String,@Field("count")count:String,@Field("check")check:String):Single<ModelAddCart>

    @POST("GetListComment.php")
    @FormUrlEncoded
    fun Get_Comment(@Field("idproduct")idproduct:String): Single<Comment_List>
    @POST("List_Comment.php")
    @FormUrlEncoded
    fun Add_Comment(@Field("user")user:String,@Field("username")username:String,@Field("comment")comment:String,@Field("idproduct")idproduct:String): Single<StatusComment>

    @POST("del_cart.php")
    @FormUrlEncoded
    fun Del_Cart(@Field("idcart")idcart:String):Single<Del_Cart>
    companion object {
        operator fun invoke(): ApiService {
            val Api = Retrofit.Builder().baseUrl("http://amirhusseindeveloper.ir/project/shop/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()



            return Api.create(ApiService::class.java)
        }

    }
}
package project.android.shoponlineproject.view.actvity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.HandlerCompat.postDelayed
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import de.hdodenhof.circleimageview.CircleImageView
import design.android.navigationdrawer.util.SetIntent
import design.android.navigationdrawer.util.SetIntentwithoneextrastring
import design.android.navigationdrawer.util.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_one.*
import kotlinx.android.synthetic.main.navigationdrawer.*
import project.android.ecommers.Repositry.App
import project.android.ecommers.Repositry.Factory
import project.android.ecommers.view.Adabter.AdabterMain
import project.android.ecommers.view.Slider.GlideImageLoadingService
import project.android.ecommers.view.Slider.MainSliderAdapter
import project.android.shoponlineproject.R
import project.android.shoponlineproject.dataBase.AppDatabase
import project.android.shoponlineproject.util.ClickIntent
import project.android.shoponlineproject.util.LogoutLogin
import project.android.shoponlineproject.util.SaveDate
import project.android.shoponlineproject.view.viewmodel.HomeViewModel
import ss.com.bannerslider.Slider


class MainActivity : AppCompatActivity() , ClickIntent,NavigationView.OnNavigationItemSelectedListener{
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeViewModel2: HomeViewModel
   lateinit var name:String





    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigationdrawer)
        setSupportActionBar(toolbar)
        toolbar.title = "NavigiationDrawer"
        setUpNavigationDrawer()

        setTheradlider()
        homeViewModel = ViewModelProvider(this, Factory(App())).get(HomeViewModel::class.java)
        homeViewModel._Context=this


        name=LogoutLogin.GetShared_name(this)







       show()



        make_list_home()
        make_list_fake(recyclerView2,"859")
        make_list_fake(recyclerView3,"123")







    }
    fun setTheradlider() {
        Thread(Runnable {

            Slider.init(GlideImageLoadingService(this))
            banner_slider1.setAdapter(MainSliderAdapter())
            banner_slider1.setSelectedSlide(2)


        }).start()
    }

    fun show(){
        val navigationView =
            findViewById<View>(R.id.navigation_view) as NavigationView

                val hView = navigation_view.getHeaderView(0)
        val nav_user = hView.findViewById(R.id.nav_header_name) as TextView
        val nav_email = hView.findViewById(R.id.textView) as TextView
        val nav_image = hView.findViewById(R.id.nav_header_avater) as CircleImageView

        var uri:String

        uri=SaveDate.GetShared_Image(this)



        Glide
            .with(this)
            .load(uri)
            .placeholder(R.drawable.place_holder)
            .error(R.drawable.place_holder)
            .into(nav_image);
            nav_user.text= SaveDate.GetShared_name(this).toString()
            nav_email.text= SaveDate.GetShared_email(this).toString()

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        return true
    }
    fun setUpNavigationDrawer(){
        var toggel= ActionBarDrawerToggle(this,drawer_layout,toolbar,
            R.string.open,
            R.string.close
        )

        navigation_view.setNavigationItemSelectedListener(this)
        drawer_layout.addDrawerListener(toggel)
        toggel.syncState()

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.account -> setIntent()

            R.id.search -> toast("search")
            R.id.shop -> toast("shop")


        }
        return super.onOptionsItemSelected(item)
    }


    @SuppressLint("WrongConstant")
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.nav_home ->toast("Inbox")
            R.id.nav_Login ->setIntent()
            R.id.nav_sendEmail ->toast("sendEmail")
            R.id.nav_draft ->toast("Draft")


        }

        drawer_layout.closeDrawer(Gravity.START)
        return  true
    }
    fun setIntent(){
        if(name.equals("login")){
            SetIntent(LoginActivity::class.java)
            LogoutLogin.SetSharedUser(this,"profile")
        }else if(name.equals("profile")){
            SetIntent(ProfileActivity::class.java)
            LogoutLogin.SetSharedUser(this,"profile")
        }else{
            SetIntent(LoginActivity::class.java)
            LogoutLogin.SetSharedUser(this,"profile")
        }
    }

    @SuppressLint("WrongConstant")
    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(Gravity.START)){
            drawer_layout.closeDrawer(Gravity.START)
        }else{
            super.onBackPressed()
        }

    }


    fun make_list_home() {


        //"53"

            homeViewModel.Get_List_Main("").observe(this, Observer { list ->




                AppDatabase.getInstance(applicationContext)!!.userDao()!!.insertAll(list.Details)
                AppDatabase.getInstance(applicationContext)!!.userDao()!!.Updata(list.Details)




                        recyclerView.also {


                            it.layoutManager = LinearLayoutManager(
                                this,
                                LinearLayoutManager.HORIZONTAL, false
                            )

                            var adapter= AdabterMain(AppDatabase.getInstance(this)!!.userDao()!!.all,this,this)
                            it.setHasFixedSize(true);
                            it.setItemViewCacheSize(20);
                            it.adapter = adapter


                        }

                    })



































    }

    fun make_list_fake(view:RecyclerView,code:String) {

        homeViewModel2= ViewModelProvider(this).get(HomeViewModel::class.java)

        homeViewModel2.Get_List_Main(code).observe(this, Observer { list ->

            view.also {
                it.layoutManager = LinearLayoutManager(
                    this,
                    LinearLayoutManager.HORIZONTAL, false
                )

                var adapter= AdabterMain(list!!.Details,this,this)
                it.adapter = adapter

            }
        })

    }




    override fun ClickItem(id: String) {

        SetIntentwithoneextrastring(DetailActivity::class.java,"id",id)
    }

}





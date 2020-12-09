package design.android.navigationdrawer.util

import android.content.Context
import android.content.Intent
import android.widget.Toast

fun Context.toast(message:CharSequence){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}
fun <T : Any> Context.SetIntent(cs: Class<T>) {
    this.startActivity(Intent(this, cs))
}
fun <T : Any> Context.SetIntentwithoneextrastring(cs: Class<T>,key:String,text:String) {

    var intent= Intent(this,cs)
    intent.putExtra(key,text)
    this.startActivity(intent)

}


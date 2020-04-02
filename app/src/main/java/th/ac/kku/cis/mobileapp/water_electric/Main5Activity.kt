package th.ac.kku.cis.mobileapp.water_electric

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main5.*

class Main5Activity : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        mDatabase = FirebaseDatabase.getInstance().reference
        mDatabase.orderByKey().addListenerForSingleValueEvent(itemListener)
    }
    var itemListener: ValueEventListener = object : ValueEventListener {

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // call function
            addDataToList(dataSnapshot.child("bill"))
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Getting Item failed, display log a message
            Log.w("MainActivity", "loadItem:onCancelled", databaseError.toException())
        }
    }
    fun addDataToList(dataSnapshot: DataSnapshot) {
        val room = getIntent().getExtras()!!.getString("room")
        val items = dataSnapshot.children.iterator()
        while (items.hasNext()) {
            val currentItem = items.next()
            val map = currentItem.getValue() as HashMap<String, Any>
            val todoItem = bill.create()
            if(map.get("room")as String == room){
                textView18.text = "ห้อง : "+map.get("room") as String?
                textView19.text = "ค่าห้อง : "+map.get("room_fee") as String?
                textView20.text = "ค่านํ้า : "+map.get("water") as String?
                textView21.text = "ค่าไฟ : "+map.get("elect") as String?
                textView22.text = "รวม : "+(Integer.parseInt(map.get("water") as String)+Integer.parseInt(map.get("elect") as String)+Integer.parseInt(map.get("room_fee") as String)).toString()
                textView23.text = map.get("date") as String?
            }
        }
    }
}

package th.ac.kku.cis.mobileapp.water_electric

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main3.*

class Main3Activity : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        mDatabase = FirebaseDatabase.getInstance().reference

        button4.setOnClickListener {
            val water = editText3.text.toString()
            val elect = editText4.text.toString()
            val room = editText5.text.toString()
            val date = editText6.text.toString()
            val room_fee = editText7.text.toString()

            val bill = bill.create()
            val newItem = mDatabase.child("bill").push()
            bill.elect=elect
            bill.water=water
            bill.id = newItem.key
            bill.room = room
            bill.date = date
            bill.room_fee = room_fee
            newItem.setValue(bill)
            Toast.makeText(this@Main3Activity,"เพิ่มเรียบร้อย", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}

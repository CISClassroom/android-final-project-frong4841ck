package th.ac.kku.cis.mobileapp.water_electric

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main4.*

class Main4Activity : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference

    var event_list: MutableList<bill>? = null
    lateinit var adapter: adapter
    private var listViewItems: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        mDatabase = FirebaseDatabase.getInstance().reference
        listViewItems = findViewById<View>(R.id.listview) as ListView

        event_list = mutableListOf<bill>()
        adapter = adapter(this, event_list!!)
        listViewItems!!.setAdapter(adapter)
        mDatabase.orderByKey().addListenerForSingleValueEvent(itemListener)

        listview.setOnItemClickListener{parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as bill
            Toast.makeText(this,selectedItem.room, Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Main5Activity::class.java)
            intent.putExtra("room",selectedItem.room)
            startActivity(intent)
        }
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
        val items = dataSnapshot.children.iterator()
        while (items.hasNext()) {
            val currentItem = items.next()
            val map = currentItem.getValue() as HashMap<String, Any>
            val todoItem = bill.create()
            todoItem.id = map.get("id") as String?
            todoItem.room = map.get("room") as String?
            event_list!!.add(todoItem)
            adapter.notifyDataSetChanged()
        }
    }
}

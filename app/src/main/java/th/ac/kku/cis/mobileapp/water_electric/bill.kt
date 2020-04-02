package th.ac.kku.cis.mobileapp.water_electric

class bill {
    companion object Factory {
        fun create(): bill = bill()
    }
    var id: String? = null
    var water: String? = null
    var elect: String? = null
    var room: String? = null
    var date: String? = null
    var room_fee: String? = null
}
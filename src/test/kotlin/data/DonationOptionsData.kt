package data

class DonationOptionsData {
    var amount : String? = null
    var currency : String? = null


    fun setUSD100() {
        amount = "100"
        currency = "USD"
    }
}
package data

class CardData {
    var cardNumber : String? = null
    var expirationData : String? = null
    var cvc : String? = null


    fun setValidCredentials() {
        cardNumber = "4242424242424242"
        expirationData = "0424"
        cvc = "000"
    }
}
import pages.DonationPage
import data.CardData
import data.DonationOptionsData
import data.UserData
import org.testng.Assert
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

class DonationWidgetTest : BaseTest() {
    private val donationData = DonationOptionsData()
    private val cardData = CardData()
    private val userData = UserData()

    @BeforeMethod
    fun setUp() {
        donationData.setUSD100()
        cardData.setValidCredentials()
        userData.setValidCredentials()
    }

    @Test(description = "Смотрим")
    fun donationWidgetTest() {
        val donationPage = DonationPage(initPlaywright())

        donationPage.openPage()
        donationPage.openDonationWidget()
        donationPage.fillAmountDonation(donationData.currency!!, donationData.amount!!)
        donationPage.uncheckFee()
        donationPage.fillCardInfo(cardData.cardNumber!!, cardData.expirationData!!, cardData.cvc!!)
        donationPage.fillUserInfo(userData.firstName!!, userData.lastName!!, userData.email!!)

        Assert.assertTrue(donationPage.getErrorMessage().contains("Your card was declined."))
    }
}
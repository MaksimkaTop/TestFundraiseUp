package pages

import com.microsoft.playwright.Page
import com.microsoft.playwright.options.LoadState

class DonationPage(private val page: Page) {

    private val iframeDonationWidget = page
        .frameLocator("xpath=//iframe[@title='Donation Widget']")

    private val iframeCardNumber = iframeDonationWidget
        .frameLocator("xpath=//iframe[@title='Secure card number input frame']")

    private val iframeCardExpirationDate = iframeDonationWidget
        .frameLocator("xpath=//iframe[@title='Secure expiration date input frame']")

    private val iframeCardCVC = iframeDonationWidget
        .frameLocator("xpath=//iframe[@title='Secure CVC input frame']")

    private val giveNowButton = page
        .frameLocator("xpath=//iframe[@title='Donate Button']").locator("//*[@qa='fun-element']")

    private val giveMonthlyButton = iframeDonationWidget.locator("//*[@data-qa='give-monthly']")
    private val currencySelector = iframeDonationWidget.locator("//*[@data-qa='currency-selector']")
    private val amountInput = iframeDonationWidget.locator("//*[@data-qa='amount']")
    private val donateButton = iframeDonationWidget.locator("//*[@data-qa='donate-button']")
    private val feeCheckbox = iframeDonationWidget.locator("//*[@data-qa='cover-fee-checkbox']")
    private val cardPayButton = iframeDonationWidget.locator("//*[@data-qa='cc-button']")

    private val cardNumberInput = iframeCardNumber.locator("//*[@name='cardnumber']")
    private val cardExpirationDateInput = iframeCardExpirationDate.locator("//*[@name='exp-date']")
    private val cardCVCInput = iframeCardCVC.locator("//*[@name='cvc']")
    private val cardSubmitButton = iframeDonationWidget.locator("//*[@data-qa='card-continue']")

    private val firstNameInput = iframeDonationWidget.locator("//*[@data-qa='personal-first-name']")
    private val lastNameInput = iframeDonationWidget.locator("//*[@data-qa='personal-last-name']")
    private val emailInput = iframeDonationWidget.locator("//*[@data-qa='personal-email']")
    private val userInfoSubmitButton = iframeDonationWidget.locator("//*[@data-qa='privacy-continue']")

    private val errorTitle = iframeDonationWidget.locator("//*[@data-qa='card-continue-error-title']")

    fun openPage() {
        page.navigate("https://data.fundraiseup.com/qa-test-7R58U3")
    }

    fun openDonationWidget() {
        giveNowButton.click()
    }

    fun fillAmountDonation(currency: String, amount: String) {
        page.waitForLoadState(LoadState.DOMCONTENTLOADED)
        giveMonthlyButton.click()
        currencySelector.selectOption(currency)
        amountInput.fill(amount)
        donateButton.click()
    }

    fun uncheckFee() {
        page.waitForLoadState(LoadState.DOMCONTENTLOADED)
        feeCheckbox.click()
        cardPayButton.click()
    }

    fun fillCardInfo(cardNumber: String, expirationData: String, cvc: String) {
        page.waitForLoadState(LoadState.DOMCONTENTLOADED)
        cardNumberInput.fill(cardNumber)
        cardExpirationDateInput.fill(expirationData)
        cardCVCInput.fill(cvc)
        cardSubmitButton.click()
    }

    fun fillUserInfo(firstName: String, lastName: String, email: String) {
        page.waitForLoadState(LoadState.DOMCONTENTLOADED)
        firstNameInput.fill(firstName)
        lastNameInput.fill(lastName)
        emailInput.fill(email)
        userInfoSubmitButton.click()
    }

    fun getErrorMessage(): String {
        page.waitForLoadState(LoadState.DOMCONTENTLOADED)
        return errorTitle.innerText()
    }
}
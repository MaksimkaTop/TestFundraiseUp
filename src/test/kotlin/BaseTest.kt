import com.microsoft.playwright.BrowserType
import com.microsoft.playwright.Page
import com.microsoft.playwright.Playwright

open class BaseTest {

    fun initPlaywright(): Page {
        val playwright = Playwright.create()
        val browser = playwright.chromium().launch()
        return browser.newPage()
    }
}
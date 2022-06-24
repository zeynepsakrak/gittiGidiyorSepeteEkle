package tests;


import logger.Log;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Keys;

import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import pages.GittiGidiyorPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestResultLogger;
import java.util.Random;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)//
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(TestResultLogger.class)

public class GittiGidiyorTest {
    Log log = new Log();
    GittiGidiyorPage gittiGidiyorPage=new GittiGidiyorPage();
    Actions actions=new Actions(Driver.getDriver());
    String fiyat;

    @Test
    @Order(1)
    public void bilgisayar_arat() {
        Driver.getDriver().get(ConfigReader.getProperty("GittiGidiyorUrl"));
        gittiGidiyorPage.aramaKutusu.sendKeys("bilgisayar"+ Keys.ENTER);
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().toString().contains("bilgisayar"));
    }

    @Test
    @Order(2)
    public void ikinci_sayfaya_git_kontrol_et() throws InterruptedException {
        gittiGidiyorPage.cookies.click();
        actions.moveToElement(gittiGidiyorPage.ikincisayfa).perform();
        gittiGidiyorPage.ikincisayfa.click();
        Thread.sleep(1000);
        Assert.assertTrue( Driver.getDriver().getCurrentUrl().toString().contains("2"),"ikinci sayfaya gidemedi");
    }

    @Test
    @Order(3)
    public void rastgele_urun_sec_sepete_ekle() throws InterruptedException {
        Random rnd = new Random();
        int sayi = rnd.nextInt(gittiGidiyorPage.urunlerList.size());
        log.info(gittiGidiyorPage.urunlerList.get(sayi+1).getText());
        gittiGidiyorPage.urunlerList.get(sayi+1).click();
        gittiGidiyorPage.cookies.click();
        fiyat=gittiGidiyorPage.urunfiyat.getText();
        gittiGidiyorPage.sepeteEkle.click();
        Thread.sleep(1000);
        Assert.assertTrue(gittiGidiyorPage.sepetegit.isDisplayed(),"sepete eklemedi");
    }

    @Test
    @Order(4)
    public void secilen_urun_fiyat_ile_sepettekini_karsilastir() {
        gittiGidiyorPage.sepetegit.click();
        String sepettekiFiyati=gittiGidiyorPage.sepettekiFiyat.getText();
        Assert.assertEquals(fiyat,sepettekiFiyati);
    }

    @Test
    @Order(5)
    public void secilen_urun_miktar_arttir() {
        gittiGidiyorPage.urunadedi.click();
        Assert.assertTrue(gittiGidiyorPage.urunadedi.getText().contains("2"),"ürün adedi 2 tane dğil");
    }

    @Test
    @Order(6)
    public void urun_sepetten_silinerek_sepeti_bos_oldugunu_kontrol_edilir() throws InterruptedException {
        gittiGidiyorPage.urundelete.click();
        Thread.sleep(1000);
        Assert.assertTrue(gittiGidiyorPage.bosSepet.isDisplayed(),"ürün silinemedi");
    }
}

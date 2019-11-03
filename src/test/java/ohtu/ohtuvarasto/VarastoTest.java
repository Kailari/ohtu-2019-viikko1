package ohtu.ohtuvarasto;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void virheellinenTilavuusKonstruktorissaNollaaTilavuuden() {
        Varasto v = new Varasto(-10);
        assertEquals(0.0, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void virheellinenTilavuusKonstruktorissaAlkusaldollaNollaaTilavuuden() {
        Varasto v = new Varasto(-10, -10);
        assertEquals(0.0, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenSaldoKonstruktorissaAlkusaldollaNollaaAlkusaldon() {
        Varasto v = new Varasto(-10, -10);
        assertEquals(0.0, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriAlkusaldollaEiSalliYlitayttoa() {
        Varasto v = new Varasto(10, 100);
        assertEquals(10.0, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriAlkusaldollaAsettaaOikeanSaldon() {
        Varasto v = new Varasto(10, 4);
        assertEquals(4.0, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void lisaysEiNostaSaldoaJosMaaraOnNegatiivinen() {
        varasto.lisaaVarastoon(-10.0);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysEiMuutaVapaataTilaaJosMaaraOnNegatiivinen() {
        varasto.lisaaVarastoon(-10.0);
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void paljonkoMahtuuPalauttaaNollanKunLisaysTayttaaVaraston() {
        varasto.lisaaVarastoon(9.0);
        varasto.lisaaVarastoon(100.0);
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void varastonSaldoOnTilavuusKunLisaysTayttaaVaraston() {
        varasto.lisaaVarastoon(9.0);
        varasto.lisaaVarastoon(100.0);
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaNollanKunMaaraOnNegatiivinen() {
        double saatuMaara = varasto.otaVarastosta(-10.0);
        assertEquals(0.0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaJaljellaolevanSaldonJosMaaraOnSuurempiKuinSaldo() {
        varasto.lisaaVarastoon(8.0);
        double saatuMaara = varasto.otaVarastosta(10);
        assertEquals(8.0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void toStringKertooSaldon() {
        varasto.lisaaVarastoon(4.0);
        assertTrue(varasto.toString().contains("4.0"));
    }

    @Test
    public void toStringKertooVapaanTilan() {
        varasto.lisaaVarastoon(4.0);
        assertTrue(varasto.toString().contains("6.0"));
    }
}
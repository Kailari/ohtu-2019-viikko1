package main;

import ohtu.ohtuvarasto.Varasto;

public class Main {

    public static void main(String[] args) {
        Varasto mehua = new Varasto(100.0);
        Varasto olutta = new Varasto(100.0, 20.2);

        tulostaAlkutila(mehua, olutta);
        tulostaOlutta(olutta);
        tulostaMehutesti(mehua);
        tulostaVirhetilanteita();
    }

    private static void tulostaVirhetilanteita() {
        System.out.println("Virhetilanteita:");
        System.out.println("new Varasto(-100.0);");
        Varasto huono = new Varasto(-100.0);
        System.out.println(huono);

        System.out.println("new Varasto(100.0, -50.7)");
        huono = new Varasto(100.0, -50.7);
        System.out.println(huono);
    }

    private static void tulostaMehutesti(Varasto mehua) {
        System.out.println("Mehusetterit:");
        System.out.println("Lisätään 50.7");
        mehua.lisaaVarastoon(50.7);
        System.out.println("Mehuvarasto: " + mehua);
        System.out.println("Otetaan 3.14");
        mehua.otaVarastosta(3.14);
        System.out.println("Mehuvarasto: " + mehua);
    }

    private static void tulostaOlutta(Varasto olutta) {
        System.out.println("Olutgetterit:");
        System.out.println("getSaldo()     = " + olutta.getSaldo());
        System.out.println("getTilavuus    = " + olutta.getTilavuus());
        System.out.println("paljonkoMahtuu = " + olutta.paljonkoMahtuu());
    }

    private static void tulostaAlkutila(Varasto mehua, Varasto olutta) {
        System.out.println("Luonnin jälkeen:");
        System.out.println("Mehuvarasto: " + mehua);
        System.out.println("Olutvarasto: " + olutta);
    }
}

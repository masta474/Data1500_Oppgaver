// HashJoin.java
import java.io.BufferedReader; 
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HashJoin {

	  public static void main(String[] args) {
		    // Last inn oppslagstabeller for studenter og kurs i minne vha. Java interface Map (klasse HashMap)
        // Mekanismen er implementert i egen funksjon 
        // lastInnMap(filnavn, feltposisjon for nøkkel, feltposisjon for verdi, om nøkkel er et tall)
		    Map<Object, String> studenter = lastInnMap(args[0], 0, 1, true); // ligger i RAM og har raskt oppslag og innsetting
		    Map<Object, String> kurs = lastInnMap(args[1], 0, 1, false); // ligger i RAM og har raskt oppslag og innsetting
        
		    // Iterer gjennom koblingsfilen (paameldinger) og finn navn til student og navn på kurs
		    try (BufferedReader leser = new BufferedReader(new FileReader(args[2]))) {
			      String linje;
			      while ((linje = leser.readLine()) != null) {
				        String[] felt = linje.split(",");
				        if (felt.length == 2) {
                    // Tips: "kast" studentId fra paameldinger til int
                    // Tips: hent inn kursnavn fra kurs som String
                    // Tips: bruke HashMap objekter for å finne studentnavn og kursnavn
                    // Tips: sjekk at studentnavn og kursnavn ikke er null
                    // Tips: skriv ut post (linje) til stdout (System.out i Java) på følgende format
                    // <studentnavn> er påmeldt <kursnavn>
					          // Skriv din kode her ...
                  
				        }
			      } 
		    } catch (IOException e) {
			      e.printStackTrace();
		    }
	  }
  
    // Funksjonen lastInnMap 
    // Argumenter: 
    // - filnavn (navn til datafilen som skal lastes i minne)
    // - nokkelIndeks (posisjon for feltet som holder indeks i datafilen)
    // - verdiIndeks (posisjon for feltet som holder verdi i datafilen 
    // - parseKeyAsInt (om nokkelIndeks skal tolkes som et tall, int i dette tilfelle)
    // studentdata: 101,Mickey,CS (nokkelIndeks er 101 og verdiIndeks er "Mickey")
    // kursdata: DATA1500,Intro to Databases (nokkelIndeks er "DATA1500" og verdiIndeks er "Intro to Databases"
	  private static Map<Object, String> lastInnMap(String filnavn, int nokkelIndeks, int verdiIndeks, boolean parseKeyAsInt) {
		    Map<Object, String> map = new HashMap<>();
		    try (BufferedReader leser = new BufferedReader(new FileReader(filnavn))) {
			      String linje;
			      while ((linje = leser.readLine()) != null) {
				        String[] felt = linje.split(",");
				        if (felt.length > Math.max(nokkelIndeks, verdiIndeks)) {
					          Object nokkel = felt[nokkelIndeks].trim();
					          if (nokkelIndeks == 0) {
						            if (parseKeyAsInt) {
                        	  nokkel = Integer.parseInt((String) nokkel);
                    	  }
					          }
					          map.put(nokkel, felt[verdiIndeks].trim());
				        }
			      }
		    } catch (IOException e) {
			      e.printStackTrace();
		    }
		    return map;
	  }

} 

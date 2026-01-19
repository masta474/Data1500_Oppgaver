// FinnBruker.java
import java.time.Instant;
import java.time.Duration;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

// Eksempel på en post (den første linjen i filen med brukerdata): 
// 1,bruker1@epost.no,Navn Navnesen 1
public class FinnBruker {
    public static void main(String[] args) {
        // Velg å lage en egen funksjon finnBruker(String, String)
		    // Både filnavn og søkeuttrykk er av typen String
		    finnBruker(args[0], args[1]);
	  }

    // Implemeter funksjon finnBruker(String, String) her
	  private static void finnBruker(String filnavn, String epost) {
    
        // Bruk Instant klassen for å starte tidtaking
		    Instant start = Instant.now();
    
        // Bruk BufferedReader for å åpne filen og lese fra den
		    try(BufferedReader leser = new BufferedReader (new FileReader(filnavn))) {
			      String linje;
            // Bruk while-løkke med leser.readLine() for å sekvensielt gå over alle linjene i brukerdata
			      while ((linje = leser.readLine()) != null) {
                // Tips: kan bruke linje.contains metoden for å sjekke om linjen inneholder søkeuttrykket
                // Tips: hvis linjen inneholder søkeuttrykket bruke Instant.now() igjen for å finne slutt-tiden på søking
                // Tips: bruk Duration.between metoden for å finne forskjellg på start-tiden og slutt-tiden
                // Skriv ut følgende to rader til stdout (System.out i Java): 
                // Fant epost: <søkeuttrykk>
                // Tid brukt: <N> ms.
                // ms står for millisekunder.
                // returner fra funksjonen
				        // Skriv din kode her ...
        
			      }    
		    } catch (FileNotFoundException e) {
                System.err.println("Feil: kunne ikke finne filen " + filnavn);
                e.printStackTrace(); 
        } catch (IOException e) {
                System.err.println("Feil: IOException ");
                e.printStackTrace(); 
        }
	  }
}

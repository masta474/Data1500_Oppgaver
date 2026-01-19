// DataGenerator.java
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataGenerator {

	  public static void main(String[] args) {
        // Ta inn filnavn for den nye filen fra kommandolinje
        String filnavn = args[0];
        // Tips: Ta inn antall linjer som skal genereres fra args[1]
        // Tips: Husk å "kaste" type til int
        // Skriv kode her ...
      
        // Bruk BuffereWriter for å skrive brukerdata til filen
        try (BufferedWriter skriver = new BufferedWriter(new FileWriter(filnavn))) {
            for (int i = 1; i <= antallLinjer; i++) {
                // Formatter linjen for brukerdata slik som spesifisert i oppgaveteksten (README fil)
                // Tips: kan bruke String.format metoden for det
                // Skriv kode her ...
                // Tips: bruk skriver.write metoden for å skrive den formatterte linjen til filen 
                // Tips: bruk skriver.newLine() for å skrive en linjeskift tegn til filen
                // Skriv kode her ...
            }
            // Tips: skriv ut til stdout (System.out i java) om hvor mange linjer er blitt skrevet til filen
        } catch (IOException e) {
			      e.printStackTrace();
		    }

    }

}

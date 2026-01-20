import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataGenerator {

	  public static void main(String[] args) {

        String filnavn = args[0];
        int antallLinjer = Integer.parseInt(args[1]);
      
        // Bruk BuffereWriter for å skrive brukerdata til filen
        try (BufferedWriter skriver = new BufferedWriter(new FileWriter(filnavn))) {
            for (int i = 1; i <= antallLinjer; i++) {
                skriver.write(i+",bruker"+i+"@epost.no,Navn Navnesen "+i);
                skriver.newLine();
            }
        } catch (IOException e) {
			      e.printStackTrace();
		    }

    }

}

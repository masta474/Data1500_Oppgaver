import java.time.Instant;
import java.time.Duration;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FinnBruker {
    public static void main(String[] args) {
		    finnBruker(args[0], args[1]);
	  }
	  private static void finnBruker(String filnavn, String epost) {
		    Instant start = Instant.now();
    
		    try(BufferedReader leser = new BufferedReader (new FileReader(filnavn))) {
			      String linje;
			      while ((linje = leser.readLine()) != null) {
					  if (linje.contains(epost)){
						  Instant slutt = Instant.now();
						  long tidMS = Duration.between(start, slutt).toMillis();
						  System.out.println("Fant epost: " + epost);
						  System.out.println("Tid brukt: " + tidMS + " ms.");
						  return;
					  }
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

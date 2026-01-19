// SokMedIndeks.java
import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.HashMap;

public class SokMedIndeks {
    
	@SuppressWarnings("unchecked") // tar ansvar selv, hvis man vet nøyaktig hvordan indeksfilen ble generert

    // Koden som du skal skape selv er å finne hvor i filen skal man plassere tidtaker-funksjoner (for måle ytelse med bruk av indeks):
    // Instant start = Instant.now();
    // Instant slutt = Instant.now();
    // Duration dur = Duration.between(start, slutt);
    
	public static void main(String[] args) {
        // Tar inn navn til datafil, navn til indeksfil (bygget med IndeksBygger) og søkeuttrykket
		String dataFil = args[0];
		String indeksFil = args[1];
		String epostSok = args[2];

		// Last indeksen fra fil (som inneholder serialiserte data man genererte med IndeksBygger)
		Map<String, Long> indeks = new HashMap<>();
        
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(indeksFil))) {
			indeks = (Map<String, Long>) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}

		
		// O(1) oppslag i indeksen
		Long posisjon = null;
		if (indeks != null) {
			posisjon = indeks.get(epostSok);
		}

		if (posisjon != null) {
			// Hopp direkte til posisjonen i datafilen
			try (RandomAccessFile raf = new RandomAccessFile(dataFil, "r")) {
				raf.seek(posisjon);
				String linje = raf.readLine();
				System.out.println("Fant linje: " + linje);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("E-post ikke funnet i indeksen.");
		}

		// Skriv ut en linje til stdout (System.out i Java) på følgende format:
        // Søket med indeks tok N nanos (M ms).
        // Hvor N er tallet i nanosekunder og M er tallet i millisekunder.
		System.out.println("...");

	}
}

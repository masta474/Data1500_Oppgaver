import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.HashMap;

public class SokMedIndeks {
    
	@SuppressWarnings("unchecked")


	public static void main(String[] args) {
		String dataFil = args[0];
		String indeksFil = args[1];
		String epostSok = args[2];

		Map<String, Long> indeks = new HashMap<>();
        
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(indeksFil))) {
			indeks = (Map<String, Long>) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}

		Duration dur = null;
		// O(1) oppslag i indeksen
		Long posisjon = null;
		if (indeks != null) {
			Instant start = Instant.now();
			posisjon = indeks.get(epostSok);
			Instant slutt = Instant.now();
			dur = Duration.between(start, slutt);

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

		System.out.println("Søket med indeks tok " + dur.toNanos() + " nanos (" + dur.toMillis() +" ms).");

	}
}

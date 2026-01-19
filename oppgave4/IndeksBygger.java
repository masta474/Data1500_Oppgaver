// IndeksBygger.java
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class IndeksBygger {
    
	public static void main(String[] args) {
        
        // Tar inn verdiene fra kommandolinje for en eksisterende datafil og navn til en ny indeksfil
		String dataFil = args[0];
        // indeks skal bygges for kolonne med e-post
		String indeksFil = args[1]; 

        // Allokerer en ny HashMap for indeks
		Map<String, Long> indeks = new HashMap<>();

        // For å kunne finne posisjoner for hver linje i datafilen, kan man bruke RandomAccessFile klassen
		try (RandomAccessFile raf = new RandomAccessFile(dataFil, "r")) {
            // Etter hver avlesing med readLine, blir fil-pekeren endret
			long posisjon = raf.getFilePointer(); 
			String linje;
            
            // Leser sekvensielt linje etter linje og finner posisjon i filen til første tegn på hver linje
			while ((linje = raf.readLine()) != null) {
				String epost = linje.split(",")[1].trim();  
				// Tips: sett inn epost og psosisjon i HashMap objektet indeks
                // Skriv din kode her ...
                
				posisjon = raf.getFilePointer();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
        
		// Serialiserer Map-objektet (en spesial format for rask lagring og henting)
        // Skriver data fra minne (RAM) til disk (SSD mest sannsynlig)
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(indeksFil))) {
			oos.writeObject(indeks);
			System.out.println("Indeks skrevet til " + indeksFil);
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

}


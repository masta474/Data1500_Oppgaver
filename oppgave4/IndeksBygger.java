import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class IndeksBygger {
    
	public static void main(String[] args) {
        
		String dataFil = args[0];
		String indeksFil = args[1];

		Map<String, Long> indeks = new HashMap<>();

		try (RandomAccessFile raf = new RandomAccessFile(dataFil, "r")) {
			long posisjon = raf.getFilePointer();
			String linje;
            
			while ((linje = raf.readLine()) != null) {
				String epost = linje.split(",")[1].trim();  
				indeks.put(epost, posisjon);
                
				posisjon = raf.getFilePointer();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
        
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(indeksFil))) {
			oos.writeObject(indeks);
			System.out.println("Indeks skrevet til " + indeksFil);
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

}


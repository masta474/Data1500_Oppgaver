import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HashJoin {
	  public static void main(String[] args) {
		    Map<Object, String> studenter = lastInnMap(args[0], 0, 1, true); // ligger i RAM og har raskt oppslag og innsetting
		    Map<Object, String> kurs = lastInnMap(args[1], 0, 1, false); // ligger i RAM og har raskt oppslag og innsetting
        
		    try (BufferedReader leser = new BufferedReader(new FileReader(args[2]))) {
			      String linje;
			      while ((linje = leser.readLine()) != null) {
				        String[] felt = linje.split(",");
					  if (felt.length == 2) {
							int studentId = Integer.parseInt(felt[0]);
							String kursId = felt[1].trim();
							String studentNavn = studenter.get(studentId);
							String kursNavn = kurs.get(kursId);
							if (studentNavn != null && kursNavn != null ){
								//jeg er usikker på hvor jeg bør skrive dette men jeg måtte legge
								// til en student til, 105 (i students.csv)
								System.out.println(studentNavn + " er påmeldt "+ kursNavn);
							}
				        }
			      } 
		    } catch (IOException e) {
			      e.printStackTrace();
		    }
	  }
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

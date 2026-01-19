// LesStudenter.java
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

// 1. Definere en klasse for data fra en datafil om studenter basert på strukturen i data (se oppgavetekst i README.md)
// Velg egnede typer for felt i filen med studentinfo, f.eks. første felt kan være en int, siden den representerer id
// eller studentnummer. Andre felt, som representerer navn til studenten, kan være en String, for eksempel, osv.
class Student {
    // Skriv kode her ...

    // Metoden erstatter en eksisterende metode toString() 
    @Override
    public String toString() {
        return "Student{" + .... + "}";
    }

}

// 2. Bruk try-with-resources for sikker filbehandling og les inn data fra fil i en Java datastruktur 
// ved bruk av Scanner, linje for linje, og til slutt skriv ut datafeltene til 
// stdout(System.out i Java)
public class LesStudenter {
    public static void main(String[] args) {
        try (Scanner leser = new Scanner(new File(args[0]))) {
            // Tips: bruk while-løkke med leser.hasNextLine() for å sekvensielt gå over alle rader i filen med studentinfo
            while (leser.hasNext()) {
                // Tips: bruk leser.nextLine().split(",") for å splitte opp linje (post) i felt (leses inn i en String[])
                // Tips: sjekk at lengden til post er 3 (du trenger ikke å implementer else) 
                // Tips: ta inn det første feltet som int (forutsatt at datafeltet i klassen Student er int)
                // Skriv kode her ...
                // Tips: alloker Student-objekt for hver linje i filen med studentinfo (fullfør initialisering av objektet Student)
                Student s = new Student(...);
                // Tips: Skriv ut dataene med den overskrevede toString() metoden i klassen Student.
                System.out.println(s);
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }
}

/** Output skal være: 
Student{id=101, navn='Mickey', program='CS'}
Student{id=102, navn='Daffy', program='EE'}
Student{id=103, navn='Donald', program='CS'}
Student{id=104, navn='Minnie', program='PSY'}
*/

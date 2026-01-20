import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Student {
    private String name;
    private int id;
    private String program;

    public Student(String name, int id, String program) {
        this.name = name;
        this.id = id;
        this.program = program;
    }

    @Override
    public String toString() {
        return "Student{id="+id+", navn='"+name+"', program='"+program+"'}";
    }
}
public class LesStudenter {
    public static void main(String[] args) {
        try (Scanner leser = new Scanner(new File(args[0]))) {
            // Tips: bruk while-løkke med leser.hasNextLine() for å sekvensielt gå over alle rader i filen med studentinfo
            while (leser.hasNext()) {
                String[] post = leser.nextLine().split(",");
                if (post.length == 3) {
                    int id = Integer.parseInt(post[0]);
                    String name = post[1];
                    String program = post[2];
                    Student s = new Student(name,id, program);
                    System.out.println(s.toString());

                }
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }
}

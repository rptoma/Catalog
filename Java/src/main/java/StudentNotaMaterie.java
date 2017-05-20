/**
 * Created by toma on 19/05/2017.
 */
public class StudentNotaMaterie {
    private Student student;
    private Materie materie;
    private Nota nota;

    public StudentNotaMaterie(Student student, Materie materie, Nota nota) {
        this.student = student;
        this.materie = materie;
        this.nota = nota;
    }

    public Student getStudent() {
        return student;
    }

    public Materie getMaterie() {
        return materie;
    }

    public Nota getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return student.toString() + " " + materie.toString() + " " + nota.toString();
    }

    public String toFormattedString() {
        return student.toFormattedString() + " " + materie.toFormattedString() + " " + nota.toFormattedString();
    }
}

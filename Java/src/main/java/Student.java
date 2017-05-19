/**
 * Created by toma on 19/05/2017.
 */
public class Student {
    private Integer id_student;
    private String prenume;
    private String nume;
    private Integer grupa;

    public Student(Integer id_student, String prenume, String nume, Integer grupa) {
        this.id_student = id_student;
        this.prenume = prenume;
        this.nume = nume;
        this.grupa = grupa;
    }

    public Integer getId_student() {
        return id_student;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getNume() {
        return nume;
    }

    public Integer getGrupa() {
        return grupa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id_student=" + id_student +
                ", prenume='" + prenume + '\'' +
                ", nume='" + nume + '\'' +
                ", grupa=" + grupa +
                '}';
    }
}

/**
 * Created by toma on 19/05/2017.
 */
public class Nota {
    private int nota;

    public Nota(int nota) {
        this.nota = nota;
    }

    public int getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "nota=" + nota +
                '}';
    }
}

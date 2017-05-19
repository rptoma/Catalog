/**
 * Created by toma on 19/05/2017.
 */
public class Materie {
    private int id_materie;
    private String nume_materie;
    private int nr_credite;

    public Materie(int id_materie, String nume_materie, int nr_credite) {
        this.id_materie = id_materie;
        this.nume_materie = nume_materie;
        this.nr_credite = nr_credite;
    }

    public int getId_materie() {
        return id_materie;
    }

    public String getNume_materie() {
        return nume_materie;
    }

    public int getNr_credite() {
        return nr_credite;
    }

    @Override
    public String toString() {
        return "Materie{" +
                "id_materie=" + id_materie +
                ", nume_materie='" + nume_materie + '\'' +
                ", nr_credite=" + nr_credite +
                '}';
    }
}

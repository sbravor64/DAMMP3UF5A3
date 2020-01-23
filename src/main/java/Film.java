import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "dataroot")
@XmlAccessorType(XmlAccessType.PROPERTY)
class Films {
    @XmlElement(name="FILM")
    List<Film> filmsList;
}

public class Film implements Serializable {

    @XmlElement(name="IDFILM")
    String idFilm;

    @XmlElement(name="TITOL")
    String titol;

    @XmlElement(name="INTERPRETS")
    String interpretets;

    @XmlElement(name="ANY")
    int any;

    @XmlElement(name="DIRECCIO")
    String direccio;

    @XmlElement(name="SINOPSI")
    String sinopsi;

    @XmlElement(name="IDIOMA_x0020_ORIGINAL")
    String idioma;

    String buscar;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(idioma, film.idioma);
    }



    @Override
    public int hashCode() {
        return Objects.hash(idioma);
    }

    public Film() {
        super();
    }

    @Override
    public String toString() {
        return  "titol: " + titol + "\n" +
                "Any: " + any + "\n" +
                "Direccio: " + direccio + "\n";
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public String getIdFilm() {
        return idFilm;
    }

    public String getTitol() {
        return titol;
    }

    public int getAny() {
        return any;
    }

    public String getDireccio() {
        return direccio;
    }

    public String getSinopsi() {
        return sinopsi;
    }

    public String getInterpretets() { return interpretets; }

    public String getIdioma() {
        return idioma;
    }
}
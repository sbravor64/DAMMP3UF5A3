import org.w3c.dom.ls.LSOutput;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class ConeccionXML {
    long numResultat;
    Film film = new Film();
    List<Film> films;
    Scanner scanner = new Scanner(System.in);

    public void connected() throws JAXBException, IOException {
        URL url = new URL("http://gencat.cat/llengua/cinema/provacin.xml");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.addRequestProperty("User-Agent", "Mozilla/4.76");
        InputStream is = http.getInputStream();
        JAXBContext jaxbContext = JAXBContext.newInstance(Films.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        films = ((Films)jaxbUnmarshaller.unmarshal(is)).filmsList;
    }

    public void buscarPorTitulo(String input){
        films.stream().filter(film1 -> film1.getTitol().contains(input))
                .forEach(System.out::println);

        numResultat= films.stream().filter(film1 -> film1.getTitol().contains(input))
                .count();

        System.out.println("Resultado: " + numResultat);
    }

    public void buscarPorDirector(String input){
        films.stream().filter(film1 -> film1.getDireccio().equals(film.getBuscar()))
                .forEach(System.out::println);

        numResultat= films.stream().filter(film1 -> film1.getDireccio().equals(film.getBuscar()))
                .count();

        System.out.println("Resultado: " + numResultat);
    }

    public void buscarPorAño(int input){
        films.stream().filter(film1 -> film1.getAny() == input)
                .forEach(System.out::println);

        numResultat= films.stream().filter(film1 -> film1.getAny() == input)
                .count();

        System.out.println("Resultado: " + numResultat);

    }

    public void buscarPorInterprete( String input) {
        films.stream().filter(film1 -> film1.getInterpretets().equals(input))
                .forEach(System.out::println);

        numResultat= films.stream().filter(film1 -> film1.getInterpretets().equals(input))
                .count();

        System.out.println("Resultado: " + numResultat);
    }

    public void maxYear(){

        Film f = films.stream()
                .max((t1,t2) -> {
                    if(t1.getAny()>t2.getAny()) return 1;
                    else if(t1.getAny()<t2.getAny()) return -1;
                    else return 0;
                }
                )
                .get();

        System.out.println(f);
    }

    public void minYear(){
        Film f = films.stream()
                .min((t1,t2) -> {
                            if(t1.getAny()>t2.getAny()) return 1;
                            else if(t1.getAny()<t2.getAny()) return -1;
                            else return 0;
                        }
                )
                .get();

        System.out.println(f);
    }

    public void filmsCatala(){
        numResultat= films.stream().filter(film1 -> film1.getIdioma().contains("català"))
                .count();
        System.out.println(numResultat);
    }

    public void idiomasDisponibles() {
        films.stream().distinct().forEach(film1 -> System.out.println(film1.getIdioma()));
    }

    public void modificarAño() {
        films.stream().map(film1 -> film1.getAny() - 1);
    }
}

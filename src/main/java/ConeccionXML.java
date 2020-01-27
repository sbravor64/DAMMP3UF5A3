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
    List<Film> films;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

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

        System.out.println(ANSI_PURPLE + "Resultado: " + numResultat+ANSI_RESET);
    }

    public void buscarPorDirector(String input){
        films.stream().filter(film1 -> film1.getDireccio().equals(input))
                .forEach(System.out::println);

        numResultat= films.stream().filter(film1 -> film1.getDireccio().equals(input))
                .count();

        System.out.println(ANSI_PURPLE +"Resultado: " + numResultat+ANSI_RESET);
    }

    public void buscarPorAño(int input){
        films.stream().filter(film1 -> film1.getAny() == input)
                .forEach(System.out::println);

        numResultat= films.stream().filter(film1 -> film1.getAny() == input)
                .count();

        System.out.println(ANSI_PURPLE +"Resultado: " + numResultat+ANSI_RESET);

    }

//    public void buscarPorInterprete(String input) throws NullPointerException {
//
//        films.stream().filter(film1 -> film1.getInterprets().contains(input))
//                .forEach(film -> System.out.println(film.getInterprets()));
//
////        List<Film> lista = films.stream().collect(Collectors.toList());
////        lista.stream().filter(film1 -> film1.getInterprets().contains(input)).
////                forEach(film -> System.out.println
////                ("Película: " + film.getTitol() + "\n" + "Interpretes: " + film.getInterprets() + "\n"));
//
////        numResultat= films.stream().filter(film1 -> film1.getInterprets().contains(input))
////                .count();
////
////        System.out.println(ANSI_PURPLE+ "Resultado: " + numResultat+ANSI_RESET);
//    }

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

    public void ordenarPorAños(){
        films.stream().sorted(Comparator.comparingInt(Film::getAny)).collect(Collectors.toList()).forEach(System.out::println);
    }
}

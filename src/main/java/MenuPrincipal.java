import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class MenuPrincipal {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public void show() throws IOException, JAXBException {
        Scanner scanner = new Scanner(System.in);
        String input;
        ConeccionXML coneccionXML = new ConeccionXML();
        coneccionXML.connected();

        Scanner sc = new Scanner(System.in);
        int opcion;
        do{
            System.out.println(ANSI_CYAN + "FILMS" + ANSI_RESET);
            System.out.println("Buscador: ");
            System.out.println("1. POR TITULO ");
            System.out.println("2. POR DIRECTOR");
            System.out.println("3. POR AÑO");
            System.out.println("4. POR INTERPRETE");
            System.out.println();
            System.out.println("Otras opciones:");
            System.out.println("5. MODIFICAR AÑOS DE LAS PELICULAS");
            System.out.println("6. LISTA DE PELICULAS POR AÑO (ORDENADO)");
            System.out.println("7. DATOS CURIOSOS");
            System.out.println();
            System.out.println("8. Acabar");

            opcion= sc.nextInt();

            switch (opcion) {
                case 0:
                    break;
                case 1:
                    System.out.print("Escribe el nombre de la pelicula: ");
                    input = scanner.nextLine();
                    coneccionXML.buscarPorTitulo(input);
                    break;
                case 2:
                    System.out.print("Escribe el nombre del director/a: ");
                    input = scanner.nextLine();
                    coneccionXML.buscarPorDirector(input);
                    break;
                case 3:
                    System.out.print("Escribe el año: ");
                    int input1 = sc.nextInt();
                    coneccionXML.buscarPorAño(input1);
                    break;
                case 4:
//                    System.out.print("Escribe el interprete: ");
//                    input = scanner.nextLine();
////                    coneccionXML.buscarPorInterprete(input);
                    break;
                case 5:
                    System.out.println("Al parecer todas las peliculas tienen un año de más");
                    System.out.println("!Vamos a corregirlo¡");
                    coneccionXML.modificarAño();
                    break;
                case 6:
                    System.out.println("Lista Peliculas Ordenadas por Año");
                    coneccionXML.ordenarPorAños();
                    break;
                case 7:
                    System.out.print(ANSI_CYAN + "¿Cuantas peliculas en catalán hay?: " + ANSI_RESET);
                    coneccionXML.filmsCatala();
                    System.out.println();

                    System.out.println(ANSI_CYAN + "¿Cual es la ultima pelicula añadida? "+ ANSI_RESET);
                    coneccionXML.maxYear();

                    System.out.println(ANSI_CYAN + "¿Cual es la primera pelicula añadida? "+ ANSI_RESET);
                    coneccionXML.minYear();

                    System.out.println(ANSI_CYAN + "¿cuantos idiomas están disponibles? "+ ANSI_RESET);
                    coneccionXML.idiomasDisponibles();
                    break;
            }
        } while (opcion!=8);
    }
}

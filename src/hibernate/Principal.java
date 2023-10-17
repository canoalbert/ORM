package hibernate;

import hibernate.Direccion.Direccion;
import hibernate.Profesor.Profesor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Principal {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        //Inicia la sesion
        Session session = null;
        String opcion;
        Profesor profesor = new Profesor("Alberto", "Cano", "Roca");



       do {
           System.out.println("¿Que desea hacer?");
           System.out.println("1- INSERTAR");
           System.out.println("2- LEER");
           System.out.println("3- ACTUALIZAR");
           System.out.println("4- Borrar");
           System.out.println("5- SALIR");
           try {
               opcion = (br.readLine());

               session = sessionFactory.openSession();
               session.beginTransaction();
               switch (opcion) {
                   case "1" -> insertarProfesor(session);
                   case "2" -> leerProfesor(session);
                   case "3" -> modificarProfesor(session);
                   case "4" -> borrarProfesor(session);

                   default -> {
                       if (!opcion.equals("5")) System.out.println("Aqui te espero");
                   }
               }
           } catch (IOException e) {
               System.out.println("Error manin mejor pon un número de la lista");
               opcion = "0";
           }
           session.getTransaction().commit();
           session.close();

       }while (!opcion.equals("5"));
       try {
           br.close();

        } catch (IOException e) {
           throw new RuntimeException(e);
       } ;


        //INSERTAR
        //session.persist(profesor);

        //LEER
        //Profesor miProfesor = session.get(Profesor.class, 1);
        //System.out.println(miProfesor);

        //ACTUALIZAR
        //session.merge(new Profesor(1, "Alberto", "Cano", "Cano"));

        //BORRAR
        //session.remove(new Profesor(1, null, null, null));

        //Ejecuta las instrucciones
        //session.getTransaction().commit();
        //Cierra la sesión
        //session.close();
    }

    private static void borrarProfesor(Session session) throws IOException{
        System.out.println("Ha seleccionado la Opción BORRAR.");
        System.out.println("Dime el id del profesor que deseas borrar");
        int id = Integer.parseInt(br.readLine());
        //System.out.println(session.createQuery(""));
        System.out.println(session.get(Profesor.class, id));
        System.out.println("¿Es este el profesor que deseas modificar?  si para borrar?");
        if (br.readLine().equalsIgnoreCase("si")){
            session.remove(new Profesor(id, null, null, null));
        }
    }

    private static void modificarProfesor(Session session) throws IOException {
        System.out.println("Has seleccionado la Opción de modificar");
        Profesor profesor = leerDatos(true);
        System.out.println("******* ANTES *******");
        System.out.println(session.get(Profesor.class, profesor.getId()));
        System.out.println("******* DESPUÉS *******");
        System.out.println(profesor);
        System.out.println("¿Es este el profesor que deseas modificar?  si para modificar");
        String respuesta = br.readLine();
        if (respuesta.equals("si")){
            session.merge(profesor);
        }
    }

    private static void leerProfesor(Session session) throws IOException{
        System.out.println("Has seleccionado la Opción de leer");
        System.out.println("Dime el id del profesor a mostrar");
        int id = Integer.parseInt(br.readLine());
        Profesor profesor = session.get(Profesor.class, id);
        System.out.println(profesor);
    }

    private static void insertarProfesor(Session session) throws IOException {
        System.out.println("Has seleccionado la opción de insertar");
        Profesor profesor = leerDatos(false);
        session.persist(profesor);
        System.out.println(profesor);
    }

    private static Profesor leerDatos(boolean modificando) throws IOException {
        System.out.println("Dime tu nombre");
        String nombre = br.readLine();

        System.out.println("Dime tu primer apellido");
        String apellido1 = br.readLine();

        System.out.println("Dime tu segundo nombre");
        String apellido2 = br.readLine();

        Direccion direccion = leerDireccion();
        if (modificando){
            System.out.println("Dime el id que quieres modificar");
            int id = Integer.parseInt(br.readLine());
            Profesor profesorActu = new Profesor(id, nombre, apellido1, apellido2);
            return profesorActu;
        }
        Profesor profesor = new Profesor(nombre, apellido1, apellido2);
        profesor.setDireccion(direccion);
        direccion.setProfesor(profesor);
        return profesor;
    }

    private static Direccion leerDireccion() throws IOException {
        System.out.println("Dime tu calle");
        String calle = br.readLine();

        System.out.println("Dime tu cpoblacion");
        String poblacion = br.readLine();

        System.out.println("Dime tu provincia");
        String provincia = br.readLine();

    return new Direccion(calle, poblacion, provincia);
    }

}

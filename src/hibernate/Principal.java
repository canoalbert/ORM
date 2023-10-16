package hibernate;

import hibernate.Profesor.Profesor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Principal {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int opcion;
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        //Inicia la sesion
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Profesor profesor = new Profesor("Alberto", "Cano", "Roca");
        session.persist(profesor);
        Profesor miProfesor = session.get(Profesor.class, 1);
        System.out.println("Estos son los datos del usuario");


        session.getTransaction().commit();
        session.close();


       do {
           System.out.println("¿Que desea hacer?");
           System.out.println("1- INSERTAR");
           System.out.println("2- LEER");
           System.out.println("3- ACTUALIZAR");
           System.out.println("4- Borrar");
           System.out.println("5- SALIR");
           try {
               opcion = Integer.parseInt(reader.readLine());
               switch (opcion) {
                   case 1:
                       System.out.println("Ha seleccionado la INSERTAR.");
                       session.beginTransaction();
                       session.persist(profesor);
                       session.getTransaction().commit();
                       session.close();
                       break;
                   case 2:
                       System.out.println("Ha seleccionado la Opción Leer.");
                       session.beginTransaction();
                       Profesor miProfesor = session.get(Profesor.class, 1);
                       System.out.println(miProfesor);

                       session.getTransaction().commit();
                       session.close();
                       break;
                   case 3:
                       System.out.println("Ha seleccionado la Opción ACTUALIZAR.");
                       session.beginTransaction();

                       session.merge(new Profesor(1, "Alberto", "Cano", "Cano"));

                       session.getTransaction().commit();
                       session.close();
                       break;

                   case 4:
                       System.out.println("Ha seleccionado la Opción BORRAR.");
                       session.beginTransaction();

                       session.remove(new Profesor(1, null, null, null));

                       session.getTransaction().commit();
                       session.close();
                       break;
                   case 5:
                       System.out.println("Ha seleccionado la Opción SALIR.");
                       default:
                       System.out.println("Aqui te espero");
                       break;

               }
           } catch (IOException e) {
               System.out.println("Error manin");
               opcion = 0;
           }
       }while (opcion != 4);
       try {
           reader.close();

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
}

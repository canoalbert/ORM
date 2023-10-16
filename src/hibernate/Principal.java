package hibernate;

import hibernate.Profesor.Profesor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Principal {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        //Inicia la sesion
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Profesor profesor = new Profesor("Alberto", "Cano", "Roca");

        Profesor miProfesor = session.get(Profesor.class, 1);
        System.out.println("Estos son los datos del usuario");
        System.out.println(miProfesor);

        session.getTransaction().commit();
        session.close();









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

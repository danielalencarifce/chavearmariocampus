package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection {
    private EntityManagerFactory factory;
    private EntityManager bd;
    /*public static void main (String args[]) {
    	Connection();
    }*/

    public EntityManager getInstance() {
        return bd;
    }

    public Connection() {
        factory = Persistence.createEntityManagerFactory("armarios");
        bd = factory.createEntityManager();
    }

    public void close() {
        bd.close();
        factory.close();
    }
}

package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.util.List;

/**
 * Created by Настя on 25.03.2017.
 */
public class DBHelper {

    private final SessionFactory sessionFactory;

    public DBHelper(){
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();

    }

    public Groups groups(){

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery( "from GroupData" ).list();
        session.getTransaction().commit();
        session.close();
        return  new Groups(result);
    }

    public Users users(){

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> result = session.createQuery( "from Userdata where deprecated = '0000-00-00'" ).list();
        session.getTransaction().commit();
        session.close();
        return  new Users(result);
    }


}

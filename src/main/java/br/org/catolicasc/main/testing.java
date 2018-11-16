package br.org.catolicasc.main;

import br.org.catolicasc.dao.*;
import br.org.catolicasc.model.Role;
import br.org.catolicasc.model.State;
import br.org.catolicasc.model.Status;
import br.org.catolicasc.model.User;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class testing {
    public static void main(String[] args) {
        // create DataBase
        // deve ser nessa ordem por causa das relações
        // dentro das classe DAO, quando chama o super, o primeiro paramentro é o drop table (está true)

        // tem que testar
        CategoryDao.getNewInstance();
        CompanyDao.getNewInstance();
        ProductDao.getNewInstance();
        UserDao.getNewInstance();
        VendorDao.getNewInstance();
        InvoiceEntriesDao.getNewInstance();
        InvoiceProductsDao.getNewInstance();

        //UserDao.getNewInstance().modify(new User("admin","rua dali","xaraguá","123", State.SC, Role.ADMIN, Status.ACTIVE));

        List<User> listUser =  UserDao.getNewInstance().getAllWithWhere("name='admin'");
        if(!listUser.isEmpty()){
            System.out.println(listUser.get(0).getName());
        }else{
            System.out.println("não encontrado");
        }
    }
}

package br.org.catolicasc.main;

import br.org.catolicasc.dao.*;
import br.org.catolicasc.model.Role;
import br.org.catolicasc.model.State;
import br.org.catolicasc.model.Status;
import br.org.catolicasc.model.User;

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

        //UserUi user = new UserUi("guizao","rua dali","xaraguá","123", State.SC, Role.ADMIN, Status.ACTIVE);
        //UserDao.getNewInstance().insert(user);
        System.out.println(UserDao.getNewInstance().getById(1).toString());
        System.out.println(UserDao.getNewInstance().getAllWithWhere("name='admin'"));
    }
}

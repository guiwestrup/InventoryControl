package br.org.catolicasc.main;

import br.org.catolicasc.dao.*;

public class testing {
    public static void main(String[] args) {
        // create DataBase
        // deve ser nessa ordem por causa das relações
        // dentro das classe DAO, quando chama o super, o primeiro paramentro é o drop table (está true)

        System.out.println("\nExecutando...");

        // tem que testar
        CategoryDao.getNewInstance();
        ProductDao.getNewInstance();
        UserDao.getNewInstance();
        VendorDao.getNewInstance();
        InvoiceEntriesDao.getNewInstance();
        InvoiceProductsDao.getNewInstance();

        //User user = new User("guizao","rua dali","xaraguá","123", State.SC, Role.ADMIN, Status.ACTIVE);

    }
}

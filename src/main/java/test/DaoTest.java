package test;

import br.org.catolicasc.dao.CategoryDao;
import br.org.catolicasc.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DaoTest {
    @Test
    public void CategoryDaoTest() {
        CategoryDao categoryDao = CategoryDao.getNewInstance();

        Category cat1 = new Category();
        cat1.setName("Plastico");
        categoryDao.insert(cat1);

        Assertions.assertTrue(cat1.getId() != -1);

        cat1.setName("Metal");

        categoryDao.update(cat1);

        int id = cat1.getId();
        cat1 = null;

        for (Category c: categoryDao.getAll()) {
            if(c.getId() == id){
                cat1 = c;
            }
        }

        Assertions.assertNotNull(cat1);
        Assertions.assertEquals("Metal", cat1.getName());

        categoryDao.deleteById(cat1.getId());

        if(categoryDao.getAll().isEmpty()){
            Assertions.assertTrue(true);
        }else{
            Assertions.fail("List Is Not Empty");
        }
    }
}

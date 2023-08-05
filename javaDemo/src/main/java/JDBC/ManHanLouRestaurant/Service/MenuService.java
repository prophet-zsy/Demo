package JDBC.ManHanLouRestaurant.Service;

import JDBC.ManHanLouRestaurant.DAO.MenuDAO;
import JDBC.ManHanLouRestaurant.Domain.Menu;

import java.util.List;

// todo service层还要再写sql语句么？（完全不建议sql语句放在service层，sql语句只停留在DAO层就好）
public class MenuService {
    private MenuDAO menuDAO = new MenuDAO();

    public List<Menu> list () {
        return menuDAO.queryMulti("select * from menu", Menu.class);
    }

    public Menu getMenuById(int id) {
        return menuDAO.querySingle("select * from menu where id = ?", Menu.class, id);
    }
}

package JDBC.ManHanLouRestaurant.Service;

import JDBC.ManHanLouRestaurant.DAO.DiningTableDAO;
import JDBC.ManHanLouRestaurant.Domain.DiningTable;

import java.util.List;

// todo service层还要再写sql语句么？（完全不建议sql语句放在service层，sql语句只停留在DAO层就好）
public class DiningTableService {
    private DiningTableDAO diningTableDAO = new DiningTableDAO();

    public List<DiningTable> list() {
        return diningTableDAO.queryMulti("select * from diningTable", DiningTable.class);
    }

    public DiningTable getDiningTable(int id) {
        return diningTableDAO.querySingle("select * from diningTable where id = ?", DiningTable.class, id);
    }

    public Boolean bookDiningTable(int id, String orderName, String orderTel) {
        int update = diningTableDAO.update("update diningTable set state = '已经预定', orderName = ?, orderTel = ? where id = ?", orderName, orderTel, id);
        return update > 0;
    }

    public Boolean setDiningTableState(int id, String state) {
        int update = diningTableDAO.update("update diningTable set state = ? where id = ?", state, id);
        return update > 0;
    }

    public boolean setDiningTableFree(int id) {
        int update = diningTableDAO.update("update diningTable set state = '空闲', orderName = '', orderTel = '' where id = ?", id);
        return update > 0;
    }

}

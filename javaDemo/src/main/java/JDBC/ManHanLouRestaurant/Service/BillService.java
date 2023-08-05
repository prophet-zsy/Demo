package JDBC.ManHanLouRestaurant.Service;

import JDBC.ManHanLouRestaurant.DAO.BillDAO;
import JDBC.ManHanLouRestaurant.DAO.MultiBeanDAO;
import JDBC.ManHanLouRestaurant.Domain.Bill;
import JDBC.ManHanLouRestaurant.Domain.MultiBean;

import java.util.Date;
import java.util.List;
import java.util.UUID;

// todo service层还要再写sql语句么？（完全不建议sql语句放在service层，sql语句只停留在DAO层就好）
public class BillService {
    private BillDAO billDAO = new BillDAO();
    private MultiBeanDAO multiBeanDAO = new MultiBeanDAO();
    private MenuService menuService = new MenuService();
    private DiningTableService diningTableService = new DiningTableService();

    public List<Bill> list() {
        return billDAO.queryMulti("select * from bill", Bill.class);
    }

    public List<MultiBean> listFromMulti() {
        return multiBeanDAO.queryMulti("select bill.*, menu.name as menuName, menu.price as menuPrice from bill, menu where bill.menuId = menu.id", MultiBean.class);
    }

    public Boolean orderMenu(int menuId, int nums, int diningTableId) {
        String billId = UUID.randomUUID().toString();
        double money = menuService.getMenuById(menuId).getPrice() * nums;
        int update = billDAO.update("insert into bill values(null, ?, ?, ?, ?, ?, now(), '未结账')", billId, menuId, nums, money, diningTableId);
        if (update <= 0) {
            return false;
        }
        return diningTableService.setDiningTableState(diningTableId, "就餐中");
    }

    public Boolean hasBillToPay(int diningTableId) {
        Object numToPay = billDAO.queryScalar("select count(*) from bill where diningTableId = ?", diningTableId);
        return (long) numToPay > 0;
    }

    public Boolean payBill(int diningTableId, String payMode) {
        // TODO: 2022/1/27 如果这里使用事务的话，需要使用ThreadLocal来解决，框架中比如mybatis提供了事务支持
        
        int update = billDAO.update("update bill set state = ? where diningTableId = ?", payMode, diningTableId);
        if (update <= 0) {
            return false;
        }
        return diningTableService.setDiningTableFree(diningTableId);
    }

}

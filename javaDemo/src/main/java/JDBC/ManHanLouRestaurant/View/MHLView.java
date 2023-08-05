package JDBC.ManHanLouRestaurant.View;

import JDBC.ManHanLouRestaurant.Domain.*;
import JDBC.ManHanLouRestaurant.Service.BillService;
import JDBC.ManHanLouRestaurant.Service.DiningTableService;
import JDBC.ManHanLouRestaurant.Service.EmployeeService;
import JDBC.ManHanLouRestaurant.Service.MenuService;
import JDBC.ManHanLouRestaurant.Utils.Utility;

import java.util.List;
import java.util.Objects;

public class MHLView {
    private Boolean loop = true;
    private String key = "";

    private EmployeeService employeeService = new EmployeeService();
    private DiningTableService diningTableService = new DiningTableService();
    private MenuService menuService = new MenuService();
    private BillService billService = new BillService();

    public static void main(String[] args) {
        new MHLView().mainMenu();
    }

    private void mainMenu() {
        while (loop) {
            System.out.println("================满汉楼================");
            System.out.println("\t\t 1 登录满汉楼");
            System.out.println("\t\t 2 退出满汉楼");
            System.out.print("请输入你的选择：");
            key = Utility.readString();
            switch (key) {
                case "1":
                    System.out.print("输入员工号：");
                    String empId = Utility.readString();
                    System.out.print("输入密码：");
                    String pwd = Utility.readString();
                    Employee employee = employeeService.getEmployeeByIdAndPwd(empId, pwd);
                    if (employee != null) {
                        System.out.println("================登录成功[" + employee.getName() + "]================");
                        while (loop) {
                            System.out.println("================满汉楼二级菜单================");
                            System.out.println("\t\t 1 显示餐桌状态");
                            System.out.println("\t\t 2 预定餐桌");
                            System.out.println("\t\t 3 显示所有菜品");
                            System.out.println("\t\t 4 点餐服务");
                            System.out.println("\t\t 5 查看账单");
                            System.out.println("\t\t 6 结账");
                            System.out.println("\t\t 9 退出满汉楼");
                            System.out.print("请输入你的选择：");
                            key = Utility.readString();
                            switch (key) {
                                case "1":
                                    listDiningTable();
                                    break;
                                case "2":
                                    bookDiningTable();
                                    break;
                                case "3":
                                    listMenu();
                                    break;
                                case "4":
                                    orderMenu();
                                    break;
                                case "5":
//                                    listBill();
                                    listBillWithMenuInfo();
                                    break;
                                case "6":
                                    payBill();
                                    break;
                                case "9":
                                    loop = false;
                                    System.out.println("你退出了满汉楼系统~");
                                    break;
                                default:
                                    System.out.println("你的输入有误，请重新输入.");
                            }
                        }
                    } else {
                        System.out.println("================登录失败================");
                    }
                    break;
                case "2":
                    loop = false;
                    break;
                default:
                    System.out.println("你的输入有误，请重新输入.");
            }
        }
    }

    private void payBill() {
        System.out.println("================结账服务================");
        System.out.print("请选择要结账的餐桌编号（-1退出）：");
        // TODO: 2022/1/26 可以直接读取成int的类型
        String diningTableId = Utility.readString();
        if (diningTableId.equals("-1")) {
            System.out.println("================取消结账================");
            return;
        }
//        校验餐桌是否存在，是否未结账
        DiningTable diningTable = diningTableService.getDiningTable(Integer.parseInt(diningTableId));
        if (diningTable == null) {
            System.out.println("================餐桌不存在================");
            return;
        }
        if (! billService.hasBillToPay(Integer.parseInt(diningTableId))) {
            System.out.println("================餐桌已经结账================");
            return;
        }

        System.out.print("请选择付款方式（现金/支付宝/微信）：");
        // TODO: 2022/1/26 这里未校验输入内容是否只为 现金/支付宝/微信 三种中的一种
        String payMode = Utility.readString();
        if (billService.payBill(Integer.parseInt(diningTableId), payMode)) {
            System.out.println("================完成结账================");
        } else {
            System.out.println("================结账失败================");
        }
    }

    private void listBill() {
        List<Bill> list = billService.list();
        System.out.println("\n编号\t\t菜品号\t菜品量\t金额\t\t桌号\t\t日期\t\t\t\t\t\t状态");
        for (Bill bill : list) {
            System.out.println(bill.getId() + "\t\t" + bill.getMenuId() +
                                "\t\t" + bill.getNums() + "\t\t" + bill.getMoney() + "\t" +
                                bill.getDiningTableId() + "\t\t" + bill.getBillDate()
                                + "\t\t" + bill.getState());
        }
        System.out.println("================显示完毕================");
    }

    private void listBillWithMenuInfo() {
        List<MultiBean> list = billService.listFromMulti();
        System.out.println("\n编号\t\t菜品号\t菜品名\t菜品单价\t\t菜品量\t金额\t\t桌号\t\t日期\t\t\t\t\t\t状态");
        for (MultiBean multiBean : list) {
            System.out.println(multiBean.getId() + "\t\t" + multiBean.getMenuId() + "\t\t" +
                    multiBean.getMenuName() + "\t" + multiBean.getMenuPrice() +
                    "\t\t" + multiBean.getNums() + "\t\t" + multiBean.getMoney() + "\t" +
                    multiBean.getDiningTableId() + "\t\t" + multiBean.getBillDate()
                    + "\t\t" + multiBean.getState());
        }
        System.out.println("================显示完毕================");
    }

    private void orderMenu() {
        System.out.print("请输入点餐的桌号（-1退出）：");
        String diningTableId = Utility.readString();
        if (Objects.equals(diningTableId, "-1")) {
            System.out.println("================取消点餐================");
            return;
        }
//        先校验并获取餐桌，如果不存在，后续无意义
        DiningTable diningTable = diningTableService.getDiningTable(Integer.parseInt(diningTableId));
        if (diningTable == null) {
            System.out.println("================餐桌不存在================");
            return;
        }

        System.out.print("请输入点餐的菜品号（-1退出）：");
        String menuId = Utility.readString();
        if (Objects.equals(menuId, "-1")) {
            System.out.println("================取消点餐================");
            return;
        }
//        先校验并获取菜品，如果不存在，后续无意义
        Menu menu = menuService.getMenuById(Integer.parseInt(menuId));
        if (menu == null) {
            System.out.println("================菜品不存在================");
            return;
        }

        System.out.print("请输入点餐的菜品量（-1退出）：");
        String menuNum = Utility.readString();
        if (Objects.equals(menuNum, "-1")) {
            System.out.println("================取消点餐================");
            return;
        }

        if (billService.orderMenu(Integer.parseInt(menuId), Integer.parseInt(menuNum), Integer.parseInt(diningTableId))) {
            System.out.println("================点单成功================");
        } else {
            System.out.println("================点单失败================");
        }
    }

    private void listDiningTable () {
        List<DiningTable> list = diningTableService.list();
        System.out.println("\n餐桌编号\t 餐桌状态");
        for (DiningTable diningTable : list) {
            System.out.println("\t" + diningTable.getId() + "\t\t" + diningTable.getState());
        }
        System.out.println("================显示完毕================");
    }

    private void listMenu() {
        List<Menu> list = menuService.list();
        System.out.println("\n菜品编号\t\t名称\t\t种类\t\t价格");
        for (Menu menu : list) {
            System.out.println(menu.getId() + "\t\t\t" + menu.getName() + "\t\t" + menu.getType() + "\t\t" + menu.getPrice());
        }
        System.out.println("================显示完毕================");
    }

    private void bookDiningTable() {
        System.out.println("================预定餐桌================");
        System.out.print("请输入要预定的餐桌编号（-1退出）：");
        key = Utility.readString();
        if ("-1".equals(key)) {
            System.out.println("================取消预定餐桌================");
            return;
        }
        System.out.println("确定预定餐桌" + key + "么？（y or n）");
        if (Utility.readString().equals("y")) {
            DiningTable diningTable = diningTableService.getDiningTable(Integer.parseInt(key));
            if (diningTable == null) {
                System.out.println("================餐桌不存在================");
                return;
            }
            if (! diningTable.getState().equals("空")) {
                System.out.println("================该餐桌已经预定或就餐中================");
                return;
            }
            System.out.print("预定人的名字：");
            String orderName = Utility.readString();
            System.out.print("预定人的电话：");
            String orderTel = Utility.readString();
            if (diningTableService.bookDiningTable(Integer.parseInt(key), orderName, orderTel)) {
                System.out.println("================预定餐桌成功================");
            } else {
                System.out.println("================预定餐桌失败================");
            }
        } else {
            System.out.println("================取消预定餐桌================");
        }
    }
}

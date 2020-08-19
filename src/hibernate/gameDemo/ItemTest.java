package hibernate.gameDemo;

public class ItemTest {
    public static void main(String[] args) {
        ModuleItem modItem = new ModuleItem(100001);
        modItem.showItems();

        System.out.println("新增道具----------------------------");
        modItem.addItem(101, "帽子", 1);
        modItem.addItem(102, "鞋", 2);
        modItem.addItem(103, "上衣", 3);
        modItem.addItem(104, "武器", 4);
        modItem.showItems();

        System.out.println("删除道具----------------------------");
        modItem.removeItem(103);
        modItem.showItems();

        System.out.println("更新道具----------------------------");
        modItem.updateItem(104, "武器", 1);
        modItem.showItems();


    }
}

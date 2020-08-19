package hibernate.gameDemo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleItem {
    private int humanId;
    Map<Integer, ItemDB> itemDBMap = new HashMap<>();

    public ModuleItem(int humanId) {
        this.humanId = humanId;
        loadData();
    }

    private void loadData() {
        List<?> itemDBs = ModuleDB.findByHumanId(ItemDB.class, humanId);
        itemDBs.forEach(item -> {
            ItemDB itemDB = (ItemDB) item;
            itemDBMap.put(itemDB.getId(), itemDB);
        });
    }

    public boolean addItem(int id, String name, int num) {
        ItemDB itemDB = itemDBMap.get(id);
        if (itemDB != null) {
            return false;
        }

        itemDB = new ItemDB();
        itemDB.setId(id);
        itemDB.setName(name);
        itemDB.setNum(num);
        itemDB.setHumanId(humanId);

        itemDBMap.put(itemDB.getId(), itemDB);
        itemDB.insert();
        return true;
    }

    public boolean removeItem(int id) {
        ItemDB itemDB = itemDBMap.get(id);
        if (itemDB == null) {
            return false;
        }

        itemDBMap.remove(id);
        itemDB.delete();
        return true;
    }

    public boolean updateItem(int id, String name, int num) {
        ItemDB itemDB = itemDBMap.get(id);
        if (itemDB == null) {
           return false;
        }

        itemDB.setName(name);
        itemDB.setNum(num);
        itemDB.setHumanId(humanId);

        itemDB.update();
        return true;
    }

    public void showItems() {
        itemDBMap.values().forEach(itemDB -> System.out.println(itemDB));
    }

}

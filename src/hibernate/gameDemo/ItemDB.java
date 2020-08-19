package hibernate.gameDemo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class ItemDB {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "humanId")
    private long humanId;
    @Column(name = "name")
    private String name;
    @Column(name = "num")
    private int num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getHumanId() {
        return humanId;
    }

    public void setHumanId(long humanId) {
        this.humanId = humanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "ItemDB{" +
                "id=" + id +
                ", humanId=" + humanId +
                ", name='" + name + '\'' +
                ", num=" + num +
                '}';
    }

    /**
     * 增
     */
    public void insert() {
        ModuleDB.insert(this);
    }

    /**
     * 删
     */
    public void delete() {
        ModuleDB.delete(this);
    }

    /**
     * 改
     */
    public void update() {
        ModuleDB.update(this);
    }

}

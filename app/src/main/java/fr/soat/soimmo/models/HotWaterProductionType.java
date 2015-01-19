package fr.soat.soimmo.models;

public class HotWaterProductionType {

    private Long id;

    private String name;

    public HotWaterProductionType() {
    }

    public HotWaterProductionType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HotWaterProductionType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

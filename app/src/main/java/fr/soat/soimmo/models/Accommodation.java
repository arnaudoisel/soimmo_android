package fr.soat.soimmo.models;


import java.util.Date;

public class Accommodation {

    private Long id;
    private Address address;
    private Double surface;
    private Integer rooms;
    private Integer floor;
    private Boolean elevator;
    private Double rent;
    private Double charge;
    private Date availableFrom;
    private Boolean active;
    private AccommodationType type;
    private HotWaterProductionType hotWaterProductionType;
    private HeatingType heatingType;

    public Accommodation() {
    }

    public Accommodation(long id, Address address, Double surface, Integer rooms, Integer floor, Boolean elevator, Double rent, Double charge, Date availableFrom, Boolean active, AccommodationType type, HotWaterProductionType hotWaterProductionType, HeatingType heatingType) {
        this.address = address;
        this.surface = surface;
        this.rooms = rooms;
        this.floor = floor;
        this.elevator = elevator;
        this.rent = rent;
        this.charge = charge;
        this.availableFrom = availableFrom;
        this.active = active;
        this.type = type;
        this.hotWaterProductionType = hotWaterProductionType;
        this.heatingType = heatingType;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Double getSurface() {
        return surface;
    }

    public void setSurface(Double surface) {
        this.surface = surface;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Boolean getElevator() {
        return elevator;
    }

    public void setElevator(Boolean elevator) {
        this.elevator = elevator;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
    }

    public Date getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(Date availableFrom) {
        this.availableFrom = availableFrom;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public AccommodationType getType() {
        return type;
    }

    public void setType(AccommodationType type) {
        this.type = type;
    }

    public HotWaterProductionType getHotWaterProductionType() {
        return hotWaterProductionType;
    }

    public void setHotWaterProductionType(HotWaterProductionType hotWaterProductionType) {
        this.hotWaterProductionType = hotWaterProductionType;
    }

    public HeatingType getHeatingType() {
        return heatingType;
    }

    public void setHeatingType(HeatingType heatingType) {
        this.heatingType = heatingType;
    }

    @Override
    public String toString() {
        return "Accommodation{" +
                "id=" + id +
                ", address=" + address +
                ", surface=" + surface +
                ", rooms=" + rooms +
                ", floor=" + floor +
                ", elevator=" + elevator +
                ", rent=" + rent +
                ", charge=" + charge +
                ", availableFrom=" + availableFrom +
                ", active=" + active +
                ", type=" + type +
                ", hotWaterProductionType=" + hotWaterProductionType +
                ", heatingType=" + heatingType +
                '}';
    }
}

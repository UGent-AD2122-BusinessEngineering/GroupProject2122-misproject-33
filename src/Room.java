import java.util.Objects;

public class Room {
    private Room room;
    private String address;
    private enum insulationType {Insulated, notInsulated}
    private int area;
    private enum characteristics {semi_detached, detached, terraced}
    //zie https://ec.europa.eu/info/energy-climate-change-environment/standards-tools-and-labels/products-labelling-rules-and-requirements/energy-label-and-ecodesign/about_en
    private enum energyLabel {A, B, C, D, E, F, G}
    private enum appliances {fridges_and_freezers, dishwashers, washing_machines, televisions, lamps}

    public Room(Room room, String address, int area) {
        this.room = room;
        this.address = address;
        this.area = area;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room1 = (Room) o;
        return area == room1.area
                && Objects.equals(room, room1.room)
                && Objects.equals(address, room1.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room, address, area);
    }
}

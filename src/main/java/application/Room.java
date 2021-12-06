package application;

import java.util.Objects;

public class Room extends Appliance {
    private int roomid;
    private String roomnumber;

    public Room() {

    }

    public Room(int roomid, String roomnumber) {
        super();
        this.roomid = roomid;
        this.roomnumber = roomnumber;
    }


    public int getRoomid() {
        return roomid;
    }

    public String getRoomnumber() {
        return roomnumber;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public void setRoomnumber(String roomnumber) {
        this.roomnumber = roomnumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomid == room.roomid && Objects.equals(roomnumber, room.roomnumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomid, roomnumber);
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomid=" + roomid +
                ", roomnumber='" + roomnumber + '\'' +
                '}';
    }
}

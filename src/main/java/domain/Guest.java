package domain;

public class Guest {
    private String id;
    private String name;
    private String room;

    public Guest(String id, String name, String room, String phone){
        this.id = id;
        this.name = name;
        this.room = room;
        this.phone = phone;
    }

    public Guest(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String phone;
}

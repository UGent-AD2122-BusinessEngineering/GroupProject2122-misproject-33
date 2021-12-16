package models;

import application.Student;

public class AddStudentModel {

    private int roomid;
    private Student student;
    private Boolean contactPersoon;
    private String studentemail;
    private String landlordemail;

    private String email;
    private String firstname;
    private String lastname;

    private int locationID;

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Boolean getContactPersoon() {
        return contactPersoon;
    }

    public void setContactPersoon(Boolean contactPersoon) {
        this.contactPersoon = contactPersoon;
    }

    public String getStudentemail() {
        return studentemail;
    }

    public void setStudentemail(String studentemail) {
        this.studentemail = studentemail;
    }

    public String getLandlordemail() {
        return landlordemail;
    }

    public void setLandlordemail(String landlordemail) {
        this.landlordemail = landlordemail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }
}

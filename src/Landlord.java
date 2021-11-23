import java.util.Objects;

public class Landlord {
    private Landlord landlord;
    private String firstname_landlord;
    private String lastname_landlord;
    private String email_landlord;
    private String phone_landlord;

    public Landlord getLandlord() {
        return landlord;
    }

    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }

    public String getFirstname_landlord() {
        return firstname_landlord;
    }

    public void setFirstname_landlord(String firstname_landlord) {
        this.firstname_landlord = firstname_landlord;
    }

    public String getLastname_landlord() {
        return lastname_landlord;
    }

    public void setLastname_landlord(String lastname_landlord) {
        this.lastname_landlord = lastname_landlord;
    }

    public String getEmail_landlord() {
        return email_landlord;
    }

    public void setEmail_landlord(String email_landlord) {
        this.email_landlord = email_landlord;
    }

    public String getTelefoonnummer_landlord() {
        return phone_landlord;
    }

    public void setTelefoonnummer_landlord(String telefoonnummer_landlord) {
        this.phone_landlord = telefoonnummer_landlord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Landlord landlord1 = (Landlord) o;
        return Objects.equals(landlord, landlord1.landlord)
                && Objects.equals(firstname_landlord, landlord1.firstname_landlord)
                && Objects.equals(lastname_landlord, landlord1.lastname_landlord)
                && Objects.equals(email_landlord, landlord1.email_landlord)
                && Objects.equals(phone_landlord, landlord1.phone_landlord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(landlord, firstname_landlord, lastname_landlord, email_landlord, phone_landlord);
    }
}

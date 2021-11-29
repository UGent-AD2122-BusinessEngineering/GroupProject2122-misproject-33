package application;
import java.time.LocalDate;

//ik zou hier alle methodes doen om te interageren met de gebruiker voor studentroomadministration,
// ik werk er morgen ook aan verder

public class StudentRoomAdministration {
    private int dagVdMaand;

    public StudentRoomAdministration(){
        dagVdMaand = LocalDate.now().getDayOfMonth();
    }

    //methode en als dagvdmaand 1 is allemaal dingen met scanner vragen

}

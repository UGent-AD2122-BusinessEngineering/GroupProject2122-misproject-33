public class AdditionException extends Exception {
    public AdditionException(Student student) {
        super("The student '" + student.toString() + "'" + " already exists!");
    }
    public AdditionException(Landlord landlord) {
        super("The landlord '" + landlord.toString() + "'" + " already exists!");
    }
}

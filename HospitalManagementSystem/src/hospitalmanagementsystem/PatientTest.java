package hospitalmanagementsystem;

public class PatientTest {

    public static void main(String[] args) {

        PatientDAO dao = new PatientDAO();

        int patientId = 4;

        if (dao.deletePatient(patientId)) {
            System.out.println("PATIENT DELETED SUCCESSFULLY!");
        } else {
            System.out.println("PATIENT DELETE FAILED!");
        }
    }
}
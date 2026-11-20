package hospitalmanagementsystem;

public class DoctorTest {

    public static void main(String[] args) {

        DoctorDAO dao = new DoctorDAO();

        int doctorId = 4;

        if (dao.deleteDoctor(doctorId)) {

            System.out.println(
                    "DOCTOR DELETED SUCCESSFULLY!"
            );

        } else {

            System.out.println(
                    "DOCTOR DELETE FAILED!"
            );
        }
    }
}
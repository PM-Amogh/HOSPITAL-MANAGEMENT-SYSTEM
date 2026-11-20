package hospitalmanagementsystem;

public class AppointmentTest {

    public static void main(String[] args) {

        AppointmentDAO dao =
                new AppointmentDAO();

        int appointmentId = 4;

        if (dao.deleteAppointment(
                appointmentId)) {

            System.out.println(
                    "APPOINTMENT DELETED SUCCESSFULLY!"
            );

        } else {

            System.out.println(
                    "APPOINTMENT DELETE FAILED!"
            );
        }
    }
}
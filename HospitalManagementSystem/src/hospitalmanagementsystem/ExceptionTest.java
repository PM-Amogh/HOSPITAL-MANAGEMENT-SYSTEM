package hospitalmanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExceptionTest {

    public static void main(String[] args) {

        String sql =
                "INSERT INTO appointments "
                + "(patient_id, doctor_id, appointment_date, "
                + "appointment_time, reason, status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            // Invalid Patient ID and Doctor ID
            // used intentionally to test exception handling
            ps.setInt(1, 9999);
            ps.setInt(2, 9999);

            ps.setDate(
                    3,
                    java.sql.Date.valueOf("2026-09-10")
            );

            ps.setTime(
                    4,
                    java.sql.Time.valueOf("10:00:00")
            );

            ps.setString(
                    5,
                    "Exception Testing"
            );

            ps.setString(
                    6,
                    "Scheduled"
            );

            ps.executeUpdate();

            System.out.println(
                    "APPOINTMENT INSERTED SUCCESSFULLY!"
            );

        } catch (SQLException e) {

            System.out.println(
                    "EXCEPTION HANDLED SUCCESSFULLY!"
            );

            System.out.println(
                    "ERROR MESSAGE: "
                    + e.getMessage()
            );
        }
    }
}
package hospitalmanagementsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    // CREATE
    public boolean addAppointment(Appointment a) {

        String sql = "INSERT INTO appointments "
                + "(patient_id, doctor_id, appointment_date, "
                + "appointment_time, reason, status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, a.getPatientId());
            ps.setInt(2, a.getDoctorId());
            ps.setDate(3, a.getAppointmentDate());
            ps.setTime(4, a.getAppointmentTime());
            ps.setString(5, a.getReason());
            ps.setString(6, a.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "ERROR ADDING APPOINTMENT: "
                    + e.getMessage()
            );

            return false;
        }
    }

    // READ
    public List<Appointment> getAllAppointments() {

        List<Appointment> list = new ArrayList<>();

        String sql = "SELECT * FROM appointments";

        try (
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)
        ) {

            while (rs.next()) {

                Appointment a = new Appointment();

                a.setAppointmentId(
                        rs.getInt("appointment_id")
                );

                a.setPatientId(
                        rs.getInt("patient_id")
                );

                a.setDoctorId(
                        rs.getInt("doctor_id")
                );

                a.setAppointmentDate(
                        rs.getDate("appointment_date")
                );

                a.setAppointmentTime(
                        rs.getTime("appointment_time")
                );

                a.setReason(
                        rs.getString("reason")
                );

                a.setStatus(
                        rs.getString("status")
                );

                list.add(a);
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR READING APPOINTMENTS: "
                    + e.getMessage()
            );
        }

        return list;
    }

    // UPDATE
    public boolean updateAppointment(Appointment a) {

        String sql = "UPDATE appointments SET "
                + "patient_id=?, doctor_id=?, "
                + "appointment_date=?, appointment_time=?, "
                + "reason=?, status=? "
                + "WHERE appointment_id=?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, a.getPatientId());
            ps.setInt(2, a.getDoctorId());
            ps.setDate(3, a.getAppointmentDate());
            ps.setTime(4, a.getAppointmentTime());
            ps.setString(5, a.getReason());
            ps.setString(6, a.getStatus());
            ps.setInt(7, a.getAppointmentId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "ERROR UPDATING APPOINTMENT: "
                    + e.getMessage()
            );

            return false;
        }
    }

    // DELETE
    public boolean deleteAppointment(int id) {

        String sql =
                "DELETE FROM appointments "
                + "WHERE appointment_id=?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "ERROR DELETING APPOINTMENT: "
                    + e.getMessage()
            );

            return false;
        }
    }
}
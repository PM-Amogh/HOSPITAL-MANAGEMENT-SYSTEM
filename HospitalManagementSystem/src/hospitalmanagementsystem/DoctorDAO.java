package hospitalmanagementsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    // CREATE
    public boolean addDoctor(Doctor d) {

        String sql = "INSERT INTO doctors "
                + "(name, specialization, phone, email) "
                + "VALUES (?, ?, ?, ?)";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, d.getName());
            ps.setString(2, d.getSpecialization());
            ps.setString(3, d.getPhone());
            ps.setString(4, d.getEmail());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "ERROR ADDING DOCTOR: "
                    + e.getMessage()
            );

            return false;
        }
    }

    // READ
    public List<Doctor> getAllDoctors() {

        List<Doctor> list = new ArrayList<>();

        String sql = "SELECT * FROM doctors";

        try (
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)
        ) {

            while (rs.next()) {

                Doctor d = new Doctor();

                d.setDoctorId(
                        rs.getInt("doctor_id")
                );

                d.setName(
                        rs.getString("name")
                );

                d.setSpecialization(
                        rs.getString("specialization")
                );

                d.setPhone(
                        rs.getString("phone")
                );

                d.setEmail(
                        rs.getString("email")
                );

                list.add(d);
            }

        } catch (SQLException e) {

            System.out.println(
                    "ERROR READING DOCTORS: "
                    + e.getMessage()
            );
        }

        return list;
    }

    // UPDATE
    public boolean updateDoctor(Doctor d) {

        String sql = "UPDATE doctors SET "
                + "name=?, specialization=?, phone=?, email=? "
                + "WHERE doctor_id=?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, d.getName());
            ps.setString(2, d.getSpecialization());
            ps.setString(3, d.getPhone());
            ps.setString(4, d.getEmail());
            ps.setInt(5, d.getDoctorId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "ERROR UPDATING DOCTOR: "
                    + e.getMessage()
            );

            return false;
        }
    }

    // DELETE
    public boolean deleteDoctor(int id) {

        String sql =
                "DELETE FROM doctors WHERE doctor_id=?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                    "ERROR DELETING DOCTOR: "
                    + e.getMessage()
            );

            return false;
        }
    }
}
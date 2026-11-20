package hospitalmanagementsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public boolean addPatient(Patient p) {

        String sql = "INSERT INTO patients "
                + "(name, age, gender, phone, email, address) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, p.getName());
            ps.setInt(2, p.getAge());
            ps.setString(3, p.getGender());
            ps.setString(4, p.getPhone());
            ps.setString(5, p.getEmail());
            ps.setString(6, p.getAddress());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("ERROR ADDING PATIENT: " + e.getMessage());
            return false;
        }
    }

    public List<Patient> getAllPatients() {

        List<Patient> list = new ArrayList<>();

        String sql = "SELECT * FROM patients";

        try (
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)
        ) {

            while (rs.next()) {

                Patient p = new Patient();

                p.setPatientId(rs.getInt("patient_id"));
                p.setName(rs.getString("name"));
                p.setAge(rs.getInt("age"));
                p.setGender(rs.getString("gender"));
                p.setPhone(rs.getString("phone"));
                p.setEmail(rs.getString("email"));
                p.setAddress(rs.getString("address"));

                list.add(p);
            }

        } catch (SQLException e) {
            System.out.println("ERROR READING PATIENTS: " + e.getMessage());
        }

        return list;
    }

    public boolean updatePatient(Patient p) {

        String sql = "UPDATE patients SET "
                + "name=?, age=?, gender=?, phone=?, email=?, address=? "
                + "WHERE patient_id=?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, p.getName());
            ps.setInt(2, p.getAge());
            ps.setString(3, p.getGender());
            ps.setString(4, p.getPhone());
            ps.setString(5, p.getEmail());
            ps.setString(6, p.getAddress());
            ps.setInt(7, p.getPatientId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("ERROR UPDATING PATIENT: " + e.getMessage());
            return false;
        }
    }

    public boolean deletePatient(int id) {

        String sql = "DELETE FROM patients WHERE patient_id=?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("ERROR DELETING PATIENT: " + e.getMessage());
            return false;
        }
    }
}
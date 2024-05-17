package dao;

import domain.Guest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class GuestDao {
    private Connection connection;

    public GuestDao(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Guest> showAll() throws SQLException {
        String sql = "SELECT * FROM Guests ORDER BY name";
        ArrayList<Guest> guests = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Guest guest = new Guest();
            guest.setId(resultSet.getString("id_personal"));
            guest.setName(resultSet.getString("name"));
            guest.setRoom(resultSet.getString("room"));
            guest.setPhone(resultSet.getString("phone_number"));
            guests.add(guest);
        }
        return guests;
    }

    public void add(Guest guest) throws SQLException {
        if (existGuest(guest.getId()))
            System.out.print("This guest already exists");
        else {
            String sql = "INSERT INTO Guests (id_personal, name, room, phone_number) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, guest.getId());
            statement.setString(2, guest.getName());
            statement.setString(3, guest.getRoom());
            statement.setString(4, guest.getPhone());
            statement.executeUpdate();
        }
    }

    public Optional<Guest> findById(String id) throws SQLException {
        String sql = "SELECT * FROM Guests WHERE id_personal = ?";
        Guest guest = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            guest = new Guest();
            guest.setId(resultSet.getString("id_personal"));
            guest.setName(resultSet.getString("name"));
            guest.setRoom(resultSet.getString("room"));
            guest.setPhone(resultSet.getString("phone_number"));
        }

        return Optional.ofNullable(guest);
    }

    private boolean existGuest(String id) throws SQLException {
        Optional<Guest> guest = findById(id);
        return guest.isPresent();
    }

    public boolean delete(String id) {
        try {
            String sql = "DELETE FROM Guests WHERE id_personal = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            int rows = statement.executeUpdate();
            return rows == 1;
        }catch (SQLException sqle){
            System.out.println("Problems probably related with this guest appearing in other tables");
            return false;
        }
    }

    public boolean modify(String id, Guest guest) throws SQLException {
        if (!existGuest(guest.getId())){
            System.out.print("This guest doesn't exists");
            return false;
        }else{
            String sql = "UPDATE Guests SET id_personal = ?, name = ?, room = ? phone_number = ? WHERE id_personal = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, guest.getId());
            statement.setString(2, guest.getName());
            statement.setString(3, guest.getRoom());
            statement.setString(4, guest.getPhone());
            statement.setString(5, guest.getId());

            int rows = statement.executeUpdate();
            return rows == 1;
        }
    }
}

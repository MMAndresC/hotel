package dao;

import domain.Bicycle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class BicycleDao {
    private Connection connection;

    public BicycleDao(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Bicycle> showAll() throws SQLException {
        String sql = "SELECT * FROM Bicycles ORDER BY brand";
        ArrayList<Bicycle> bicycles = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Bicycle bicycle = new Bicycle();
            bicycle.setCod(resultSet.getInt("cod_bicycle"));
            bicycle.setBrand(resultSet.getString("brand"));
            bicycle.setModel(resultSet.getString("model_bicycle"));
            bicycle.setSize(resultSet.getString("size_bicycle"));
            bicycle.setType(resultSet.getString("type_bicycle"));
            bicycle.setImage(resultSet.getString("image"));
            bicycles.add(bicycle);
        }
        return bicycles;
    }

    public Bicycle findByCod(int cod) throws SQLException {
        String sql = "SELECT * FROM Bicycles WHERE cod_bicycle = ?";
        Bicycle bicycle = new Bicycle(-1, "", "", "", "", "");

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cod);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            bicycle.setCod(resultSet.getInt("cod_bicycle"));
            bicycle.setBrand(resultSet.getString("brand"));
            bicycle.setModel(resultSet.getString("model_bicycle"));
            bicycle.setType(resultSet.getString("type_bicycle"));
            bicycle.setSize(resultSet.getString("size_bicycle"));
            bicycle.setImage(resultSet.getString("image"));
        }

        return bicycle;
    }

    private boolean existBicycle(int cod) throws SQLException {
        Bicycle bicycle = findByCod(cod);
        return bicycle.getCod() == -1;
    }

    private int nextIndex() throws SQLException {
        String sql = "SELECT MAX(cod_bicycle) FROM Bicycles";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt("MAX(cod_bicycle)") + 1;
    }
    public void add(Bicycle bicycle) throws SQLException {
        int index = nextIndex();
        if(index > 0){
            String sql = "INSERT INTO Bicycles (cod_bicycle, brand, model_bicycle, type_bicycle, size_bicycle, image) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, index);
            statement.setString(2, bicycle.getBrand());
            statement.setString(3, bicycle.getModel());
            statement.setString(4, bicycle.getType());
            statement.setString(5, bicycle.getSize());
            statement.setString(6, bicycle.getImage());
            statement.executeUpdate();
        }else{
            System.out.print("Error getting index");
        }

    }

    public boolean delete(int cod) {
        try {
            String sql = "DELETE FROM Bicycles WHERE cod_bicycle = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cod);
            int rows = statement.executeUpdate();
            return rows == 1;
        }catch (SQLException sqle){
            System.out.println("Problems probably related with this book appearing in other tables");
            return false;
        }
    }

    public boolean modify(int cod, Bicycle bicycle) throws SQLException {
        if (!existBicycle(bicycle.getCod())){
            System.out.print("This bicycle doesn't exists");
            return false;
        }else{
            String sql = "UPDATE Bicycles SET cod_bicycle = ?, brand = ?, model_bicycle = ?, type_bicycle = ?, size_bicycle = ?, image = ? WHERE cod_bicycle = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, bicycle.getCod());
            statement.setString(2, bicycle.getBrand());
            statement.setString(3, bicycle.getModel());
            statement.setString(4, bicycle.getType());
            statement.setString(5, bicycle.getSize());
            statement.setString(6, bicycle.getImage());
            statement.setInt(7, bicycle.getCod());

            int rows = statement.executeUpdate();
            return rows == 1;
        }
    }
}

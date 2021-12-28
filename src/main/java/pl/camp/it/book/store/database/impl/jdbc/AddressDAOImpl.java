package pl.camp.it.book.store.database.impl.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IAddressDAO;
import pl.camp.it.book.store.model.Address;

import java.sql.*;
import java.util.Optional;

@Repository
public class AddressDAOImpl implements IAddressDAO {

    @Autowired
    Connection connection;

    @Override
    public void addAddress(Address address) {
        try {
            String sql = "INSERT INTO taddress (id, name, surname, address, postalCode, city, phone) VALUES (NULL, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, address.getName());
            preparedStatement.setString(2, address.getSurname());
            preparedStatement.setString(3, address.getAddress());
            preparedStatement.setString(4, address.getPostalCode());
            preparedStatement.setString(5, address.getCity());
            preparedStatement.setString(6, address.getPhone());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            address.setId(rs.getInt(1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<Address> getAddressById(int id) {
        try {
            String sql = "SELECT * FROM taddress WHERE id = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                Address address = new Address();
                address.setId(rs.getInt("id"));
                address.setName(rs.getString("name"));
                address.setSurname(rs.getString("surname"));
                address.setAddress(rs.getString("address"));
                address.setCity(rs.getString("city"));
                address.setPostalCode(rs.getString("postalCode"));
                address.setPhone(rs.getString("phone"));
                return Optional.of(address);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }
}

package pl.camp.it.book.store.database.impl.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IAddressDAO;
import pl.camp.it.book.store.database.IOrderDAO;
import pl.camp.it.book.store.database.IOrderPositionDAO;
import pl.camp.it.book.store.database.IUserDAO;
import pl.camp.it.book.store.model.Address;
import pl.camp.it.book.store.model.Order;
import pl.camp.it.book.store.model.OrderPosition;
import pl.camp.it.book.store.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class OrderDAOImpl implements IOrderDAO {

    @Autowired
    Connection connection;

    @Autowired
    IAddressDAO addressDAO;

    @Autowired
    IOrderPositionDAO orderPositionDAO;

    @Autowired
    IUserDAO userDAO;

    @Override
    public void addOrder(Order order) {
        try {
            this.addressDAO.addAddress(order.getAddress());
            String sql = "INSERT INTO torder (id, address_id, user_id, status) VALUES (NULL, ?, ?, ?)";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, order.getAddress().getId());
            preparedStatement.setInt(2, order.getUser().getId());
            preparedStatement.setString(3, order.getStatus().name());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            order.setId(rs.getInt(1));
            for(OrderPosition orderPosition : order.getOrderPositions()) {
                this.orderPositionDAO.addOrderPosition(orderPosition, order.getId());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Order> getOrdersByUserLogin(String login) {
        List<Order> result = new ArrayList<>();
        Optional<User> userBox = this.userDAO.getUserByLogin(login);
        if(!userBox.isPresent()) {
            return result;
        }
        try {
            String sql = "SELECT * FROM torder WHERE user_id = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, userBox.get().getId());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setUser(userBox.get());
                order.setStatus(Order.Status.valueOf(rs.getString("status")));
                order.setId(rs.getInt("id"));
                Optional<Address> addressBox = this.addressDAO.getAddressById(rs.getInt("address_id"));
                if(addressBox.isPresent()) {
                    order.setAddress(addressBox.get());
                }
                //order.setOrderPositions(this.orderPositionDAO.getOrderPositionsByOrderId(order.getId()));
                result.add(order);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}

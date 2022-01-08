package pl.camp.it.book.store.database.impl.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.database.IOrderPositionDAO;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.model.OrderPosition;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class OrderPositionDAOImpl implements IOrderPositionDAO {

    @Autowired
    Connection connection;

    @Autowired
    IBookDAO bookDAO;

    @Override
    public void addOrderPosition(OrderPosition orderPosition, int orderId) {
        try {
            String sql = "INSERT INTO torderposition (id, order_id, book_id, positionquantity) VALUES (NULL, ?, ?, ?)";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, orderPosition.getBook().getId());
            preparedStatement.setInt(3, orderPosition.getPositionQuantity());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            orderPosition.setId(rs.getInt(1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<OrderPosition> getOrderPositionsByOrderId(int orderId) {
        List<OrderPosition> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM torderposition WHERE order_id = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                OrderPosition orderPosition = new OrderPosition();
                orderPosition.setId(rs.getInt("id"));
                orderPosition.setPositionQuantity(rs.getInt("positionquantity"));
                Optional<Book> bookBox = this.bookDAO.getBookById(rs.getInt("book_id"));
                if(bookBox.isPresent()) {
                    orderPosition.setBook(bookBox.get());
                }
                result.add(orderPosition);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}

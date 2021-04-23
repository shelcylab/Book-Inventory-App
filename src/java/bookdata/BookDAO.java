/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shelc
 */
public class BookDAO {

    String jdbcURL = "jdbc:derby://localhost:1527/sample;create=true;user=app;password=app";

    private static final String ADD_BOOK = "INSERT INTO bookstore" + " (bookName, authorName, publisher) VALUES "
            + " (?, ?, ?)";
    private static final String DELETE_BOOK = "delete from bookstore where serialNumber = ?";
    private static final String SELECT_ALL_BOOKS = "select * from bookstore";
    private static final String UPDATE_BOOK = "update bookstore set bookName = ?,authorName= ?, publisher =? where serialNumber = ?";

    public BookDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {

            connection = DriverManager.getConnection(jdbcURL);
        } catch (SQLException e) {

            e.printStackTrace();

        }
        return connection;
    }

    public void insertBook(Book book) throws SQLException {
        System.out.println(ADD_BOOK);
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(ADD_BOOK)) {
            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setString(2, book.getAuthorName());
            preparedStatement.setString(3, book.getPublisher());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public boolean deleteBook(int serialNumber) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_BOOK)) {
            statement.setInt(1, serialNumber);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public List< Book> selectAllBooks() {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List< Book> books = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS)) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int serialNumber = rs.getInt("serialNumber");
                String bookName = rs.getString("bookName");
                String authorName = rs.getString("authorName");
                String publisher = rs.getString("publisher");
                books.add(new Book(serialNumber, bookName, authorName, publisher));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return books;
    }

    public boolean updateBook(Book book) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_BOOK)) {
            statement.setString(1, book.getBookName());
            statement.setString(2, book.getAuthorName());
            statement.setString(3, book.getPublisher());
            statement.setInt(4, book.getSerialNumber());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}

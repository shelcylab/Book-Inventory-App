/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookdata;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shelc
 */
@WebServlet("/")
public class BookServlet extends HttpServlet {

    private BookDAO bookDAO;

    public void init() {
        bookDAO = new BookDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertBook(request, response);
                    break;
                case "/delete":
                    deleteBook(request, response);
                    break;

                default:
                    listBook(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List< Book> listBook = bookDAO.selectAllBooks();
        request.setAttribute("listBook", listBook);
        RequestDispatcher dispatcher = request.getRequestDispatcher("booklist.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addbook.jsp");
        dispatcher.forward(request, response);
    }

    private void insertBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String bookName = request.getParameter("bookName");
        String authorName = request.getParameter("authorName");
        String publisher = request.getParameter("publisher");
        Book newBook = new Book(bookName, authorName, publisher);
        bookDAO.insertBook(newBook);
        response.sendRedirect("list");
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int serialNumber = Integer.parseInt(request.getParameter("serialNumber"));
        bookDAO.deleteBook(serialNumber);
        response.sendRedirect("list");

    }
}

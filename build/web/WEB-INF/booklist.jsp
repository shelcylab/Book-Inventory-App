<%-- 
    Document   : booklist
    Created on : 27 Nov, 2020, 5:34:46 PM
    Author     : shelc
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <title>Book Inventory Management</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>

    <body>

        <header>
            <nav class="navbar navbar-expand-md navbar-dark" style="background-color: blue">
                <div>


                </div>

                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Books</a></li>
                </ul>
            </nav>
        </header>
        <br>

        <div class="row">


            <div class="container">
                <h3 class="text-center">List of Books</h3>
                <hr>

                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Serial Number</th>
                            <th>Book Name</th>
                            <th>Author Name</th>
                            <th>Publisher</th>
                            <th> </th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach var="book" items="${listBooks}">
                            <tr>
                                <td>
                                    <c:out value="${book.serialNumber}" />
                                </td>
                                <td>
                                    <c:out value="${book.bookName}" />
                                </td>
                                <td>
                                    <c:out value="${book.authorName}" />
                                </td>
                                <td>
                                    <c:out value="${book.publiher}" />
                                </td>
                                <td> <a href="delete?id=<c:out value='${book.serialNumber}' />">Delete</a></td>
                            </tr>
                        </c:forEach>
                        <!-- } -->
                    </tbody>
                    <hr>
                    <div class="container text-bottom">

                        <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
                            New Book</a>
                    </div>
                </table>
            </div>
        </div>
    </body>

</html>
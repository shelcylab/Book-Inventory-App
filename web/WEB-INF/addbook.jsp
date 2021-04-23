<%-- 
    Document   : addbook
    Created on : 27 Nov, 2020, 5:44:13 PM
    Author     : shelc
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <title>Add Book Details</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>

    <body>

        <header>
            <nav class="navbar navbar-expand-md navbar-dark" style="background-color: buttonface">
                <div>
                    <a href="https://www.javaguides.net" class="navbar-brand"> Book Inventory App </a>
                </div>

                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Books</a></li>
                </ul>
            </nav>
        </header>
        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <c:if test="${book != null}">
                        <form action="update" method="post">
                        </c:if>
                        <c:if test="${book == null}">
                            <form action="insert" method="post">
                            </c:if>

                            <caption>
                                <h2>
                                    <c:if test="${book != null}">
                                        Edit book
                                    </c:if>
                                    <c:if test="${book == null}">
                                        Add New Book
                                    </c:if>
                                </h2>
                            </caption>

                            <c:if test="${book != null}">
                                <input type="hidden" name="id" value="<c:out value='${book.serialNumber}' />" />
                            </c:if>

                            <fieldset class="form-group">
                                <label>Book Name</label> <input type="text" value="<c:out value='${book.bookName}' />" class="form-control" name="bookName" required="required">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Book Author</label> <input type="text" value="<c:out value='${book.authorName}' />" class="form-control" name="authorName">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Book Publisher</label> <input type="text" value="<c:out value='${book.publisher}' />" class="form-control" name="publisher">
                            </fieldset>

                            <button type="submit" class="btn btn-success">Save</button>
                        </form>
                </div>
            </div>
        </div>
    </body>

</html>

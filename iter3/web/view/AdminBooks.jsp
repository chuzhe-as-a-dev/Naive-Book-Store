<%--
  Created by IntelliJ IDEA.
  User: tang
  Date: 2017/5/8
  Time: 17:25
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <meta name="description" content="Naive Bookstore - A Web Application Development course project">
    <meta name="author" content="Tang Chuzhe">
    <!--    <link rel="icon" href="">-->
    <title>Naïve Bookstore Admin - Books</title>

    <!-- Bootstrap -->
    <link href="../css/framework/bootstrap.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../css/framework/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/navbar-adjust.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="../js/framework/html5shiv.js"></script>
    <script src="../js/framework/respond.js"></script>
    <![endif]-->

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../js/framework/jquery-3.2.0.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/framework/bootstrap.js"></script>
    <script src="../js/framework/parsley.js"></script>
    <script src="../js/framework/i18n/zh_cn.extra.js"></script>
    <script src="../js/admin_books.js"></script>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <!-- nav title -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
            </button>
            <a class="navbar-brand" href="#">Naïve Bookstore Admin</a>
        </div>

        <!-- nav tags -->
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="<s:url action="books"/>">Book</a></li>
                <li><a href="<s:url action="users"/>">User</a></li>
                <li><a href="<s:url action="orders"/>">Order</a></li>
                <li><a href="<s:url action="statistics"/>">Statistics</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <!--header-->
                <div class="panel-heading">
                    <h3 class="panel-title">Book Records</h3>
                </div>

                <!-- Table -->
                <div class="table-responsive">
                    <table class="table table-striped ">
                        <thead>
                        <tr>
                            <th>
                                <button type="button" class="btn btn-default btn-xs add-button" aria-label="Left Align"
                                        data-toggle="modal" data-target="#edit-modal">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                </button>
                            </th>
                            <th>Book Name</th>
                            <th>ISBN</th>
                            <th>Author</th>
                            <th>Price</th>
                            <th>Stock</th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="books">
                            <tr>
                                <th scope="row"><s:property value="bookId"/></th>
                                <td class="book-name"><s:property value="bookName"/></td>
                                <td class="isbn"><s:property value="isbn"/></td>
                                <td class="author"><s:property value="author"/></td>
                                <td class="price"><s:property value="price"/></td>
                                <td class="stock"><s:property value="stock"/></td>
                                <td class="description hidden"><s:property value="description"/></td>
                                <td width="40">
                                    <button type="button" class="btn btn-default btn-xs edit-button" data-toggle="modal"
                                            data-target="#edit-modal">Edit
                                    </button>
                                </td>
                                <td width="100">
                                    <button type="button" class="btn btn-danger btn-xs remove-button">Remove
                                    </button>
                                </td>
                            </tr>
                        </s:iterator>
                        </tbody>
                    </table>
                </div>
                <!--footer-->
                <div class="panel-footer"><s:property value="books.size()"/> books in store</div>
            </div>
        </div>
    </div>
</div>

<%-- book edit modal --%>
<div class="modal fade" tabindex="-1" role="dialog" id="edit-modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Edit book</h4>
            </div>
            <div class="modal-body">
                <form id="info-form" method="post">
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="control-label" for="book-name">Book name</label>
                            <input type="text" name="bookName" id="book-name" class="form-control"
                                   placeholder="Book name" required maxlength="255" data-parsley-trigger="change"
                                   data-parsley-errors-messages-disabled>
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="control-label" for="author">Author</label>
                            <input type="text" name="author" class="form-control" id="author" placeholder="Author"
                                   required maxlength="255" data-parsley-trigger="change"
                                   data-parsley-errors-messages-disabled>
                            <span class="help-block"></span>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-4">
                            <label class="control-label" for="isbn">ISBN</label>
                            <input type="text" name="isbn" class="form-control" id="isbn" placeholder="ISBN" required
                                   data-parsley-is-isbn data-parsley-type="alphanum" data-parsley-trigger="change"
                                   data-parsley-errors-messages-disabled>
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group col-md-4">
                            <label class="control-label" for="price">Price</label>
                            <input name="price" class="form-control" id="price" data-parsley-type="number" step="0.01"
                                   min="0.01" max="99999999.99" placeholder="Price" required
                                   data-parsley-trigger="change" data-parsley-errors-messages-disabled>
                            <span class="help-block"></span>
                        </div>
                        <div class="form-group col-md-4">
                            <label class="control-label" for="stock">Stock</label>
                            <input name="stock" class="form-control" id="stock" data-parsley-type="integer" min="1"
                                   max="99999999999" placeholder="Stock" required data-parsley-trigger="change"
                                   data-parsley-errors-messages-disabled>
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="control-label" for="description">Description</label>
                            <textarea name="description" class="form-control" rows="8" id="description"
                                      placeholder="Description" required data-parsley-trigger="change"
                                      data-parsley-errors-messages-disabled></textarea>
                            <span class="help-block"></span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" id="modal-submit-button" class="btn btn-primary" data-dismiss="modal">Submit
                </button>
            </div>
        </div>
    </div>
</div>
</body>
</html>

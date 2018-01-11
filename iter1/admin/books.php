<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <meta name="description" content="Naive Bookstore - A Web Application Development course project">
    <meta name="author" content="Tang Chuzhe">
<!--    <link rel="icon" href="">-->
    <title>Naïve Bookstore</title>

    <!-- Bootstrap -->
    <link href="../css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/customized.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="../js/html5shiv.js"></script>
    <script src="../js/respond.js"></script>
    <![endif]-->
</head>
<body>
<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Naïve Bookstore Admin</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="../">Back to customer page</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="./users.php">CRUD users</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="./orders.php">CRUD orders</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="./books.php">CRUD books</a></li>
            </ul>
        </div><!--/.nav-collapse-->
    </div>
</nav>

<!--view book records-->
<?php require "../src/conn.php"; ?>
<div class="container">
    <div class="container col-md-12">
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
                        <th>#</th>
                        <th>Book Name</th>
                        <th>ISBN</th>
                        <th>Author</th>
                        <th>Price</th>
                        <th>Stock</th>
                    </tr>
                    </thead>
                    <tbody>
                    <?php
                    $sql = "SELECT book_id, book_name, isbn, author, price, stock
                        FROM book_basic";
                    $result = $conn->query($sql);

                    if ($result->num_rows > 0) {
                        while ($row = $result->fetch_assoc()) {
                            echo "<tr>";
                            echo "<th scope=\"row\">" . $row["book_id"] . "</th>";
                            echo "<td>" . $row["book_name"] . "</td>";
                            echo "<td>" . $row["isbn"] . "</td>";
                            echo "<td>" . $row["author"] . "</td>";
                            echo "<td>$" . $row["price"] . "</td>";
                            echo "<td>" . $row["stock"] . "</td>";
                            echo "</tr>";
                        }
                    }
                    ?>
                    </tbody>
                </table>
            </div>
            <!--footer-->
            <div class="panel-footer"><?php echo $result->num_rows ?> books in store</div>
        </div>
    </div>
</div>
<?php $conn->close(); ?>


<div class="container">
    <!--add book records-->
    <div class="container col-md-4">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Add new books</h3>
            </div>
            <div class="panel-body">
                <form action="../src/admin/add_book.php">
                    <div class="form-group">
                        <label>Book name</label>
                        <input type="text" class="form-control" name="new_book_name" placeholder="Book name">
                    </div>
                    <div class="form-group">
                        <label>ISBN</label>
                        <input type="text" class="form-control" name="new_book_isbn" placeholder="ISBN">
                    </div>
                    <div class="form-group">
                        <label>Author</label>
                        <input type="text" class="form-control" name="new_book_author" placeholder="Author">
                    </div>
                    <div class="form-group">
                        <label>Price</label>
                        <input type="number" step="0.01" min="0.01" class="form-control" name="new_book_price" placeholder="Price">
                    </div>
                    <div class="form-group">
                        <label>Amount</label>
                        <input type="number" min="1" class="form-control" name="new_book_amount" placeholder="Amount">
                    </div>
                    <div class="form-group">
                        <label>URL of book cover</label>
                        <input type="url" class="form-control" name="new_book_cover_url" placeholder="URL">
                    </div>
                    <div class="form-group">
                        <label>Description</label>
                        <textarea class="form-control" rows="3" name="new_book_description" placeholder="Description"></textarea>
                    </div>
                    <button type="submit" class="btn btn-default" onclick="return checkNewBook();">Submit</button>
                </form>
            </div>
        </div>
    </div>

    <!--update book records-->
    <div class="container col-md-4">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Update book information</h3>
            </div>
            <div class="panel-body">
                <form action="../src/admin/update_book.php">
                    <div class="form-group">
                        <label>Book ID</label>
                        <input type="number" min="0" class="form-control" name="book_id" id="update_book_id" placeholder="Book ID">
                    </div>
                    <div class="form-group">
                        <label>Book name</label>
                        <input type="text" class="form-control" name="new_book_name" placeholder="Book name">
                    </div>
                    <div class="form-group">
                        <label>ISBN</label>
                        <input type="text" class="form-control" name="new_book_isbn" placeholder="ISBN">
                    </div>
                    <div class="form-group">
                        <label>Author</label>
                        <input type="text" class="form-control" name="new_book_author" placeholder="Author">
                    </div>
                    <div class="form-group">
                        <label>Price</label>
                        <input type="number" step="0.01" min="0.01" class="form-control" name="new_book_price" placeholder="Price">
                    </div>
                    <div class="form-group">
                        <label>Stock</label>
                        <input type="number" min="1" class="form-control" name="new_book_amount" placeholder="Stock">
                    </div>
                    <div class="form-group">
                        <label>URL of book cover</label>
                        <input type="url" class="form-control" name="new_book_cover_url" placeholder="URL">
                    </div>
                    <div class="form-group">
                        <label>Description</label>
                        <textarea class="form-control" rows="3" name="new_book_description" placeholder="Description"></textarea>
                    </div>
                    <button type="submit" class="btn btn-default" onclick="checkUpdateBook()">Submit</button>
                </form>
            </div>
        </div>
    </div>

    <!--remove book records-->
    <div class="container col-md-4">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Remove book records</h3>
            </div>
            <div class="panel-body">
                <form action="../src/admin/remove_book.php">
                    <div class="form-group">
                        <label>Book ID</label>
                        <input type="number" min="0" class="form-control" name="delete_book_id" placeholder="Book ID">
                    </div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="confirm">I am aware that removal is <b>irreversible</b>.
                        </label>
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div>
        </div>
    </div>

    <!--command file-->
    <div class="container col-md-4">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">File execution</h3>
            </div>
            <div class="panel-body">
                <form action="../src/admin/command_book.php">
                    <div class="form-group">
                        <label for="command_file">Command file</label>
                        <input type="file" name="command_file">
                        <p class="help-block">File for large scale execution.</p>
                    </div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="confirm">I am aware that execution is <b>irreversible</b>.
                        </label>
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!--footer-->
<div class="container">
    <footer class="footer">
        <p>&copy; 2017 Tang Chuzhe</p>
    </footer>
</div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../js/ie10-viewport-bug-workaround.js"></script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.min.js"></script>

<script src="../js/admin/books.js"></script>
</body>
</html>
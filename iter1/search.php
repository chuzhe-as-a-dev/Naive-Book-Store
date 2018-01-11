<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <meta name="description" content="Naive Bookstore - A Web Application Development course project">
    <meta name="author" content="Tang Chuzhe">
<!--    <link rel="icon" href="">-->
    <title>Search | Naïve Bookstore</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/customized.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.js"></script>
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
            <a class="navbar-brand" href="#">Naïve Bookstore</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#">Cart</a></li>
                <li><a href="#">Account</a></li>
            </ul>
            <form class="navbar-form navbar-right" action="search.php" method="get">
                <div class="input-group">
                    <?php
                    if (isset($_GET['q'])) {
                        $placeholder = $_GET['q'];
                    } else {
                        $placeholder = "Search for...";
                    }
                    ?>

                    <input type="text" class="form-control" placeholder="<?php echo $placeholder; ?>" name="q">
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="button">Go!</button>
                    </span>
                </div>
            </form>
        </div><!--/.nav-collapse-->
    </div>
</nav>

<!--book grid-->
<div class="container">
    <div class="row marketing">
        <?php
        require "src/conn.php";
        if (isset($_GET["q"])) {
            $sql = "SELECT book_name, author, price, cover_url, description
                    FROM book_basic
                    WHERE stock > 0 AND book_name LIKE ?";
            $stmt = $conn->prepare($sql);
            $like_clause = "%" . $_GET['q'] . "%";
            $stmt->bind_param("s", $like_clause);
        } else {
            $sql = "SELECT book_name, author, price, cover_url, description
                    FROM book_basic
                    WHERE stock > 0";
            $stmt = $conn->prepare($sql);
        }

        if (!$stmt->execute()) {
            echo "fuck";
        }

        $result = $stmt->get_result();
        while ($row = $result->fetch_assoc()) {
            echo "<div class=\"col-xs-6 col-sm-4 col-md-3\">";
            echo "<div class=\"thumbnail\">";
            echo "<img src=\"" . $row["cover_url"] . "\" alt=\"...\">";
            echo "<div class=\"caption\">";
            echo "<h3>" . $row["book_name"] . " <small>" . $row["author"] . "</small></h3>";
            echo "<p>" . $row["description"] . "</p>";
            echo "<p><a href=\"#\" class=\"btn btn-default\" role=\"button\">Add to cart</a></p>";
            echo "</div></div></div>";
        }

        $conn->close();
        ?>
    </div>
</div> <!-- /container -->


<div class="container">
    <footer class="footer">
        <p>&copy; 2017 Tang Chuzhe</p>
    </footer>
</div>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="js/ie10-viewport-bug-workaround.js"></script>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>
<?php
/**
 * Created by PhpStorm.
 * User: tang
 * Date: 2017/3/10
 * Time: 21:38
 */


require "../../src/helper.php";


// check book id
if (!$_GET["book_id"]) {
    say_sorry("missing", "book id");
    return;
}
if ($_GET["book_id"] != (int)$_GET["book_id"] or $_GET["book_id"] <= 0) {
    say_sorry("book id", "invalid");
    return;
}
$book_id = (int)$_GET["book_id"];

// check ISBN, if any
if ($_GET["new_book_isbn"]) {
    $new_book_isbn = str_replace("-", "", $_GET["new_book_isbn"]);
    if (!is_numeric($new_book_isbn)) {
        say_sorry("invalid", "ISBN");
        return;
    }
}

// check price, if any
if ($_GET["new_book_price"]) {
    if (!is_numeric($_GET["new_book_price"]) or $_GET["new_book_price"] <= 0) {
        say_sorry("invalid", "price");
        return;
    }
    $new_book_price = (float)$_GET["new_book_price"];
}

// check amount, if any
if ($_GET["new_book_amount"]) {
    if ($_GET["new_book_amount"] != (int)$_GET["new_book_amount"] or $_GET["new_book_amount"] <= 0) {
        say_sorry("invalid", "amount");
        return;
    }
    $new_book_amount = (int)$_GET["new_book_amount"];
}


require "../../src/conn.php";

// check if this book already exists
$sql = "SELECT book_name, isbn, author, price, stock, cover_url, description FROM book_basic WHERE book_id = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $book_id);
if (!$stmt->execute()) {
    say_sorry("something bad", "happened");
    $stmt->close();
    $conn->close();
    return;
}

$result = $stmt->get_result();
if ($result->num_rows != 1) {
    say_sorry("no such", "book id");
    $stmt->close();
    $conn->close();
    return;
}

// if book exists
$row = $result->fetch_assoc();

$book_name = $_GET["new_book_name"] ? $_GET["new_book_name"] : $row["book_name"];
$isbn = $_GET["new_book_isbn"] ? $new_book_isbn : $row["isbn"];
$author = $_GET["new_book_author"] ? $_GET["author"] : $row["author"];
$price = $_GET["new_book_price"] ? $new_book_price : $row["price"];
$stock = $_GET["new_book_amount"] ? $new_book_amount : $row["stock"];
$url = $_GET["new_book_cover_url"] ? $_GET["new_book_cover_url"] : $row["cover_url"];
$description = $_GET["new_book_description"] ? $_GET["new_book_description"] : $row["description"];

// find existing infos
$sql = "UPDATE book_basic
        SET book_name = ?, isbn = ?, author = ?, price = ?, stock = ?, cover_url = ?, description = ?
        WHERE book_id = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("sssdissi", $book_name, $isbn, $author, $price, $stock, $url, $description, $book_id);
if (!$stmt->execute()) {
    say_sorry("something bad", "happened");
    $stmt->close();
    $conn->close();
    return;
}

$stmt->close();
$conn->close();
header('Location: ../../admin/books.php');

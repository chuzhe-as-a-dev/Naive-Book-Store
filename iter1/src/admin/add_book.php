<?php
/**
 * Created by PhpStorm.
 * User: tang
 * Date: 2017/3/10
 * Time: 21:37
 */

require "../../src/helper.php";

// check book name
if (!$_GET["new_book_name"]) {
    say_sorry("missing", "book name");
    return;
}
$new_book_name = $_GET["new_book_name"];

// check ISBN
if (!$_GET["new_book_isbn"]) {
    say_sorry("missing", "book ISBN");
    return;
}
$new_book_isbn = str_replace("-", "", $_GET["new_book_isbn"]);
if (!is_numeric($new_book_isbn)) {
    say_sorry("invalid", "ISBN");
    return;
}

// check author
if (!$_GET["new_book_author"]) {
    say_sorry("missing", "book author");
    return;
}
$new_book_author = $_GET["new_book_author"];

// check price
if (!$_GET["new_book_price"]) {
    say_sorry("missing", "book price");
    return;
}
if (!is_numeric($_GET["new_book_price"]) or $_GET["new_book_price"] <= 0) {
    say_sorry("invalid", "price");
    return;
}
$new_book_price = (float)$_GET["new_book_price"];

// check amount
if (!$_GET["new_book_amount"]) {
    say_sorry("missing", "added amount");
    return;
}
if ($_GET["new_book_amount"] != (int)$_GET["new_book_amount"] or $_GET["new_book_amount"] <= 0) {
    say_sorry("invalid", "amount");
    return;
}
$new_book_amount = (int)$_GET["new_book_amount"];


// check if this book already exists
require "../../src/conn.php";

$sql = "SELECT COUNT(*) AS num FROM book_basic WHERE isbn = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("s", $new_book_isbn);
if ($stmt->execute()) {
    $result = $stmt->get_result();

    // if book exists
    if ($result->fetch_assoc()["num"] == 1) {
        // find existing infos
        $sql = "SELECT book_id, book_name, author, price, stock FROM book_basic WHERE isbn = ?";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("s", $new_book_isbn);
        if (!$stmt->execute()) {
            say_sorry("something bad", "happened");
            $stmt->close();
            $conn->close();
            return;
        }
        $result = $stmt->get_result();
        $old_book_info = $result->fetch_assoc();

        // compare if two are in coordination
        if ($old_book_info["book_name"] != $new_book_name or
            $old_book_info["author"] != $new_book_author or
            $old_book_info["price"] != $new_book_price) {
            say_sorry("book exists", "incompatible!");
            $stmt->close();
            $conn->close();
            return;
        }

        $stmt->close();
        // prepare update statement
        $new_book_stock = $old_book_info["stock"] + $new_book_amount;
        if ($_GET["new_book_cover_url"]) {
            if ($_GET["new_book_description"]) {
                $sql = "UPDATE book_basic SET stock = ?, cover_url = ?, description = ? WHERE book_id = ?";
                $stmt = $conn->prepare($sql);
                $stmt->bind_param("issi", $new_book_stock, $_GET["new_book_cover_url"], $_GET["new_book_description"], $old_book_info["book_id"]);
            } else {
                $sql = "UPDATE book_basic SET stock = ?, cover_url = ? WHERE book_id = ?";
                $stmt = $conn->prepare($sql);
                $stmt->bind_param("issi", $new_book_stock, $_GET["new_book_cover_url"], $old_book_info["book_id"]);
            }
        } else {
            if ($_GET["new_book_description"]) {
                $sql = "UPDATE book_basic SET stock = ?, description = ? WHERE book_id = ?";
                $stmt = $conn->prepare($sql);
                $stmt->bind_param("issi", $new_book_stock, $_GET["new_book_description"], $old_book_info["book_id"]);
            } else {
                $sql = "UPDATE book_basic SET stock = ?, WHERE book_id = ?";
                $stmt = $conn->prepare($sql);
                $stmt->bind_param("issi", $new_book_stock, $old_book_info["book_id"]);
            }
        }

        if (!$stmt->execute()) {
            say_sorry("something bad", "happened");
            $stmt->close();
            $conn->close();
            return;
        }
    } else {  // if no such book exists
        $sql = "INSERT INTO book_basic (book_name, isbn, author, price, stock, cover_url, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("sssdiss", $new_book_name, $new_book_isbn, $new_book_author, $new_book_price, $new_book_amount, $_GET["new_book_cover_url"], $_GET["new_book_description"]);
        if (!$stmt->execute()) {
            say_sorry("something bad", "happened");
            $stmt->close();
            $conn->close();
            return;
        }
    }
} else {
    say_sorry("something bad", "happened");
    $stmt->close();
    $conn->close();
    return;
}

header('Location: ../../admin/books.php');

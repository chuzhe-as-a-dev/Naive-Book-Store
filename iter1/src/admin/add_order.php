<?php
/**
 * Created by PhpStorm.
 * User: tang
 * Date: 2017/3/10
 * Time: 21:37
 */

require "../../src/helper.php";

// check buyer id
if (!$_GET["buyer_id"]) {
    say_sorry("missing", "buyer id");
    return;
}
if ($_GET["buyer_id"] != (int)$_GET["buyer_id"] or $_GET["buyer_id"] <= 0) {
    say_sorry("invalid", "buyer id");
    return;
}
$buyer_id = (int)$_GET["buyer_id"];

// check book id
if (!$_GET["book_id"]) {
    say_sorry("missing", "book id");
    return;
}
if ($_GET["book_id"] != (int)$_GET["book_id"] or $_GET["book_id"] <= 0) {
    say_sorry("invalid", "book id");
    return;
}
$book_id = (int)$_GET["book_id"];

// check amount
if (!$_GET["amount"]) {
    say_sorry("missing", "added amount");
    return;
}
if ($_GET["amount"] != (int)$_GET["amount"] or $_GET["amount"] <= 0) {
    say_sorry("invalid", "amount");
    return;
}
$amount = (int)$_GET["amount"];

// check placed time
if (!$_GET["placed_time"]) {
    say_sorry("missing", "placed time");
    return;
}
$placed_time = date("Y-m-d H:i:s", strtotime($_GET["placed_time"]));


require "../../src/conn.php";

// check if buyer id is valid
$sql = "SELECT balance FROM user_basic WHERE user_id = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $buyer_id);
if (!$stmt->execute()) {
    say_sorry("something bad", "happened");
    $stmt->close();
    $conn->close();
    return;
}
$result = $stmt->get_result();
if ($result->num_rows != 1) {
    say_sorry("no such user", "bad!");
    $stmt->close();
    $conn->close();
    return;
}

$old_balance = $result->fetch_assoc()["balance"];

// check if book id is valid
$sql = "SELECT stock, price FROM book_basic WHERE book_id = ?";
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
    say_sorry("no such book", "bad!");
    $stmt->close();
    $conn->close();
    return;
}

$row = $result->fetch_assoc();
$old_stock = $row["stock"];
$price = $row["price"];

// check number of books in store
if ($old_stock < $amount) {
    say_sorry("books", "not enough");
    $stmt->close();
    $conn->close();
    return;
}


// check if buyer is rich enough
if ($old_balance < $price * $amount) {
    say_sorry("money", "not enough");
    $stmt->close();
    $conn->close();
    return;
}


// actual insert
$sql = "INSERT INTO orders (user_id, book_id, amount, placed_time) VALUES (?, ?, ?, ?)";
$stmt = $conn->prepare($sql);
$stmt->bind_param("iiis", $buyer_id, $book_id, $amount, $placed_time);
if (!$stmt->execute()) {
    say_sorry("something bad", "happened");
    $stmt->close();
    $conn->close();
    return;
}


// change book stock
$new_stock = $old_stock - $amount;
$sql = "UPDATE book_basic SET stock = ? WHERE book_id = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("ii", $new_stock, $book_id);
if (!$stmt->execute()) {
    say_sorry("something bad", "happened");
    $stmt->close();
    $conn->close();
    return;
}


// change user balance
$new_balance = $old_balance - $price * $amount;
$sql = "UPDATE user_basic SET balance = ? WHERE user_id = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("di", $new_balance, $buyer_id);
if (!$stmt->execute()) {
    say_sorry("something bad", "happened");
    $stmt->close();
    $conn->close();
    return;
}


$stmt->close();
$conn->close();
header('Location: ../../admin/orders.php');

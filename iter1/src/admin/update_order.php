<?php
/**
 * Created by PhpStorm.
 * User: tang
 * Date: 2017/3/10
 * Time: 21:38
 */


require "../../src/helper.php";
require "../../src/conn.php";

// check order id
if (!$_GET["order_id"]) {
    say_sorry("missing", "order id");
    return;
}
if ($_GET["order_id"] != (int)$_GET["order_id"] or $_GET["order_id"] <= 0) {
    say_sorry("order id", "invalid");
    return;
}
$order_id = (int)$_GET["order_id"];

// check buyer id, if any
if ($_GET["buyer_id"]) {
    if ($_GET["buyer_id"] != (int)$_GET["buyer_id"] or $_GET["buyer_id"] <= 0) {
        say_sorry("buyer id", "invalid");
        return;
    }

    $sql = "SELECT 1 FROM user_basic WHERE user_id = ?";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("i", $_GET["buyer_id"]);
    if (!$stmt->execute()) {
        say_sorry("something bad", "happened");
        $stmt->close();
        $conn->close();
        return;
    }

    if ($stmt->get_result()->num_rows != 1) {
        say_sorry("no such", "user id");
        $stmt->close();
        $conn->close();
        return;
    }
}

// check book id, if any
if ($_GET["book_id"]) {
    if ($_GET["book_id"] != (int)$_GET["book_id"] or $_GET["book_id"] <= 0) {
        say_sorry("book id", "invalid");
        return;
    }

    $sql = "SELECT 1 FROM book_basic WHERE book_id = ?";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("i", $_GET["book_id"]);
    if (!$stmt->execute()) {
        say_sorry("something bad", "happened");
        $stmt->close();
        $conn->close();
        return;
    }

    if ($stmt->get_result()->num_rows != 1) {
        say_sorry("no such", "book id");
        $stmt->close();
        $conn->close();
        return;
    }
}

// check amount id, if any
if ($_GET["amount"]) {
    if ($_GET["amount"] != (int)$_GET["amount"] or $_GET["amount"] <= 0) {
        say_sorry("amount", "invalid");
        return;
    }
}


// check if this order exists
$sql = "SELECT user_id, book_id, amount, placed_time FROM orders WHERE order_id = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $order_id);
if (!$stmt->execute()) {
    say_sorry("something bad", "happened");
    $stmt->close();
    $conn->close();
    return;
}

$result = $stmt->get_result();
if ($result->num_rows != 1) {
    say_sorry("no such", "order id");
    $stmt->close();
    $conn->close();
    return;
}

// if order exists
$row = $result->fetch_assoc();

$user_id = $_GET["buyer_id"] ? (int)$_GET["buyer_id"] : $row["user_id"];
$book_id = $_GET["book_id"] ? (int)$_GET["book_id"] : $row["book_id"];
$amount = $_GET["amount"] ? (int)$_GET["amount"] : $row["amount"];
$time = $_GET["placed_time"] ? date("Y-m-d H:i:s", strtotime($_GET["placed_time"])) : $row["placed_time"];

// actual insert
$sql = "UPDATE orders
        SET user_id = ?, book_id = ?, amount = ?, placed_time = ?
        WHERE order_id = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("iiisi", $user_id, $book_id, $amount, $time, $order_id);
if (!$stmt->execute()) {
    say_sorry("something bad", "happened");
    $stmt->close();
    $conn->close();
    return;
}


$stmt->close();
$conn->close();
header('Location: ../../admin/orders.php');

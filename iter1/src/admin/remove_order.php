<?php
/**
 * Created by PhpStorm.
 * User: tang
 * Date: 2017/3/11
 * Time: 00:10
 */


require "../../src/helper.php";

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

// check checkbox
if (!isset($_GET["confirm"])) {
    say_sorry("too naive", "to delete");
    return;
}


require "../../src/conn.php";

// delete from orders
$sql = "DELETE FROM orders WHERE order_id = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $order_id);
if (!$stmt->execute()) {
    say_sorry("something bad", "happened");
    $stmt->close();
    $conn->close();
    return;
}

$stmt->close();
$conn->close();
header('Location: ../../admin/orders.php');
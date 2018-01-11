<?php
/**
 * Created by PhpStorm.
 * User: tang
 * Date: 2017/3/11
 * Time: 00:10
 */


require "../../src/helper.php";

// check user id
if (!$_GET["user_id"]) {
    say_sorry("missing", "user id");
    return;
}
if ($_GET["user_id"] != (int)$_GET["user_id"] or $_GET["user_id"] <= 0) {
    say_sorry("user id", "invalid");
    return;
}
$user_id = (int)$_GET["user_id"];

// check checkbox
if (!isset($_GET["confirm"])) {
    say_sorry("too naive", "to delete");
    return;
}


require "../../src/conn.php";

// delete from orders
$sql = "DELETE FROM user_basic WHERE user_id = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $user_id);
if (!$stmt->execute()) {
    say_sorry("something bad", "happened");
    $stmt->close();
    $conn->close();
    return;
}

$stmt->close();
$conn->close();
header('Location: ../../admin/users.php');
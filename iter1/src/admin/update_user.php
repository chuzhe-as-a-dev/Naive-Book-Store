<?php
/**
 * Created by PhpStorm.
 * User: tang
 * Date: 2017/3/10
 * Time: 21:38
 */

require "../../src/helper.php";

// check user id
if (!$_POST["user_id"]) {
    say_sorry("missing", "user id");
    return;
}
if ($_POST["user_id"] != (int)$_POST["user_id"] or $_POST["user_id"] <= 0) {
    say_sorry("user id", "invalid");
    return;
}
$user_id = (int)$_POST["user_id"];


require "../../src/conn.php";

// check if user exists
$sql = "SELECT user_name, user_pwd, balance FROM user_basic WHERE user_id = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $user_id);
if (!$stmt->execute()) {
    say_sorry("something bad 1", "happened");
    $stmt->close();
    $conn->close();
    return;
}

$result = $stmt->get_result();
if ($result->num_rows != 1) {
    say_sorry("no such", "user id");
    $stmt->close();
    $conn->close();
    return;
}

// update
$row = $result->fetch_assoc();
$user_name = $_POST["user_name"] ? $_POST["user_name"] : $row["user_name"];
$user_pwd = $_POST["password"] ? $_POST["password"] : $row["user_pwd"];
$balance = $_POST["balance"] ? $_POST["balance"] : $row["balance"];

$sql = "UPDATE user_basic SET user_name = ?, user_pwd = ?, balance = ? WHERE user_id = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("ssdi", $user_name, $user_pwd, $balance, $user_id);
if (!$stmt->execute()) {
    say_sorry("something bad 2", "happened");
    $stmt->close();
    $conn->close();
    return;
}

header('Location: ../../admin/users.php');

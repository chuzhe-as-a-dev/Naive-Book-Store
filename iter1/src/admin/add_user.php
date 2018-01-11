<?php
/**
 * Created by PhpStorm.
 * User: tang
 * Date: 2017/3/10
 * Time: 21:37
 */

require "../../src/helper.php";

// check user name
if (!$_POST["user_name"]) {
    say_sorry("missing", "username");
    return;
}
$user_name= $_POST["user_name"];

// check password
if (!$_POST["password"]) {
    say_sorry("missing", "password");
    return;
}
$password = $_POST["password"];

// check balance
if (!$_POST["balance"]) {
    say_sorry("missing", "balance");
    return;
}
$balance = (int)$_POST["balance"];


require "../../src/conn.php";

$sql = "INSERT INTO user_basic (user_name, user_pwd, balance) VALUES (?, ?, ?)";
$stmt = $conn->prepare($sql);
$stmt->bind_param("ssi", $user_name, $password, $balance);
if (!$stmt->execute()) {
    say_sorry("something bad", "happened");
    $stmt->close();
    $conn->close();
    return;
}

header('Location: ../../admin/users.php');

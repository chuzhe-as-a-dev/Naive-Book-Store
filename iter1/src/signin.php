<?php
/**
 * Created by PhpStorm.
 * User: tang
 * Date: 2017/3/21
 * Time: 10:36
 */

require "helper.php";

if (!$_POST["username"] or !$_POST["password"]) {
    say_sorry("complete", "the form");
    return;
}

require "conn.php";

$sql = "SELECT COUNT(*) AS num FROM user_basic WHERE user_name = ? and user_pwd = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("ss", $_POST["username"], $_POST["password"]);
if (!$stmt->execute()) {
    say_sorry("something bad", "happened");
    $stmt->close();
    $conn->close();
    return;
}

$result = $stmt->get_result();
if ($result->fetch_assoc()["num"] != 1) {
    say_sorry("username wrong", "or password");
    $stmt->close();
    $conn->close();
    return;
}

session_start();
$_SESSION["username"] = $_POST["username"];

$stmt->close();
$conn->close();
header('Location: ../index.php');

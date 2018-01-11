<?php
/**
 * Created by PhpStorm.
 * User: tang
 * Date: 2017/3/3
 * Time: 21:40
 */

$server = "localhost";
$username = "bookstore";
$password = "0000";
$dbname = "bookstore";

// Create connection
$conn = new mysqli($server, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

<?php
/**
 * Created by PhpStorm.
 * User: tang
 * Date: 2017/3/11
 * Time: 00:10
 */


require "../../src/helper.php";

// check book id
if (!$_GET["delete_book_id"]) {
    say_sorry("missing", "book id");
    return;
}
if ($_GET["delete_book_id"] != (int)$_GET["delete_book_id"] or $_GET["delete_book_id"] <= 0) {
    say_sorry("invalid id", "bad!");
    return;
}
$delete_book_id = (int)$_GET["delete_book_id"];

// check checkbox
if (!isset($_GET["confirm"])) {
    say_sorry("too naive", "to delete");
    return;
}


require "../../src/conn.php";
// delete from book basic
$sql = "DELETE FROM book_basic WHERE book_id = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $delete_book_id);
if (!$stmt->execute()) {
    say_sorry("something bad", "happened");
    $stmt->close();
    $conn->close();
    return;
}

// delete from book comments
$sql = "DELETE FROM book_comments WHERE book_id = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $delete_book_id);
if (!$stmt->execute()) {
    say_sorry("something bad", "happened");
    $stmt->close();
    $conn->close();
    return;
}

$stmt->close();
$conn->close();
header('Location: ../../admin/books.php');
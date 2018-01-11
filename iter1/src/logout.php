<?php
/**
 * Created by PhpStorm.
 * User: tang
 * Date: 2017/3/21
 * Time: 10:36
 */

session_start();
session_destroy();

header('Location: ../index.php');
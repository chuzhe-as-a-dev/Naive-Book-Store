-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 2017-03-18 13:57:06
-- 服务器版本： 5.7.17
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bookstore`
--

USE bookstore;

-- --------------------------------------------------------

--
-- 表的结构 `book_basic`
--

CREATE TABLE `book_basic` (
  `book_id` int(11) NOT NULL,
  `book_name` varchar(255) NOT NULL,
  `isbn` char(13) NOT NULL,
  `author` varchar(128) NOT NULL,
  `price` decimal(8,2) UNSIGNED NOT NULL,
  `stock` int(10) UNSIGNED NOT NULL,
  `cover_url` text,
  `description` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `book_basic`
--

INSERT INTO `book_basic` (`book_id`, `book_name`, `isbn`, `author`, `price`, `stock`, `cover_url`, `description`) VALUES
(1, 'Core Java Volume I--Fundamentals (10th Edition) (Core Series)', '9780134177304', 'Cay S. Horstmann', '59.99', 33, 'https://images-cn.ssl-images-amazon.com/images/I/51ELl-mPKvL.jpg', 'Core Java is cool.'),
(2, 'PHP the BEST!', '0000000000000', 'JBoss', '99.99', 100, 'https://images-cn.ssl-images-amazon.com/images/I/51siol0o5VL.jpg', 'Nobody doubts the bestness of PHP ever.'),
(3, 'Python, the BEST of the bests', '1111111111111', 'Ter', '33.00', 30, 'https://images-cn.ssl-images-amazon.com/images/I/61-YRthT-pL.jpg', 'So compelling that President Trump twitted about it.');

-- --------------------------------------------------------

--
-- 表的结构 `book_comments`
--

CREATE TABLE `book_comments` (
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `comment_time` datetime NOT NULL,
  `comment` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `amount` int(10) UNSIGNED NOT NULL,
  `placed_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `orders`
--

INSERT INTO `orders` (`order_id`, `user_id`, `book_id`, `amount`, `placed_time`) VALUES
(1, 2, 1, 1, '2017-03-10 20:13:42'),
(2, 3, 2, 20, '2017-03-10 20:24:55'),
(3, 1, 1, 1, '2017-03-10 12:00:00'),
(4, 2, 2, 10, '2017-03-11 17:00:00'),
(5, 1, 1, 35, '2012-01-01 01:01:00'),
(6, 3, 3, 1, '2017-03-01 12:02:00'),
(8, 2, 1, 1, '2017-01-01 01:01:00'),
(9, 2, 1, 1, '2017-01-01 01:01:00');

-- --------------------------------------------------------

--
-- 表的结构 `user_basic`
--

CREATE TABLE `user_basic` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `user_pwd` binary(255) NOT NULL,
  `balance` decimal(10,2) UNSIGNED NOT NULL DEFAULT '10000.00' COMMENT 'unit in cent'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `user_basic`
--

INSERT INTO `user_basic` (`user_id`, `user_name`, `user_pwd`, `balance`) VALUES
(1, 'Tang Chuzhe', 0x303030300000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '940.00'),
(2, 'Qin Jiarui', 0x313233340000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '940.00'),
(3, 'Chen Shihao', 0x343332310000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '1967.00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book_basic`
--
ALTER TABLE `book_basic`
  ADD PRIMARY KEY (`book_id`),
  ADD UNIQUE KEY `isbn_UNIQUE` (`isbn`),
  ADD UNIQUE KEY `book_id_UNIQUE` (`book_id`);

--
-- Indexes for table `book_comments`
--
ALTER TABLE `book_comments`
  ADD KEY `comment_fk1_idx` (`user_id`),
  ADD KEY `comment_fk2_idx` (`book_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD UNIQUE KEY `order_id_UNIQUE` (`order_id`),
  ADD KEY `orders_user_idx` (`user_id`),
  ADD KEY `orders_book_idx` (`book_id`);

--
-- Indexes for table `user_basic`
--
ALTER TABLE `user_basic`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  ADD UNIQUE KEY `user_name_UNIQUE` (`user_name`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `book_basic`
--
ALTER TABLE `book_basic`
  MODIFY `book_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- 使用表AUTO_INCREMENT `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- 使用表AUTO_INCREMENT `user_basic`
--
ALTER TABLE `user_basic`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- 限制导出的表
--

--
-- 限制表 `book_comments`
--
ALTER TABLE `book_comments`
  ADD CONSTRAINT `comment_fk1` FOREIGN KEY (`user_id`) REFERENCES `user_basic` (`user_id`),
  ADD CONSTRAINT `comment_fk2` FOREIGN KEY (`book_id`) REFERENCES `book_basic` (`book_id`);

--
-- 限制表 `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_fk1` FOREIGN KEY (`user_id`) REFERENCES `user_basic` (`user_id`),
  ADD CONSTRAINT `orders_fk2` FOREIGN KEY (`book_id`) REFERENCES `book_basic` (`book_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 29, 2016 at 08:48 AM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `tinder`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE IF NOT EXISTS `account` (
  `account_id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `age` int(3) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`account_id`, `username`, `password`, `full_name`, `sex`, `age`) VALUES
(1, 'Shannon1', '', 'Shannon', 'Male', 21),
(5, 'shannon', '', 'Shannon', 'Female', 75),
(6, 'man1', '', 'Jack Sanders', 'Male', 22),
(7, 'man2', '', 'Van', 'Male', 23),
(8, 'man3', '', 'Sam', 'Male', 24),
(9, 'man4', '', 'Vaughn', 'Male', 25),
(10, 'man5', '', 'Tron', 'Male', 26),
(11, 'man6', '', 'Jackson', 'Male', 27),
(12, 'man7', '', 'Mike', 'Male', 28),
(13, 'man8', '', 'Vlad', 'Male', 29),
(14, 'man9', '', 'Evan', 'Male', 30),
(15, 'man10', '', 'John', 'Male', 31),
(35, 'female1', '', 'Stevie', 'Female', 22),
(36, 'female2', '', 'Rebecca', 'Female', 23),
(37, 'female3', '', 'Samantha', 'Female', 24),
(38, 'female4', '', 'Melissa', 'Female', 25),
(39, 'female5', '', 'Kimberly', 'Female', 26),
(40, 'female6', '', 'Sarah', 'Female', 27),
(41, 'female7', '', 'Leah', 'Female', 28),
(42, 'female8', '', 'Paris', 'Female', 29),
(43, 'female9', '', 'Ivanka', 'Female', 30),
(44, 'female10', '', 'Suzie', 'Female', 31);

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE IF NOT EXISTS `message` (
  `message_id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL,
  `message` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`message_id`, `sender_id`, `receiver_id`, `message`) VALUES
(1, 6, 5, 'Hi there'),
(2, 6, 5, 'Sup g'),
(13, 7, 5, 'Test'),
(14, 8, 5, 'Wassup'),
(15, 5, 9, 'Yo whatsup'),
(16, 9, 5, 'I lvoe you'),
(17, 6, 5, 'Hey bud'),
(18, 5, 6, 'Hows it'),
(19, 6, 8, 'Tetet'),
(20, 5, 8, 'sdafsdf'),
(21, 6, 5, 'I''m algood you?'),
(22, 5, 6, 'Fucked bruh'),
(23, 5, 6, 'hi there'),
(24, 5, 6, ''),
(25, 5, 6, 'Hows it going bruh?'),
(26, 5, 6, 'test'),
(27, 5, 6, 'test'),
(28, 5, 6, ''),
(29, 5, 6, 'Yoza'),
(30, 5, 1, 'sup'),
(31, 5, 1, 'sup'),
(32, 5, 1, 'sup'),
(33, 5, 1, 'sup'),
(34, 5, 1, 'sup'),
(35, 5, 1, 'sup'),
(36, 5, 1, 'sup'),
(37, 5, 1, 'sup'),
(38, 5, 1, 'sup');

-- --------------------------------------------------------

--
-- Table structure for table `swipe`
--

CREATE TABLE IF NOT EXISTS `swipe` (
  `swipe_id` int(11) NOT NULL,
  `user1_id` int(11) NOT NULL,
  `user2_id` int(11) NOT NULL,
  `liked` int(1) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `swipe`
--

INSERT INTO `swipe` (`swipe_id`, `user1_id`, `user2_id`, `liked`) VALUES
(1, 5, 1, 0),
(3, 5, 2, 1),
(5, 5, 4, 0),
(6, 5, 6, 1),
(7, 5, 14, 1),
(8, 5, 8, 1),
(9, 5, 43, 1),
(10, 5, 36, 1),
(11, 5, 40, 1),
(12, 6, 14, 1),
(13, 6, 38, 1),
(14, 35, 11, 1),
(15, 35, 6, 1),
(16, 35, 15, 1),
(17, 0, 14, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`account_id`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`message_id`);

--
-- Indexes for table `swipe`
--
ALTER TABLE `swipe`
  ADD PRIMARY KEY (`swipe_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=45;
--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
  MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=39;
--
-- AUTO_INCREMENT for table `swipe`
--
ALTER TABLE `swipe`
  MODIFY `swipe_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=18;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

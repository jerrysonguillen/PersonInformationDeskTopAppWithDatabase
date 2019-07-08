-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 08, 2019 at 09:12 AM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `persondb`
--

-- --------------------------------------------------------

--
-- Table structure for table `people`
--

CREATE TABLE `people` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `age` enum('child','adult','senior','') NOT NULL,
  `employment_status` varchar(200) NOT NULL,
  `tax_id` varchar(11) NOT NULL,
  `ph_citizen` tinyint(1) NOT NULL,
  `gender` enum('male','female','','') NOT NULL,
  `occupation` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `people`
--

INSERT INTO `people` (`id`, `name`, `age`, `employment_status`, `tax_id`, `ph_citizen`, `gender`, `occupation`) VALUES
(1, 'andress', 'adult', 'employed', '132', 1, 'male', 'manager'),
(2, 'jerryson', 'adult', 'employed', '1131', 1, 'male', 'Software Engineer'),
(3, 'marilyn', 'adult', 'employed', '9632', 1, 'female', 'Actress'),
(4, 'ben', 'adult', 'employed', '9632', 1, 'male', 'Singer'),
(5, 'spens', 'adult', 'employed', '9632', 1, 'female', 'Actress'),
(6, 'angela', 'adult', 'employed', '9632', 1, 'female', 'engineer'),
(7, 'sample7', 'adult', 'employed', '1321', 1, 'male', 'Chef'),
(13, 'arriane', 'adult', 'selfEmployed', '1653', 0, 'female', 'Sinnger');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `people`
--
ALTER TABLE `people`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `people`
--
ALTER TABLE `people`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

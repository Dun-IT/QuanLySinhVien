-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 09, 2022 at 02:40 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `database_quanlysinhvien`
--

-- --------------------------------------------------------

--
-- Table structure for table `information`
--

CREATE TABLE `information` (
  `HoTen` varchar(50) NOT NULL,
  `Khoa` int(11) NOT NULL,
  `MSSV` varchar(10) NOT NULL,
  `Lop` varchar(10) NOT NULL,
  `GPA` float NOT NULL,
  `DRL` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `information`
--

INSERT INTO `information` (`HoTen`, `Khoa`, `MSSV`, `Lop`, `GPA`, `DRL`) VALUES
('Nguyễn Hải Phong', 65, '20207624', 'LTU 01', 3.67, 96),
('Đỗ Trung Hiếu', 65, '20207604', 'LTU 01', 3.2, 89),
('Đặng Nhật Duy', 65, '20207612', 'LTU 02', 4, 94),
('Nguyễn Duy Thái', 65, '20207630', 'LTU 01', 1, 1),
('Phạm Ngọc Hải', 65, '20207601', 'LTU 02', 4, 80),
('Lê Kỳ Anh', 65, '20207586', 'LTU 01', 2.9, 78),
('Phạm Thanh Bình', 65, '20207587', 'LTU 01', 3, 80),
('Phạm Thái Dương', 65, '20207595', 'LTU 01', 2.8, 70),
('Đỗ Văn Hải', 65, '20207600', 'LTU 01', 3.2, 80),
('Vũ Ngọc Hải', 65, '20207602', 'LTU 01', 2.5, 70),
('Trần Việt Hoàng', 65, '20207606', 'LTU 01', 2.8, 80),
('Đào An Khánh', 65, '20207609', 'LTU 01', 2.9, 80),
('Phan Thanh Lộc', 65, '20207614', 'LTU 01', 2.7, 85),
('Trần Phạm Thành Long', 65, '20207616', 'LTU 01', 3, 89),
('Nguyễn Minh Nhật', 65, '20207645', 'LTU 01', 3.2, 79),
('Trần Minh Quang', 65, '20207647', 'LTU 01', 2.6, 80),
('Nguyễn Xuân Sơn', 65, '20207648', 'LTU 01', 3, 89),
('Nguyễn Viết Thành', 65, '20207632', 'LTU 01', 3.2, 89),
('Nguyễn Đức Tú', 65, '20207638', 'LTU 01', 3.2, 89);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`username`, `password`) VALUES
('admin@hust.edu.vn', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `ten` varchar(30) NOT NULL,
  `tuoi` int(11) NOT NULL,
  `mail` varchar(30) NOT NULL,
  `sdt` varchar(10) NOT NULL,
  `maGiaoVien` varchar(10) NOT NULL,
  `vienDaoTao` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`ten`, `tuoi`, `mail`, `sdt`, `maGiaoVien`, `vienDaoTao`) VALUES
('Nguyen Hai Phong', 20, 'nhp@gmail.com', '001', 'IT1', 'CNTT'),
('Nguyen Khoa Doan', 20, 'nkd@gmail.com', '002', 'IT2', 'CNTT'),
('Do Trung Hieu', 30, 'dth@gmail.com', '003', 'IT3', 'CNTT'),
('Pham Ngoc Hai', 20, 'pnh@gmail.com', '004', 'IT4', 'CNTT'),
('Ta Hai Tung', 40, 'tung.tahai@hust.edu.vn', '005001', '002', 'CNTT'),
('Nguyen Duy Thai', 30, 'ndt@gmail.com', '00345', 'IT0', 'Kinh Te'),
('Pham Thanh Binh', 50, 'ptb@hust.edu.vn', '099', 'EM1', 'Kinh Te'),
('Nghiem Hong Dang', 60, 'nhd@hust.edu.vn', '03666', 'SSH01', 'Chinh Tri'),
('Tran Minh Quang', 55, 'tmq@hust.edu.vn', '113', 'TC1', 'The Chat'),
('Do Van Hai', 99, 'dvh@hust.edu.vn', '115', 'IT99', 'CNTT');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

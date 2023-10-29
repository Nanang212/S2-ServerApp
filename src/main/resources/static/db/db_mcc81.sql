-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: fuzi-mysql
-- Generation Time: Oct 17, 2023 at 08:18 AM
-- Server version: 8.0.32
-- PHP Version: 8.0.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_mcc81`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_country`
--

CREATE TABLE `tb_country` (
  `country_id` int NOT NULL,
  `country_code` varchar(2) NOT NULL,
  `country_name` varchar(20) NOT NULL,
  `region_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_country`
--

INSERT INTO `tb_country` (`country_id`, `country_code`, `country_name`, `region_id`) VALUES
(4, 'ID', 'Indonesia', 4),
(8, 'SG', 'Singapura', 4),
(9, 'JP', 'Japan', 4),
(10, 'MY', 'Malaysia', 4),
(13, 'HK', 'Hongkong', 12);

-- --------------------------------------------------------

--
-- Table structure for table `tb_employee`
--

CREATE TABLE `tb_employee` (
  `id` int NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(50) NOT NULL,
  `phone` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_employee`
--

INSERT INTO `tb_employee` (`id`, `email`, `name`, `phone`) VALUES
(6, 'bagus@gmail.com', 'Bagus', '000011113333'),
(8, 'dono@gmail.com', 'Dono', '000011113333'),
(9, 'dino@gmail.com', 'Dino', '000011113333'),
(10, 'hendro@gmail.com', 'Hendo', '000011113333'),
(11, 'ergi@gmail.com', 'Ergi', '000011113333'),
(12, 'admin@gmail.com', 'Admin', '000011113333'),
(13, 'admin2@gmail.com', 'Admin2', '000011113333');

-- --------------------------------------------------------

--
-- Table structure for table `tb_privilege`
--

CREATE TABLE `tb_privilege` (
  `id` int NOT NULL,
  `name` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_privilege`
--

INSERT INTO `tb_privilege` (`id`, `name`) VALUES
(1, 'create_user'),
(2, 'read_user'),
(3, 'update_user'),
(4, 'delete_user'),
(5, 'create_admin'),
(6, 'read_admin'),
(7, 'update_admin'),
(8, 'delete_admin');

-- --------------------------------------------------------

--
-- Table structure for table `tb_region`
--

CREATE TABLE `tb_region` (
  `region_id` int NOT NULL,
  `region_name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_region`
--

INSERT INTO `tb_region` (`region_id`, `region_name`) VALUES
(3, 'Amerikaaa'),
(4, 'Asia'),
(5, 'Afrikaaa'),
(6, 'Australia'),
(7, 'belanda'),
(8, 'Nganjuk'),
(9, 'Tuban'),
(10, 'tes'),
(12, 'haha'),
(13, 'inifajri'),
(14, 'itu yaya'),
(15, 'ini nazula');

-- --------------------------------------------------------

--
-- Table structure for table `tb_role`
--

CREATE TABLE `tb_role` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_role`
--

INSERT INTO `tb_role` (`id`, `name`) VALUES
(1, 'Admin'),
(2, 'User');

-- --------------------------------------------------------

--
-- Table structure for table `tb_tr_role_privilege`
--

CREATE TABLE `tb_tr_role_privilege` (
  `role_id` int NOT NULL,
  `privilege_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_tr_role_privilege`
--

INSERT INTO `tb_tr_role_privilege` (`role_id`, `privilege_id`) VALUES
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8);

-- --------------------------------------------------------

--
-- Table structure for table `tb_tr_user_role`
--

CREATE TABLE `tb_tr_user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_tr_user_role`
--

INSERT INTO `tb_tr_user_role` (`user_id`, `role_id`) VALUES
(6, 2),
(8, 2),
(9, 2),
(10, 2),
(11, 2),
(8, 1),
(12, 1),
(13, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tb_user`
--

CREATE TABLE `tb_user` (
  `id` int NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `is_enabled` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_user`
--

INSERT INTO `tb_user` (`id`, `password`, `username`, `is_enabled`) VALUES
(6, '$2a$12$7OD3WJf0HRkgZUKmf1iJQuCcJg5KihJqFOkkn63vTyuNgp8AEDqPi', 'bagus', b'0'),
(8, '$2a$12$77CJWlg.8g4DTia.JlfreuTnLdr7ZcdAfPoRhbxQyjSoG1znh3Uxu', 'dono', b'1'),
(9, 'dino', 'dino', NULL),
(10, '$2a$10$kOxzZgs7Eb8ppcxvrGN8NO4FllkUX0IV26VvHyI0RrEVQK/1F7Hfm', 'hendro', b'1'),
(11, '$2a$10$sPKNKJnb3ks2jrtiQzuGGOkhWO.40skiR7sE9qxI5kTjg8OIc7Ai2', 'ergi', b'1'),
(12, '$2a$10$ZDIhWKBMmFspCK/DiLJq9uG.QeMjieAh40bkd84d89I3Kzgqu.VWa', 'admin', b'1'),
(13, '$2a$10$rkDHn5ML8VTdp2ZjWk2oA.nIWcbItujtdo.UihKNegGr69rwamBT6', 'admin2', b'1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_country`
--
ALTER TABLE `tb_country`
  ADD PRIMARY KEY (`country_id`),
  ADD UNIQUE KEY `UK_m9atefvvjtd98boe823dovatt` (`country_code`),
  ADD KEY `FKow3r4mvn453tgoqu3em28ilu5` (`region_id`);

--
-- Indexes for table `tb_employee`
--
ALTER TABLE `tb_employee`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_em9lvg7whqbrro5fbav5bt0gy` (`email`);

--
-- Indexes for table `tb_privilege`
--
ALTER TABLE `tb_privilege`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_region`
--
ALTER TABLE `tb_region`
  ADD PRIMARY KEY (`region_id`);

--
-- Indexes for table `tb_role`
--
ALTER TABLE `tb_role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_tr_role_privilege`
--
ALTER TABLE `tb_tr_role_privilege`
  ADD KEY `FK1mxqp25o03axbfetfl77upkt1` (`privilege_id`),
  ADD KEY `FKk3bioyxr0o4pbsk297dap6s1c` (`role_id`);

--
-- Indexes for table `tb_tr_user_role`
--
ALTER TABLE `tb_tr_user_role`
  ADD KEY `FKgra7ve9sw7nn23oprc87454tx` (`role_id`),
  ADD KEY `FKcn7k4v4pdk0gro6c6pi64uewx` (`user_id`);

--
-- Indexes for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_4wv83hfajry5tdoamn8wsqa6x` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_country`
--
ALTER TABLE `tb_country`
  MODIFY `country_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `tb_employee`
--
ALTER TABLE `tb_employee`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `tb_privilege`
--
ALTER TABLE `tb_privilege`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `tb_region`
--
ALTER TABLE `tb_region`
  MODIFY `region_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `tb_role`
--
ALTER TABLE `tb_role`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_country`
--
ALTER TABLE `tb_country`
  ADD CONSTRAINT `FKow3r4mvn453tgoqu3em28ilu5` FOREIGN KEY (`region_id`) REFERENCES `tb_region` (`region_id`);

--
-- Constraints for table `tb_tr_role_privilege`
--
ALTER TABLE `tb_tr_role_privilege`
  ADD CONSTRAINT `FK1mxqp25o03axbfetfl77upkt1` FOREIGN KEY (`privilege_id`) REFERENCES `tb_privilege` (`id`),
  ADD CONSTRAINT `FKk3bioyxr0o4pbsk297dap6s1c` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`);

--
-- Constraints for table `tb_tr_user_role`
--
ALTER TABLE `tb_tr_user_role`
  ADD CONSTRAINT `FKcn7k4v4pdk0gro6c6pi64uewx` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`),
  ADD CONSTRAINT `FKgra7ve9sw7nn23oprc87454tx` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`);

--
-- Constraints for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD CONSTRAINT `FKeevlntsedmt1rdsr2d2lilphg` FOREIGN KEY (`id`) REFERENCES `tb_employee` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 11 Okt 2016 pada 12.38
-- Versi Server: 10.1.13-MariaDB
-- PHP Version: 5.6.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `si_piket_smkn3`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `guru`
--

CREATE TABLE `guru` (
  `id` int(11) NOT NULL,
  `nip` varchar(18) DEFAULT 'Kosong',
  `nama` varchar(40) NOT NULL,
  `mata_pelajaran` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `guru`
--

INSERT INTO `guru` (`id`, `nip`, `nama`, `mata_pelajaran`) VALUES
(3, 'Kosong', 'Iman', 'Matematika'),
(4, '123456789012345678', 'Suprayitno', 'Matematika'),
(5, 'Kosong', 'Jubaedah', 'Mengaji'),
(6, 'Kosong', 'Zulkifli', 'Agro');

-- --------------------------------------------------------

--
-- Struktur dari tabel `piket`
--

CREATE TABLE `piket` (
  `id` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `nis` varchar(8) NOT NULL,
  `nama_siswa` varchar(40) NOT NULL,
  `kelas_siswa` varchar(8) NOT NULL,
  `jenis_pelanggaran` varchar(20) NOT NULL,
  `keterangan` varchar(40) NOT NULL,
  `tindak_lanjut` varchar(40) NOT NULL,
  `id_guru_1` int(11) NOT NULL,
  `nama_guru_1` varchar(40) NOT NULL,
  `id_guru_2` int(11) NOT NULL,
  `nama_guru_2` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `piket`
--

INSERT INTO `piket` (`id`, `tanggal`, `nis`, `nama_siswa`, `kelas_siswa`, `jenis_pelanggaran`, `keterangan`, `tindak_lanjut`, `id_guru_1`, `nama_guru_1`, `id_guru_2`, `nama_guru_2`) VALUES
(3, '1970-01-01', '76543210', 'Zlatan Ibrahimovic', 'XII-PM-6', 'Rambut Gondrong', 'Gondrong sampai se-mata kaki', 'Hukuman botak', 4, 'Suprayitno', 5, 'Jubaedah'),
(4, '2016-10-12', '3354222', 'Bin Idris', 'XI-UPW-4', 'Celana terlalu ketat', 'Ketat banget coy', 'DIsuruh pakai sarung', 5, 'Jubaedah', 4, 'Suprayitno'),
(5, '2016-10-06', '23456789', 'Gilang Pasca Dwitama', 'XI-MN-3', 'Ngupil', 'Banyak bgt upilnya', 'Mandi wajib', 5, 'Jubaedah', 4, 'Suprayitno');

-- --------------------------------------------------------

--
-- Struktur dari tabel `siswa`
--

CREATE TABLE `siswa` (
  `nis` varchar(8) NOT NULL,
  `nama` varchar(40) NOT NULL,
  `kelas` varchar(8) NOT NULL,
  `jurusan` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `siswa`
--

INSERT INTO `siswa` (`nis`, `nama`, `kelas`, `jurusan`) VALUES
('13434342', 'Komeng', 'XII-PM-6', 'Pemasaran'),
('22345678', 'Akida doang', 'XI-MN-3', 'Manajemen'),
('23456789', 'Gilang Pasca Dwitama', 'XI-MN-3', 'Manajemen'),
('3354222', 'Bin Idris', 'XI-UPW-4', 'Usaha Perjalanan Wisata'),
('76543210', 'Zlatan Ibrahimovic', 'XII-PM-6', 'Pemasaran'),
('98765432', 'Paul Labile Pogba', 'XI-MM-3', 'Multimedia');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `guru`
--
ALTER TABLE `guru`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `piket`
--
ALTER TABLE `piket`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`nis`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `guru`
--
ALTER TABLE `guru`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `piket`
--
ALTER TABLE `piket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

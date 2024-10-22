-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th8 18, 2023 lúc 03:52 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `thietbi`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `iddonhang` int(11) NOT NULL,
  `idsp` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `gia` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`iddonhang`, `idsp`, `soluong`, `gia`) VALUES
(19, 7, 1, '1233'),
(20, 7, 1, '1233'),
(21, 7, 1, '1233'),
(21, 8, 1, '345323'),
(22, 7, 1, '1233'),
(22, 8, 1, '345323'),
(23, 7, 1, '1233'),
(24, 7, 1, '1233'),
(25, 7, 1, '1233'),
(26, 7, 1, '1233'),
(27, 8, 1, '345323'),
(28, 8, 1, '345323'),
(29, 8, 1, '345323'),
(30, 8, 1, '345323'),
(31, 8, 1, '345323'),
(32, 8, 1, '345323'),
(33, 8, 1, '345323'),
(34, 8, 1, '345323'),
(35, 8, 1, '345323'),
(36, 8, 1, '345323'),
(37, 8, 1, '345323'),
(38, 8, 1, '345323'),
(39, 7, 1, '1233'),
(40, 7, 1, '1233'),
(41, 7, 1, '1233'),
(42, 7, 1, '1233'),
(43, 7, 1, '1233'),
(44, 7, 1, '1233'),
(49, 7, 1, '1233'),
(50, 8, 1, '345323'),
(51, 8, 1, '345323'),
(51, 7, 1, '1233'),
(52, 7, 1, '1233'),
(52, 8, 1, '345323'),
(53, 7, 1, '1233'),
(54, 7, 1, '1233'),
(55, 8, 1, '345323'),
(56, 8, 1, '345323'),
(57, 8, 2, '690646'),
(58, 7, 1, '30000'),
(59, 7, 1, '30000'),
(59, 8, 1, '23000'),
(60, 8, 4, '25000'),
(60, 13, 1, '20000'),
(61, 19, 3, '20000'),
(61, 11, 2, '32000'),
(62, 19, 3, '20000'),
(62, 11, 2, '32000'),
(63, 19, 3, '20000'),
(63, 11, 3, '32000'),
(64, 13, 3, '20000'),
(65, 13, 4, '20000'),
(66, 8, 1, '25000'),
(67, 19, 3, '20000'),
(68, 8, 1, '25000'),
(68, 48, 1, '12000'),
(69, 8, 1, '25000'),
(69, 11, 1, '32000'),
(70, 11, 1, '32000'),
(71, 8, 2, '25000'),
(71, 11, 2, '32000'),
(72, 8, 1, '25000'),
(72, 11, 1, '32000'),
(72, 13, 1, '20000'),
(73, 51, 1, '1233'),
(74, 8, 1, '25000'),
(75, 16, 4, '12000'),
(76, 57, 1, '11111'),
(77, 11, 1, '32000'),
(78, 8, 1, '25000'),
(79, 8, 1, '25000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `id` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `diachi` text NOT NULL,
  `sodienthoai` varchar(222) NOT NULL,
  `email` varchar(222) NOT NULL,
  `soluong` int(11) NOT NULL,
  `tongtien` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`id`, `iduser`, `diachi`, `sodienthoai`, `email`, `soluong`, `tongtien`) VALUES
(77, 18, 'a', '123321', 'd@gmail.com', 1, '32000'),
(78, 18, 'aaaa', '123321', 'd@gmail.com', 1, '25000'),
(79, 18, 'da nang', '123321', 'd@gmail.com', 1, '25000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `id` int(11) NOT NULL,
  `tensanpham` varchar(200) NOT NULL,
  `hinhanh` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `loaisanpham`
--

INSERT INTO `loaisanpham` (`id`, `tensanpham`, `hinhanh`) VALUES
(1, 'Trang chủ', 'https://ngochieu.name.vn/img/home.png'),
(2, 'Thức ăn', 'https://inhat.vn/ha-noi/wp-content/uploads/2019/05/mon-an-vat-ha-noi-5-1-750x750.jpg'),
(5, 'Đồ uống', 'https://cdn.tgdd.vn/2021/06/content/Nuocepcantay-800x450.jpg'),
(6, 'Thông tin ', 'http://ngochieu.name.vn/img/contact.png'),
(9, 'Liên hệ', 'http://ngochieu.name.vn/img/mobile.png'),
(10, 'Đơn hàng', 'http://ngochieu.name.vn/img/contact.png'),
(15, 'Chat GPT', 'https://www.shutterstock.com/image-vector/chatgpt-openai-vector-illustration-design-600w-2252104563.jpg'),
(18, 'Đăng xuất', 'https://cdn.vectorstock.com/i/preview-1x/43/31/logout-icon-exit-icon-in-trendy-style-vector-46264331.webp');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(11) NOT NULL,
  `tensp` varchar(200) NOT NULL,
  `giasp` int(15) NOT NULL,
  `hinhanh` varchar(1000) NOT NULL,
  `mota` varchar(1000) NOT NULL,
  `loai` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`id`, `tensp`, `giasp`, `hinhanh`, `mota`, `loai`) VALUES
(8, 'Gỏi bò bóp thấu', 80000, 'https://zozozo.vn/wp-content/uploads/2019/11/goi-bo-bop-thau.jpg', 'Gỏi bò bóp thấu là món ăn khai vị được sử dụng phổ biến tại các nhà hàng để kích thích vị giác cho thực khách.\r\n\r\nCách làm bò bóp thấu không quá khó, nguyên liệu đơn giản, dễ tìm. Món ăn là sự kết hợp của thịt bò với các loại rau củ quả salad. Vị chua, ngọt, chát hòa quyện mang lại trải nghiệm thú vị cho người thưởng thức.\r\n\r\nBò bóp thấu là món nhậu khoái khẩu của nhiều người, đặc biệt trong những ngày mưa, thời tiết se lạnh.', 1),
(11, 'Bò sốt me', 60000, 'https://zozozo.vn/wp-content/uploads/2019/11/bo-sot-me.jpg', 'Bò sốt me là món nhậu ngon dễ làm, giàu dinh dưỡng, thích hợp cho các bữa tiệc cuối tuần, sinh nhật, liên hoan.\r\n\r\nThịt bò tươi vừa chín tới, mềm, thấm đẫm nước sốt me chua ngọt, khiến bất cứ ai thử qua cũng phải trầm trồ và ngất ngây.\r\n\r\nĐiểm đặc biệt của bò sốt me là hương vị chua tự nhiên của sốt me kết hợp các loại rau, nấm, giữ cho món ăn giữ được độ giòn, ngọt, đem lại cảm giác ngon miệng cho người ăn.', 1),
(13, 'Gỏi tôm sống', 50000, 'https://zozozo.vn/wp-content/uploads/2019/11/goi-tom-song-thai-lan.jpg', 'Gỏi tôm Thái hay còn gọi là tôm sống sốt Thái, gỏi tôm ngũ vị là một trong các món nhậu độc chiêu mang đậm phong cách ẩm thực xứ sở chùa vàng .\r\n\r\nNguyên liệu chính làm gỏi tôm sống kiểu Thái là những con tôm còn tươi, bóc vỏ sau đó trộn với hành tây, gừng, rau thơm, ớt, sả… và mù tạt.\r\n\r\nMón ăn có vị ngọt của tôm tươi sần sật, hòa quyện cùng hương thơm dễ gây nghiện của sốt Thái chua cay mặn ngọt.\r\n\r\nNếu Bạn là tín đồ của món ăn Thái, yêu thích hải sản và những món ăn lạ miệng thì chắc chắn không thể bỏ qua món nhậu lạ miệng độc đáo này.', 1),
(15, 'Tôm sốt bơ tỏi', 50000, 'https://zozozo.vn/wp-content/uploads/2019/11/tom-sot-bo-toi.jpg', 'Tôm là loại hải sản yêu thích của người Việt, thường xuyên xuất hiện trên những bàn tiệc đãi khách vào mỗi dịp cuối tuần.\r\n\r\nTôm có nhiều cách chế biến khác nhau, trong đó món tôm sốt bơ tỏi được nhiều bà nội trợ lựa chọn hơn cả vì dễ làm mà hương vị thì vô cùng hấp dẫn.\r\n\r\nTôm sốt bơ tỏi có vị đậm đà, cay cay. Thịt tôm (tôm hùm, tôm sú, tôm càng xanh…) mềm dai, thơm lừng vị bơ tỏi. Dù là người lớn hay trẻ em thì cũng đều yêu thích món ăn này.', 1),
(16, 'Coca-Cola', 10000, 'https://sieuthixanh.com.vn/Upload/products/zoom/Loc-6-lon-nuoc-ngot-Coca-Cola-320ml132690948229883779.jpg', 'Coca-Cola – đổi mới để mang danh hiệu “nước giải khát cho mọi người”\r\nNhu cầu sử dụng các loại nước giải khát của người tiêu dùng ngày càng gia tăng. Vậy làm sao để có thể đa dạng hóa các danh mục sản phẩm của mình sao cho phù hợp với thị yếu người dùng - là một bài toán chung cho tất cả các thương hiệu nước giải khát trên toàn cầu.\r\nNgười dùng họ luôn cần một sản phẩm nước ngọt thật sự thoả mãn cơn khát, hay thậm chí họ còn mong mỏi một loại thức uống có sự kết hợp độc đáo nhằm đánh thức vị giác vốn đã nhàm chán với những sản phẩm đã quá đỗi quen thuộc.v.v..\r\nCoca-Cola đã và đang làm rất tốt việc này, họ luôn luôn sáng tạo đổi mới từ cả hương vị cho đến mẫu mã của chai, dung tích của thành phẩm cũng được đa dạng hóa… Uống Coca-Cola, bạn không chỉ có những trải nghiệm hương vị tuyệt vời mà mỗi dòng sản phẩm của nó là mang một sự sáng tạo, một nét khác biệt mô hình chung nó có thể truyền cảm hứng cho người dùng với những “năng lượng” tích cực đó.', 2),
(17, 'Pepsi', 10000, 'https://sieuthixanh.com.vn/Upload/products/zoom/Loc-6-lon-nuoc-ngot-Pepsi-Cola-330ml132690937107383744.jpg', 'Thức uống có gas Lốc 6 lon nước ngọt Pepsi Cola 330ml mang trong mình một hương vị đầy lôi cuốn cùng hương vị ngọt nhẹ, không gắt, mang đến cho người dùng một cảm giác vô cùng sảng khoái trong cái nắng gay gắt của mùa hè.\r\n\r\nSự kết hợp hài hòa giữ mùi vị thơm ngon đậm hương tự nhiên cùng chất tạo ngọt tổng hợp nó sẽ trở thành một loại nước ngọt bổ sung năng lượng làm việc mỗi ngày.', 2),
(18, 'Lòng xào dưa', 40000, 'https://zozozo.vn/wp-content/uploads/2019/11/long-xao-dua-chua-1.jpg', 'Lòng non xào dưa chua giòn ngon sần sật, là mồi nhậu khoái khẩu của cánh mày râu khi uống bia, rượu.\r\n\r\nMột trong những món nhậu đơn giản dễ làm tại nhà, không tốn quá nhiều thời gian của Bạn nhưng hiệu quả thì “đưa cơm ngùn ngụt”.\r\n\r\nNguyên liệu chính là lòng non dai dai, béo béo được xào chín cùng dưa chua giòn giòn, thêm ít cà chua tạo màu bắt mắt là Bạn đã có ngay món lòng xào dưa nóng hổi, thơm phức. Ăn với cơm trong thời tiết se se lạnh thì đúng là không còn gì bằng.', 1),
(19, 'Gỏi ngó sen tai heo', 35000, 'https://zozozo.vn/wp-content/uploads/2019/11/goi-ngo-sen-tai-heo.jpg', 'Một món nhậu ngon yêu thích của người Nam bộ và cũng là món khai vị quen thuộc trong các nhà hàng tiệc cưới.\r\n\r\nCách làm gỏi ngó sen tai heo vô cùng đơn giản, nguyên liệu dễ tìm, dễ mua ở chợ. Vị giòn giòn của ngó sen và sần sật của tai heo, thấm đẫm thêm nước mắm chua ngọt, thêm vị bùi thơm của đậu phộng, gỏi tai heo là sự kết hợp hoàn hảo, đánh thức mọi giác quan của thực khách.', 1),
(20, 'Chân gà chiên mắm', 50000, 'https://zozozo.vn/wp-content/uploads/2019/11/chan-ga-chien-mam-toi-ot.jpg', 'Món chân gà chiên nước mắm tỏi ớt là một biến thể thú vị của món chân gà chiên mắm truyền thống.\r\n\r\nVẫn là chân gà mềm, dai dai, giòn giòn nhưng được bổ sung thêm vị đậm đà của tỏi, vị cay cay của ớt, ăn hoài không ngán.\r\n\r\nMón ăn chinh phục sự hài lòng của mọi thực khách, mang lại sự thú vị trong hương vị mới lạ và cả niềm vui cho người chế biến.', 1),
(21, 'Cá hấp bia', 80000, 'https://zozozo.vn/wp-content/uploads/2019/11/ca-hap-bia.jpg', 'Khi Bạn đã cảm thấy ngán các món nhậu chiên, xào… chứa nhiều dầu mỡ thì món cá hấp bia là sự lựa chọn tuyệt vời để thay đổi khẩu vị.\r\n\r\nĐây là món ăn phổ biến trong các gia đình, nhà hàng, khách sạn và được nhiều thực khách ưa chuộng. Hương vị vừa thơm vừa ngọt của cá tươi hòa quyện cùng men bia, chấm thêm nước mắm gừng thì không gì ngon bằng.\r\n\r\nCách làm cá hấp bia cũng không quá phức tạp như nhiều người tưởng tượng, có thể ăn kèm với cơm, làm món nhậu hoặc cuốn bánh tráng rau sống, bún tươi đều hợp cả.', 1),
(22, 'Mực xào cần tây', 60000, 'https://zozozo.vn/wp-content/uploads/2019/11/muc-xao-can-tay-1.jpg', 'Những miếng mực trắng tinh, ngọt thịt, giòn giòn cùng cần tây xanh mướt, thơm nức, chắc chắn là món nhậu hải sản mà bạn không thể bỏ qua trên bàn tiệc đãi khách nhà mình.\r\n\r\nMực xào cần tây khá dễ ăn, thích hợp cho cả người lớn và trẻ em. Cách làm đơn giản, chỉ cần chú ý thời gian xào để cần tây và mực vừa chín tới là Bạn đã có mồi nhậu ngon nóng hổi, thơm lừng.', 1),
(23, 'Lẩu kim chi hải sản', 200000, 'https://zozozo.vn/wp-content/uploads/2019/11/lau-kim-chi-hai-san.jpg', 'Trong bữa tiệc, lẩu là món ăn chính cuối cùng và để tăng thêm sự mới lạ cho khách dự tiệc, Bạn có thể chiêu đãi họ bằng món lẩu kim chi – món ăn truyền thống nổi tiếng của Hàn Quốc.\r\n\r\nKhông cần đi tới Hàn Quốc xa xôi, Bạn có thể nấu món lẩu này bằng các nguyên liệu sẵn có tại Việt Nam như: kim chi, nấm, thị ba chỉ, hải sản… đều là những thực phẩm dễ tìm.\r\n\r\nLẩu kim chi ăn rất ngon, mang đậm hương vị đặc trưng Hàn Quốc: cay cay tê tê, đậm đà, xuýt xoa, xì xụp thích thú khi ăn nóng. Ăn lẩu kim khi nhậu, khi sum vầy hội họp thì đúng là “tuyệt cú mèo”.', 1),
(24, 'Sữa đậu nành', 10000, 'https://cdn.tgdd.vn/2021/06/content/Suadaunanh-800x450.jpg', 'Kích thước màn hình 6.9 inches\r\nCông nghệ màn hình Dynamic AMOLED\r\nCamera sau 108 MP, f/1.8, 26mm (wide), 1/1.33\", 0.8µm, PDAF, Laser AF, OIS\r\n12 MP, f/3.0, 103mm (periscope telephoto), 1.0µm, PDAF, OIS, 5x optical zoom, 50x hybrid zoom\r\n12 MP, f/2.2, 13mm (ultrawide), 1/2.55\", 1.4µm\r\nCamera trước 10 MP, f/2.2, 26mm (wide), 1/3.2\", 1.22µm, Dual Pixel PDAF\r\nChipset Exynos 990 (7 nm+)\r\nDung lượng RAM 12 GB', 2),
(25, 'Lẩu gà ớt hiểm', 200000, 'https://zozozo.vn/wp-content/uploads/2019/11/lau-ga-ot-hiem.jpg', 'Thêm một món nhậu dễ làm tại nhà nổi tiếng của miền Tây, đang “làm mưa làm gió” trên các cộng đồng ăn uống (foody, diadiemanuong).\r\n\r\nLẩu gà tiềm ớt sử dụng nước dùng bằng dừa tươi và rất nhiều ớt hiểm. Tuy vậy, nước lẩu không quá cay, có vị ngọt thanh ăn cùng gà ta thì rất “đã”.\r\n\r\nLẩu ăn kèm các loại rau như cải bẹ xanh, rau muống, hoa chuối… là món ăn đáng để bạn bỏ công, trải nghiệm cảm giác độc và lạ.', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `mobile` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `email`, `pass`, `username`, `mobile`) VALUES
(13, 'admin@gmail.com', '123', 'admin', '090'),
(20, 'abc@gmail.com', '123', 'Hiếu', '0399092147');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=80;

--
-- AUTO_INCREMENT cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

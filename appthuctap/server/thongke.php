<?php
include "connect.php";


$query = "SELECT idsp,sanpham.tensp ,COUNT(`soluong`) As tong FROM `chitietdonhang` INNER JOIN sanpham ON sanpham.id = chitietdonhang.idsp GROUP BY `idsp`";
$data = mysqli_query($conn, $query);
$result = array();
while ($row = mysqli_fetch_assoc($data)) {
	// code...
	$result[] = ($row);
}
if (!empty($result)) {
	// code...
	$arr = [
		'success' => true,
		'message' => "thanh cong",
		'result' => $result
	];
}
else {
	$arr = [
		'success' => false,
		'message' => " ko thanh cong",
		'result' => $result
	];
}
print_r(json_encode($arr))

?>
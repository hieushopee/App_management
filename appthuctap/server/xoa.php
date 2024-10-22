<?php
include "connect.php";
$id = $_POST['id'];



$query = 'DELETE FROM `sanpham` WHERE `id`='.$id;
$data = mysqli_query($conn, $query);

if ($data==true) {
	$arr = [
		'success' => true,
		'message' => "thanh cong"
	];
}else{
	$arr = [
		'success' => false,
		'message' => "xóa không thành công "
	];
}
print_r(json_encode($arr));

?>
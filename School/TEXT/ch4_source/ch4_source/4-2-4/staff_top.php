<?php
session_start();
if(isset($_SESSION['login'])==false)
{
	print 'ログインされていません。<br />';
	print '<a href="../staff_login/staff_login.html">ログイン画面へ</a>';
	exit();
}
?>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> ろくまる農園</title>
</head>
<body>

ショップ管理トップメニュー<br />
<br />
<a href="../staff/staff_list.php">スタッフ管理</a><br />
<br />
<a href="../product/pro_list.php">商品管理</a><br />

</body>
</html>
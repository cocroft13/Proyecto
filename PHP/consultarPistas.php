<?php

	include("./configuracion.php");

	$response = array();
	$obj = "";

	$con = mysql_connect(DB_HOST,DB_USER,DB_PASSWORD);
	if(!$con || !mysql_select_db(DB_NAME,$con)){

		echo "<h2 align='center'> Error en el acceso a la base de datos. Error : " . mysql_error() . "</h2>";

	} else {


		$sqlSelect = "SELECT pista,horario FROM disponibles WHERE dia='lunes' ";
		$resSelect = mysql_query($sqlSelect,$con);

		if (mysql_affected_rows($con)){

			while ($res = mysql_fetch_array($resSelect)) {
				
				$response = $res;
				echo json_encode($response) . "\n";

			}
		}


	}


?>
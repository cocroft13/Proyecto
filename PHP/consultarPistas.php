<?php

	include("./configuracion.php");

	$response['Pistas'] = array();

	$fecha = "";
	$data = "";


	if (isset($_SERVER['HTTP_JSON'])) {
		
		$json = $_SERVER['HTTP_JSON'];
		$data = json_decode($json);
		$fecha = $data-> fecha;
	}

	

	$con = mysql_connect(DB_HOST,DB_USER,DB_PASSWORD);
	if(!$con || !mysql_select_db(DB_NAME,$con)){

		echo "<h2 align='center'> Error en el acceso a la base de datos. Error : " . mysql_error() . "</h2>";

	} else {


		$sqlSelect = "SELECT nombre_pista,horario_pista FROM pista WHERE id_pista NOT IN(SELECT id_pista FROM reserva WHERE fecha='$fecha')";
		$resSelect = mysql_query($sqlSelect,$con);

		if (mysql_affected_rows($con)){

			while ($res = mysql_fetch_array($resSelect)) {

				$pista = array();

				//$pista['id_pista'] = $res['id_pista'];
				$pista['nombre_pista'] = $res['nombre_pista'];
				$pista['horario_pista'] = $res['horario_pista'];
				


				array_push($response['Pistas'], $pista);

				//$response = $res;
				
			}
			echo json_encode($response);
			
		}
	}

?>
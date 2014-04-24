<?php


	include("./configuracion.php");


	$response = array();

	$json = $_SERVER['HTTP_JSON'];

	$data = json_decode($json);


	$dni = $data-> dni;
	$pista = $data-> id_pista;
	$fecha = $data-> fecha;
	$horario = $data-> horario;

	$con = mysql_connect(DB_HOST,DB_USER,DB_PASSWORD);


	if (!$con || !mysql_select_db(DB_NAME,$con)) {
    	
    	echo "<h2 align='center'> Error en el acceso a la base de datos. Error : " . mysql_error() . "</h2>";
    
    } else {

    	$sqlInsert = "INSERT INTO reserva (id_usuario, id_pista, fecha, horario) VALUES('$dni','$pista','$fecha','$horario')";

    	$resInsert = mysql_query($sqlInsert,$con);

    }


?>
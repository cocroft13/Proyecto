<?php

	include("configuracion.php");

    $response = array();

  	$json = $_SERVER['HTTP_JSON'];

    $data = json_decode($json);
      
    
    $dni = $data-> Dni;
    $password = $data-> Password;

    $con = mysql_connect(DB_HOST,DB_USER,DB_PASSWORD);
    if (!$con || !mysql_select_db(DB_NAME,$con)) {
    	
    	echo "<h2 align='center'> Error en el acceso a la base de datos. Error : " . mysql_error() . "</h2>";
    
    } else {


        $sqlSelect = "SELECT * FROM users WHERE dni='$dni' AND password='$password'";
        $resSelect = mysql_query($sqlSelect,$con);


        //Si hay lineas afectadas es que el usuario existe y pasa a la pantalla siguiente, devolvemos 1.
        if (mysql_affected_rows($con)) {
            
            $response = 1;
            echo json_encode($response);
            
         //Si no existe, el valor será 0, sugiriendo al usuario que cree una cuenta.       
        } else {
		  		
        $response = 0;    	
        echo json_encode($response);

        }   
    }

    mysql_close($con);
?>
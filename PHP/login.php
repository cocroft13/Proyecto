<?php
	
	include("configuracion.php");

	$response = array();

  	$json = $_SERVER['HTTP_JSON'];
    
    $data = json_decode($json);
      
    
    $dni = $data-> DNI;
    $name = $data-> Nombre;
    $email = $data-> Email;
    $password = $data-> Pass;
    $rePassword = $data-> Repass; //NO ES NECESARIO


    $con = mysql_connect(DB_HOST,DB_USER,DB_PASSWORD);
    if (!$con || !mysql_select_db(DB_NAME,$con)) {
    	
    	echo "<h2 align='center'> Error en el acceso a la base de datos. Error : " . mysql_error() . "</h2>";
    
    } else {

        $sqlSelect = "SELECT * FROM users WHERE  dni='$dni'";
        $resSelect = mysql_query($sqlSelect,$con);


        //Si hay lineas afectadas es que el usuario existe, devolvemos 0.
        if (mysql_affected_rows($con)) {
            
            $response = 0;
            echo json_encode($response);

        //Si no existe, el valor será 1, y si los datos son correctos el usuario será registrado.        
        } else {

    	$sqlInsert = "INSERT INTO users (dni,nombre,email,password) VALUES ('$dni', '$name', '$email', '$password')";

    	//Lanzamos la sentencia SQL de insercion en la BD
    	$resInsert = mysql_query($sqlInsert,$con);

        $response = 1;
        echo json_encode($response);

        }   
    }

    mysql_close($con);
    
?>
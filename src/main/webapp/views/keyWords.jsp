<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Extractor de Palabras Clave</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
        <style>
            body{
                flex-direction: column;
                background-image: url("https://images.pexels.com/photos/937782/pexels-photo-937782.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2");
                background-size: 100vw 100vh;
                background-repeat: no-repeat;
                overflow: hidden;
            }
            #cuerpo{
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
            }
            .container{
                background-color: white;
                opacity: 0.7;
                height: 240px;
                width: 450px;
                border-radius: 10px;
                text-align: center;
                overflow-x: hidden;
                overflow-y: scroll;
            }
            .boton{
                background-color: #EA4C89;
                border-radius: 8px;
                border-style: none;
                box-sizing: border-box;
                color: #FFFFFF;
                cursor: pointer;
                display: inline-block;
                font-family: "Haas Grot Text R Web", "Helvetica Neue", Helvetica, Arial, sans-serif;
                font-size: 14px;
                font-weight: 500;
                height: 40px;
                line-height: 20px;
                list-style: none;
                margin: 0;
                outline: none;
                padding: 10px 16px;
                position: relative;
                text-align: center;
                text-decoration: none;
                transition: color 100ms;
                vertical-align: baseline;
                user-select: none;
                -webkit-user-select: none;
                touch-action: manipulation;
            }
            .boton:hover {
                background-color: #F082AC;
            }
            .salir{
                position: absolute;
            }
        </style>
    </head>
    <body>
        <form action="../home" method="post">
            <button type="submit" class="boton" id="salir" title="Atrás" name="boton" value="salir">
                <i class="bi bi-arrow-left-circle"></i>
            </button>
        </form>
        <div id="cuerpo">
            <br><br>
            <div class="container">
                <h2>Informacion del texto</h2>
                ID: ${texto.getId()}<br>
                Texto: ${texto.getTexto()}<br>
                Cédula: ${usuario.getCedula()}<br>
                Nombre: ${usuario.getNombre()}<br>
                Cantidad de palabras: ${texto.getPalabras()}<br>
                Fecha de registro: ${texto.getFecha_hora()}<br>
            </div><br><br><br><br>
            
            <div class="container">
                <h2>Palabras clave extraídas</h2>
                ${palabras}<br>
            </div>
        </div>
    </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
        <title>Home</title>
        <style>
            body{
                background-image: url("https://images.pexels.com/photos/937782/pexels-photo-937782.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2");
                background-size: 100vw 100vh;
                background-repeat: no-repeat;
                overflow: hidden;
            }
            .container{
                background-color: white;
                opacity: 0.7;
                height: 240px;
                width: 450px;
                border-radius: 10px;
                text-align: center;
            }

            #registrar_tematica_container{
                position: absolute;
                left: 120px;
            }
            #registrar_texto_container{
                position: absolute;
                left: 670px;
            }
            #buscar_textos_container{
                text-align: left;
                width: 1000px;
                max-width: 1000px;
                height: 330px;
                position: absolute;
                left: 120px;
                bottom: 30px;
                overflow-x: hidden;
                overflow-y: scroll;
            }
            #tematicas_select{
                position: fixed;
                padding-left: 10px;
                text-align: center;
                width: 500px;
            }
            #container_opciones{
                position: absolute;
                top: 20px;
                left: 550px;
                width: 400px;
                overflow-x: hidden;
            }
            .texto{
                resize: none;
                width: 80%;
                border-radius: 7px;
            }
            #descripcion{
                height: 40%;
            }
            #botones button{
                display: flex;
                justify-content: space-between;
                position: relative;
                width: 100px;
                top: 100%;
                text-align: center;

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
            #salir{
                position: absolute;
            }
            #usuarios{
                position: absolute;
                top: 60px;
            }
            #ver_tematicas{
                position: absolute;
                top: 110px;
            }
            #logo{
                position: absolute;
                height: 50px;
                width: 50px;
                border-radius: 10px;
                bottom: 20px;
            }
        </style>
    </head>
    <body>
        <form action="../home" method="post">
            <button type="submit" class="boton" id="salir" title="Cerrar sesión" name="boton" value="cerrar_sesion">
                <i class="bi bi-arrow-left-circle"></i>
            </button>
            <button type="submit" class="boton" id="usuarios" title="Usuarios" name="boton" value="ver_usuarios">
                <i class="bi bi-people-fill"></i>
            </button>
            <button type="submit" class="boton" id="ver_tematicas" title="Temáticas" name="boton" value="ver_tematicas">
                <i class="bi bi-card-list"></i>
            </button>
        </form>
        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSvMPOIB1yOSYfXd2vcEGZK9XDx350NQ5R3q6cvNepXPA&s" id="logo">

        <div id="registrar_tematica_container" class="container">
            <h2>Registrar Temática</h2>
            <form action="../home" method="post"  enctype="multipart/form-data">
                <textarea class="texto" id="nombre" name="nombre" placeholder="Nombre de la temática" required></textarea><br>
                <textarea class="texto" id="descripcion" name="descripcion" placeholder="Descripción de la temática" required rows="4"></textarea><br><br>
                <input type="file" id="imagen" name="imagen" id="imagen" value="imagen" accept="image/png, image/jpeg, image/jpg" required/>
                <input type="submit" class="boton" name="boton" value="Registrar temática">
            </form>                
        </div>

        <div id="registrar_texto_container" class="container">
            <h2>Registrar Texto</h2>
            <form action="../home" method="post" enctype="multipart/form-data">
                <label for="tematica">Seleccione una temática:</label>
                <select name="tematica">
                    <c:forEach items="${tematicas}" var="tematica">
                        <option value="${tematica.getId()}">${tematica.getNombre()}</option>
                    </c:forEach>
                </select><br><br>
                <textarea class="texto" name="texto" placeholder="Ingrese el texto a registrar" required rows="4"></textarea><br><br>
                <input type="submit" class="boton" name="boton" value="Registrar texto">
            </form>
        </div>
                      
        <div id="buscar_textos_container" class="container">
            
            <form action="../home" method="post">
                <div id="tematicas_select">
                    <h2>Consultar Textos</h2>
                        <label for="tematica2">Seleccione una temática:</label>
                        <select name="tematica2">
                            <c:forEach items="${tematicas}" var="tematica">
                                <option value="${tematica.getId()}">${tematica.getNombre()}</option>
                            </c:forEach>
                        </select><br><br>
                        <input type="submit" class="boton" name="boton" value="Consultar textos" style="position: relative; left: 20px;"><br><br>
            </form>

            <form action="../home" method="post">
                    <div id="botones">
                        <input type="submit" class="boton" name="boton" value="Análisis de sentimientos">
                        <input type="submit" class="boton" name="boton" value="Generar wordcloud">
                    </div><br>
                    <div id="botones">
                        <input type="submit" class="boton" name="boton" value="Enviar PDF">
                        <input type="submit" class="boton" name="boton" value="Generar archivo de audio">
                    </div><br>
                    <div id="botones">
                        <input type="submit" class="boton" name="boton" value="Extraer palabras clave">
                        <input type="submit" class="boton" name="boton" value="Consultar a ChatGPT">
                        
                    </div>
                </div>
              
                <div id="container_opciones">
            <c:forEach items="${textos}" var="texto">
                <label>
                    <input type="radio" id="${texto.getId()}" name="texto_selecionado" value="${texto.getId()}" required>${texto.getTextoCortado()}
                    <span id="dots_${texto.getId()}">...</span>
                    <span id="moreText_${texto.getId()}" style="display:none;">${texto.getTextoUltimo()}</span>
                    <button onclick="toggleText('${texto.getId()}')" id="toggleBtn_${texto.getId()}">Ver más</button><br>
                </label>
            </c:forEach>
        </div>
    </form>

    <script>
        function toggleText(textId) {
            var dots = document.getElementById("dots_" + textId);
            var moreText = document.getElementById("moreText_" + textId);
            var toggleBtn = document.getElementById("toggleBtn_" + textId);

            if (dots.style.display === "none") {
                dots.style.display = "inline";
                toggleBtn.innerHTML = "Ver más";
                moreText.style.display = "none";
            } else {
                dots.style.display = "none";
                toggleBtn.innerHTML = "Ver menos";
                moreText.style.display = "inline";
            }
        }
    </script>

        </div>
    </body>
</html>

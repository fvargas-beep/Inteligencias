<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
        <title>Tematicas</title>
        <style>
            body{
                flex-direction: column;
                background-image: url("https://images.pexels.com/photos/937782/pexels-photo-937782.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2");
                background-size: 100vw 100vh;
                background-repeat: no-repeat;
                overflow: hidden;
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
            #cuerpo{
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
            }
            .container{
                background-color: white;
                opacity: 0.7;
                height: 550px;
                width: 650px;
                border-radius: 10px;
                text-align: center;
                overflow-x: hidden;
                overflow-y: scroll;
            }
            .tematica{
                position: relative;
                padding: 10px;
                width: 550px;
                left: 50px;
                display: flex;
                flex-direction: row;
                justify-content: center;
            }
            .salir{
                position: absolute;
            }
            #texto{
                text-align: center;
                padding-left: 20px;
            }
        </style>
    </head>
    <body>
        <input type="hidden" class="boton" name="cedula" value="${cedula}">
        <form action="../home" method="post">
            <button type="submit" class="boton" id="salir" title="Atrás" name="boton" value="salir">
                <i class="bi bi-arrow-left-circle"></i>
            </button>
        </form>
        <div id="cuerpo">
            <br><br>
            <div class="container">
                <h2>Temáticas</h2>
                <hr>
                
                <c:forEach items="${tematicas}" var="tematica">
                    <div class="tematica">
                        <img src="data:image/jpeg;base64,${tematica.getFoto()}" width="100px" height="150px"/>
                        <div id="texto"><br><br>
                            <b>ID:</b> ${tematica.getId()}<br>
                            <b>Texto:</b> ${tematica.getNombre()}<br>
                            <b>Descripción:</b> ${tematica.getDescripcion()}<br>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <!DOCTYPE html>
     <html>
         <head>
             <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
             <title>Couche</title>
         </head>
         <body>
            <p>${best.nom}</p>
            <canvas id="canvas" width="<c:out value='${carre.getX2()-carre.getX1()}'/>" height="<c:out value='${carre.getY2()-carre.getY4()}'/>" style="border:solid black;"></canvas>
             <script>
                 var type = <c:out value='${type}'/>;
                 var source = [
                 <c:forEach var="p" items="${listval}">
                    {
                        x:<c:out value="${p.ray.getX()-carre.getX1()}"/>,
                        y:<c:out value="${p.ray.getY()-carre.getY1()}"/>, 
                        label:'<c:out value="${p.ray.nom}"/>',
                        value:'<c:out value="${p.efficacite}"/>'
                    },
                </c:forEach>
                
                 ];
                 //alert(JSON.stringify(source));
                 const ctx = document.getElementById('canvas').getContext('2d');
                 function show(){
                     ctx.fillStyle = 'black';
                     source.forEach( e => {
                         ctx.fillRect(e.x, e.y, 3, 3);
                         ctx.fillText(e.label, e.x, e.y);
                         ctx.fillText(e.value, e.x, e.y+10);
                     });
                     var grd = ctx.createLinearGradient(
                            <c:out value="${worst.getX()-carre.getX1()}"/>, 
                             <c:out value="${worst.getY()-carre.getY1()}"/>, 
                             <c:out value="${best.getX()-carre.getX1()}"/>, 
                             <c:out value="${best.getY()-carre.getY1()}"/>,
                                 );
                     if(type==1){
                         grd.addColorStop(0, "black");
                         grd.addColorStop(1, "grey");
                     }
                     else if(type==2){
                         grd.addColorStop(0, "yellow");
                         grd.addColorStop(1, "red");
                     }
                     else if(type==3){
                         grd.addColorStop(0, "blue");
                         grd.addColorStop(1, "green");
                     }else{
                        grd.addColorStop(0, "green");
                         grd.addColorStop(1, "red");

                     }
                     ctx.strokeStyle = grd;
                     canvas_arrow(ctx,
                            <c:out value="${worst.getX()-carre.getX1()}"/>, 
                             <c:out value="${worst.getY()-carre.getY1()}"/>, 
                             <c:out value="${best.getX()-carre.getX1()}"/>, 
                             <c:out value="${best.getY()-carre.getY1()}"/>,
                             );
                     ctx.stroke();
                 }
                 show();
                 function canvas_arrow(context, fromx, fromy, tox, toy) {
                     var headlen = 10; // length of head in pixels
                     var dx = tox - fromx;
                     var dy = toy - fromy;
                     var angle = Math.atan2(dy, dx);
                     context.moveTo(fromx, fromy);
                     context.lineTo(tox, toy);
                     context.lineTo(tox - headlen * Math.cos(angle - Math.PI / 6), toy - headlen * Math.sin(angle - Math.PI / 6));
                     context.moveTo(tox, toy);
                     context.lineTo(tox - headlen * Math.cos(angle + Math.PI / 6), toy - headlen * Math.sin(angle + Math.PI / 6));
                 }
             </script>
         </body>
     </html>
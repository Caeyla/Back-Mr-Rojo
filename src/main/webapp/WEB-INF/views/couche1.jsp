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
                 var incr=0;
                 var source1 = [
                 <c:forEach var="p" items="${listval1}">
                    {
                        x:<c:out value="${p.ray.getX()-carre.getX1()}"/>,
                        y:<c:out value="${p.ray.getY()-carre.getY1()}"/>, 
                        label:'<c:out value="${p.ray.nom}"/>',
                        value:'<c:out value="${p.efficacite}"/>',
                        ref:'durete'
                    },
                </c:forEach>
                
                 ];
                 var source2 = [
                 <c:forEach var="p" items="${listval2}">
                    {
                        x:<c:out value="${p.ray.getX()-carre.getX1()}"/>,
                        y:<c:out value="${p.ray.getY()-carre.getY1()}"/>, 
                        label:'<c:out value="${p.ray.nom}"/>',
                        value:'<c:out value="${p.efficacite}"/>',
                        ref:'prop'
                    },
                </c:forEach>
                
                 ];
                 var source3 = [
                 <c:forEach var="p" items="${listval3}">
                    {
                        x:<c:out value="${p.ray.getX()-carre.getX1()}"/>,
                        y:<c:out value="${p.ray.getY()-carre.getY1()}"/>, 
                        label:'<c:out value="${p.ray.nom}"/>',
                        value:'<c:out value="${p.efficacite}"/>',
                        ref:'efficacite'
                    },
                </c:forEach>
                
                 ];
                 var source4 = [
                 <c:forEach var="p" items="${listval4}">
                    {
                        x:<c:out value="${p.ray.getX()-carre.getX1()}"/>,
                        y:<c:out value="${p.ray.getY()-carre.getY1()}"/>, 
                        label:'<c:out value="${p.ray.nom}"/>',
                        value:'<c:out value="${p.efficacite}"/>',
                        ref:'exploitation'
                    },
                </c:forEach>
                
                 ];
                 const ctx = document.getElementById('canvas').getContext('2d');
                 const ctx2 = document.getElementById('canvas').getContext('2d');
                 const ctx3 = document.getElementById('canvas').getContext('2d');
                 const ctx4 = document.getElementById('canvas').getContext('2d');
                 function show1(){
                     ctx.fillStyle = 'black';
                    
                     source1.forEach( e => {
                         ctx.fillRect(e.x, e.y, 3, 3);
                         ctx.fillText(e.label, e.x, e.y);
                         ctx.fillText(e.ref+":"+e.value, e.x, e.y+10);
                     });
                     var grd = ctx.createLinearGradient(
                            <c:out value="${worst1.getX()-carre.getX1()+20}"/>, 
                             <c:out value="${worst1.getY()-carre.getY1()+10}"/>, 
                             <c:out value="${best1.getX()-carre.getX1()+20}"/>, 
                             <c:out value="${best1.getY()-carre.getY1()+10}"/>,
                                 );
                                 grd.addColorStop(0, "blue");
                         grd.addColorStop(1, "blue");
                     ctx.strokeStyle = grd;
                     canvas_arrow(ctx,
                            <c:out value="${worst1.getX()-carre.getX1()+20}"/>, 
                             <c:out value="${worst1.getY()-carre.getY1()+10}"/>, 
                             <c:out value="${best1.getX()-carre.getX1()+20}"/>, 
                             <c:out value="${best1.getY()-carre.getY1()+10}"/>,
                             );
                     ctx.stroke();
                 }
                 function show2(){
                     ctx2.fillStyle = 'black';
                    
                     source2.forEach( e => {
                         ctx2.fillText(e.ref+":"+e.value, e.x, e.y+20);
                     });
                     var grd = ctx2.createLinearGradient(
                            <c:out value="${worst2.getX()-carre.getX1()+20}"/>, 
                             <c:out value="${worst2.getY()-carre.getY1()+20}"/>, 
                             <c:out value="${best2.getX()-carre.getX1()+20}"/>, 
                             <c:out value="${best2.getY()-carre.getY1()+20}"/>,
                                 );
                    
                         grd.addColorStop(0, "black");
                         grd.addColorStop(1, "black");
                     
                     

                     
                     ctx2.strokeStyle = grd;
                     canvas_arrow(ctx2,
                            <c:out value="${worst2.getX()-carre.getX1()+20}"/>, 
                             <c:out value="${worst2.getY()-carre.getY1()+20}"/>, 
                             <c:out value="${best2.getX()-carre.getX1()+20}"/>, 
                             <c:out value="${best2.getY()-carre.getY1()+20}"/>,
                             );
                     ctx2.stroke();
                 }
                 function show3(){
                     ctx3.fillStyle = 'black';
                    
                     source3.forEach( e => {
                         ctx3.fillText(e.ref+":"+e.value, e.x, e.y+30);
                     });
                     var grd = ctx3.createLinearGradient(
                            <c:out value="${worst3.getX()-carre.getX1()+20}"/>, 
                             <c:out value="${worst3.getY()-carre.getY1()+30}"/>, 
                             <c:out value="${best3.getX()-carre.getX1()+20}"/>, 
                             <c:out value="${best3.getY()-carre.getY1()+30}"/>,
                                 );
                                 grd.addColorStop(0, "red");
                         grd.addColorStop(1, "red");
                     ctx3.strokeStyle = grd;
                     canvas_arrow(ctx3,
                            <c:out value="${worst3.getX()-carre.getX1()+20}"/>, 
                             <c:out value="${worst3.getY()-carre.getY1()+30}"/>, 
                             <c:out value="${best3.getX()-carre.getX1()+20}"/>, 
                             <c:out value="${best3.getY()-carre.getY1()+30}"/>,
                             );
                     ctx3.stroke();
                 }
                 function show4(){
                     ctx4.fillStyle = 'black';
                    
                     source4.forEach( e => {
                         ctx4.fillText(e.ref+":"+e.value, e.x, e.y+40);
                     });
                     var grd = ctx4.createLinearGradient(
                            <c:out value="${worst4.getX()-carre.getX1()+20}"/>, 
                             <c:out value="${worst4.getY()-carre.getY1()+40}"/>, 
                             <c:out value="${best4.getX()-carre.getX1()+20}"/>, 
                             <c:out value="${best4.getY()-carre.getY1()+40}"/>,
                                 );
                                 grd.addColorStop(0, "green");
                         grd.addColorStop(1, "green");
                     ctx4.strokeStyle = grd;
                     canvas_arrow(ctx4,
                            <c:out value="${worst4.getX()-carre.getX1()+20}"/>, 
                             <c:out value="${worst4.getY()-carre.getY1()+40}"/>, 
                             <c:out value="${best4.getX()-carre.getX1()+20}"/>, 
                             <c:out value="${best4.getY()-carre.getY1()+40}"/>,
                             );
                     ctx4.stroke();
                 }
                 show1();
                 show2();
                 show3();
                 show4();
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
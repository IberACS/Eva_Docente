/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var peticion = false;

var testPasado = false;

try
{
    peticion = new XMLHttpRequest();
}
catch (trymicrosoft) {
    try
    {
        peticion = new ActiveXObject("Msxml2.XMLHTTP");
    }
    catch (othermicrosoft)
    {
        try
        {
            peticion = new ActiveXObject("Microsoft.XMLHTTP");
        }
        catch (failed)
        {
            peticion = false;
        }
    }
}

if (!peticion) {
    alert("ERROR AL INICIALIZAR!");
}

function peti() {
    var peticion = false;
    try
    {
        peticion = new XMLHttpRequest();
    }
    catch (trymicrosoft) {
        try
        {
            peticion = new ActiveXObject("Msxml2.XMLHTTP");
        }
        catch (othermicrosoft)
        {
            try
            {
                peticion = new ActiveXObject("Microsoft.XMLHTTP");
            }
            catch (failed)
            {
                peticion = false;
            }
        }
    }

    if (!peticion) {
        alert("ERROR AL INICIALIZAR!");
    } else {
        return peticion;
    }
}

function MuestraProfesor(url, element_id) {
    var elemento = document.getElementById(element_id);
    var periodo = document.getElementById('p1').value;
    var carrera=document.getElementById('car').value;
    var cambio=document.getElementById('cambio').value;
    
if (isNaN(periodo)){
        var fragment_url = url + '?periodo=' + periodo+'&carrera='+carrera+'&cambio='+cambio;
        elemento.innerHTML = '<img src="images/loading.gif"/>' + '  Procesando tu informaci\u00f3n espera un momento.';
        peticion.open("post", fragment_url);
        peticion.onreadystatechange = function ()
        {
            if (peticion.readyState === 4)
            {
                elemento.innerHTML = peticion.responseText;
            }

        };
        peticion.send(null);
}
    else{
    alert("Debes seleccionar un Periodo");
    return false; 
    }
}



function MuestraProfesor2M(url, element_id) {
    var elemento = document.getElementById(element_id);
    var cambio=document.getElementById('cambio11').value;
    var carrera=document.getElementById('carreraM').value;
    var periodo=document.getElementById('periodoM').value;
    var grupo=document.getElementById('grupoM').value;
    
    if(grupo.length===0){
     alert("Debes llenar todos los campos");   
     return false;
    }
    else{
        var fragment_url = url + '?cambio='+cambio+'&carrera='+carrera+'&periodo='+periodo+'&grupo='+grupo;
        elemento.innerHTML = '<img src="images/loading.gif"/>' + '  Procesando tu informaci\u00f3n espera un momento.';
        peticion.open("post", fragment_url);
        peticion.onreadystatechange = function ()
        {
            if (peticion.readyState === 4)
            {
                elemento.innerHTML = peticion.responseText;
            }
           
        };
        peticion.send(null);

}
}


function MuestraMaterias(url, element_id) {
    var elemento = document.getElementById(element_id);
    var idprofesor = document.getElementById('idp').value;
    var cambio=document.getElementById('cambio2').value;
    var carrera=document.getElementById('car').value;
    var periodo=document.getElementById('per').value;
if (idprofesor===0){
    alert("Debes seleccionar un Profesor");
    return false;
}
   else{
        var fragment_url = url + '?idprofesor=' + idprofesor+'&cambio='+cambio+'&carrera='+carrera+'&periodo='+periodo;
        elemento.innerHTML = '<img src="images/loading.gif"/>' + '  Procesando tu informaci\u00f3n espera un momento.';
        peticion.open("post", fragment_url);
        peticion.onreadystatechange = function ()
        {
            if (peticion.readyState === 4)
            {
                elemento.innerHTML = peticion.responseText;
            }
        };
        peticion.send(null);
}
}

function MuestraMaterias2M(url, element_id) {
    var elemento = document.getElementById(element_id);
    var idprofesor = document.getElementById('idpM').value;
    var cambio=document.getElementById('cambio12').value;
    var carrera=document.getElementById('carM').value;
    var periodo=document.getElementById('perM').value;
    var grupo=document.getElementById('grupoM').value;
    if (idprofesor===0){
        alert("Debes seleccionar un Profesor");
        return false;
    }
       else{
            var fragment_url = url + '?idprofesor=' + idprofesor+'&cambio='+cambio+'&carrera='+carrera+'&periodo='+periodo+'&grupo='+grupo;
            elemento.innerHTML = '<img src="images/loading.gif"/>' + '  Procesando tu informaci\u00f3n espera un momento.';
            peticion.open("post", fragment_url);
            peticion.onreadystatechange = function ()
            {
                if (peticion.readyState === 4)
                {
                    elemento.innerHTML = peticion.responseText;
                }
            };
            peticion.send(null);
    }
}


function GraficaDocentes(url, element_id) {
    var elemento = document.getElementById(element_id);
    var carrera = document.getElementById('carrera').value;
    var cambio=document.getElementById('cambio3').value;
    var idprof=document.getElementById('idp').value;
    var perio=document.getElementById('per').value;
        var fragment_url = url + '?idprofesor=' + idprof+'&cambio='+cambio+'&carrera='+carrera+'&periodo='+perio;
        elemento.innerHTML = '<img src="images/loading.gif"/>' + '  Procesando tu informaci\u00f3n espera un momento.';
        peticion.open("post", fragment_url);
        peticion.onreadystatechange = function ()
        {
            if (peticion.readyState === 4)
            {
                elemento.innerHTML = peticion.responseText;
              //  document.getElementById('footer').style.display = 'none';
              // document.getElementById('sec4').style.display = 'block';
            }
        };
        peticion.send(null);
}

function GraficaDocentes2M(url, element_id) {
    var elemento = document.getElementById(element_id);
    var carrera = document.getElementById('carreraM').value;
    var cambio=document.getElementById('cambio13').value;
    var idprof=document.getElementById('idpM').value;
    var perio=document.getElementById('perM').value;
    var grupo=document.getElementById('grupoM').value;
        var fragment_url = url + '?idprofesor=' + idprof+'&cambio='+cambio+'&carrera='+carrera+'&periodo='+perio+'&grupo='+grupo;
        elemento.innerHTML = '<img src="images/loading.gif"/>' + '  Procesando tu informaci\u00f3n espera un momento.';
        peticion.open("post", fragment_url);
        peticion.onreadystatechange = function ()
        {
            if (peticion.readyState === 4)
            {
                elemento.innerHTML = peticion.responseText;
               // document.getElementById('footer').style.display = 'none';
//                document.getElementById('sec4').style.display = 'block';
            }
        };
        peticion.send(null);
}

function GraficaDocentes2MM(url, element_id,g) {
    var elemento = document.getElementById(element_id);
    var carrera = document.getElementById('carrera').value;
    var cambio=document.getElementById('cambio3').value;
    var idprof=document.getElementById('idp').value;
    var perio=document.getElementById('per').value;
    //var grupo=document.getElementById(g).value;
    var grupo=g;
        var fragment_url = url + '?idprofesor=' + idprof+'&cambio='+cambio+'&carrera='+carrera+'&periodo='+perio+'&grupo='+grupo;
        elemento.innerHTML = '<img src="images/loading.gif"/>' + '  Procesando tu informaci\u00f3n espera un momento.';
        peticion.open("post", fragment_url);
        peticion.onreadystatechange = function ()
        {
            if (peticion.readyState === 4)
            {
                elemento.innerHTML = peticion.responseText;
              //  document.getElementById('footer').style.display = 'none';

            }
        };
        peticion.send(null);
}

function generarPDF() {
    
   var PDA2= document.getElementById("PDA2").value;
   var PPC2=document.getElementById("PPC2").value;
   var PAA2= document.getElementById("PAA2").value;
   var PEM2= document.getElementById("PEM2").value;
   var PM2= document.getElementById("PM2").value;
   var PE2= document.getElementById("PE2").value;
   var PC2= document.getElementById("PC2").value;
   var PG2= document.getElementById("PG2").value;
   var PTLA2= document.getElementById("PTLA2").value;
   var PS2 = document.getElementById("PS2").value;
   var RG2 = document.getElementById("RG2").value;
   var period = document.getElementById("period").value;
   var url = 'GraficasPDF' + '?PDA2='+PDA2+'&PPC2='+PPC2+'&PAA2='+PAA2+'&PEM2='+PEM2+'&PM2='+PM2+'&PE2='+PE2+'&PC2='+PC2+'&PG2='+PG2+'&PTLA2='+PTLA2+'&PS2='+PS2+'&RG2='+RG2+'&period='+period;//Enviamos datos
     window.open(url, "Formato_Evaluación", "directories=no, location=no, menubar=no, scrollbars=yes,statusbar=no, tittlebar=yes, width=600, height=800");
   
}

function CreaReportePeriodo(servets, mensaje) {
    var mensaje2 = document.getElementById(mensaje);
    var Periodo = document.getElementById("pl").value;
    var per=0;
    
    if(Periodo==='0'){
        alert("Selecciona un periodo valido");
        return false;
    }
    else{
    var fragment_url = servets + '?dato=' + Periodo;//Enviamos datos
    mensaje2.innerHTML = '<img src="images/loading.gif"/>' + '  Procesando tu informaci\u00f3n espera un momento.';
    peticion.open("Post", fragment_url); //abrimos la Peticion
    peticion.onreadystatechange = function () //creamos funcion variable para hacer el tratamiento de los datos
    {
        if (peticion.readyState === 4)
        {
            mensaje2.innerHTML = peticion.responseText;
        }
    };
    peticion.send(null);
}
}

function Actualiza(servets, mensaje) {
    
    var mensaje2 = document.getElementById(mensaje);
    var name = document.getElementById("nam").value;
    var pater = document.getElementById("pat").value;
    var mater = document.getElementById("mat").value;
    var pa = document.getElementById("pa").value;
    var ti=document.getElementById('ti').value;
    if(name.length===0 | pater.length===0 | mater.length===0 | pa.length===0 | ti.length===0){
        alert('Debes llenar todos los campos');
       return false;
   }
        else{
    var fragment_url = servets + '?name=' + encodeURIComponent(name)+'&pater='+encodeURIComponent(pater)+'&mater='+encodeURIComponent(mater)+'&pa='+pa+'&ti='+encodeURIComponent(ti);//Enviamos datos
    mensaje2.innerHTML = '<img src="images/loading.gif"/>' + '  Procesando tu informaci\u00f3n espera un momento.';
    peticion.open("POST", fragment_url,true); //abrimos la Peticion
    peticion.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8",');
    peticion.onreadystatechange = function () //creamos funcion variable para hacer el tratamiento de los datos
    {
        if (peticion.readyState === 4)
        {
            mensaje2.innerHTML = peticion.responseText;
            document.getElementById("formu").reset();
        }
    };
      peticion.send(null);
}
}

function Cambio(servlet,div){
   var mensaje2=document.getElementById(div);
   var a=document.getElementById('act').value;
   var b=document.getElementById('fas').value;
    if(a==='x' | b==='x'){
    alert("Verifica tus datos");
    return false;
    }
    else{
    var frag = servlet +'?cambio='+a+'&cambio2='+b;
    mensaje2.innerHTML = '<img src="images/loading.gif"/>' + '  Procesando tu informaci\u00f3n espera un momento.';
    peticion.open("POST", frag,true); //abrimos la Peticion
    peticion.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8",');
    peticion.onreadystatechange = function () //creamos funcion variable para hacer el tratamiento de los datos
    {
        if (peticion.readyState === 4)
        {
            mensaje2.innerHTML = peticion.responseText;
        }
    };
      peticion.send(null);
}   

}
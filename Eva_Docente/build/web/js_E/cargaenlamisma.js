var pe = false;
var te = false;

try {
    pe = new XMLHttpRequest();
}
catch (trymicrosoft)
{
    try
    {
        pe = new ActiveXObject("Msxml2.XMLHTTP");
    }
    catch (othermicrosoft)
    {
        try
        {
            pe = new ActiveXObject("Microsoft.XMLHTTP");
        }
        catch (failed)
        {
            pe = false;
        }
    }
}
if (!pe)
    alert("ERROR AL INICIALIZAR!");


function invocaServlet(servlet, div) {
  //  alert(servlet);
    var element = document.getElementById(div);
    element.innerHTML = '<center><img src="images/loading.gif"/>' + '  Procesando tu informaci&oacute;n espera un momento.</center>';
    pe.open("Post", servlet); //abrimos la Peticion
    pe.onreadystatechange = function () //creamos funcion variable para hacer el tratamiento de los datos ActualizarContraseña
    {
        if (pe.readyState === 4)
        {
            element.innerHTML = pe.responseText;
        }
    };
    pe.send();
}

function init() {
    for (var a = 2; a <= 8; a++) {
        document.getElementById('pestana' + a).style.display = 'none';
    }
    }
   
function nobackbutton(){	
   window.location.hash="no-back-button";
   window.location.hash="Again-No-back-button"; //chrome
   window.onhashchange=function(){window.location.hash="no-back-button";}
   if(history.forward(1)){
   history.replace(history.forward(1));
}
}

function viewSections(id) {
    for (var i = 1; i <= 11; i++) {        
        if(i === id){
        }
        try {
            if (i !== id) {
                document.getElementById('pestana' + i).style.display = 'none';//oculta div
                //alert('pestana'+i);
            }
            else {
                document.getElementById('pestana' + i).style.display = 'block';//muestra div 
               // alert('pestana'+i);
            } 
        } catch (err) {
        }
}
}

function viewSections2(id) {
    for (var i = 1; i <= 8; i++) {
        if (i === id) {
            if (i === 3) {
                invocaServlet( 'MMateria','pestana'+i);
                viewSections(i);
            }
            if (i === 4) {
                invocaServlet( 'LEvaluados','pestana'+i);
                viewSections(i);
            }
            if (i === 5) {
                invocaServlet( 'Persona','pestana'+i);
                viewSections(i);
            }
            if (i === 6) {
                invocaServlet( 'PeriodoED','pestana'+i);
                viewSections(i);
            }
        }
    }
}

function limpiar(id) {
var d = document.getElementById(id);
while (d.hasChildNodes())
d.removeChild(d.firstChild);
}


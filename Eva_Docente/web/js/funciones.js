
    $(window).load(function() {
        $('.flexslider').flexslider({
            controlsContainer: '.flex-container'
            });
        });
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


var peticion = false;
var  testPasado = false;

try 
{
        peticion = new XMLHttpRequest();
}
    catch (trymicrosoft) 
    {
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

if (!peticion)alert("ERROR AL INICIALIZAR!");

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 
 function Login(url, element_id) {
    
    var element = document.getElementById(element_id);
    
    var Usuario=document.getElementById("Usu");
    var Usu=Usuario.value;
    var Passw=document.getElementById("Pas");
    var Pas=Passw.value;
   
   if(Usu.length === 0 || Pas.length === 0){
       return false;
   }else{
    var fragment_url = url+'?Usu='+Usu+'&Pas='+Pas;//Enviamos datos
    element.innerHTML = '<img src="images/loading.gif"/>'+' Procesando tu información espera un momento.'; 
    peticion.open("POST", fragment_url); //abrimos la Peticion
    peticion.onreadystatechange = function() //creamos funcion variable para hacer el tratamiento de los datos
    {if (peticion.readyState === 4) 
       {
            var msj=peticion.responseText.trim();
            
            if(msj === 'Error1'){
                alert("Error de conexión, Favor de verificarlo con el administrador del sistema.");
            }else if(msj === 'Error2'){
                alert("Error al consultar la información, Favor de verificarlo con el administrador del sistema.");
            }else if(msj === 'Error3'){
                element.innerHTML= '<b style=\"color:#F60A35\">Matricula o Contraseña incorrecta, Favor de verificar tu información.</b>';
            }else if(msj === 'Error4'){
                element.innerHTML= '<b style=\"color:#F60A35\">¡Lo sentimos!, Aún no son fechas de evaluación Docente.</b>';
            }else{
                document.location.replace(msj);    
            }
        } 
    }; 
   peticion.send(null);
  }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 
 function MuestraEncues(url, IdInscr, NombProf, NombAsig) {
    
    var element = document.getElementById("Encu");
   
    var fragment_url = url+'?IdInscripcion='+IdInscr+'&NombProf='+NombProf+'&NombAsig='+NombAsig;//Enviamos datos
    element.innerHTML = '<img src="images/loading.gif"/>'+' Procesando tu información espera un momento.'; 
    peticion.open("POST", fragment_url); //abrimos la Peticion
    peticion.onreadystatechange = function() //creamos funcion variable para hacer el tratamiento de los datos
    {if (peticion.readyState === 4) 
       {
            element.innerHTML = peticion.responseText;
           $("#form1").hide(); 
           $("#form2").show(); 
        } 
    }; 
   peticion.send(null);
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

function GPEncuesta(url, IdInscr) {
    
    var element = document.getElementById("Inscrip");
    
    var Preg1 = $("input[id=pregunta1]:checked").length;
    var P1 = $("input[id=pregunta1]:checked").val();
    var Preg2 = $("input[id=pregunta2]:checked").length;
    var P2 = $("input[id=pregunta2]:checked").val();
    var Preg3 = $("input[id=pregunta3]:checked").length;
    var P3 = $("input[id=pregunta3]:checked").val();
    var Preg4 = $("input[id=pregunta4]:checked").length;
    var P4 = $("input[id=pregunta4]:checked").val();
    var Preg5 = $("input[id=pregunta5]:checked").length;
    var P5 = $("input[id=pregunta5]:checked").val();
    var Preg6 = $("input[id=pregunta6]:checked").length;
    var P6 = $("input[id=pregunta6]:checked").val();
    var Preg7 = $("input[id=pregunta7]:checked").length;
    var P7 = $("input[id=pregunta7]:checked").val();
    var Preg8 = $("input[id=pregunta8]:checked").length;
    var P8 = $("input[id=pregunta8]:checked").val();
    var Preg9 = $("input[id=pregunta9]:checked").length;
    var P9 = $("input[id=pregunta9]:checked").val();
    var Preg10 = $("input[id=pregunta10]:checked").length;
    var P10 = $("input[id=pregunta10]:checked").val();
    var Preg11 = $("input[id=pregunta11]:checked").length;
    var P11 = $("input[id=pregunta11]:checked").val();
    var Preg12 = $("input[id=pregunta12]:checked").length;
    var P12 = $("input[id=pregunta12]:checked").val();
    var Preg13 = $("input[id=pregunta13]:checked").length;
    var P13 = $("input[id=pregunta13]:checked").val();
    var Preg14 = $("input[id=pregunta14]:checked").length;
    var P14 = $("input[id=pregunta14]:checked").val();
    var Preg15 = $("input[id=pregunta15]:checked").length;
    var P15 = $("input[id=pregunta15]:checked").val();

    if(Preg1 === 0 || Preg2 === 0 || Preg3 === 0 || Preg4 === 0 || Preg5 === 0 ||
       Preg6 === 0 || Preg7 === 0 || Preg8 === 0 || Preg9 === 0 || Preg10 === 0 ||
       Preg11 === 0 || Preg12 === 0 || Preg13 === 0 || Preg14 === 0 || Preg15 === 0){  
     return false;   
    }else{
    var fragment_url = url+'?IdInscr='+IdInscr+'&Pregunta1='+P1+'&Pregunta2='+P2+'&Pregunta3='+P3+'&Pregunta4='+P4+'&Pregunta5='+P5+
                                               '&Pregunta6='+P6+'&Pregunta7='+P7+'&Pregunta8='+P8+'&Pregunta9='+P9+'&Pregunta10='+P10+
                                               '&Pregunta11='+P11+'&Pregunta12='+P12+'&Pregunta13='+P13+'&Pregunta14='+P14+'&Pregunta15='+P15;//Enviamos datos
    peticion.open("POST", fragment_url); //abrimos la Peticion
    peticion.onreadystatechange = function() //creamos funcion variable para hacer el tratamiento de los datos
    {if (peticion.readyState === 4) 
       {
            var msj=peticion.responseText.trim();
            
            if(msj === 'Error1'){
                alert("Error de conexión, favor de verificarlo con el administrador del sistema.");
            }else if(msj === 'Error2'){
                alert("Error al guardar la Evaluación, favor de verificarlo con el administrador del sistema.");
            }else{
                alert("El profesor se a evaluado correctamente.");
                element.innerHTML = peticion.responseText;
                $("#form1").show(); 
                $("#form2").hide(); 
            }
       }
    }; 
   peticion.send(null);
   }
}
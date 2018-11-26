/* global peticion */

//Como vemos la función se encarga de hacer un bucle de 1 a 4 y cada valor concaternarlo a ‘contenido’ para al sacar el 
//id del div hacerle un display=’none’.

//function viewSection()

function init(){
    for(var a=2; a <= 2; a++){
        document.getElementById('pestana'+a).style.display ='none';
    }
}

function viewSection(id){
    
    for (var i=0; i <= 1; i++){
        
        if(i === id){
            
            if(i===1){
                document.getElementById("Sel1").className="current_page_item";
            }
        }
        
        if(i !== id){
            document.getElementById('pestana'+i).style.display = 'none';    
        }
        else{
            document.getElementById('pestana'+i).style.display = 'block';
        }        
    }
}


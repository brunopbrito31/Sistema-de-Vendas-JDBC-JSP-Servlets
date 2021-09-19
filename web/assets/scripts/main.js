/* global body */
var semana = ["Domingo", "Segunda-Feira", "Terça-Feira", "Quarta-Feira", "Quinta-Feira", "Sexta-Feira", "Sábado"];
var d = new Date();
//var buttonLogin = document.querySelector(".btlogin");
//buttonLogin.disabled(true);
//var formLogin = document.querySelector(".main");
//
//document.querySelector(".main").action.defineProperties("disabled",true);

function dataAtualFormatada(){
    var data = new Date(),
        dia  = data.getDate().toString().padStart(2, '0'),
        mes  = (data.getMonth()+1).toString().padStart(2, '0'), //+1 pois no getMonth Janeiro começa com zero.
        ano  = data.getFullYear();
    return (semana[data.getDay()]).substring(0,3)+", "+dia+"/"+mes+"/"+ano;
}

function welcomeMsg(){
//    var texto = document.getElementById('lblerror').textContent;
//    document.getElementById('lblerror').textContent = "novo";
//    var texto2 = document.getElementById('lblerror').textContent;
//    console.log("Teste: "+texto+" Teste2: "+texto2);
    alert("Bem vindo! "+dataAtualFormatada());
    

}

function setUserMsg(){
    document.getElementById('inputname').placeholder = "Digite o usuário";
}

function backToUser(){
    document.getElementById('inputname').placeholder = "Usuário";
}

function setPasswordMsg(){
    document.getElementById('bottbn').placeholder = "Digite o senha";
}

function backToPassword(){
    document.getElementById('bottbn').placeholder = "Senha";
}

function validateButton(){
    var userName = document.getElementById('inputname').value;
    var userPassword = document.getElementById('bottbn').value;
    var buttonLogin = document.querySelector('btlogin');

    if(userName === "" || userPassword === ""){
//        buttonLogin.disabled(true);
        document.getElementById('lblerror').textContent = "Usuário e/ou senha inválidos";
        alert("Usuário e/ou senha inválidos");
    }
    
}


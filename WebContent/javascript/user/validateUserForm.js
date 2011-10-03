$(document).ready(function(){
	$("form").validate({
        rules:{
            "user.login":{
                required: true,
            },
            "user.email": {
                required: true,
                email: true
            },
            "user.password": {
                required: true
            },
            "confirmation":{
                required: true,
                equalTo: "#password"
            }
        },
        messages:{
            "user.login":{
                required: "O campo login � obrigat�rio.",
            },
            "user.email": {
                required: "O campo email � obrigat�rio.",
                email: "O campo e-mail deve conter um e-mail v�lido."
            },
            "user.password": {
                required: "O campo senha � obrigat�rio."
            },
            "confirmation":{
                required: "O campo confirma��o de senha � obrigat�rio.",
                equalTo: "O campo confirma��o de senha deve ser id�ntico ao campo senha."
            }
        }
    });
});
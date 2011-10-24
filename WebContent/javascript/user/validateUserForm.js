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
            "user.name": {
            	required: true
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
                required: "O campo login &eacute; obrigat&oacute;rio."
            },
            "user.email": {
                required: "O campo e-mail &eacute; obrigat&oacute;rio.",
                email: "O campo e-mail deve conter um e-mail v&aacute;lido."
            },
            "user.name": {
            	required: "O campo nome é obrigatório."
            },
            "user.password": {
                required: "O campo senha &eacute; obrigat&oacute;rio."
            },
            "confirmation":{
                required: "O campo confirma&ccedil;&atilde;o de senha &eacute; obrigat&oacute;rio.",
                equalTo: "O campo confirma&ccedil;&atilde;o de senha deve ser id&ecirc;ntico ao campo senha."
            }
        }
    });
});
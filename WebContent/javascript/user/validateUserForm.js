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
                required: " O campo login é obrigatório.",
            },
            "user.email": {
                required: " O campo e-mail é obrigatório.",
                email: " O campo e-mail deve conter um e-mail válido."
            },
            "user.password": {
                required: " O campo senha é obrigatório."
            },
            "confirmation":{
                required: " O campo confirmação de senha é obrigatório.",
                equalTo: " O campo confirmação de senha deve ser idêntico ao campo senha."
            }
        }
    });
});
package infra;

public class Email {
	public static String templateForMessage(String description, long id) {
		return
			description +
			'\n' +
			"Link para a pergunta: " +
			"http://linux.ime.usp.br:8080/rede-social-de-especialistas/perguntas/" +
			id + '/';
	}
}

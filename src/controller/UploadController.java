package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import infra.UserSession;
import interceptor.annotations.LoggedUser;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

@Resource
public class UploadController {
	private final Result result;
	private final UserSession userSession;
	
	public UploadController(Result result, UserSession userSession) {
		this.result = result;
		this.userSession = userSession;
	}

	@LoggedUser
	@Path("/upload/salvar/")
	public void saveFile(UploadedFile uploadFile) {
		File file = new File("/tmp/dummy.c"); //Mudar aqui
		if (uploadFile == null) {
			result.include("sucesso",new String("Fracasso!"));
			return;
		}
		result.include("sucesso", new String("Sucesso!"));
		try {
			IOUtils.copy(uploadFile.getFile(), new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace(); //Mudar aqui
		} catch (IOException e) {
			e.printStackTrace(); //Mudar aqui
		}
	}
	
	@LoggedUser
	@Path("/upload/")
	public void uploadForm() {
	}
}

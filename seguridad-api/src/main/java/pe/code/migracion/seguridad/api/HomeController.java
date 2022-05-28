package pe.code.migracion.seguridad.api;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.code.migracion.seguridad.util.Utilitario;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class HomeController {

	@GetMapping("home") 
	public ResponseEntity<String> home() {
		String mensaje = "Bienvenido en la fecha:" + Utilitario.getDateFormat("dd/MM/yyyyy HH:mm:ss", new Date());
		return new ResponseEntity<String>(mensaje, HttpStatus.OK);
	}
}

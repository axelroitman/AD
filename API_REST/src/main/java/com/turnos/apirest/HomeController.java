package com.turnos.apirest;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import controlador.Controlador;
import views.UsuarioView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = {"application/json"})
	public @ResponseBody<json> String login(@RequestParam(value="usuario", required=true) String usuario, @RequestParam(value="password", required=true) String password) throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			UsuarioView user = Controlador.getInstancia().getUsuarioLogIn(usuario, password);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(user);

	}
	
	@RequestMapping(value = "/getUsuario", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getUsuario(@RequestParam(value="usuario", required=true) String usuario) throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			UsuarioView user = Controlador.getInstancia().getUsuario(usuario);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(user);

	}

	@RequestMapping(value = "/getTurno", method = RequestMethod.GET, produces = {"application/json"})
	
	public @ResponseBody<json> String getTurno(@RequestParam(value="id", required=true) int id) throws JsonProcessingException {
		
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			TurnoView Turno = Controlador.getInstancia().getTurno(id);	
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(Turno);
	}

	
	/*@RequestMapping(value = "/getMedicos", method = RequestMethod.GET, produces = {"application/json"})
		
		public @ResponseBody<json> String getMedicos(@RequestParam(value="matricula", required=true) String matricula) throws JsonProcessingException {	
			//ResponseBody<json>: Aclara que el String guarda un JSON		
			//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			MedicoView med = Controlador.getInstancia().getMedico(matricula);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(med);
		}
	*/
	
	@RequestMapping(value = "/eliminarTurno", method = RequestMethod.DELETE)
	public @ResponseEntity<void> eliminarTurno(@RequestParam(value="id", required=true) int id) {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
		try {
			controlador.getInstancia().eliminarTurno(id);
			return new ResponseEntity<void>(HttpStatus.OK);
		} catch ( TurnoException e) {	
			return new ResponseEntity<void>(HttpStatus.CONFLICT);
	}
}

package com.turnos.apirest;

import java.text.DateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import controlador.Controlador;
import exceptions.ListaDeEsperaException;
import exceptions.TurnoException;
import views.TurnoView;
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
		
		model.addAttribute("serverTime", formattedDate);
		
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
	public ResponseEntity<Void>  eliminarTurno(@RequestParam(value="id", required=true) int id) {
		try {
			Controlador.getInstancia().eliminarTurno(id);
			return new ResponseEntity<Void>(HttpStatus.OK);						
		} catch (TurnoException e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);												
			// TODO Auto-generated catch block
			//e.getMessage();
		}
		
	}
	
	@RequestMapping(value = "/agregarAListaDeEspera", method = RequestMethod.PUT)
	public ResponseEntity<Void> agregarAListaDeEspera(@RequestParam(value="idPaciente", required=true) int idPaciente, @RequestParam(value="idEspecialidad", required=true) int idEspecialidad, @RequestParam(value="matricula", required=true) String matricula) {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
				
		try {
			
			Controlador.getInstancia().agregarAListaDeEspera(idPaciente, idEspecialidad, matricula);
			return new ResponseEntity<Void>(HttpStatus.CREATED);												
		} catch (ListaDeEsperaException e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);												
			
		}
		
	}
	
	@RequestMapping(value = "/agregarTurno", method = RequestMethod.PUT)
	public ResponseEntity<Void> agregarTurno(@RequestParam(value="fecha", required=true) Date fecha, @RequestParam(value="hora", required=true) LocalTime hora, @RequestParam(value="idEspecialidad", required=true) int idEspecialidad, @RequestParam(value="matricula", required=true) String matricula, @RequestParam(value="idPaciente", required=true) int idPaciente) {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
		
		try {
			
			Controlador.getInstancia().agregarTurno(fecha, hora, idEspecialidad, matricula, idPaciente);
			return new ResponseEntity<Void>(HttpStatus.CREATED);												
		} catch (TurnoException e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);												
			
		}
		
	}
	
	@RequestMapping(value = "/agregarTurnos", method = RequestMethod.PUT)
	public ResponseEntity<Void> agregarTurnos(@RequestParam(value="idEspecialidad", required=true) int idEspecialidad, @RequestParam(value="matricula", required=true) String matricula, @RequestParam(value="duracion", required=true) int duracion, @RequestParam(value="hora_inicial", required=true) LocalTime horaInicial, @RequestParam(value="hora_final", required=true) LocalTime horaFinal, @RequestParam(value="fecha_inicial", required=true) Date fechaInicial, @RequestParam(value="fecha_final", required=true) Date fechaFinal, @RequestParam(value="lunes", required=true) boolean lunes, @RequestParam(value="martes", required=true) boolean martes, @RequestParam(value="miercoles", required=true) boolean miercoles, @RequestParam(value="jueves", required=true) boolean jueves, @RequestParam(value="viernes", required=true) boolean viernes, @RequestParam(value="sabado", required=true) boolean sabado, @RequestParam(value="domingo", required=true) boolean domingo) {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
				
		try {
			
			Controlador.getInstancia().agregarTurnos(idEspecialidad, matricula, duracion, horaInicial, horaFinal, fechaInicial, fechaFinal, lunes, martes, miercoles, jueves, viernes, sabado, domingo);
			return new ResponseEntity<Void>(HttpStatus.CREATED);												
		} catch (TurnoException e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);												
			
		}
		
	}


}

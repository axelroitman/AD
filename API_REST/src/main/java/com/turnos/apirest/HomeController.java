package com.turnos.apirest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import controlador.Controlador;
import exceptions.ListaDeEsperaException;
import exceptions.TurnoException;
import views.EspecialidadView;
import views.MedicoView;
import views.PacienteView;
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

			TurnoView turno = null;
			try {
				turno = Controlador.getInstancia().getTurno(id);
			} catch (TurnoException e) {
			}	
			
			ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
			return mapper.writeValueAsString(turno);
	}

	@RequestMapping(value = "/getPaciente", method = RequestMethod.GET, produces = {"application/json"})	
	public @ResponseBody<json> String getPaciente(@RequestParam(value="id", required=true) int id) throws JsonProcessingException {
		
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			PacienteView Paciente = Controlador.getInstancia().getPaciente(id);	
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(Paciente);
	}
	
	@RequestMapping(value = "/getMedico", method = RequestMethod.GET, produces = {"application/json"})	
	public @ResponseBody<json> String getMedico(@RequestParam(value="matricula", required=true) String matricula) throws JsonProcessingException {	
		//ResponseBody<json>: Aclara que el String guarda un JSON		
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

		MedicoView med = Controlador.getInstancia().getMedico(matricula);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(med);	
	}

	@RequestMapping(value = "/getEspecialidades", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getEspecialidades() throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			List<EspecialidadView> especialidades = Controlador.getInstancia().getEspecialidades();
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(especialidades);

	}

	@RequestMapping(value = "/getMedicosPorEspecialidad", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getMedicosPorEspecialidad(@RequestParam(value="idEspecialidad", required=true) int idEspecialidad) throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			List<MedicoView> medicoPorEspecialidad = Controlador.getInstancia().getMedicosPorEspecialidad(idEspecialidad);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(medicoPorEspecialidad);

	}

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

	@RequestMapping(value = "/eliminarTurnos", method = RequestMethod.DELETE)
	public ResponseEntity<Void>  eliminarTurnos(@RequestParam(value="matricula", required=true) String matricula, @RequestParam(value="horaInicial", required=true) String horaInicial, @RequestParam(value="horaFinal", required=true) String horaFinal, @RequestParam(value="fechaInicial", required=true) String fechaInicial, @RequestParam(value="fechaFinal", required=true) String fechaFinal, @RequestParam(value="lunes", required=false) boolean lunes, @RequestParam(value="martes", required=false) boolean martes, @RequestParam(value="miercoles", required=false) boolean miercoles, @RequestParam(value="jueves", required=false) boolean jueves, @RequestParam(value="viernes", required=false) boolean viernes, @RequestParam(value="sabado", required=false) boolean sabado, @RequestParam(value="domingo", required=false) boolean domingo) {
		try {
			try {
				Controlador.getInstancia().eliminarTurnos(matricula, LocalTime.parse(horaInicial), LocalTime.parse(horaFinal), new SimpleDateFormat("dd/MM/yyyy").parse(fechaInicial), new SimpleDateFormat("dd/MM/yyyy").parse(fechaFinal), lunes, martes, miercoles, jueves, viernes, sabado, domingo);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new ResponseEntity<Void>(HttpStatus.OK);						
		} catch (TurnoException e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);												
			// TODO Auto-generated catch block
			//e.getMessage();
		}
		
	}
	
	@RequestMapping(value = "/agregarAListaDeEspera", method = RequestMethod.PUT)
	public ResponseEntity<Void> agregarAListaDeEspera(@RequestParam(value="idPaciente", required=true) int idPaciente, @RequestParam(value="idEspecialidad", required=true) int idEspecialidad, @RequestParam(value="matricula", required=false) String matricula) {
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
	public ResponseEntity<Void> agregarTurno(@RequestParam(value="fecha", required=true) @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd hh:mm:ss") Date fecha, @RequestParam(value="idEspecialidad", required=true) int idEspecialidad, @RequestParam(value="matricula", required=true) String matricula) {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

		try {
			Controlador.getInstancia().agregarTurno(fecha, idEspecialidad, matricula);
			return new ResponseEntity<Void>(HttpStatus.CREATED);												
		} catch (TurnoException e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);												
			
		}
		
	}
	
	@RequestMapping(value = "/agregarTurnos", method = RequestMethod.PUT)
	public ResponseEntity<Void> agregarTurnos(@RequestParam(value="idEspecialidad", required=true) int idEspecialidad, @RequestParam(value="matricula", required=true) String matricula, @RequestParam(value="duracion", required=true) int duracion, @RequestParam(value="horaInicial", required=true) String horaInicial, @RequestParam(value="horaFinal", required=true) String horaFinal, @RequestParam(value="fechaInicial", required=true) String fechaInicial, @RequestParam(value="fechaFinal", required=true) String fechaFinal, @RequestParam(value="lunes", required=false) boolean lunes, @RequestParam(value="martes", required=false) boolean martes, @RequestParam(value="miercoles", required=false) boolean miercoles, @RequestParam(value="jueves", required=false) boolean jueves, @RequestParam(value="viernes", required=false) boolean viernes, @RequestParam(value="sabado", required=false) boolean sabado, @RequestParam(value="domingo", required=false) boolean domingo) {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

		try {
			try {
				Controlador.getInstancia().agregarTurnos(idEspecialidad, matricula, duracion, LocalTime.parse(horaInicial), LocalTime.parse(horaFinal), new SimpleDateFormat("dd/MM/yyyy").parse(fechaInicial), new SimpleDateFormat("dd/MM/yyyy").parse(fechaFinal), lunes, martes, miercoles, jueves, viernes, sabado, domingo);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new ResponseEntity<Void>(HttpStatus.CREATED);												
		} catch (TurnoException e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);												
			
		}
		
	}

	@RequestMapping(value = "/modificarTurno", method = RequestMethod.PUT)
	public ResponseEntity<Void> modificarTurnos( @RequestParam(value="idTurno", required=true) int idTurno, @RequestParam(value="idEspecialidad", required=true) int idEspecialidad, @RequestParam(value="hora_inicial", required=true) String horaInicial ) {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
				
		LocalTime hora = LocalTime.parse(horaInicial);
		
		try {
			
			Controlador.getInstancia().modificarTurno(idTurno, idEspecialidad,hora);
			return new ResponseEntity<Void>(HttpStatus.OK);												
		} catch (TurnoException e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);												
			
		}
		
	}

	@RequestMapping(value = "/getTurnosPaciente", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getTurnosPaciente(@RequestParam(value="idPaciente", required=true) int idPaciente, @RequestParam(value="proximos", required=true) boolean proximos) throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			List<TurnoView> turnosPac = Controlador.getInstancia().getTurnosPaciente(idPaciente, proximos);
			ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
			return mapper.writeValueAsString(turnosPac);
	}

	@RequestMapping(value = "/cambiarEstadoDeTurno", method = RequestMethod.PUT)
	public ResponseEntity<Void> cambiarEstadoDeTurno( @RequestParam(value="idTurno", required=true) int idTurno, @RequestParam(value="idPaciente", required=false) Integer idPaciente, @RequestParam(value="asistencia", required=false) Integer asistencia, @RequestParam(value="disponibilidad", required=false) Integer disponibilidad) {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
				
		try {
			
			Controlador.getInstancia().cambiarEstadoDeTurno(idTurno, idPaciente, asistencia, disponibilidad);
			return new ResponseEntity<Void>(HttpStatus.CREATED);												
		} catch (TurnoException e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);												
			
		}
		
	}

	@RequestMapping(value = "/getProximoTurnoPaciente", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getProximoTurnoPaciente(@RequestParam(value="idPaciente", required=true) int idPaciente) throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			TurnoView ProxTurnoPac = Controlador.getInstancia().buscarProxTurnoPaciente(idPaciente);
			ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
			return mapper.writeValueAsString(ProxTurnoPac);
	}

	@RequestMapping(value = "/getTurnosPacientePorEstado", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getTurnosPacientePorEstado(@RequestParam(value="idPaciente", required=true) int idPaciente, @RequestParam(value="estado", required=true) int estado) throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			List<TurnoView> Turnos = Controlador.getInstancia().getTurnosPacientePorEstado(idPaciente, estado);
			ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
			return mapper.writeValueAsString(Turnos);
	}

	@RequestMapping(value = "/getTurnosMedico", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getTurnosMedico(@RequestParam(value="matricula", required=true) String matricula, @RequestParam(value="estado", required=false) Integer estado) throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			List<TurnoView> turnosMed = Controlador.getInstancia().getTurnosMedico(matricula, estado);
			ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
			return mapper.writeValueAsString(turnosMed);
	}

	@RequestMapping(value = "/getTurnosMedicoPorDia", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getTurnosMedicoPorDia(@RequestParam(value="idMedico", required=true) String idMedico, @RequestParam(value="fecha", required=true) @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd") Date fecha) throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			List<TurnoView> turnosMed = Controlador.getInstancia().getTurnosMedicoPorDia(idMedico, fecha);
			ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
			return mapper.writeValueAsString(turnosMed);
	}
	
	@RequestMapping(value = "/getTurnosMedicoPorEspecialidad", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getTurnosMedicoPorEspecialidad(@RequestParam(value="matricula", required=true) String matricula, @RequestParam(value="idEspecialidad", required=true) int idEspecialidad, @RequestParam(value="estado", required=false) Integer estado) throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colecci�n a un JSON usando el m�todo writeValueAsString

			Collection<TurnoView> turnosMedPorEsp = Controlador.getInstancia().getTurnosMedicoPorEspecialidad(matricula,idEspecialidad,estado);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(turnosMedPorEsp);
	}

	@RequestMapping(value = "/getInfoInicioMedico", method = RequestMethod.GET, produces = {"application/json"})	//MAL PLANTEADO -> VER DE API REST
	public @ResponseBody<json> String getInfoInicioMedico(@RequestParam(value="matricula", required=true) String matricula) throws JsonProcessingException {	
		//ResponseBody<json>: Aclara que el String guarda un JSON		
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
		Map<String, String> infoMedico = Controlador.getInstancia().getInfoMedico(matricula);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(infoMedico);	
	}

	@RequestMapping(value = "/getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidad", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidad(@RequestParam(value="idEspecialidad", required=true) int idEspecialidad) throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			Map<Date,Integer> turnosDisponibles = Controlador.getInstancia().getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidad(idEspecialidad);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(turnosDisponibles);
	}

	@RequestMapping(value = "/getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadYMedico", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadYMedico(@RequestParam(value="idEspecialidad", required=true) int idEspecialidad, @RequestParam(value="matricula", required=true) String matricula) throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString

			Map<Date,Integer> turnosDisponibles = Controlador.getInstancia().getCantidadTurnosDisponiblesPorDiaDeUnaEspecialidadYMedico(idEspecialidad, matricula);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(turnosDisponibles);
	}
}

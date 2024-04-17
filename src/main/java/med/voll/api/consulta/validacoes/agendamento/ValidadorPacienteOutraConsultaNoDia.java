package med.voll.api.consulta.validacoes.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.consulta.ConsultaRepository;
import med.voll.api.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.ValidacaoException;

@Component
public class ValidadorPacienteOutraConsultaNoDia implements ValidadorAgendamentoConsultas{

	@Autowired
	private ConsultaRepository repository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		var primeiroHorario = dados.data().withHour(7);
		var ultimoHorario = dados.data().withHour(18);
		var pacientePossuiOutraConultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(),primeiroHorario,ultimoHorario);
		if(pacientePossuiOutraConultaNoDia) {
			throw new ValidacaoException("Paciente ja Possui uma Consulta Agendada Nesse Dia");
		}
	}
}

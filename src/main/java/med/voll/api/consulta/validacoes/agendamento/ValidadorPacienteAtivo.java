package med.voll.api.consulta.validacoes.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.paciente.PacienteRepository;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsultas{

	@Autowired
	private PacienteRepository repository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
		if(!pacienteEstaAtivo) {
			throw new ValidacaoException("Consulta não Pode ser Agendada com Paciente Inativo");
		}
	}
}

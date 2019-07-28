package br.com.luchiari.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luchiari.dto.TimeDTO;
import br.com.luchiari.model.Time;
import br.com.luchiari.repositories.TimeRepository;

@Service
public class TimeService {
	@Autowired
	private TimeRepository timeRepository;

	public TimeDTO salvar(TimeDTO timeDto) {
		Time time = new Time();
		time.setNomeTime(timeDto.getNomeTime());
		timeRepository.save(time);
		return timeDto;
	}

	public Time buscarPorId(long id) {
		Time time = timeRepository.findById(id).isEmpty() ? null : timeRepository.findById(id).get();
		if (time != null) {
			return time;
		}
		return null;
	}

	public List<Time> listarTimes() {

		return timeRepository.findAll();

	}

}

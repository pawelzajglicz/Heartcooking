package com.heartcooking.tracesallergen;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class TracesAllergenServiceImpl implements TracesAllergenService {

	TracesAllergenRepository tracesAllergenRepository;

	@Override
	public List<TracesAllergen> getTracesAllergens() {

		return tracesAllergenRepository.findAll();
	}
}

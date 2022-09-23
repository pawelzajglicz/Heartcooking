package com.heartcooking.tracesallergen;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/traces-allergens")
public class TracesAllergenController {

	private TracesAllergenService tracesAllergenService;

	@GetMapping(path = {"", "/"})
	public ResponseEntity<List<TracesAllergen>> getTracesAllergens() {

		return ResponseEntity.ok(tracesAllergenService.getTracesAllergens());
	}
}

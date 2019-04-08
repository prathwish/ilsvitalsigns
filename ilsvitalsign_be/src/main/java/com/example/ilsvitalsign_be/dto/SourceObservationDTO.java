package com.example.ilsvitalsign_be.dto;

import java.io.Serializable;
import java.util.List;

public class SourceObservationDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sourceName;
	List<ObservationResponseDTO> observationResponseDTO;

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public List<ObservationResponseDTO> getObservationResponseDTO() {
		return observationResponseDTO;
	}

	public void setObservationResponseDTO(List<ObservationResponseDTO> observationResponseDTO) {
		this.observationResponseDTO = observationResponseDTO;
	}

}

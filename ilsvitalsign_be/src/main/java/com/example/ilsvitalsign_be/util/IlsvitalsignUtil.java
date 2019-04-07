package com.example.ilsvitalsign_be.util;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class IlsvitalsignUtil {

	/**
	 * @name : getCurrentTimestamp
	 *
	 *
	 * @returns : Timestamp
	 *
	 * @throws :
	 *
	 * @description : This method will return the current Timestamp to the calling
	 *              method.
	 *
	 */
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

}

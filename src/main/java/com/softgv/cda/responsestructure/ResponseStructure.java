package com.softgv.cda.responsestructure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ResponseStructure<T> {
	private int status;
	private String message;
	private T body;
}

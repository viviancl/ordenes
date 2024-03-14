package com.altioracorp.ordenes.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.altioracorp.ordenes.dto.ClienteDto;
import com.altioracorp.ordenes.model.Cliente;

@Configuration
public class MapperConfig {

	@Bean
	ModelMapper modelMapper() {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.typeMap(ClienteDto.class, Cliente.class);
		modelMapper.typeMap(Cliente.class, ClienteDto.class);				
		return modelMapper;

	}
}

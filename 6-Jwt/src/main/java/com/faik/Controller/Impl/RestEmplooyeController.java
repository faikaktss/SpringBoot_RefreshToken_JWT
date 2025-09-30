package com.faik.Controller.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faik.Controller.IRestEmplooyeController;
import com.faik.Dto.DtoEmplooye;
import com.faik.Service.IEmplooyeService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;


@RestController
@RequestMapping("/employee")
public class RestEmplooyeController implements IRestEmplooyeController{
	
	@Autowired
	private IEmplooyeService emplooyeService;
	
	@GetMapping("/{id}")
	@Override
	public DtoEmplooye findEmplooyeById(@PathVariable(value = "id") Long id) { // @Valid = Notempty anatasyonunun aktif hale gelebilmesi için kullanılır
		return emplooyeService.findDtoEmplooye(id);
	}

}

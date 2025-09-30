package com.faik.Service.İmpl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.faik.Dto.DtoDepartment;
import com.faik.Dto.DtoEmplooye;
import com.faik.Model.Department;
import com.faik.Model.Employee;
import com.faik.Repository.EmplooyeRepository;
import com.faik.Service.IEmplooyeService;


@Service
public class EmplooyeServiceImpl implements IEmplooyeService{


	@Autowired
	private EmplooyeRepository emplooyeRepository;

	
	@Override
	public DtoEmplooye findDtoEmplooye(Long id) {
		DtoEmplooye dtoEmplooye = new DtoEmplooye();
		DtoDepartment dtoDepartment = new DtoDepartment();
		
		Optional<Employee> empOptional =  emplooyeRepository.findById(id); // emp optional olarak buraya döner
		
		if(empOptional.isEmpty()) {
			return null;
		}
		Employee employee = empOptional.get(); 
		Department department = employee.getDepartment();
		
		BeanUtils.copyProperties(employee, dtoEmplooye); // Dto olarak dönüyoruz burada
		BeanUtils.copyProperties(department, dtoDepartment);
		
		dtoEmplooye.setDepartment(dtoDepartment);
		
		return dtoEmplooye;
	}

}

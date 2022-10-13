package com.mmit.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mmit.model.entities.Employee;
import com.mmit.model.services.EmployeeService;

@Controller
//@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService service;
	
	@GetMapping("/employees")
	public String goHome(Model m) {
		m.addAttribute("employees",service.findAll());
		return "index";
		
	}
	
	@GetMapping("/employees/add")
	public String goAdd(Model model) {
		model.addAttribute("employee_obj",new Employee());
		model.addAttribute("cities",new String[] {"Yangon","Mandalay","Pyin OO lwin"});
		return "emp-add";
	}
	@PostMapping("/employees/save")
	public String saveEmployee(@Valid @ModelAttribute("employee_obj") Employee emp,BindingResult result) {
		if(emp.getName() != null && emp.getName().equals("virus")) {
			ObjectError error = new ObjectError("global", "It Does Not allow");
			result.addError(error);
			
		}
		
		if(result.hasErrors()) {
			return "emp-add";
			
		}
		service.save(emp);
		return "redirect:/employees";
		
	}
	@GetMapping("/employees/{empId}")
	public String goEditPage(Model m, @PathVariable("empId") int id) {
		var employee = service.findById(id);
		m.addAttribute("employee_obj",employee);
		m.addAttribute("cities",new String[] {"Yangon","Mandalay","Pyin OO lwin"});
		
		return "emp-add";
	}
	@GetMapping("/employees/delete/{id}")
	public String deletEmployee(@PathVariable("id") int empId) {
		service.deleteById(empId);
		return "redirect:/employees";
	}
}

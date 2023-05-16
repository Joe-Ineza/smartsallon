package com.SSWebApp.SmartSallonWebApp.controller;

import com.SSWebApp.SmartSallonWebApp.dto.CustomerDTO;
import com.SSWebApp.SmartSallonWebApp.dto.RegistrationDTO;
import com.SSWebApp.SmartSallonWebApp.dto.StaffDTO;
import com.SSWebApp.SmartSallonWebApp.dto.UsersDTO;
import com.SSWebApp.SmartSallonWebApp.mapper.CustomerMapper;
import com.SSWebApp.SmartSallonWebApp.mapper.StaffMapper;
import com.SSWebApp.SmartSallonWebApp.mapper.UsersMapper;
import com.SSWebApp.SmartSallonWebApp.repository.CustomerRepository;
import com.SSWebApp.SmartSallonWebApp.repository.StaffRepository;
import com.SSWebApp.SmartSallonWebApp.repository.UsersRepository;
import com.SSWebApp.SmartSallonWebApp.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.*;
import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {
    private final UsersService usersService;

    private final StaffRepository staffRepository;
    private  final StaffMapper staffMapper;

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    private  final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    public HomeController(UsersService usersService, StaffRepository staffRepository, StaffMapper staffMapper, CustomerRepository customerRepository, CustomerMapper customerMapper, UsersRepository usersRepository, UsersMapper usersMapper) {
        this.usersService = usersService;
        this.staffRepository = staffRepository;
        this.staffMapper = staffMapper;
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
    }

    @RequestMapping("/home")
    public String goHome() {
        return "index";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/logout-success")
    public String logoutPage() {
        return "logout";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "accessDenied";
    }

    @GetMapping("customerLandPage")
    public String customerPage()
    {
        return "customerLandPage";
    }

    @GetMapping("/register")
    public String showNewCustomerForm(Model model) {
//        CustomerDTO customerdto = new CustomerDTO();
//        StaffDTO staffdto = new StaffDTO();
//        UsersDTO userssdto = new UsersDTO();
//        modelCust.addAttribute("customer",customerdto);
//        modelStaff.addAttribute("staff",staffdto);
//        modelUsers.addAttribute("users",userssdto);
//        modelCust.addAttribute()
//        model.addAttribute("customer", customer);
        RegistrationDTO registrationDTO = new RegistrationDTO();
        model.addAttribute("registration",registrationDTO);

        return "register";
    }




    @PostMapping("/register")
    public String createCustomer(@ModelAttribute("registration") @Valid RegistrationDTO registrationDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }


        UsersDTO usersDTO = new UsersDTO();
        List<UsersDTO> usersList = usersService.getAllUsersByEmail(registrationDTO.getEmail());
        if(registrationDTO.getEmail().contains("acmemply.7"))
        {

            if(usersList.isEmpty()) {
                StaffDTO staffDTO = new StaffDTO();
                staffDTO.setName(registrationDTO.getName());
                staffDTO.setEmail(registrationDTO.getEmail());
                usersDTO.setUserName(registrationDTO.getEmail());
                usersDTO.setPassword(registrationDTO.getPassword());
                staffDTO.setPhoneNumber(registrationDTO.getPhoneNumber());
                staffRepository.save(staffMapper.toEntity(staffDTO));
                usersRepository.save(usersMapper.toEntity(usersDTO));
            }
            else
            {

                return "redirect:/register";

            }
        }
        else if(usersList.isEmpty()) {


            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setName(registrationDTO.getName());
            customerDTO.setEmail(registrationDTO.getEmail());
            customerDTO.setPhoneNumber(registrationDTO.getPhoneNumber());
            customerRepository.save(customerMapper.toEntity(customerDTO));
            usersDTO.setUserName(registrationDTO.getEmail());
            usersDTO.setPassword(registrationDTO.getPassword());
            usersRepository.save(usersMapper.toEntity(usersDTO));
        }
        else
        {
                return "redirect:/register";
        }

        if(registrationDTO.getEmail().contains("acmemply.7"))
        {
            return "redirect:/appointments";
        }
        else if(registrationDTO.getEmail().contains("joe.7")){
            return "redirect:/customerLandPage";
        }
        else
        {
            return "redirect:/login";
        }

    }



//    @GetMapping("/confirm-login")
//    public String showConfirmLoginPage() {
//        return "confirm-login";
//    }
}
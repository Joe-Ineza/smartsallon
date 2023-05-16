package com.SSWebApp.SmartSallonWebApp.mapper;

import com.SSWebApp.SmartSallonWebApp.dto.AppointmentDTO;
import com.SSWebApp.SmartSallonWebApp.dto.CustomerDTO;
import com.SSWebApp.SmartSallonWebApp.dto.StaffDTO;
import com.SSWebApp.SmartSallonWebApp.models.Appointment;
import com.SSWebApp.SmartSallonWebApp.models.Customer;
import com.SSWebApp.SmartSallonWebApp.models.Staff;
import com.SSWebApp.SmartSallonWebApp.dto.CustomerDTO;

import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    public static AppointmentDTO toDto(Appointment appointment) {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(appointment.getId());
        dto.setStartDateTime(appointment.getDateTime());
        dto.setEndDateTime(appointment.getDateTime().plusHours(1));
        dto.setCustomer(appointment.getCustomer());
        dto.setStaff(appointment.getStaff());
        dto.setSalonServ(appointment.getSalonServ());
        return dto;
    }

    public static Appointment toEntity(AppointmentDTO dto) {
        Appointment appointment = new Appointment();
        appointment.setId(dto.getId());
        appointment.setDateTime(dto.getStartDateTime());
        appointment.setCustomer(dto.getCustomer());
        appointment.setStaff(dto.getStaff());
        appointment.setSalonServ(dto.getSalonServ());
        return appointment;
    }



    public static void updateModelFromDto(AppointmentDTO dto, Appointment appointment) {
        if (dto.getStartDateTime() != null) {
            appointment.setDateTime(dto.getStartDateTime());
        }
        if (dto.getCustomer() != null && dto.getCustomer().getId() != null) {
            appointment.setCustomer(dto.getCustomer());
        }
        if (dto.getStaff() != null && dto.getStaff().getId() != null) {
            appointment.setStaff(dto.getStaff());
        }
        if (dto.getSalonServ() != null && dto.getSalonServ().getId() != null) {
            appointment.setSalonServ(dto.getSalonServ());
        }

    }
}

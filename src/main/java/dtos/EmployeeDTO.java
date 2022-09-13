package dtos;

import entities.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDTO {

    private Integer id;
    private String name;
    private String address;


    public EmployeeDTO(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public EmployeeDTO(Employee employee) {
        if(employee.getId()!=null)
            this.id = employee.getId();
        this.name=employee.getName();
        this.address=employee.getAddress();
    }

    public static List<EmployeeDTO> getDtos(List<Employee> employeeList) {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        employeeList.forEach(employee -> employeeDTOS.add(new EmployeeDTO(employee)));
        return employeeDTOS;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

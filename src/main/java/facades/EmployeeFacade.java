package facades;

import dtos.EmployeeDTO;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeFacade {
    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;

    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf){
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Employee getEmployeeById(int id) {
        EntityManager em = emf.createEntityManager();
        Employee employee = em.find(Employee.class, id);
        return new Employee(employee.getName(),employee.getAddress(),employee.getSalary());
    }

    public Employee getEmployeeByName(String name) {
        EntityManager em = emf.createEntityManager();
        Employee employee = em.find(Employee.class,name);
        return new Employee(name, employee.getAddress(), employee.getSalary());
    }

    public List<Employee> getAllEmployees() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        List<Employee> employees = query.getResultList();
        return employees;
    }

    public Employee createEmployee(String name, String address, int salary) {
        Employee newEmployee = new Employee(name,address, salary);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(newEmployee);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return newEmployee;
    }

    public EmployeeDTO getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        Employee employee = em.find(Employee.class, id);
        return new EmployeeDTO(employee);
    }

    public List<EmployeeDTO> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        List<Employee> employeeList = query.getResultList();
        return EmployeeDTO.getDtos(employeeList);
    }

    public EmployeeDTO getEmployeeWithHighestSalary(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e ORDER BY e.salary DESC", Employee.class);
        return  new EmployeeDTO(query.setMaxResults(1).getSingleResult());

    }
}

package jp.co.axa.apidemo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name is mandatory")
    @Column(name="EMPLOYEE_NAME")
    private String name;
    
    @NotNull(message = "salary is mandatory")
    @Min(value = 1, message = "salary should not be less than 1")
    @Column(name="EMPLOYEE_SALARY")
    private Integer salary;

    @NotBlank(message = "department is mandatory")
    @Column(name="DEPARTMENT")
    private String department;

}

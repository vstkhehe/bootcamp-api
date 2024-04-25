package br.com.frwk.badge.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_employee_badge")
@Data
public class EmployeeBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employeeId;
    @ManyToOne
    @JoinColumn(name="badge_id")
    private Badge badge;
    private Boolean active;
}

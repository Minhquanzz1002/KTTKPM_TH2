package vn.edu.iuh.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "examines")
@IdClass(ExaminePK.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Examine {
    @Id
    @ManyToOne
    private Doctor doctor;

    @Id
    @ManyToOne
    private Patient patient;
    private Date date;
    private String note;
}

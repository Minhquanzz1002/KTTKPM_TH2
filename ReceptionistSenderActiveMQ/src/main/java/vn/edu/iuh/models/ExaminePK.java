package vn.edu.iuh.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class ExaminePK implements Serializable {
    private long doctor;
    private long patient;
}

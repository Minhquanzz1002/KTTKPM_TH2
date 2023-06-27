package vn.edu.iuh.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExaminePK implements Serializable {
    private long doctor;
    private long patient;
}

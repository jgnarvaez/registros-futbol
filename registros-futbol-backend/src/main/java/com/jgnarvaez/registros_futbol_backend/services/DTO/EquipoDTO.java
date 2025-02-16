package com.jgnarvaez.registros_futbol_backend.services.DTO;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import com.jgnarvaez.registros_futbol_backend.models.FutbolistaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipoDTO {
    
    @NotBlank(message = "El código del equipo no puede estar en blanco")
    @Size(min = 3, max = 3, message = "El código del equipo debe tener 3 caracteres")
    private String codigoEquipo;

    @NotBlank(message = "El nombre del equipo no puede estar en blanco")
    @Size(min = 5, max = 50, message = "El nombre del equipo debe tener entre 5 y 50 caracteres")
    private String nombre;
    
    @NotBlank(message = "El país del equipo no puede estar en blanco")
    @Size(min = 5, max = 50, message = "El país del equipo debe tener entre 5 y 45 caracteres")
    private String pais;

    @NotBlank(message = "La categoría del equipo no puede estar en blanco")
    @Pattern(regexp = "A|B|C", message = "La categoría del equipo debe ser A, B o C")
    private String categoria;

    @NotNull(message = "El año de fundación del equipo no puede ser nulo")
    @Positive(message = "El año de fundación del equipo debe ser un número positivo")
    private Integer anioFundacion;

    @NotNull(message = "El presupuesto del equipo no puede ser nulo")
    @Positive(message = "El presupuesto del equipo debe ser un número positivo")
    private Double presupuesto;

    @Size(max = 32, message = "Un equipo no puede tener más de 32 futbolistas")
    private List<FutbolistaEntity> futbolistas;
}

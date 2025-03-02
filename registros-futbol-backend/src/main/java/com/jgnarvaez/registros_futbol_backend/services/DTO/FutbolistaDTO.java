package com.jgnarvaez.registros_futbol_backend.services.DTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FutbolistaDTO {
    
    @NotNull(message = "El ID del futbolista no puede ser nulo")
    @Positive(message = "El ID del futbolista debe ser un número positivo")
    private Integer id;

    @NotBlank(message = "El nombre del futbolista no puede estar en blanco")
    @Size(min = 5, max = 50, message = "El nombre del futbolista debe tener entre 5 y 45 caracteres")
    private String nombre;

    @NotBlank(message = "El código del equipo no puede estar en blanco")
    @Size(min = 3, max = 3, message = "El código del equipo debe tener 3 caracteres")
    private String codigoEquipo;

    @NotNull(message = "La edad del futbolista no puede ser nula")
    @Positive(message = "La edad del futbolista debe ser un número positivo")
    private Integer edad;

    @NotNull(message = "Los goles anotados por temporada no pueden ser nulos")
    @Positive(message = "Los goles anotados por temporada deben ser un número positivo")
    private Integer golesAnotadosPorTemporada;

    @NotBlank(message = "La nacionalidad del futbolista no puede estar en blanco")
    @Size(min = 5, max = 50, message = "La nacionalidad del futbolista debe tener entre 5 y 45 caracteres")
    private String nacionalidad;

    @NotBlank(message = "La posición del futbolista no puede estar en blanco")
    @Pattern(regexp = "ARQUERO|DEFENSA|MEDIO|DELANTERO", message = "La posición del futbolista debe ser ARQUERO, DEFENSA, MEDIO o DELANTERO")
    private String posicion;

    @NotNull(message = "El estado de lesiones del futbolista no puede ser nulo")
    private Boolean lesiones;
}

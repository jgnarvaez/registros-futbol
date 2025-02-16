package com.jgnarvaez.registros_futbol_backend.exceptionsControllers.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CodigoError {

    ERROR_GENERICO("RF-0001", "Error genérico"),
    ENTIDAD_YA_EXISTE("GC-0002", "ERROR ENTIDAD YA EXISTE"),
    ENTIDAD_NO_ENCONTRADA("GC-0003", "Entidad no encontrada"),
    VIOLACION_REGLA_DE_NEGOCIO("GC-0004", "Regla de negocio violada");

    private final String codigo;
    private final String llaveMensaje;
}
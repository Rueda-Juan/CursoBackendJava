# Arquitectura del Cliente (CLI)

## Stack Tecnológico
- **Interfaz**: Consola (System.out)
- **Entrada**: `InputScanner` (wrapper sobre java.util.Scanner para manejo de errores).

## Estructura de Componentes
- **Main**: Orquestador del flujo y menús.
- **InputScanner**: Sanitización y validación de tipos de entrada.
- **CONST**: Definición de estilos ANSI (colores) para una experiencia visual premium.

## Gestión de Estado
- `usuarioLogueado`: Mantiene la sesión actual.
- `tienda`: Referencia única al estado del sistema.

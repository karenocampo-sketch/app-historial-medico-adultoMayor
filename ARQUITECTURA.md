# 🏗️ Arquitectura del Proyecto - HistorialMedicoApp

## 📁 Estructura de Carpetas Organizada

```
app/src/main/java/com/example/historialmedico/
├── MainActivity.java                    # ⭐ Actividad principal (refactorizada)
├── MainActivity_backup.java            # 🔄 Respaldo de la versión original
├── models/                             # 📋 Modelos de datos
│   ├── Paciente.java                   # 👤 Datos del paciente
│   ├── HistorialMedico.java            # 🏥 Historial médico
│   └── Medicamento.java                # 💊 Modelo de medicamento
├── services/                           # ⚙️ Servicios de negocio
│   ├── MedicamentosService.java        # 💊 Gestión de medicamentos
│   └── EmergenciaService.java          # 🚨 Sistema de emergencias
├── utils/                              # 🛠️ Utilidades
│   └── FormateoUtils.java              # 📄 Formateo de datos
└── activities/                         # 📱 Actividades (futuras)
    └── MainActivity.java               # 📱 Versión en carpeta activities
```

---

## 🎯 **Clases y sus Responsabilidades**

### **📋 MODELOS (models/)**

#### **1. Paciente.java**
```java
📊 Atributos:
• nombre (String)
• edad (int)
• direccion (String)  
• tipoSangre (String)
• estatura (double)
• peso (double)
• contactoEmergencia (String)
• telefonoEmergencia (String)

🔧 Métodos principales:
• getInfoBasica() - Información formateada
• calcularIMC() - Cálculo del IMC
• Getters/Setters completos
```

#### **2. HistorialMedico.java**
```java
📊 Atributos:
• condicionesActuales (List<String>)
• cirugiasAnteriores (List<String>)
• alergias (List<String>)
• antecedentesRelevantes (List<String>)

🔧 Métodos principales:
• getHistorialFormateado() - Historial para mostrar
• agregarCondicion/Cirugia/Alergia()
• tieneAlergia/Condicion() - Validaciones
```

#### **3. Medicamento.java**
```java
📊 Atributos:
• nombre (String)
• dosis (String)
• frecuencia (String)
• horario (String)
• indicaciones (String)
• esActivo (boolean)

🔧 Métodos principales:
• getInfoFormateada() - Info para mostrar
• activar/desactivar() - Control de estado
```

### **⚙️ SERVICIOS (services/)**

#### **4. MedicamentosService.java**
```java
📊 Funcionalidades:
• Gestionar lista de medicamentos activos
• Administrar alergias a medicamentos
• Búsqueda y filtrado de medicamentos
• Formateo para visualización

🔧 Métodos principales:
• getMedicamentosActivos()
• agregarMedicamento/Alergia()
• tieneAlergia() - Verificaciones de seguridad
• getMedicamentosFormateados()
```

#### **5. EmergenciaService.java**
```java
📊 Funcionalidades:
• Sistema inteligente de detección de emergencias
• Control de clicks múltiples con timeout
• Gestión de contactos de emergencia
• Simulación de llamadas

🔧 Métodos principales:
• manejarEmergencia() - Lógica principal
• contactarFamiliar/llamar911() - Acciones
• reiniciarContador() - Control de estado
• getInstruccionesEmergencia() - Ayuda
```

### **🛠️ UTILIDADES (utils/)**

#### **6. FormateoUtils.java**
```java
📊 Funcionalidades:
• Formateo de fechas y horas
• Formateo de datos médicos (peso, estatura, IMC)
• Formateo de listas y textos
• Validaciones de datos
• Categorización de IMC

🔧 Métodos principales:
• formatearFecha/Hora/Peso/Estatura()
• formatearIMC/getCategoriaIMC()
• formatearListaConVinetas()
• capitalizarPalabras()
• esTextoValido()
```

### **📱 ACTIVIDADES**

#### **7. MainActivity.java (Refactorizada)**
```java
📊 Responsabilidades:
• Coordinación de la UI
• Inicialización de modelos y servicios
• Manejo de eventos de usuario
• Separación de responsabilidades

🔧 Estructura:
• inicializarModelos() - Setup de objetos
• inicializarVistas() - Referencias UI  
• configurarListeners() - Event handlers
• mostrarHistorial/Medicamentos() - Display
• manejarEmergencia() - Emergency logic
```

---

## 🎨 **Beneficios de la Nueva Arquitectura**

### **✅ PRINCIPIOS APLICADOS**
- **🎯 Single Responsibility**: Cada clase tiene una responsabilidad específica
- **📦 Encapsulación**: Datos privados con métodos públicos controlados  
- **🔄 Reutilización**: Servicios y utilidades reutilizables
- **🧪 Testabilidad**: Clases independientes fáciles de testear
- **📈 Escalabilidad**: Fácil agregar nuevas funcionalidades

### **🚀 MEJORAS IMPLEMENTADAS**
- **📁 Organización**: Carpetas semánticas por responsabilidad
- **🧹 Separación**: UI separada de lógica de negocio
- **💾 Mantenibilidad**: Código más fácil de modificar y mantener
- **📚 Documentación**: Cada clase bien documentada
- **🔧 Flexibilidad**: Fácil modificar comportamientos específicos

---

## 🛠️ **Uso de las Nuevas Clases**

### **Ejemplo de uso en MainActivity:**
```java
// Inicialización
private Paciente paciente = new Paciente();
private MedicamentosService medicamentos = new MedicamentosService();
private EmergenciaService emergencia = new EmergenciaService();

// Mostrar información
String info = paciente.getInfoBasica();
String meds = medicamentos.getMedicamentosFormateados();  
String emergency = emergencia.manejarEmergencia(this);
```

### **Ventajas para el mantenimiento:**
- **🔍 Fácil localización**: Cada funcionalidad en su lugar específico
- **➕ Extensibilidad**: Agregar nuevos medicamentos, condiciones, etc.
- **🧪 Testing**: Probar cada servicio de manera independiente
- **🔄 Modificación**: Cambiar lógica sin afectar otras partes

---

## 🎯 **Próximos Pasos Sugeridos**

### **📈 Mejoras Futuras**
1. **💾 Persistencia**: Agregar SQLite para guardar datos
2. **📱 Más Pantallas**: Activities para editar información
3. **📅 Recordatorios**: Service para notificaciones de medicamentos  
4. **🔗 Integración**: ContactsContract para contactos reales
5. **🧪 Testing**: Unit tests para cada servicio
6. **🎨 UI**: Fragments para mejor organización de vistas

### **🏗️ Estructura Expandida (Futura)**
```
app/src/main/java/com/example/historialmedico/
├── models/          # Modelos existentes + nuevos
├── services/        # Servicios existentes + nuevos  
├── utils/          # Utilidades existentes + nuevas
├── activities/     # Múltiples activities
├── fragments/      # UI fragments
├── adapters/       # RecyclerView adapters
├── database/       # SQLite helpers
└── notifications/  # Notification managers
```

---

*📝 **Nota**: Esta refactorización mantiene toda la funcionalidad original mientras mejora significativamente la organización y mantenibilidad del código.*

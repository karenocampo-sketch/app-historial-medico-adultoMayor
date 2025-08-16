# 🩺 Historial Médico - Adulto Mayor

Una aplicación Android especializada en el cuidado de adultos mayores, diseñada para proporcionar acceso rápido a información médica crítica y funcionalidades de emergencia.

## 📱 Características

### 🏥 **Gestión de Información Médica**
- **Información del Paciente**: Datos básicos como nombre, edad, dirección, tipo de sangre
- **Historial Médico**: Registro de condiciones médicas y cirugías previas
- **Medicamentos**: Lista de medicamentos actuales y alergias conocidas

### 🚨 **Sistema de Emergencia Inteligente**
- **2 toques rápidos**: Contacta automáticamente al familiar de emergencia
- **3+ toques rápidos**: Inicia llamada al 911
- **Timeout inteligente**: Reinicia el contador después de 5 segundos de inactividad

### 🎨 **Diseño Accesible**
- **Textos grandes**: Optimizado para adultos mayores
- **Alto contraste**: Colores que facilitan la lectura
- **Iconos intuitivos**: Emojis para reconocimiento visual rápido
- **Interfaz simple**: Navegación fácil y clara

## 🛠️ Tecnologías Utilizadas

- **Lenguaje**: Java
- **Framework**: Android SDK
- **Build System**: Gradle
- **UI**: XML Layouts
- **Compatibilidad**: Android API 21+

## 🏗️ Estructura del Proyecto

```
app/
├── src/main/
│   ├── java/com/example/historialmedico/
│   │   └── MainActivity.java          # Lógica principal de la app
│   ├── res/
│   │   ├── layout/
│   │   │   └── activity_main.xml      # Interfaz de usuario
│   │   ├── values/
│   │   │   ├── strings.xml            # Textos de la app
│   │   │   ├── colors.xml             # Colores personalizados
│   │   │   └── themes.xml             # Temas visuales
│   │   └── AndroidManifest.xml        # Configuración de la app
├── build.gradle                       # Configuración de compilación
└── proguard-rules.pro                # Reglas de ofuscación
```

## 🚀 Instalación y Uso

### Prerrequisitos
- Android Studio 4.0+
- Android SDK API 21+
- Emulador Android o dispositivo físico

### Pasos para ejecutar
1. Clona este repositorio
```bash
git clone https://github.com/karenocampo-sketch/app-historial-medico-adultoMayor.git
```

2. Abre el proyecto en Android Studio

3. Sincroniza las dependencias de Gradle

4. Ejecuta la aplicación:
```bash
./gradlew installDebug
```

## 📖 Cómo Usar la App

### Pantalla Principal
- **Información del Paciente**: Muestra datos básicos del usuario
- **Ver Historial Médico**: Accede al historial de condiciones médicas
- **Ver Medicamentos**: Lista medicamentos y alergias
- **Botón de Emergencia**: Sistema inteligente de llamadas de emergencia

### Sistema de Emergencia
1. **Para contacto familiar**: Toca 2 veces rápidamente el botón rojo
2. **Para llamar al 911**: Toca 3 o más veces rápidamente
3. **Reiniciar**: Espera 5 segundos para resetear el contador

## 👥 Equipo de Desarrollo

- **Caren Romero** - Desarrollo Frontend
- **Karen Ocampo** - Desarrollo Backend  
- **Diego Castro** - Diseño UX/UI

## 📝 Funcionalidades Futuras

- [ ] Integración con contactos del teléfono
- [ ] Recordatorios de medicamentos
- [ ] Historial de emergencias
- [ ] Múltiples perfiles de usuario
- [ ] Sincronización con servicios médicos

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Para cambios importantes:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

---

*Desarrollado con ❤️ para mejorar el cuidado de nuestros adultos mayores*

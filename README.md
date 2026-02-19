# 📘 Relatos de Papel – Proyecto QA Automation

## 📌 Descripción General

Este proyecto contiene la automatización de pruebas para el sistema **Relatos de Papel**, cubriendo distintos niveles de validación:

- ✅ Pruebas de Aceptación (Cypress)
- ✅ Pruebas de API (RestAssured + JUnit 5)
- ✅ Pruebas de Interacción UI (Selenium + Cucumber)
- ✅ Pruebas de Carga (JMeter)

El objetivo es validar el correcto funcionamiento del sistema desde diferentes enfoques: frontend, backend, integración y rendimiento.

---

# 🏗 Estructura del Proyecto

relatos-de-papel-qa
│
├── pruebas-aceptacion → Cypress (Pruebas E2E)
├── pruebas-api → RestAssured + JUnit 5
├── pruebas-interaccion → Selenium + Cucumber
├── pruebas-carga → JMeter
│
├── cypress.config.js
├── package.json
└── README.md


---

# ⚙️ Requisitos Previos

Antes de ejecutar las pruebas asegúrate de tener instalado:

- ✅ Java 17 o superior
- ✅ Maven 3.9+
- ✅ Node.js 18+ o superior
- ✅ Google Chrome actualizado
- ✅ Apache JMeter (para pruebas de carga)

Además:

- 🚀 Backend ejecutándose en http://localhost:8081
- 🌐 Frontend ejecutándose en http://localhost:5173

---

# 🚀 1️⃣ Pruebas de Aceptación – Cypress

## 📁 Ubicación
pruebas-aceptacion/

## ▶ Instalación (si es primera vez)
Desde la raíz del proyecto:
```bash
npm install
```
## ▶ Ejecutar en modo headless (CI / entrega académica)
```bash
npx cypress run --browser chrome
```
## ▶ Ejecutar en modo interactivo (debug visual)
```bash
npx cypress open
```

`📂 Evidencias generadas
🎥 Videos:
pruebas-aceptacion/cypress/videos/

📸 Screenshots (en caso de fallo):
pruebas-aceptacion/cypress/screenshots/

---

#🔌 2️⃣ Pruebas API – RestAssured + JUnit 5
## 📁 Ubicación
pruebas-api/api-tests

##▶ Ejecutar pruebas
cd pruebas-api/api-tests
mvn clean test

##📊 Resultado esperado
Tests run: 5
Failures: 0
Errors: 0
BUILD SUCCESS

##📂 Evidencias automáticas
Se generan archivos de evidencia en:
target/evidencias/

Cada prueba genera un archivo .txt con:
- Request enviado
- Response recibida
- Status code
- Body completo

---

# 🖥 3️⃣ Pruebas UI – Selenium + Cucumber
##📁 Ubicación
pruebas-interaccion/selenium-tests

##▶ Ejecutar pruebas
cd pruebas-interaccion/selenium-tests
mvn test

##📊 Resultado esperado
Tests run: X
Failures: 0
Errors: 0
BUILD SUCCESS

##🧪 Escenarios automatizados
✔ Compra exitosa
✔ Compra sin datos (validación de errores)
✔ Flujo completo de checkout

---

#🔥 4️⃣ Pruebas de Carga – JMeter
##📁 Ubicación
pruebas-carga/

##▶ Ejecutar
1. Abrir Apache JMeter
2. Cargar archivo .jmx
3. Configurar número de usuarios
4. Ejecutar prueba

##📊 Métricas recomendadas
Throughput
Tiempo promedio de respuesta
Percentil 90 / 95
Error rate




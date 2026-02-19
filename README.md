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

## ▶ Ejecutar en modo headless (CI / entrega académica)
npx cypress run --browser chrome

## ▶ Ejecutar en modo interactivo (debug visual)
npx cypress open

const { defineConfig } = require("cypress");

module.exports = defineConfig({

  video: true,
  screenshotOnRunFailure: true,

  defaultCommandTimeout: 8000,
  pageLoadTimeout: 60000,

  viewportWidth: 1280,
  viewportHeight: 720,

  e2e: {
    baseUrl: "http://localhost:5173",


    specPattern: "pruebas-aceptacion/cypress/e2e/**/*.cy.{js,jsx,ts,tsx}",
    supportFile: "pruebas-aceptacion/cypress/support/e2e.js",
    fixturesFolder: "pruebas-aceptacion/cypress/fixtures",
    screenshotsFolder: "pruebas-aceptacion/cypress/screenshots",
    videosFolder: "pruebas-aceptacion/cypress/videos",

    watchForFileChanges: false,
    numTestsKeptInMemory: 0,

    setupNodeEvents(on, config) {
      return config;
    },
  },


  allowCypressEnv: false,

});
// dashboard-main.js
document.addEventListener('DOMContentLoaded', function() {
    // Inicializar variables globales
    let currentView = 'view-dashboard';

    // Importar e inicializar módulos
    importModules();

    // Inicializar vista por defecto
    switchView('view-dashboard');
});

function importModules() {
    // Estos módulos se cargan después del DOM
    if (typeof initializeViewSystem === 'function') initializeViewSystem();
    if (typeof initializeCharts === 'function') initializeCharts();
    if (typeof initializeSidebar === 'function') initializeSidebar();
    if (typeof initializeForms === 'function') initializeForms();
    if (typeof initializeTableActions === 'function') initializeTableActions();
}
// view-system.js
function initializeViewSystem() {
    // Elementos del DOM para títulos
    const pageTitle = document.getElementById('pageTitle');
    const pageSubtitle = document.getElementById('pageSubtitle');

    // Título de cada vista
    const viewTitles = {
        'view-dashboard': ['Dashboard', 'Resumen general de la carpintería'],
        'view-lineas': ['Líneas de Diseño', 'Administra las líneas de diseño'],
        'view-categorias': ['Categorías', 'Administra las categorías de productos'],
        'view-productos': ['Productos', 'Administra tu catálogo de productos'],
        'view-empleados': ['Empleados', 'Gestión de Empleados'],
        'view-pedidos': ['Pedidos', 'Seguimiento de pedidos'],
        'view-usuarios': ['Usuarios', 'Administración de usuarios del sistema'],
        'view-configuracion': ['Configuración', 'Configuración del sistema']
    };

    // Variable global para controlar vista actual
    window.currentView = 'view-dashboard';

    // Función para cambiar de vista (disponible globalmente)
    window.switchView = function(viewId) {
        // Ocultar vista actual
        const currentElement = document.getElementById(window.currentView);
        if (currentElement) {
            currentElement.classList.remove('active');
        }

        // Mostrar nueva vista
        const newElement = document.getElementById(viewId);
        if (newElement) {
            newElement.classList.add('active');
            window.currentView = viewId;

            // Actualizar títulos en el topbar
            if (viewTitles[viewId]) {
                if (pageTitle) pageTitle.textContent = viewTitles[viewId][0];
                if (pageSubtitle) pageSubtitle.textContent = viewTitles[viewId][1];
            }

            // Reiniciar AOS para nuevas animaciones
            if (window.AOS) {
                AOS.refresh();
            }

            // Si es dashboard, inicializar gráficos
            if (viewId === 'view-dashboard' && typeof initializeCharts === 'function') {
                setTimeout(initializeCharts, 100);
            }
        }
    };

    // Configurar enlaces del sidebar
    document.querySelectorAll('[data-view]').forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            const viewId = this.getAttribute('data-view');

            // Cambiar vista
            switchView(viewId);

            // Cerrar sidebar en móvil
            if (window.innerWidth < 992) {
                const sidebar = document.getElementById('sidebar');
                if (sidebar) {
                    sidebar.classList.remove('open');
                }
            }

            // Actualizar clase activa en sidebar
            document.querySelectorAll('[data-view]').forEach(item => {
                item.classList.remove('active');
            });
            this.classList.add('active');
        });
    });

    console.log('Sistema de vistas inicializado');
}
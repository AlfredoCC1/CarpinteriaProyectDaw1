// dashboard-charts-manager.js
class DashboardChartsManager {
    constructor() {
        this.charts = {};
        this.isInitialized = false;
    }

    // Inicializar todos los gráficos
    initialize() {
        if (typeof Chart === 'undefined') {
            console.error('Chart.js no está cargado. Por favor, incluye Chart.js en tu proyecto.');
            return;
        }

        this.destroyAll();
        this.setupContainers();
        this.createCharts();
        this.setupEventListeners();

        this.isInitialized = true;
        console.log('Dashboard Charts Manager inicializado correctamente');
    }

    // Configurar contenedores de gráficos
    setupContainers() {
        const containers = [
            { id: 'ordersChart', height: '280px' },
            { id: 'productsChart', height: '320px' }
        ];

        containers.forEach(({ id, height }) => {
            const canvas = document.getElementById(id);
            if (!canvas) return;

            // Obtener el contenedor padre
            let container = canvas.parentElement;

            // Buscar el contenedor de la card
            while (container && !container.classList.contains('card-body')) {
                container = container.parentElement;
            }

            if (container) {
                // Establecer dimensiones mínimas
                container.style.minHeight = height;
                container.style.position = 'relative';
            }

            // Configurar el canvas
            canvas.style.width = '100%';
            canvas.style.height = '100%';
            canvas.style.display = 'block';
        });
    }

    // Crear gráfico de pedidos
    createOrdersChart() {
        const ctx = document.getElementById('ordersChart');
        if (!ctx) return null;

        // Datos de ejemplo (deberías reemplazar esto con datos reales)
        const data = {
            labels: ['Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov'],
            datasets: [{
                label: 'Pedidos',
                data: [14, 18, 21, 19, 24, 27],
                borderRadius: 6,
                backgroundColor: 'rgba(139, 90, 43, 0.8)',
                borderColor: 'rgba(139, 90, 43, 1)',
                borderWidth: 1,
                barPercentage: 0.7,
                categoryPercentage: 0.8
            }]
        };

        const options = {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: { display: false },
                tooltip: {
                    backgroundColor: 'rgba(0, 0, 0, 0.8)',
                    padding: 12,
                    titleFont: { size: 13, family: "'Poppins', sans-serif" },
                    bodyFont: { size: 12, family: "'Poppins', sans-serif" },
                    cornerRadius: 6,
                    callbacks: {
                        label: (context) => `Pedidos: ${context.parsed.y}`
                    }
                }
            },
            scales: {
                y: {
                    beginAtZero: true,
                    grid: {
                        color: 'rgba(0, 0, 0, 0.05)',
                        drawBorder: false
                    },
                    ticks: {
                        font: { size: 11, family: "'Poppins', sans-serif" },
                        padding: 8,
                        stepSize: 5
                    }
                },
                x: {
                    grid: {
                        color: 'rgba(0, 0, 0, 0.05)',
                        drawBorder: false
                    },
                    ticks: {
                        font: { size: 11, family: "'Poppins', sans-serif" },
                        padding: 8
                    }
                }
            },
            interaction: {
                intersect: false,
                mode: 'index'
            },
            animation: {
                duration: 750,
                easing: 'easeOutQuart'
            }
        };

        const chart = new Chart(ctx, {
            type: 'bar',
            data: data,
            options: options
        });

        this.charts.ordersChart = chart;
        return chart;
    }

    // Crear gráfico de productos
    createProductsChart() {
        const ctx = document.getElementById('productsChart');
        if (!ctx) return null;

        const data = {
            labels: ['Muebles hogar', 'Comercial', 'Cocina/Closets', 'Restauración'],
            datasets: [{
                data: [45, 20, 25, 10],
                backgroundColor: [
                    'rgba(139, 90, 43, 0.9)',
                    'rgba(255, 200, 87, 0.9)',
                    'rgba(63, 137, 255, 0.9)',
                    'rgba(63, 182, 124, 0.9)'
                ],
                borderWidth: 2,
                borderColor: '#ffffff',
                hoverOffset: 12,
                hoverBorderWidth: 3
            }]
        };

        const options = {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    position: 'bottom',
                    align: 'center',
                    labels: {
                        boxWidth: 12,
                        padding: 20,
                        usePointStyle: true,
                        pointStyle: 'circle',
                        font: {
                            size: 12,
                            family: "'Poppins', sans-serif",
                            weight: '500'
                        },
                        color: '#495057',
                        generateLabels: (chart) => {
                            const data = chart.data;
                            if (data.labels.length && data.datasets.length) {
                                return data.labels.map((label, i) => {
                                    const dataset = data.datasets[0];
                                    const value = dataset.data[i];
                                    const total = dataset.data.reduce((a, b) => a + b, 0);
                                    const percentage = Math.round((value / total) * 100);

                                    return {
                                        text: `${label}: ${value} (${percentage}%)`,
                                        fillStyle: dataset.backgroundColor[i],
                                        strokeStyle: dataset.borderColor,
                                        lineWidth: dataset.borderWidth,
                                        hidden: false,
                                        index: i
                                    };
                                });
                            }
                            return [];
                        }
                    }
                },
                tooltip: {
                    backgroundColor: 'rgba(0, 0, 0, 0.8)',
                    padding: 12,
                    titleFont: { size: 13, family: "'Poppins', sans-serif" },
                    bodyFont: { size: 12, family: "'Poppins', sans-serif" },
                    cornerRadius: 6,
                    callbacks: {
                        label: (context) => {
                            const label = context.label || '';
                            const value = context.parsed;
                            const total = context.dataset.data.reduce((a, b) => a + b, 0);
                            const percentage = Math.round((value / total) * 100);
                            return `${label}: ${value} unidades (${percentage}%)`;
                        }
                    }
                }
            },
            cutout: '65%',
            radius: '85%',
            layout: {
                padding: {
                    top: 20,
                    bottom: 20,
                    left: 10,
                    right: 10
                }
            },
            animation: {
                animateScale: true,
                animateRotate: true,
                duration: 1000,
                easing: 'easeOutQuart'
            }
        };

        const chart = new Chart(ctx, {
            type: 'doughnut',
            data: data,
            options: options
        });

        this.charts.productsChart = chart;
        return chart;
    }

    // Crear todos los gráficos
    createCharts() {
        this.createOrdersChart();
        this.createProductsChart();
    }

    // Redimensionar todos los gráficos
    resizeAll() {
        Object.values(this.charts).forEach(chart => {
            if (chart) {
                chart.resize();
            }
        });
    }

    // Destruir un gráfico específico
    destroyChart(chartName) {
        if (this.charts[chartName]) {
            this.charts[chartName].destroy();
            delete this.charts[chartName];
        }
    }

    // Destruir todos los gráficos
    destroyAll() {
        Object.keys(this.charts).forEach(chartName => {
            this.destroyChart(chartName);
        });
        this.charts = {};
    }

    // Configurar event listeners
    setupEventListeners() {
        // Redimensionar en resize
        let resizeTimeout;
        window.addEventListener('resize', () => {
            clearTimeout(resizeTimeout);
            resizeTimeout = setTimeout(() => {
                this.resizeAll();
            }, 200);
        });

        // Actualizar al cambiar mes/año
        const mesSelect = document.getElementById('selectMes');
        const anioSelect = document.getElementById('selectAnio');
        const refreshBtn = document.getElementById('btnRefrescar');

        if (mesSelect) {
            mesSelect.addEventListener('change', () => this.refreshData());
        }
        if (anioSelect) {
            anioSelect.addEventListener('change', () => this.refreshData());
        }
        if (refreshBtn) {
            refreshBtn.addEventListener('click', () => this.refreshData());
        }
    }

    // Refrescar datos (simulado - deberías conectar con tu backend)
    refreshData() {
        console.log('Refrescando datos del dashboard...');

        // Aquí iría la lógica para obtener nuevos datos del servidor
        // Por ahora solo recreamos los gráficos
        this.destroyAll();
        this.createCharts();

        // Ejemplo de actualización de métricas
        this.updateMetrics();
    }

    // Actualizar métricas (simulado)
    updateMetrics() {
        // Aquí iría la actualización de las métricas
        console.log('Actualizando métricas...');
    }

    // Obtener instancia de un gráfico
    getChart(name) {
        return this.charts[name];
    }

    // Verificar si está inicializado
    isReady() {
        return this.isInitialized;
    }
}

// Instancia global
const dashboardCharts = new DashboardChartsManager();

// Inicializar cuando el DOM esté listo
document.addEventListener('DOMContentLoaded', () => {
    dashboardCharts.initialize();
});

// También inicializar cuando la vista del dashboard se muestre
function showDashboardView() {
    // Pequeño retraso para asegurar que el DOM está renderizado
    setTimeout(() => {
        if (!dashboardCharts.isReady()) {
            dashboardCharts.initialize();
        } else {
            dashboardCharts.resizeAll();
        }
    }, 100);
}

// Exportar para uso global
window.dashboardCharts = dashboardCharts;
window.showDashboardView = showDashboardView;
window.initializeCharts = () => dashboardCharts.initialize();
window.resizeCharts = () => dashboardCharts.resizeAll();
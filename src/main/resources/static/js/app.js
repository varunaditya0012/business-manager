/**
 * Business Tool Manager - Frontend Application Script
 * Bootstrap 5 interactive enhancements, navigation tracking, and UX helpers
 */

document.addEventListener('DOMContentLoaded', () => {
    // 1. Initialize Bootstrap Tooltips & Popovers
    initTooltips();

    // 2. Sidebar Active Navigation Highlighting & Mobile Toggle
    initSidebar();

    // 3. Auto-Dismiss Flash Alerts
    initAutoDismissAlerts();

    // 4. Form Validation Helpers
    initFormValidation();

    // 5. Delete Confirmation Dialogs
    initDeleteConfirmations();

    // 6. Search Input Debounce (optional live filter support)
    initSearchDebounce();

    // 7. Smooth Scrolling
    initSmoothScroll();
});

/**
 * Initialize Bootstrap 5 Tooltips across the page
 */
function initTooltips() {
    if (typeof bootstrap !== 'undefined' && bootstrap.Tooltip) {
        const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
        tooltipTriggerList.forEach(tooltipTriggerEl => {
            new bootstrap.Tooltip(tooltipTriggerEl);
        });
    }
}

/**
 * Handle active link matching for sidebar and responsive toggle
 */
function initSidebar() {
    const currentPath = window.location.pathname;
    const navLinks = document.querySelectorAll('.sidebar-nav .nav-link');

    navLinks.forEach(link => {
        const href = link.getAttribute('href');
        if (href) {
            // Check if current path matches or starts with link path (ignoring trailing slash)
            const cleanHref = href.split('?')[0].replace(/\/$/, '');
            const cleanCurrent = currentPath.replace(/\/$/, '');

            if (cleanHref && (cleanCurrent === cleanHref || (cleanHref !== '' && cleanHref !== '/' && cleanCurrent.startsWith(cleanHref)))) {
                link.classList.add('active');
            }
        }
    });

    // Mobile Sidebar Toggle
    const sidebarToggle = document.getElementById('sidebarToggle');
    const sidebar = document.getElementById('sidebar');

    if (sidebarToggle && sidebar) {
        sidebarToggle.addEventListener('click', (e) => {
            e.stopPropagation();
            sidebar.classList.toggle('show');
        });

        // Close sidebar on click outside when expanded on mobile
        document.addEventListener('click', (e) => {
            if (window.innerWidth < 768 && sidebar.classList.contains('show')) {
                if (!sidebar.contains(e.target) && e.target !== sidebarToggle) {
                    sidebar.classList.remove('show');
                }
            }
        });
    }
}

/**
 * Automatically dismiss flash alerts after 5 seconds
 */
function initAutoDismissAlerts() {
    const alerts = document.querySelectorAll('.alert-dismissible');
    alerts.forEach(alertEl => {
        setTimeout(() => {
            if (typeof bootstrap !== 'undefined' && bootstrap.Alert) {
                const alertInstance = bootstrap.Alert.getOrCreateInstance(alertEl);
                if (alertInstance) {
                    alertInstance.close();
                }
            } else {
                alertEl.style.transition = 'opacity 0.5s ease';
                alertEl.style.opacity = '0';
                setTimeout(() => alertEl.remove(), 500);
            }
        }, 5000);
    });
}

/**
 * Bootstrap 5 Client-Side Form Validation
 */
function initFormValidation() {
    const forms = document.querySelectorAll('.needs-validation');
    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            // Check password match if register form
            const password = form.querySelector('#password');
            const confirmPassword = form.querySelector('#confirmPassword');

            if (password && confirmPassword && password.value !== confirmPassword.value) {
                confirmPassword.setCustomValidity('Passwords do not match');
            } else if (confirmPassword) {
                confirmPassword.setCustomValidity('');
            }

            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }

            form.classList.add('was-validated');
        }, false);
    });
}

/**
 * Enhanced Confirmation Dialog for Delete Actions
 */
function initDeleteConfirmations() {
    const deleteForms = document.querySelectorAll('form[action*="delete"]');
    deleteForms.forEach(form => {
        if (!form.hasAttribute('onsubmit')) {
            form.addEventListener('submit', (e) => {
                const confirmed = window.confirm('Are you sure you want to delete this record? This action cannot be undone.');
                if (!confirmed) {
                    e.preventDefault();
                }
            });
        }
    });
}

/**
 * Debounce helper for Search inputs
 */
function initSearchDebounce() {
    const searchInputs = document.querySelectorAll('input[name="search"], input[name="keyword"]');
    searchInputs.forEach(input => {
        let debounceTimer;
        input.addEventListener('input', (e) => {
            clearTimeout(debounceTimer);
            debounceTimer = setTimeout(() => {
                // Emits a custom search-debounce event that parent forms or live search handlers can bind to
                const event = new CustomEvent('search-debounced', { detail: { query: e.target.value } });
                input.dispatchEvent(event);
            }, 400);
        });
    });
}

/**
 * Smooth scrolling for internal anchor links
 */
function initSmoothScroll() {
    document.querySelectorAll('a[href^="#"]:not([href="#"])').forEach(anchor => {
        anchor.addEventListener('click', function(e) {
            const target = document.querySelector(this.getAttribute('href'));
            if (target) {
                e.preventDefault();
                target.scrollIntoView({
                    behavior: 'smooth',
                    block: 'start'
                });
            }
        });
    });
}

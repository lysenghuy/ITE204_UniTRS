// University Management System (UMS) - Main JavaScript
document.addEventListener('DOMContentLoaded', function () {
    // Auto-dismiss alerts after 5 seconds if enabled
    const alerts = document.querySelectorAll('.alert-dismissible');
    alerts.forEach(function (alert) {
        setTimeout(function () {
            const bsAlert = bootstrap.Alert.getOrCreateInstance(alert);
            if (bsAlert) {
                bsAlert.close();
            }
        }, 5000);
    });
});

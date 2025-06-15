// HR Demo Functions - Define globally without IIFE
window.hrDemoFunctions = {
    showIframe: function() {
        // Redirect to demos.somdip.dev instead of showing iframe
        window.location.href = 'https://demos.somdip.dev';
    },
    
    showMetrics: function() {
        // Hide iframe, show metrics
        const demoMetrics = document.getElementById('demoMetrics');
        const iframeDemo = document.getElementById('iframeDemo');
        
        if (iframeDemo) iframeDemo.classList.add('hidden');
        if (demoMetrics) demoMetrics.style.display = 'block';
        
        // Update button states
        const metricsBtn = document.getElementById('metricsBtn');
        const iframeBtn = document.getElementById('iframeBtn');
        
        if (metricsBtn) {
            metricsBtn.classList.add('bg-blue-600');
            metricsBtn.classList.remove('bg-gray-600');
        }
        if (iframeBtn) {
            iframeBtn.classList.add('bg-gray-600');
            iframeBtn.classList.remove('bg-blue-600');
        }
    },
    
    reloadIframe: function() {
        // Redirect to demos.somdip.dev
        window.location.href = 'https://demos.somdip.dev';
    },
    
    openInNewTab: function() {
        // Open demos.somdip.dev in new tab
        window.open('https://demos.somdip.dev', '_blank');
    },
    
    iframeLoaded: function() {
        console.log('HR Demo iframe loaded successfully');
    },
    
    iframeError: function() {
        console.error('Error loading HR Demo iframe');
        // Redirect to external URL on error
        const demoConfig = window.demoConfig || {};
        const externalUrl = demoConfig.externalUrl || 'https://demos.somdip.dev';
        window.location.href = externalUrl;
    }
};

// Show metrics by default when page loads
if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', function() {
        if (window.hrDemoFunctions && window.hrDemoFunctions.showMetrics) {
            window.hrDemoFunctions.showMetrics();
        }
    });
} else {
    // DOM already loaded
    if (window.hrDemoFunctions && window.hrDemoFunctions.showMetrics) {
        window.hrDemoFunctions.showMetrics();
    }
}

console.log('hr-demo.js loaded, hrDemoFunctions defined:', window.hrDemoFunctions);
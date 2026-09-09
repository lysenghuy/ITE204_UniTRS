<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UniTRS - Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    <style>
        body {
            background: linear-gradient(135deg, #0d1b2a 0%, #1b2838 50%, #0d6efd 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 2rem 0;
        }
        .register-card {
            background: rgba(255, 255, 255, 0.95);
            border-radius: 1rem;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.3);
            max-width: 550px;
            width: 100%;
            padding: 2.5rem;
        }
        .register-card .logo {
            font-size: 2rem;
            font-weight: 700;
            color: #0d6efd;
            text-align: center;
            margin-bottom: 0.25rem;
        }
        .register-card .subtitle {
            text-align: center;
            color: #6c757d;
            margin-bottom: 2rem;
            font-size: 0.9rem;
        }
        .form-control:focus, .form-select:focus {
            border-color: #0d6efd;
            box-shadow: 0 0 0 0.2rem rgba(13, 110, 253, 0.15);
        }
        .btn-primary {
            background-color: #0d6efd;
            border: none;
            padding: 0.65rem;
            font-weight: 600;
        }
        .btn-primary:hover {
            background-color: #0b5ed7;
        }
        
        /* Validation Styles */
        .validation-message {
            font-size: 0.8rem;
            margin-top: 0.25rem;
            display: flex;
            align-items: center;
            gap: 0.25rem;
        }
        .text-success-custom { color: #198754; }
        .text-danger-custom { color: #dc3545; }
        .text-warning-custom { color: #ffc107; }
        
        /* Password Strength Meter */
        .password-strength-container {
            margin-top: 0.5rem;
            font-size: 0.8rem;
        }
        .strength-bar-container {
            height: 4px;
            background-color: #e9ecef;
            border-radius: 2px;
            margin-bottom: 0.5rem;
            overflow: hidden;
            display: flex;
        }
        .strength-bar-segment {
            height: 100%;
            flex: 1;
            transition: background-color 0.3s ease;
        }
        .strength-bar-segment:not(:last-child) {
            border-right: 1px solid white;
        }
        .rule-list {
            list-style: none;
            padding: 0;
            margin: 0;
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 0.25rem;
        }
        .rule-list li {
            color: #6c757d;
            display: flex;
            align-items: center;
            gap: 0.25rem;
        }
        .rule-list li i { font-size: 0.9rem; }
        .rule-met { color: #198754 !important; }
        .rule-unmet { color: #dc3545 !important; }
    </style>
</head>
<body>
    <div class="register-card">
        <div class="logo"><i class="bi bi-person-plus-fill"></i> Create Account</div>
        <div class="subtitle">Join University Management System</div>

        <!-- Error message -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <i class="bi bi-exclamation-triangle-fill me-2"></i>${error}
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/auth/register" method="POST" id="registerForm">
            <!-- Identifier -->
            <div class="mb-3">
                <label for="identifier" class="form-label fw-semibold">Username <span class="text-danger">*</span></label>
                <div class="input-group">
                    <span class="input-group-text"><i class="bi bi-hash"></i></span>
                    <input type="text" class="form-control" id="identifier" name="identifier"
                           placeholder="e.g. 60-24-04-91 or john.doe" required autocomplete="off" value="${identifier}">
                </div>
                <div id="identifierValidation" class="validation-message text-muted">
                    Must be 3-30 characters long (letters, numbers, dots, hyphens, underscores).
                </div>
            </div>
            
            <!-- Full Name -->
            <div class="mb-3">
                <label for="fullName" class="form-label fw-semibold">Full Name <span class="text-danger">*</span></label>
                <div class="input-group">
                    <span class="input-group-text"><i class="bi bi-person"></i></span>
                    <input type="text" class="form-control" id="fullName" name="fullName"
                           placeholder="First Last" required autocomplete="off" value="${fullName}">
                </div>
                <div id="fullNameValidation" class="validation-message text-muted">
                    Please provide both first and last name.
                </div>
            </div>
            
            <!-- Email -->
            <div class="mb-3">
                <label for="email" class="form-label fw-semibold">Email Address <span class="text-danger">*</span></label>
                <div class="input-group">
                    <span class="input-group-text"><i class="bi bi-envelope"></i></span>
                    <input type="email" class="form-control" id="email" name="email"
                           placeholder="name@gmail.com or name@unitrs.edu" required autocomplete="off" value="${email}">
                </div>
                <div id="emailValidation" class="validation-message text-muted">
                    Make sure the email can receive messages because you need to verify it.
                </div>
            </div>
            
            <!-- Major -->
            <div class="mb-3">
                <label for="majorSelect" class="form-label fw-semibold">Major (Optional)</label>
                <div class="input-group mb-2">
                    <span class="input-group-text"><i class="bi bi-book"></i></span>
                    <select class="form-select" id="majorSelect" name="majorSelect">
                        <option value="">-- Select Major --</option>
                        <option value="Computer Science">Computer Science</option>
                        <option value="Information Technology">Information Technology</option>
                        <option value="Software Engineering">Software Engineering</option>
                        <option value="Business Administration">Business Administration</option>
                        <option value="Accounting">Accounting</option>
                        <option value="Other">Other (Please specify)</option>
                    </select>
                </div>
                <input type="text" class="form-control d-none" id="majorInput" name="majorInput" placeholder="Enter your major">
            </div>
            
            <!-- Password -->
            <div class="row">
                <div class="col-md-6 mb-3">
                    <label for="password" class="form-label fw-semibold">Password <span class="text-danger">*</span></label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="bi bi-lock"></i></span>
                        <input type="password" class="form-control" id="password" name="password" required>
                    </div>
                </div>
                <div class="col-md-6 mb-3">
                    <label for="confirmPassword" class="form-label fw-semibold">Confirm <span class="text-danger">*</span></label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="bi bi-lock-fill"></i></span>
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                    </div>
                    <div id="confirmValidation" class="validation-message"></div>
                </div>
            </div>
            
            <!-- Password Strength -->
            <div class="password-strength-container mb-4">
                <div class="strength-bar-container">
                    <div class="strength-bar-segment" id="seg1"></div>
                    <div class="strength-bar-segment" id="seg2"></div>
                    <div class="strength-bar-segment" id="seg3"></div>
                    <div class="strength-bar-segment" id="seg4"></div>
                </div>
                <ul class="rule-list">
                    <li id="rule-len"><i class="bi bi-circle"></i> 8-64 characters</li>
                    <li id="rule-upper"><i class="bi bi-circle"></i> Uppercase letter</li>
                    <li id="rule-lower"><i class="bi bi-circle"></i> Lowercase letter</li>
                    <li id="rule-num"><i class="bi bi-circle"></i> Number</li>
                    <li id="rule-spec"><i class="bi bi-circle"></i> Special character</li>
                </ul>
            </div>
            
            <button type="submit" class="btn btn-primary w-100 mb-3" id="submitBtn">
                <i class="bi bi-person-plus me-2"></i>Register
            </button>
            <div class="text-center">
                <span class="text-muted">Already have an account?</span>
                <a href="${pageContext.request.contextPath}/auth/login" class="text-decoration-none fw-semibold ms-1">Sign In</a>
            </div>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        const contextPath = '${pageContext.request.contextPath}';
        
        // Form Elements
        const identifierInput = document.getElementById('identifier');
        const identifierVal = document.getElementById('identifierValidation');
        
        const fullNameInput = document.getElementById('fullName');
        const fullNameVal = document.getElementById('fullNameValidation');
        
        const emailInput = document.getElementById('email');
        const emailVal = document.getElementById('emailValidation');
        
        const majorSelect = document.getElementById('majorSelect');
        const majorInput = document.getElementById('majorInput');
        
        const passwordInput = document.getElementById('password');
        const confirmInput = document.getElementById('confirmPassword');
        const confirmVal = document.getElementById('confirmValidation');
        
        const submitBtn = document.getElementById('submitBtn');
        const registerForm = document.getElementById('registerForm');

        // State
        let isIdentifierValid = false;
        let isEmailValid = false;
        let isFullNameValid = false;
        let identifierTimer;
        let emailTimer;
        
        // Handle server-side major restoration
        const serverMajor = '${major}';
        if (serverMajor) {
            const options = Array.from(majorSelect.options).map(opt => opt.value);
            if (options.includes(serverMajor)) {
                majorSelect.value = serverMajor;
            } else {
                majorSelect.value = 'Other';
                majorInput.value = serverMajor;
                majorInput.classList.remove('d-none');
                majorInput.required = true;
            }
        }

        // --- Helper ---
        function setValidationMsg(el, msg, colorClass, iconClass) {
            el.className = 'validation-message ' + colorClass;
            el.innerHTML = iconClass ? `<i class="bi ${iconClass}"></i> ${msg}` : msg;
        }

        // --- Identifier Validation ---
        function validateIdentifier() {
            clearTimeout(identifierTimer);
            const val = identifierInput.value.trim();
            isIdentifierValid = false;
            
            if (!val) {
                setValidationMsg(identifierVal, 'Required', 'text-danger-custom', 'bi-x-circle');
                return;
            }
            
            // Format checks (allow letters, numbers, dots, hyphens, underscores)
            if (!/^[A-Za-z0-9._-]{3,30}$/.test(val)) {
                setValidationMsg(identifierVal, '3-30 chars. Only letters, numbers, dots, hyphens, underscores.', 'text-danger-custom', 'bi-x-circle');
                return;
            }
            if (/^[._-]|[._-]$/.test(val)) {
                setValidationMsg(identifierVal, 'Cannot start or end with ., -, or _', 'text-danger-custom', 'bi-x-circle');
                return;
            }
            if (/\.\.|\_\_|\-\-|\.\_|\_\.|\.\-|\-\.|\_\-|\-\_/.test(val)) {
                setValidationMsg(identifierVal, 'Cannot contain consecutive punctuation', 'text-danger-custom', 'bi-x-circle');
                return;
            }
            if (/^\d+$/.test(val)) {
                setValidationMsg(identifierVal, 'Cannot be purely numeric', 'text-danger-custom', 'bi-x-circle');
                return;
            }
            const reserved = ["admin", "root", "support", "null", "undefined", "system", "moderator", "superuser"];
            if (reserved.includes(val.toLowerCase())) {
                setValidationMsg(identifierVal, 'This identifier is reserved', 'text-danger-custom', 'bi-x-circle');
                return;
            }

            // Async Uniqueness Check
            setValidationMsg(identifierVal, 'Checking availability...', 'text-warning-custom', 'bi-hourglass-split');
            
            identifierTimer = setTimeout(async () => {
                try {
                    const res = await fetch(contextPath + '/api/validate/identifier?identifier=' + encodeURIComponent(val));
                    if (res.status === 429) {
                        setValidationMsg(identifierVal, 'Rate limit reached, please wait a moment', 'text-warning-custom', 'bi-hourglass-split');
                        return;
                    }
                    const data = await res.json();
                    if (data.available) {
                        isIdentifierValid = true;
                        setValidationMsg(identifierVal, 'Identifier is available!', 'text-success-custom', 'bi-check-circle');
                    } else {
                        isIdentifierValid = false;
                        setValidationMsg(identifierVal, 'Identifier is taken', 'text-danger-custom', 'bi-x-circle');
                    }
                } catch (e) {
                    setValidationMsg(identifierVal, 'Error checking availability', 'text-danger-custom', 'bi-exclamation-circle');
                }
            }, 400);
        }

        identifierInput.addEventListener('input', validateIdentifier);

        // --- Full Name Validation ---
        function validateFullName() {
            let val = fullNameInput.value.replace(/\s+/g, ' ').trim();
            fullNameInput.value = val;
            isFullNameValid = false;
            
            if (!val) {
                setValidationMsg(fullNameVal, 'Required', 'text-danger-custom', 'bi-x-circle');
            } else if (val.length < 2 || val.length > 100) {
                setValidationMsg(fullNameVal, 'Full name must be between 2 and 100 characters', 'text-danger-custom', 'bi-x-circle');
            } else if (/^[-']|[-']$/.test(val)) {
                setValidationMsg(fullNameVal, 'Cannot start/end with hyphens or apostrophes', 'text-danger-custom', 'bi-x-circle');
            } else if (!/^[a-zA-Z\s'-]+$/.test(val)) {
                setValidationMsg(fullNameVal, 'Full name can only contain letters, spaces, hyphens, and apostrophes', 'text-danger-custom', 'bi-x-circle');
            } else if (!val.includes(' ')) {
                setValidationMsg(fullNameVal, 'Please provide both first and last name (separated by space)', 'text-danger-custom', 'bi-x-circle');
            } else {
                isFullNameValid = true;
                setValidationMsg(fullNameVal, 'Looks good!', 'text-success-custom', 'bi-check-circle');
            }
        }

        fullNameInput.addEventListener('blur', validateFullName);

        // --- Email Validation ---
        function validateEmail() {
            clearTimeout(emailTimer);
            const val = emailInput.value.trim().toLowerCase();
            isEmailValid = false;
            
            if (!val) {
                setValidationMsg(emailVal, 'Make sure the email can receive messages because you need to verify it.', 'text-muted', '');
                return;
            }
            
            if (val.length > 64) {
                setValidationMsg(emailVal, 'Max 64 characters allowed', 'text-danger-custom', 'bi-x-circle');
                return;
            }

            if (!/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/.test(val)) {
                setValidationMsg(emailVal, 'Please enter a valid email address format', 'text-danger-custom', 'bi-x-circle');
                return;
            }
            
            if (!val.endsWith('@gmail.com') && !val.endsWith('.edu')) {
                setValidationMsg(emailVal, 'Must be a @gmail.com or .edu address', 'text-danger-custom', 'bi-x-circle');
                return;
            }
            
            setValidationMsg(emailVal, 'Checking availability...', 'text-warning-custom', 'bi-hourglass-split');
            emailTimer = setTimeout(async () => {
                try {
                    const res = await fetch(contextPath + '/api/validate/email?email=' + encodeURIComponent(val));
                    if (res.status === 429) {
                        setValidationMsg(emailVal, 'Rate limit reached, please wait a moment', 'text-warning-custom', 'bi-hourglass-split');
                        return;
                    }
                    const data = await res.json();
                    if (data.available) {
                        isEmailValid = true;
                        setValidationMsg(emailVal, 'Email is available!', 'text-success-custom', 'bi-check-circle');
                    } else {
                        isEmailValid = false;
                        setValidationMsg(emailVal, 'Email is already registered', 'text-danger-custom', 'bi-x-circle');
                    }
                } catch (e) {
                    setValidationMsg(emailVal, 'Error checking availability', 'text-danger-custom', 'bi-exclamation-circle');
                }
            }, 400);
        }

        emailInput.addEventListener('input', validateEmail);

        // --- Major Handling ---
        majorSelect.addEventListener('change', () => {
            if (majorSelect.value === 'Other') {
                majorInput.classList.remove('d-none');
                majorInput.required = true;
            } else {
                majorInput.classList.add('d-none');
                majorInput.required = false;
                majorInput.value = '';
            }
        });

        // --- Password Strength ---
        function checkPasswordRules(val) {
            return {
                len: val.length >= 8 && val.length <= 64,
                upper: /[A-Z]/.test(val),
                lower: /[a-z]/.test(val),
                num: /[0-9]/.test(val),
                spec: /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>/?~`]/.test(val)
            };
        }

        function isPasswordValid(val) {
            if (!val || val.trim() === '') return false;
            const r = checkPasswordRules(val);
            return r.len && r.upper && r.lower && r.num && r.spec;
        }

        passwordInput.addEventListener('input', () => {
            const val = passwordInput.value;
            const checks = checkPasswordRules(val);

            let score = 0;
            for (let key in checks) {
                const el = document.getElementById('rule-' + key);
                const icon = el.querySelector('i');
                if (checks[key]) {
                    el.classList.add('rule-met');
                    el.classList.remove('rule-unmet');
                    icon.className = 'bi bi-check-circle-fill';
                    score++;
                } else {
                    el.classList.remove('rule-met');
                    if (val.length > 0) el.classList.add('rule-unmet');
                    icon.className = val.length > 0 ? 'bi bi-x-circle-fill' : 'bi bi-circle';
                }
            }
            
            if (val.trim() === '') score = 0;

            const segments = ['seg1', 'seg2', 'seg3', 'seg4'];
            const colors = ['#dc3545', '#ffc107', '#0dcaf0', '#198754'];
            
            segments.forEach((seg, i) => {
                const el = document.getElementById(seg);
                if (i < score - 1 || (score === 5 && i === 3)) {
                    el.style.backgroundColor = colors[Math.max(0, score - 2)];
                } else {
                    el.style.backgroundColor = 'transparent';
                }
            });
            
            validateConfirm();
        });

        // --- Confirm Password ---
        confirmInput.addEventListener('input', validateConfirm);

        function validateConfirm() {
            const val = confirmInput.value;
            const target = passwordInput.value;
            if (!val) {
                confirmVal.innerHTML = '';
            } else if (val === target) {
                setValidationMsg(confirmVal, 'Passwords match', 'text-success-custom', 'bi-check-circle');
            } else {
                setValidationMsg(confirmVal, 'Passwords do not match', 'text-danger-custom', 'bi-x-circle');
            }
        }

        // Form Submit Check
        registerForm.addEventListener('submit', (e) => {
            validateFullName();

            if (!isIdentifierValid) {
                e.preventDefault();
                alert("Please provide a valid and available identifier/username before registering.");
                identifierInput.focus();
                return;
            }

            if (!isFullNameValid) {
                e.preventDefault();
                alert("Please provide a valid first and last name.");
                fullNameInput.focus();
                return;
            }

            if (!isEmailValid) {
                e.preventDefault();
                alert("Please provide a valid, available @gmail.com or .edu email address.");
                emailInput.focus();
                return;
            }

            if (!isPasswordValid(passwordInput.value)) {
                e.preventDefault();
                alert("Please satisfy all password complexity requirements (8-64 characters, uppercase, lowercase, number, and special character).");
                passwordInput.focus();
                return;
            }

            if (passwordInput.value !== confirmInput.value) {
                e.preventDefault();
                alert("Passwords do not match.");
                confirmInput.focus();
                return;
            }

            if (majorSelect.value === 'Other' && !majorInput.value.trim()) {
                e.preventDefault();
                alert("Please specify your major.");
                majorInput.focus();
                return;
            }

            // Map major safely without creating duplicate hidden inputs
            let finalMajor = document.getElementById('finalMajorInput');
            if (!finalMajor) {
                finalMajor = document.createElement('input');
                finalMajor.type = 'hidden';
                finalMajor.name = 'major';
                finalMajor.id = 'finalMajorInput';
                registerForm.appendChild(finalMajor);
            }
            finalMajor.value = majorSelect.value === 'Other' ? majorInput.value.trim() : majorSelect.value;
        });

        // --- Local Storage & Autofill Management ---
        window.addEventListener('DOMContentLoaded', () => {
            // Restore from localStorage if input is empty
            if (!identifierInput.value && localStorage.getItem('reg_identifier')) {
                identifierInput.value = localStorage.getItem('reg_identifier');
            }
            if (!fullNameInput.value && localStorage.getItem('reg_fullName')) {
                fullNameInput.value = localStorage.getItem('reg_fullName');
            }
            if (!emailInput.value && localStorage.getItem('reg_email')) {
                emailInput.value = localStorage.getItem('reg_email');
            }
            if (!majorSelect.value && localStorage.getItem('reg_majorSelect')) {
                majorSelect.value = localStorage.getItem('reg_majorSelect');
                majorSelect.dispatchEvent(new Event('change'));
            }
            if (!majorInput.value && localStorage.getItem('reg_majorInput')) {
                majorInput.value = localStorage.getItem('reg_majorInput');
            }

            // Trigger validations for pre-filled / restored fields
            if (identifierInput.value.trim() !== '') {
                validateIdentifier();
            }
            if (fullNameInput.value.trim() !== '') {
                validateFullName();
            }
            if (emailInput.value.trim() !== '') {
                validateEmail();
            }
        });

        // Save values on change
        identifierInput.addEventListener('input', () => localStorage.setItem('reg_identifier', identifierInput.value));
        fullNameInput.addEventListener('input', () => localStorage.setItem('reg_fullName', fullNameInput.value));
        emailInput.addEventListener('input', () => localStorage.setItem('reg_email', emailInput.value));
        majorSelect.addEventListener('change', () => localStorage.setItem('reg_majorSelect', majorSelect.value));
        majorInput.addEventListener('input', () => localStorage.setItem('reg_majorInput', majorInput.value));

    </script>
</body>
</html>

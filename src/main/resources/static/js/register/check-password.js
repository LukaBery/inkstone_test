document.addEventListener("DOMContentLoaded", function () {
    const password = document.getElementById("password");
    const confirmPassword = document.getElementById("confirmPassword");
    const errorMessage = document.getElementById("password-error");

    function validatePassword() {
        if (password.value === "") {
            errorMessage.style.display = "none";
            return;
        }

        if (confirmPassword.value === "") {
            errorMessage.style.display = "none";
            return;
        }

        if (password.value !== confirmPassword.value) {
            errorMessage.style.display = "block";
        } else {
            errorMessage.style.display = "none";
        }
    }


    password.addEventListener("blur", validatePassword);
    confirmPassword.addEventListener("blur", validatePassword);
});
document.addEventListener("DOMContentLoaded", function () {
    const phoneInput = document.getElementById("phone");

    if (phoneInput.value) {
        phoneInput.value = formatPhoneNumber(phoneInput.value);
    }

    phoneInput.addEventListener("input", function () {
        phoneInput.value = formatPhoneNumber(phoneInput.value);
    });

    function formatPhoneNumber(input) {
        input = input.replace(/\D/g, "");

        if (!input.startsWith("010")) {
            input = "010" + input;
        }

        if (input.length > 11) {
            input = input.substring(0, 11);
        }

        if (input.length > 7) {
            return input.substring(0, 3) + "-" + input.substring(3, 7) + "-" + input.substring(7);
        } else if (input.length > 3) {
            return input.substring(0, 3) + "-" + input.substring(3);
        }

        return input;
    }
});
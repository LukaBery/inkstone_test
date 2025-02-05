document.addEventListener("DOMContentLoaded", function () {
    const phoneInput = document.getElementById("phone");

    phoneInput.addEventListener("input", function (event) {
        let input = phoneInput.value.replace(/\D/g, ""); // 숫자 이외 제거

        if (!input.startsWith("010")) {
            input = "010" + input; // "010" 자동 추가
        }

        if (input.length > 11) {
            input = input.substring(0, 11); // 최대 11자리 제한
        }

        // "010-XXXX-XXXX" 형식으로 변환
        if (input.length > 7) {
            input = input.substring(0, 3) + "-" + input.substring(3, 7) + "-" + input.substring(7);
        } else if (input.length > 3) {
            input = input.substring(0, 3) + "-" + input.substring(3);
        }

        phoneInput.value = input; // 입력 필드에 적용
    });
});
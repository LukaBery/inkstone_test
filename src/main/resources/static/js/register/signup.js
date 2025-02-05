document.querySelector(".submit-button").addEventListener("click", function (event) {
    event.preventDefault();

    let phoneInput = document.getElementById("phone").value;
    phoneInput = phoneInput.replace(/-/g, "");
    if (phoneInput.startsWith("010")) {
        phoneInput = phoneInput.substring(3);
    }

    const hiddenLoginId = document.getElementById("hiddenLoginId").value;

    if (!hiddenLoginId) {
        alert("아이디 중복 체크를 먼저 해주세요.");
        return;
    }

    const formData = {
        name: document.getElementById("name").value,
        loginId: hiddenLoginId,
        password: document.getElementById("password").value,
        confirmPassword: document.getElementById("confirmPassword").value,
        phone: phoneInput
    };

    fetch("/api/member/signup", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(formData)
    })
        .then(response => {
            if (!response.ok) {
                return response.json().then(err => { throw err; });
            }
            return response.json();
        })
        .then(data => {
            if (data.success) {
                const userName = encodeURIComponent(data.data.name);
                window.location.href = `/member/signup-success?name=${userName}`;
            } else {
                throw { message: data.message || "회원가입 실패" };
            }
        })
        .catch(error => {
            console.error("Error:", error);

            const errors = error.data || {};

            document.querySelectorAll(".error-message").forEach(errorSpan => errorSpan.remove());

            Object.keys(errors).forEach(field => {
                const errorMessage = errors[field];
                const inputField = document.getElementById(field);

                if (inputField) {
                    let errorSpan = inputField.parentNode.querySelector(".error-message");

                    if (!errorSpan) {
                        errorSpan = document.createElement("span");
                        errorSpan.className = "error-message";
                        errorSpan.style.color = "red";
                        inputField.parentNode.appendChild(errorSpan);
                    }
                    errorSpan.innerText = errorMessage;
                }
            });

            // ✅ 필드별 에러가 없는 경우 alert로 메시지 표시
            if (!Object.keys(errors).length) {
                alert(error.message || "알 수 없는 오류 발생");
            }
        });
});
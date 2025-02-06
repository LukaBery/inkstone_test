document.addEventListener("DOMContentLoaded", function () {
    const editButton = document.querySelector(".submit-button");

    editButton.addEventListener("click", function () {
        const password = document.getElementById("password").value;
        const name = document.getElementById("name").value;
        const phone = document.getElementById("phone").value;

        if (!password || !name || !phone) {
            alert("모든 필드를 입력해주세요.");
            return;
        }

        const requestData = {
            password: password,
            name: name,
            phone: phone
        };

        fetch("/api/member/edit-profile", {
            method: "PATCH",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(requestData)
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert("회원 정보가 수정되었습니다.");
                    window.location.href = "/member/edit-profile";
                } else {
                    alert(data.message || "회원 정보 수정에 실패했습니다.");
                }
            })
            .catch(error => {
                console.error("Error:", error);
                alert("회원 정보 수정 중 오류가 발생했습니다.");
            });
    });
});
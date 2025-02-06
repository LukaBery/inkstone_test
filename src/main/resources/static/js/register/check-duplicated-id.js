function checkDuplicateId() {
    const userIdInput = document.getElementById("loginId");
    const hiddenUserIdInput = document.getElementById("hiddenLoginId");
    const checkButton = document.querySelector(".check-btn");

    const userId = userIdInput.value.trim();

    if (!userId) {
        alert("아이디를 입력하세요.");
        return;
    }

    userIdInput.disabled = true;
    checkButton.disabled = true;
    checkButton.innerText = "확인 중...";

    fetch(`/api/member/check-id?loginId=${encodeURIComponent(userId)}`)
        .then(response => {
            if (!response.ok) {
                return response.json().then(err => { throw new Error(err.message || "서버 응답 오류"); });
            }
            return response.json();
        })
        .then(data => {
            if (!data.success) {
                throw new Error(data.message || "서버 응답 오류");
            }

            if (data.data.isDuplicate) {
                alert("이미 사용 중인 아이디입니다.");
                userIdInput.disabled = false;
                hiddenUserIdInput.value = "";
                alert("사용 가능한 아이디입니다!");
                hiddenUserIdInput.value = userId;
            }
        })
        .catch(error => {
            console.error("Error:", error);
            alert(error.message || "서버 요청 중 문제가 발생했습니다. 잠시 후 다시 시도해주세요.");

            userIdInput.disabled = false;
            hiddenUserIdInput.value = "";
        })
        .finally(() => {
            checkButton.disabled = false;
            checkButton.innerText = "중복 체크";
        });
}
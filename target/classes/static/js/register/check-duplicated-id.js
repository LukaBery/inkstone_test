function checkDuplicateId() {
    const userIdInput = document.getElementById("loginId");
    const hiddenUserIdInput = document.getElementById("hiddenLoginId");
    const checkButton = document.querySelector(".check-btn");

    const userId = userIdInput.value.trim();

    if (!userId) {
        alert("아이디를 입력하세요.");
        return;
    }

    // ✅ 중복 체크 진행 중: 입력 필드 & 버튼 비활성화
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
                userIdInput.disabled = false; // ✅ 중복이면 다시 입력 가능
                hiddenUserIdInput.value = ""; // ✅ hidden 필드 초기화
            } else {
                alert("사용 가능한 아이디입니다!");
                hiddenUserIdInput.value = userId; // ✅ hidden 필드에 값 저장
            }
        })
        .catch(error => {
            console.error("Error:", error);
            alert(error.message || "서버 요청 중 문제가 발생했습니다. 잠시 후 다시 시도해주세요.");

            userIdInput.disabled = false; // 오류 발생 시 다시 입력 가능
            hiddenUserIdInput.value = ""; // hidden 필드 초기화
        })
        .finally(() => {
            checkButton.disabled = false; // 버튼 활성화
            checkButton.innerText = "중복 체크";
        });
}
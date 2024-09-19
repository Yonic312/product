/*하단 나타내기*/
    document.getElementById("manageButton").addEventListener("click", function() {
    var subMenu = document.getElementById("subMenu");
    if (subMenu.style.display === "none" || subMenu.style.display === "") {
    subMenu.style.display = "block";  // 하위 메뉴를 보이게 설정
} else {
    subMenu.style.display = "none";   // 하위 메뉴를 숨김
}
});


function post() {

    var questionId = $("#question_id:hidden").val();
    var content = $("#content").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
            "parentId":questionId,
            "content":content,
            "type":2
        }),
        success: function (response) {
            if (response.code == "2004") {
                $("#comment_section").hide();
            } else {
                if (response.code == "2003") {
                    var ifAccepted = confirm(response.message);
                    if (ifAccepted == true) {
                        window.open("https://github.com/login/oauth/authorize?client_id=390fafdbb045bff12f7d&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", "true");
                    }
                } else {
                    alert(response.message);
                }
            }
            console.log(response);
        },
        dataType: "json"
    })
}
function post() {

    var questionId = $("#question_id:hidden").val();
    var content = $("#question_comment").val();
    f(questionId, content, 1);

}

function f(targetId, content, type) {
    if (!content) {
        alert("回复不能为空!");
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if (response.code == "2004") {
                window.location.reload();
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
        },
        dataType: "json"
    })
}

function deployComment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    f(commentId, content, 2);
}

function deployComments(e) {
    var id = e.getAttribute("data-id");
    var comment = $("#comment-" + id);
    var deploy = e.getAttribute("data-deploy");
    if (deploy) {
        //折叠二级评论
        comment.removeClass("in");
        e.classList.remove("active");
        e.removeAttribute("data-deploy");
    } else {
        var subCommentContainer = $("#comment-" + id);
        var childLength = $("#comment-" + id).children().length;
        if (childLength !== 1) {
            //展开二级评论
            comment.addClass("in");
            e.setAttribute("data-deploy", "in");
            e.classList.add("active");
        } else {
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data, function (index, comment) {
                    var mediaElement = $("<div/>", {
                        "class": "media border-bottom"
                    });
                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded img-size",
                        "src": comment.user.avatarUrl
                    }));
                    mediaElement.append(mediaLeftElement);

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body",
                    }).append($("<h4/>", {
                        "class": "media-heading user-name",
                        html: comment.user.name
                    }));

                    var contentElement = $("<h4/>", {
                        html: comment.content
                    }).append($("<span/>", {
                        "class": "time pull-right",
                        html: moment(comment.gmtCreate).format('YYYY-MM-DD')
                    }));
                    mediaBodyElement.append(contentElement);
                    mediaElement.append(mediaBodyElement);
                    subCommentContainer.prepend(mediaElement);
                });

                //展开二级评论
                comment.addClass("in");
                e.setAttribute("data-deploy", "in");
                e.classList.add("active");

            });
        }
    }
}

function likeCount(e) {
    var id = e.getAttribute("data-id");
    var likeCount = $("#likeCount-" + id);

    var like = e.getAttribute("like");
    if (like) {
        $.getJSON("/lessLike/" + id, function (data) {
            $.each(data.data, function (index, comment) {
                likeCount.find('.like').text(comment.likeCount)
            });
        });
        e.removeAttribute("like", "add");
        e.classList.remove("active");
    } else {
        $.getJSON("/addLike/" + id, function (data) {
            $.each(data.data, function (index, comment) {
                likeCount.find('.like').text(comment.likeCount)
            });
        });
        e.setAttribute("like", "add");
        e.classList.add("active");
    }

}

function selectTag() {
    $("#pub-tag").show();
}
function addTag(e) {
    var tagName = e.getAttribute("data-id");
    var tag = $("#tag").val();

    if (tag) {
        if (tag.indexOf(tagName) === -1) {
            $("#tag").val(tag + "," + tagName);
        }
    } else {
        $("#tag").val(tagName);
    }

}
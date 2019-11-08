function gethome() {
    window.location.href ="/";
}

function loginCheck() {
    var username= $("#userName").val();
    var password= $("#password").val();

    if(username == null || password == null) {
        alert("用户名不能为空");
    }else {
        $.post({
            url: "/login/checkLogin",
            data: {
                "userName":username,
                "password":password,
            },
            error: function () {
                alert("查询出错");
                return false;
            },
            success: function (data) {
                if (data == "200") {
                    alert("登录成功");
                    window.location.href = "/";
                } else if (data == "400") {
                    alert("密码错误");
                    window.location.href = "#";
                } else {
                    alert("用户不存在");
                    window.location.href = "#";
                }
            }
        })
    }
}

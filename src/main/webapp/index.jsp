<%--
  Created by IntelliJ IDEA.
  User: wangfei
  Date: 2021/4/17
  Time: 11:41 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script>
        $(function ()
        {
            $("#ok").click(function ()
            {
                $.ajax({
                    url:"http://localhost:8080/bbs_war_exploded/test11",
                    headers:
                        {
                            "Authorization":"Bearer "+sessionStorage.getItem("token")
                        }
                })
            })

            $("#no").click(function ()
            {
                $.ajax({
                    url:"http://localhost:8080/bbs_war_exploded/user/login",
                    dataType:"json",
                    success:function(data)
                    {
                        sessionStorage.setItem("token",data.data.token)
                    },
                    error:function (data)
                    {
                        alert("请求失败")
                    }
                })
            })

            $("#test").click(function ()
            {
                $.ajax({
                    url:"http://localhost:8080/bbs_war_exploded/aaa",
                    headers:
                        {
                            "Authorization":"Bearer "+sessionStorage.getItem("token")
                        },
                })
            })

        })
    </script>
</head>
<body>
<button id="ok">点击</button>
<button id="no">登录</button>
<button id="test">测试</button>
</body>
</html>

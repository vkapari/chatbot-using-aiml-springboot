<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

<!-- Bootstrap CSS File -->
  	<link href="./css/chatbot.css" rel="stylesheet">
  
<!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

<title>Conversation</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<!-- Main Javascript File -->
  <script src="./js/chatbot.js"></script>
  
<script type="text/javascript">

var botImg = '<img src="./images/img_bot_avatar2.jpg" alt="Bot" style="width:40px; border-radius: 50%;">';
var userImg = '<img src="./images/img_user_avatar.png" alt="You" style="width:40px; border-radius: 50%;">';


</script>

</head>
<body>
    <p>Welcome ${name} !</p>
    <div id="chatBox" style="display: none;" class="chatBoxDiv">
    <div id="chatDivId" class="chatDiv" >
        <h3 style="text-align: center;">Your Conversation</h3>
        <div>
            <button type="button" class="btn btn-secondary" onclick="closeChatBox()">Close</button>
        </div>
    
        <div id="chatScreen" class="chatScreenDiv">
            <!-- <p id="msg"></p>
            <p id="reply"></p> -->
        </div>
        <div id="message" style="margin: 5px 0;">
            You: <input type="text" name="chatMsg" id="chatMsg" />
                 &nbsp;<button type="submit" id="sendButtonId" class="sendButton" onclick="chatAction()">Send</button>
        </div>
        <div style="position: fixed; bottom: 10px; right: 10px;">
            <button type="button" class="btn btn-secondary" onclick="closeChatBox()" style="text-align: right;">Close</button>
        </div>
    </div>
    </div>
    <div style="position: fixed; bottom: 10px; right: 10px;">
        <button id="openChatBoxId" class="btn btn-primary" type="button" onclick="openChatBox()" style="display: block;">Chat with us</button>
    </div>
</body>
</html>
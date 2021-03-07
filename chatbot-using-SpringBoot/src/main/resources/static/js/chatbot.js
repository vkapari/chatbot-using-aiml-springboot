/**
 * Main javascript
 */

var talking = true; //when false the speach function doesn't work

function openChatBox(){
    document.getElementById("chatBox").style.display = "block";
    document.getElementById("openChatBoxId").style.display = "none";
    document.getElementById("chatMsg").value = '';
    document.getElementById("chatMsg").focus();
    document.getElementById("chatScreen").innerHTML = "Hello I am Virtual Assistance, You can use me as a fun chat bot.";
}

function closeChatBox(){
    document.getElementById("chatMsg").value = '';
    document.getElementById("chatBox").style.display = "none";
    document.getElementById("chatScreen").innerHTML = "";
    document.getElementById("openChatBoxId").style = "display:block";
}

function addChat(chatMsg, reply){
	const mainDiv = document.getElementById("chatScreen");
	
	let userDiv = document.createElement("div");
    let userText = document.createElement("span");
    let userImage = document.createElement("img");
    userImage.src = "./images/img_user_avatar.png";
    userImage.alt = "You";
    userImage.className = "avatar";
	userDiv.id = "user";
	userDiv.className = "userDiv";
	// userDiv.innerHTML = "<span>"+message+"</span>";
	userText.innerText = chatMsg;
	userDiv.appendChild(userImage);
	userDiv.appendChild(document.createElement("br"));
	userDiv.appendChild(userText);
	mainDiv.appendChild(userDiv);
	
	let botDiv = document.createElement("div");
    let botText = document.createElement("span");
    let botImage = document.createElement("img");
    botImage.src = "./images/img_bot_avatar2.jpg";
    botImage.alt = "You";
    botImage.className = "avatar";
    botDiv.id = "bot";
	botDiv.className = "botDiv";
	// botDiv.innerHTML = "<span>"+response+"</span>";
	botText.innerText = "Typing...";
	botDiv.appendChild(botImage);
	botDiv.appendChild(document.createElement("br"));
	botDiv.appendChild(botText);
	mainDiv.appendChild(botDiv);
    
    document.getElementById("chatMsg").value = '';
    // var objDiv = document.getElementById("chatScreen");
    mainDiv.scrollTop = mainDiv.scrollHeight;
 	 
    // Fake delay to seem "real"
    setTimeout(() => {
      botText.innerText = reply;
      textToSpeech(reply)
    }, 2000
    )
    
	// says the message using the text to speech function written below
	//textToSpeech(this.responseText);
}

function chatAction() {

    var chatMsg = document.getElementById("chatMsg").value;
    console.log('chatMsg=> '+chatMsg);
    if(chatMsg != ''){
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
             if (this.readyState == 4 && this.status == 200) {
                 var response = this.responseText;
                 console.log('responseText=> '+response);
                 addChat(chatMsg, response);
             }
        };
        xhttp.open("GET", "http://localhost:8888/chat?message="+chatMsg, true);
        xhttp.setRequestHeader("Content-type", "application/json");
        xhttp.send(chatMsg);
    }
}

/* It will bind the send button with enter key in keyboard. */
document.onkeydown = function (e) {
    e = e || window.event;
    switch (e.which || e.keyCode) {
          case 13 : //Your Code Here (13 is ascii code for 'ENTER')
              //$("#sendButtonId").click();
        	  document.getElementById("sendButtonId").click();
          break;
    }
  }

/* It will bind the send button with enter key in keyboard.   
$(document).keypress(function(e){
    if (e.which == 13){
        $("#sendButtonId").click();
    }
});
 */
/* 
$(document).ready(function() {
    $.ajax({
        url: "http://rest-service.guides.spring.io/greeting"
    }).then(function(data) {
       $('.greeting-id').append(data.id);
       $('.greeting-content').append(data.content);
    });
});
 */

//text to Speech
//https://developers.google.com/web/updates/2014/01/Web-apps-that-talk-Introduction-to-the-Speech-Synthesis-API

function textToSpeech(say) {
	if ('speechSynthesis' in window && talking) {
		var utterance = new SpeechSynthesisUtterance(say);
		//msg.voice = voices[10]; // Note: some voices don't support altering params
		//msg.voiceURI = 'native';
		//utterance.volume = 1; // 0 to 1
		//utterance.rate = 0.1; // 0.1 to 10
		//utterance.pitch = 1; //0 to 2
		//utterance.text = 'Hello World';
		//utterance.lang = 'en-US';
		speechSynthesis.speak(utterance);
	}
}

var socket;
var ready;
var notifications=new array();

function connect(host){  
	try{  
		socket = new WebSocket(host);  
   
		socket.onopen = function(){  
			 console.log('Socket Status: '+socket.readyState);  
			 ready=true;
		}  
  
		socket.onmessage = function(msg){ 
			
			if(msg.data.indexOf("GetSnapshot") >= 0){
				var array=msg.data.split("###");
				console.log(array[2]);
				loadDeploymentModel(array[2]);
				alertMessage("success","Deployment Model loaded",5000); 
			}
			if(msg.data.indexOf("!update") >= 0){
				increaseNotificationNumber();
				addNotification();
			}
			
		}  
  
		socket.onclose = function(){  
			 alertMessage("success",'Connection closed: '+socket.readyState,10000);  
		}
		
		socket.onerror = function(error){
			alertMessage("error",'Error message: '+socket.readyState,20000);  
		}
  
	} catch(exception){  
		alertMessage("error",'Socket Status: '+exception,20000);  
	}  
}

function send(text){  
	try{ 
		if(!ready){
			setTimeout(function(){send(text)},1000);
		}else{
			socket.send(text);
			console.log("Request sent!");
		}
	} catch(exception){  
		alertMessage("error",'Unable to send message: '+exception,10000);  
	}  
}

function increaseNotificationNumber(){
	var span=$("#notificationCounter");
	var counter=parseInt(span.text());
	$("#notificationCounter").html(counter+"");
}

function addNotification(data){
	notifications.push(data);
	$("#notificationMenu").append("<li>"+data+"</li>");
}
$(function() {
	
     var socket = null;
	$("#btnplay").on("click",function () {
            
            socket = new WebSocket("ws://localhost:8080/Unogame/inhand/"  
				+ $("#room").val());
            
            socket.onopen = function() {
			$("#chats").text("connected");
		}
            
            socket.onmessage = function(evt) {
                         console.log("test");
			var data = JSON.parse(evt.data);
			
                        var $li = $("<li>");
			$li.text("[" + data.id + "] " + "["+data.name +"] " );
			$("#chats").prepend($li);
                        
                        
                        var temp="";   
                       //console.log(data.cardid);
                        $.each(data.cardid, function (i, val){
                        temp = "<img src=\"images/"+val.cardid+".png\">";
                        //alert(temp);
                        $("#img").append(temp);
                        
                        });
                        
                         
                        var bal="";   
                       
                        $.each(data.balcards, function (i, val){
                        bal = "<img src=\"images/"+val.balcards+".png\">";
                        //alert(temp);
                        $("#bal").append(bal);
                        
                        });
		}

	});
        
        $("#btnstart").on("click", function() {
		var msg = {
			id: $("#id").val(),
			name: $("#name").val(),
			room: $("#room").val()
                      
		}               
                 
		socket.send(JSON.stringify(msg));
	})
            
})


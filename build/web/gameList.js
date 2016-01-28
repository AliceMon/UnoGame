$(function() {
	var socket = null;
          
	$("#loadBtn").on("click", function() {
		 
            socket = new WebSocket("ws://localhost:8080/Unogame/inhand/"
				+ $("#pid").val());
            
                socket.onopen = function() {
                    $("#connect").text("connected");  
                };
                socket.onmessage = function(evt) {
                    
                    //condition 1 before join
                    console.log(">>>in on message: " + evt.data);
                    
			var data = JSON.parse(evt.data); 
                        console.log(">>>>" + data.cmd);
                        if(data.cmd === "getPlayer")
                        { 
                            $("#availGroup").prepend(data.roomName);
                        }
                        else if(data.cmd === "startGame")
                        {
                            $("#loadingPlayerDiv").hide();
                            $("#image").show();
                            
                            console.log(">>>>>>startGame<<<<<<<" +data);  
                            
                           $("#img").text("");
                            //console.log(data.cardid);
                            $.each(data.cardid, function (i, val) {

                                // temp = "<div id=\"card\"" + ">";
                                temp = "<img id =\"ab\" value=\"" + val.cardid + "\"  src=\"images/" + val.cardid + ".png\">";
                                //temp += "</div>";

                                $("#img").append(temp);


                            });
                        }
                    
                    //condition 2 after join
                       
                        
                        //balance card
                        
                        //discarded card
		};  
	});
        $("#createBtn").on("click", function(){
            var p = {
                        playerID: $("#pid").val(),
			playerName: $("#name").val(),//"name" mean-->"Player"
			cmd: "getPlayer"
                    };
                    console.log(p);  
		socket.send(JSON.stringify(p));
            
        });
        
        $("#drawBtn").on("click", function()
        {
            var p = {
                playerID: $("#pid").val(),
                cmd: "pileCard"
            };
            console.log(p);
            socket.send(JSON.stringify(p));
        });
         $("#reverseBtn").on("click", function()
        {
            var p = {
                playerID: $("#pid").val(),
                cmd: "discardedCard"
            };
            console.log(p);
            socket.send(JSON.stringify(p));
        });
        $("#joinBtn").on("click", function(){
            
            var joinGame = {
                group: $("#availGroup").text(),
                joinPlayerID: $("#pid").val(),
                joinPlayerName: $("#name").val(),
                cmd: "joinGetPlayer"
            };
            
            $("#gameListDiv").hide();
            $("#loadingPlayerDiv").show();
            
            console.log(">>>>>group<<<<" + joinGame.group);
            console.log(">>>>>joinPlayerID<<<<" + joinGame.joinPlayerID);
            console.log(">>>>>joinPlayerName<<<<" + joinGame.joinPlayerName);
            console.log(">>>>>cmd<<<<" + joinGame.cmd);
            socket.send(JSON.stringify(joinGame));
        });
        
        $("#connectBtn").on("click", function() {
                
                $("#loadDiv").hide();
                $("#gameListDiv").show();
        });
        
        ///////=========IN HAND CARDS CLICK======\\\\\\\\
        $(document).on('click', '#ab', function () {
            var $this = $(this);
            var inHandCards = {
                pid: $("#pid").val(),
                cardid: $this.attr("value"),
                cmd: "inHandCards"

            }
            socket.send(JSON.stringify(inHandCards));

        });
});


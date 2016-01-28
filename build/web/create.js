$(function() {
	var socket = null;
          
	$("#connectBtn").on("click", function() {
		 
            socket = new WebSocket("ws://localhost:8080/Unogame/inhand/"
				+ $("#group").val());
            
                socket.onopen = function() 
                {
                    $("#connect").text("connected");
                }
                socket.onmessage = function(evt) 
                {
                    console.log(">>>in on message: " + evt.data);
                    var uno = JSON.parse(evt.data); 
                    if(uno.cmd === "getRooms"){
                        $("#lblRoomName").text(uno.group);
                        $("#lblPlayerNumber").text(uno.name);
                        $("#lblCurrent").text(uno.playerList);// <-------
                    }
                    else if (uno.cmd === "joinGetPlayer")
                    {
                        $("#lblCurrent").text(uno.playerList);
                        
                    }                      
                    else if(uno.cmd === "startGame")
                    {
                        console.log(">>>>startGame<<<<<<");
                        
                        //////////////////////
//                        var bal = "";
//                        $.each(uno.balcards, function (i, val)
//                        {
//                            bal = "<img src = \"images/"+val.balcards+".png\">";
//                            $("#bal").append(bal);
//                        });
//                        
//                        var dis = "";
//                        $.each(uno.discard, function (i, val)
//                        {
//                            dis = "<img src=\"images/" + val.discard + ".png\">";
//                            $("#discard").append(dis);
//                        });
                        /////////////////////
                        
                        $("#bal").text("");
                        $.each(uno.balcards, function (i, val) {

//                            //  bal = "<div id=\"pcard\"" + ">";
                            // bal = "<img src=\"images/" + val.balcards + ".png\">";
                            bal = "<img hidden id =\"bc\" value=\"" + val.balcards + "\"  src=\"images/" + val.balcards + ".png\">";
                            // bal += "</div>";

                                                        $("#bal").append(bal);
                        });


                        var dis = "";
                        $("#discard").text("");
                        $.each(uno.discard, function (i, val) {

                            //temp = "<div id=\"card\"" + ">";
                            dis = "<img id =\"cd\" value=\"" + val.discard + "\"  src=\"images/" + val.discard + ".png\">";
                            //dis += "</div>";
                            //alert(temp);
                            $("#discard").append(dis);

                        });
                        /////////////////////
                    }
                    
		}
                
	});
        
        $("#create").on("click",function(){
            var uno = {
			name: $("#name").val(),//"name" mean-->"Player"
			group: $("#group").val(),
                        cmd: "getRooms"
                    };
                    console.log(uno);
		socket.send(JSON.stringify(uno));
        })

	$("#getRoomBtn").on("click", function() {
		    
                   $("#createDiv").hide(); 
                   $("#createdGroupListDiv").show();
	})
        
        /////////////////////////////////////////////////////
        //
        ///////=========PILE CARDS CLICK======\\\\\\\\
            $(document).on('click', '#bc', function () {
                var $this = $(this);
                var pileCard = {
                    id: $("#id").val(),
                    name: $("#name").val(),
                    room: $("#room").val(),
                    cardid: $this.attr("value"),
                    cmd: "pileCard"

                }
                socket.send(JSON.stringify(pileCard));

            });

        //////=========Discarded CARDS CLICK======\\\\\\\\
            $(document).on('click', '#cd', function () {
                var $this = $(this);
                var discardedCard = {
                    id: $("#id").val(),
                    name: $("#name").val(),
                    room: $("#room").val(),
                    cardid: $this.attr("value"),
                    cmd: "discardedCard"

                }
                socket.send(JSON.stringify(discardedCard));
            });
        /////////////////////////////////////////////////
        
        
        $("#startBtn").on("click", function(){
            
            $("#createdGroupListDiv").hide();
            $("#deskDiv").show();
            var startGame = {
			startRoomName: $("#lblRoomName").text(),//"name" mean-->"Player"
			playerNumber: $("#lblPlayerNumber").text(),
                        cmd: "startGame"
                    };
                    console.log(">>>>>>"+startGame);
                $("#count").html(function (i,val){return +val+1})    
		socket.send(JSON.stringify(startGame));     
        })  
})


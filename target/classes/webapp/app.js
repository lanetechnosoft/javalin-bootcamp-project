/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function getItems(){
     $.get("/todo",function (response) {
               var items = $("#todoItems");
               var innerhtml = "";
               response.map((item)=>{
                   innerhtml = innerhtml +"<li>"+item.title+"</li>";
               });
               items.html(innerhtml);
            });
}

function createNewItem(title,description){
    $.ajax({
        type:"POST",
        url:"/todo",
        contentType :"application/json",
        data: JSON.stringify({"title":title,"description":description}),
        dataType:"json",
        complete: function(result){
             console.log(result);
             getItems();
        }
    });
}

$(document).ready(function () {
    getItems();
    $("#submitTodo").on('click',function(e){
        var title =  $("#todoTitle").val();
        var description = $("#todoDescription").val();
        createNewItem(title,description);
        console.log(title,description);
    });
});
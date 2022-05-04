let search1 = document.querySelector('#search1');
let search2 = document.querySelector('#searchLoc');
let searchBy = document.querySelector('#select1').value;
let searchInput =document.querySelector('#name');
let shelfRackSearch= document.getElementsByName('shelfRackSearch');
let shelfSearch = document.getElementsByName('shelfSearch');
let numberSearch = document.getElementsByName('orderNumberSearch');
let content = document.querySelector('.content').innerHTML;
let searchLoc= document.getElementsByName('searchLoc');
let deleteBtn = document.getElementsByName('delete');
let addName = document.getElementsByName('nameAdd');
let addAuthor = document.getElementsByName('authorAdd');
let addYear = document.getElementsByName('yearAdd');
let addShelfRack= document.getElementsByName('shelfRackAdd');
let addShelf = document.getElementsByName('shelfAdd');
let addNumber = document.getElementsByName('orderNumberAdd');
let addBtn=document.getElementsByName('addBtn');
search2.onclick = () => {
    console.log("yes");
}
search1.onclick = () => {
    if(searchInput.value!==null){

        let url;
        if(searchBy ==='byName'){
             url = 'http://localhost:3000/name?name=';
        }
        if(searchBy ==='byAuthor'){
             url = 'http://localhost:3000/author?author=';
        }
        if(searchBy ==='byYear'){
             url = 'http://localhost:3000/year?year=';
        }



        fetch(url+searchInput.value)
            .then((resp) => resp.json())
            .then(function(data) {
                let o = JSON.parse(data)
                o.forEach(function (entry){
                    content = content+'\n'+ 'Название : '+ entry.name +
                        '\nАвтор : '+ entry.author+
                        '\nГод выпуска : '+ entry.yearOfIssue+
                        '\nСтеллаж : '+ entry.location.shelfRack+' Полка : '+ entry.location.shelf + ' Номер : '+entry.location.orderNumber;
                })


            })
            .catch(function(error) {
                console.log(error);
            });
    }
}
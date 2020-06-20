
const container = document.querySelector('.container');
const searchArtistsBtn = document.querySelector('.searchArtistsButton');
const artistInput = document.querySelector('.artist');
const artistResultContainer = document.querySelector(".artistResultContainer");
const artistList = document.querySelector('.artistList');

function searchArtist(artistName) {
    fetch('http://localhost:1234/searchArtists?q=' + artistName)
        .then(function (response) {
            return response.json();
        })
        .then(function (artists) {
            let count = 0;
            let div;
            artists.forEach(function (artist) {
                //only 3 elements in a line
                 if(count%3==0){
                     //div for every list in a line
                     div = document.createElement('div');
                     div.classList.add("list-group", "list-group-horizontal", "artistList");
                     artistResultContainer.appendChild(div);
                 }

                 //every cell in a list
                const a = document.createElement('a');
                a.classList.add("col-xs-12", "col-sm-4", "list-album-result", "box")
                a.classList.add("list-group-item", "list-group-item-action", "text-center");
                //a.id = artist.id;

                //image in a cell
                const artistImage = document.createElement('img');
                artistImage.classList.add("img-fluid", "img-thumbnail", "rounded", "image");
                artistImage.src = (artist.images)[0].impURL;

                a.appendChild(artistImage);

                //name of the artist
                const artistName = document.createElement('figcaption');
                artistName.classList.add("figCap");
                artistName.innerText = artist.name;
                a.appendChild(artistName);

                //div for hover element
                const createPlaylist = document.createElement('div');
                createPlaylist.classList.add("box-content");

                //create playlist button
                const createPlaylistButton = document.createElement('a');
                createPlaylistButton.classList.add("title", "createPlaylist");
                createPlaylistButton.innerHTML = "create playlist";
                //assign artist id to the button
                createPlaylistButton.id = artist.id;

                createPlaylist.appendChild(createPlaylistButton);

                a.appendChild(createPlaylist);

                div.appendChild(a);
                artistResultContainer.appendChild(div);
                count++;
            })

        })
}


function createPlaylist(artistId){
    fetch('http://localhost:1234/createPlaylist?artistId=' + artistId)
        .then(function (response) {
            return response.json();
        })
        .then(function (result) {

        })
}

searchArtistsBtn.addEventListener('click', function () {
    artistResultContainer.innerHTML = "";
    searchArtist(artistInput.value);
})

document.addEventListener('click', function(e){
    if(e.target && e.target.classList && e.target.classList.contains('createPlaylist')){
        createPlaylist(e.target.id);
    }

})
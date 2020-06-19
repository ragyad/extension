
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
                //div = document.createElement('div');
                 if(count%3==0){
                     div = document.createElement('div');
                     div.classList.add("list-group", "list-group-horizontal");
                     artistResultContainer.appendChild(div);
                 }
                const a = document.createElement('a');
                a.classList.add("col-xs-12", "col-sm-4", "list-album-result", "box")
                a.classList.add("list-group-item", "list-group-item-action", "text-center");
                a.id = artist.id;


                const artistImage = document.createElement('img');
                artistImage.classList.add("img-fluid", "img-thumbnail", "rounded", "image");
                artistImage.src = (artist.images)[0].impURL;

                const createPlaylist = document.createElement('div');
                createPlaylist.classList.add("box-content");

                const createPlaylistButton = document.createElement('p');
                createPlaylistButton.classList.add("title");
                createPlaylistButton.innerHTML = "create playlist";

                createPlaylist.appendChild(createPlaylistButton);

                a.appendChild(artistImage);
                const artistName = document.createElement('figcaption');
                artistName.classList.add("figCap");
                artistName.innerText = artist.name;
                a.appendChild(artistName);

                a.appendChild(createPlaylist);

                div.appendChild(a);
                artistResultContainer.appendChild(div);
                count++;
            })

        })
}

searchArtistsBtn.addEventListener('click', function () {
    artistResultContainer.innerHTML = "";
    searchArtist(artistInput.value);
})
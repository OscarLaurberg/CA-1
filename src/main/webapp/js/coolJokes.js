const state = {
    jokes: []
};

document.addEventListener('DOMContentLoaded', (event) => {
    fetch("api/jokes/all")
            .then(res => res.json())
            .then(data => state.jokes = data)
});

function getAllJokes() {
    fetch("api/jokes/all")
            .then(res => res.json())
            .then(data => populateTable(data));
}
;
function getJokeFromId() {
    let amountOfJokes = state.jokes.length;
    console.log(amountOfJokes);
    const jokeId = document.getElementById('jokeId').value;
    if (jokeId > amountOfJokes || jokeId < 1) {
        document.getElementById('tableBody').innerHTML = "Joke Id doesn't exist - try again";

    }else{
        
    
    (fetch("api/jokes/" + jokeId))
            .then(res => res.json())
            .then(data => {
                let jokeFromId = `<tr><td>${data.id}</td><td>${data.punchLine}</td><td>${data.category}</td></tr>`;
                document.getElementById('tableBody').innerHTML = jokeFromId;

            })
}}
;

function getRandomJoke() {
    fetch("api/jokes/random")
            .then(res => res.json())
            .then(data => {
                let randomJoke = `<table><tr><td> ${data.id}</td><td>${data.punchLine}</td><td>${data.category}</td></tr>`;
                document.getElementById('tableBody').innerHTML = randomJoke;
            });
}

const populateTable = data => {
    const dataArray = data.map(data => `<tr><td>${data.id}</td><td>${data.punchLine}</td><td>${data.category}</td></tr>`);
    document.getElementById('tableBody').innerHTML = dataArray.join('');
}

document.getElementById('getJoke').addEventListener('click', getJokeFromId);
document.getElementById('getAllJokes').addEventListener('click', getAllJokes);
document.getElementById('getRandomJoke').addEventListener('click', getRandomJoke);
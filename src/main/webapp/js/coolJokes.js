 function getAllJokes() {
            fetch("api/jokes/all")
                    .then(res => res.json())
                    .then(data => {
                        console.log("data", data);
                        let tableString = "<table><tr><th>ID</th><th>The Joke</th><th>Category</th></tr>"
                        let jokeTableArray = data.map(data => data = `<tr><td>${data.id}</td><td>${data.punchLine}</td><td>${data.category}</td></tr>`);
                        jokeTableArray.forEach(data => {
                            tableString += data;
                        });
                        tableString += "</table>";
                        document.getElementById("tableDiv").innerHTML = tableString;
                    });
        }

        function getJokeFromId() {
            if (document.getElementById('jokeId').value === 0) {
                document.getElementById('jokeFromId').innerHTML = "Please enter a valid Id.";
            }else{
            const jokeId = document.getElementById('jokeId').value;
            (fetch("api/jokes/" + jokeId)
                    .then(res => res.json())
                    .then(data => {
                        console.log("data", data);
                        let jokeString = `${data.punchLine}`
                        document.getElementById('jokeFromId').innerHTML = jokeString;
                    }));

        }}
        
        function getRandomJoke(){
           fetch("api/jokes/random")
                   .then(res => res.json())
                   .then(data => {
                       let randomJoke = `<table><tr><td>ID: ${data.id}</td><td> The joke: ${data.punchLine}</td><td>Category: ${data.category}</td></tr></table>`; 
               document.getElementById('jokeFromId').innerHTML = randomJoke;
           });
        }

        document.getElementById('getJoke').addEventListener('click', getJokeFromId);
        document.getElementById('getAllJokes').addEventListener('click', getAllJokes);
        document.getElementById('getRandomJoke').addEventListener('click', getRandomJoke);
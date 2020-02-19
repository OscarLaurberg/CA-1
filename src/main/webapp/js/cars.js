const state = {
    cars: [],
    filteredCars: []
};

document.addEventListener('DOMContentLoaded', (event) => {
    fetch("/CA-1/api/cars")
            .then(res => res.json())
            .then(data => state.cars = data)
            .then(data => state.filteredCars = data)
            .then(data => populateTable(data));


});

const populateTable = data => {
    const dataArray = data.map(data => `<tr><td>${data.id}</td><td>${data.make}</td><td>${data.model}</td><td>${data.year}</td><td>${data.price}</td></tr>`);
    document.getElementById('tableBody').innerHTML = dataArray.join('');
};


const handleFilterTable = e => {
    e.preventDefault();
    let filteredCars = state.cars;
    const make = document.getElementById('inputMake').value.toLowerCase();
    const model = document.getElementById('inputModel').value.toLowerCase();
    const year = document.getElementById('inputYear').value;
    const price = document.getElementById('inputPrice').value;

    make ? filteredCars = filteredCars.filter(car => car.make.toLowerCase() === make) : null;
    model ? filteredCars = filteredCars.filter(car => car.model.toLowerCase() === model) : null;
    year ? filteredCars = filteredCars.filter(car => car.year >= Number(year)) : null;
    price ? filteredCars = filteredCars.filter(car => car.price <= Number(price)) : null;

    state.filteredCars = filteredCars;
    populateTable(filteredCars);
};

const sort = e => {
    let cars = state.filteredCars;
    switch (e.target.text) {
        case 'Make':
            cars.sort((a, b) => (a.make.toLowerCase() > b.make.toLowerCase()) ? 1 : -1);
            break;
        case 'Model':
            cars.sort((a, b) => (a.model.toLowerCase() > b.model.toLowerCase()) ? 1 : -1);
            break;
        case 'Year':
            cars.sort((a, b) => (a.year > b.year) ? 1 : -1);
            break;
        case 'Price':
            cars.sort((a, b) => (a.price > b.price) ? 1 : -1);
            break;
        default:
            cars.sort((a, b) => (a.id > b.id) ? 1 : -1);
            break;

    }
    populateTable(cars);
};